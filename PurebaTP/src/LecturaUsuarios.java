

import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturaUsuarios {

//	public static LinkedList<Usuario> listaUsuario() {
//		LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
//
//		try {
//			FileReader input = new FileReader("Usuarios.txt");
//			BufferedReader bufInput = new BufferedReader(input);
//			String line;
//			line = bufInput.readLine();
//			while (line != null) {
//				String[] datos;
//				datos = line.split(";");
//				String nombre = datos[0];
//				int presupuesto = Integer.parseInt(datos[1]);
//				Double tiempo = Double.parseDouble(datos[2]);
//				String avPreferencia = datos[3];
//				Usuario u=new Usuario(nombre, presupuesto, tiempo, avPreferencia);
//				listaUsuarios.add(u);
//
//				line = bufInput.readLine();
//
//			}
//			bufInput.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return listaUsuarios;
//	}
	
	public static LinkedList<Usuario> obtenerUsuariosDB() {
		LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
        Connection connection = null;
        try {
            // Crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // Límite de tiempo de query en segundos
 
          
            // Leo los datos
            ResultSet rs = statement.executeQuery("SELECT nombre, presupuesto, tiempo, descripcion FROM usuario \r\n"
            		+ "JOIN tipo_de_atraccion ON usuario.preferencia_id = tipo_de_atraccion.id");
            while (rs.next()) {
            	Usuario u = new Usuario(rs.getString("nombre"), rs.getInt("presupuesto"), rs.getDouble("tiempo"), rs.getString("descripcion"));
                listaUsuarios.add(u);
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
        return listaUsuarios;
    }
	
	public static void imprimirUsuarios() {
		for (Usuario u : obtenerUsuariosDB())
			System.out.println(u);
	}


}