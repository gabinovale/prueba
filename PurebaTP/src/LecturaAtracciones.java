

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class LecturaAtracciones {

//	public static LinkedList<Atraccion> listaAtracciones() {
//		LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
//		try {
//			FileReader input = new FileReader("Atracciones.txt");
//			BufferedReader bufInput = new BufferedReader(input);
//			String line;
//			line = bufInput.readLine();
//			while (line != null) {
//				String[] datos;
//				datos = line.split(";");
//				String nombre = datos[0];
//				int costo = Integer.parseInt(datos[1]);
//				Double time = Double.parseDouble(datos[2]);
//				int cupo = Integer.parseInt(datos[3]);
//				String tipo = datos[4];
//				Atraccion a = new Atraccion(nombre, costo, time,cupo, tipo);
//				listaAtracciones.add(a);
//
//				line = bufInput.readLine();
//
//			}
//			bufInput.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return listaAtracciones;
//	}
	
	public static LinkedList<Atraccion> obtenerAtraccionesDB() {
		LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
        Connection connection = null;
        try {
            // Crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // Límite de tiempo de query en segundos
 
          
            // Leo los datos
            ResultSet rs = statement.executeQuery("SELECT nombre, costo, tiempo, cupo, descripcion FROM atraccion JOIN tipo_de_atraccion ON atraccion.tipo_de_atraccion_id = tipo_de_atraccion.id");
            while (rs.next()) {
            	Atraccion a = new Atraccion(rs.getString("nombre"), rs.getInt("costo"), rs.getDouble("tiempo"), rs.getInt("cupo"),rs.getString("descripcion"));
                listaAtracciones.add(a);
            }
        } catch (SQLException e) {
            // Muestro errores si hay. Si es "out of memory", puede ser causado porque no se encontró archivo de bd
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
        return listaAtracciones;
    }
	
	public static void imprimirAtracciones() {
		for (Atraccion a : obtenerAtraccionesDB())
			System.out.println(a);
	}

}
