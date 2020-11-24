import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;



public class LecturaItinerarios {

	public static void listaItinerarios() {

		try {
			FileReader input = new FileReader("Itinerarios.txt");
			BufferedReader bufInput = new BufferedReader(input);
			String line;
			line = bufInput.readLine();
			while (line != null) {
				String[] datos;
				datos = line.split(";");
				String itinerario = "Usuario: " + datos[0];
				itinerario += " - Costo Total: " + datos[1];
				itinerario += " - Tiempo Total: " + datos[2];
				itinerario += " - Compras: ";
				for (int i = 3; i < datos.length; i++) {
					itinerario += datos[i] + ", ";
				}

				System.out.println(itinerario);
				line = bufInput.readLine();

			}
			bufInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static LinkedList<Itinerario> obtenerItinerariosDB() {
		LinkedList<Itinerario> listaItinerarios = new LinkedList<Itinerario>();
		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.obtenerAtraccionesDB();
		Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();
		for (Atraccion a : listaAtracciones) {
			mapaAtracciones.put(a.getNombre(), a);
		}
		LinkedList<Usuario> listaUsuarios = LecturaUsuarios.obtenerUsuariosDB();
		Map<String, Usuario> mapaUsuarios = new HashMap<String, Usuario>();
		for (Usuario u :listaUsuarios) {
			mapaUsuarios.put(u.getNombre(), u);
		}
		Connection connection = null;
		try {
			// Crea una conexión con la base de datos
			connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // Límite de tiempo de query en segundos

			// Leo los datos
			ResultSet rs = statement.executeQuery(
					"SELECT usuario.nombre, itinerario.costo_total,itinerario.tiempo_total, group_concat(atraccion.nombre) AS atracciones FROM itinerario \r\n"
							+ "JOIN usuario ON usuario.id = itinerario.usuario_id\r\n"
							+ "JOIN itinerario_atraccion ON itinerario_atraccion.itinerario_id = itinerario.id\r\n"
							+ "JOIN atraccion ON atraccion.id = itinerario_atraccion.atraccion_id\r\n"
							+ "GROUP BY usuario.nombre");
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				int costoTotal = rs.getInt("costo_total");
				Double tiempoTotal = rs.getDouble("tiempo_total");
				String linea = rs.getString("atracciones");
				
				System.out.println("Usuario: "+nombre+" - Costo Total: "+costoTotal+" - Tiempo total: "+tiempoTotal+" - Atracciones: "+linea);
				
				String[] atracciones = linea.split(",");
				LinkedList<Producto> nuevaLista = new LinkedList<Producto>();
				for (int i = 0; i < atracciones.length; i++) {
					Atraccion a = mapaAtracciones.get(atracciones[i]);
					nuevaLista.add(a);
				}
				Usuario usuario = mapaUsuarios.get(nombre);
				Itinerario i = new Itinerario(usuario, nuevaLista);
						
				listaItinerarios.add(i);
			}
		} catch (SQLException e) {
			// Muestro errores si hay. Si es "out of memory", puede ser causado porque no se
			// encontró archivo de bd
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// Falló al cerrar la conexión
				System.err.println(e.getMessage());
			}
		}
		return listaItinerarios;
	}
	
	public static void imprimirItinerarios() {
		for (Itinerario i : obtenerItinerariosDB())
			System.out.println(i);
	}
}