

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LecturaPromociones {

//	public static LinkedList<Promocion> listaPromocion() {
//		LinkedList<Promocion> listaPromociones = new LinkedList<Promocion>();
//		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.obtenerAtraccionesDB();
//		Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();
//		for(Atraccion a: listaAtracciones) {
//			mapaAtracciones.put(a.getNombre(), a);
//		}
//		try {
//			FileReader input = new FileReader("Promociones.txt");
//			BufferedReader bufInput = new BufferedReader(input);
//			String line;
//			line = bufInput.readLine();
//			while (line != null) {
//				String[] datos;
//				datos = line.split(";");
//				String nombre;
//				int costo=0;
//				Double tiempo = 0.0;
//				int cupo = 0;
//				Atraccion a;
//				LinkedList<Atraccion> nuevaLista = new LinkedList<Atraccion>();
//				switch (datos[0] ) {
//				case "Absoluta" : 
//					nombre = datos[1];
//					int descuento = Integer.parseInt(datos[2]);
//
//
//					for (int i = 3; i < datos.length; i++) {
//						a = mapaAtracciones.get(datos[i]);
//						nuevaLista.add(a);
//						costo+=a.getCosto();
//						tiempo+=a.getTiempo();
//						cupo+=a.getCupo();
//					}
//					Promocion pa = new PromocionAbsoluta(nombre, nuevaLista, costo, tiempo, cupo, nuevaLista.get(0).getTipo(), descuento);
//					listaPromociones.add(pa);
//					break;
//					
//
//				case "Porcentual":
//					nombre = datos[1];
//					Double porcentaje = Double.parseDouble((datos[2]));
//
//					for (int i = 3; i < datos.length; i++) {
//						a = mapaAtracciones.get(datos[i]);
//						nuevaLista.add(a);
//						costo+=a.getCosto();
//						tiempo+=a.getTiempo();
//						cupo+=a.getCupo();
//					}
//					Promocion pp = new PromocionPorcentual(nombre, nuevaLista, costo, tiempo, cupo, nuevaLista.get(0).getTipo(), porcentaje);
//					listaPromociones.add(pp);
//					break;
//					
//
//
//				case "AxB":
//					nombre = datos[1];
//					Atraccion gratis = mapaAtracciones.get((datos[2]));
//
//					for (int i = 3; i < datos.length; i++) {
//	
//						a = mapaAtracciones.get(datos[i]);
//						nuevaLista.add(a);
//						costo+=a.getCosto();
//						tiempo+=a.getTiempo();
//						cupo+=a.getCupo();
//					}
//					Promocion pAxB = new PromocionAxB(nombre, nuevaLista, costo, tiempo, cupo, nuevaLista.get(0).getTipo(), gratis);
//					listaPromociones.add(pAxB);
//				
//					break;
//				default: 
//					System.err.println("El tipo de promocion es incorrecto");
//
//				}
//
//				line = bufInput.readLine();
//
//			}
//			bufInput.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return listaPromociones;
//	}
	
	public static LinkedList<Promocion> obtenerPromocionesDB() {
		LinkedList<Promocion> listaPromociones = new LinkedList<Promocion>();
		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.obtenerAtraccionesDB();
		Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();
		for(Atraccion a: listaAtracciones) {
			mapaAtracciones.put(a.getNombre(), a);
		}
        Connection connection = null;
        
        try {
            // Crea una conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30); // Límite de tiempo de query en segundos
 
            
            // Leo los datos
            ResultSet rs = statement.executeQuery("SELECT promocion.id, promocion.nombre, promocion.costo, promocion.tiempo, promocion.cupo,  tipo_de_atraccion.descripcion AS tipo_de_atraccion, group_concat(atraccion.nombre) AS atracciones, tipo_promo.descripcion AS tipo_de_promo FROM promocion JOIN tipo_de_atraccion ON promocion.tipo_de_atraccion_id = tipo_de_atraccion.id\r\n"
            		+ "JOIN tipo_promo ON promocion.tipo_pormo_id = tipo_promo.id\r\n"
            		+ "JOIN promocion_atraccion ON promocion_atraccion.promocion_id = promocion.id\r\n"
            		+ "JOIN atraccion on atraccion.id = promocion_atraccion.atraccion_id\r\n"
            		+ "GROUP BY promocion.nombre");
            while (rs.next()) {
            	
            	String nombre = rs.getString("nombre");
            	String linea = rs.getString("atracciones");
            	String[] atracciones = linea.split(",");
            	LinkedList<Atraccion> nuevaLista = new LinkedList<Atraccion>();
				for (int i = 0; i < atracciones.length; i++) {
					Atraccion a = mapaAtracciones.get(atracciones[i]);
					nuevaLista.add(a);
				}
				String tipoAtraccion = rs.getString("tipo_de_atraccion");
            	
            	String tipoPromo = rs.getString("tipo_de_promo");
            	int id = rs.getInt("id");
            	switch (tipoPromo) {
				case "absoluta":
					Statement statement1 = connection.createStatement();
					ResultSet r = statement1.executeQuery("SELECT descuento FROM promo_absoluta WHERE id =" + id);
					r.next();
					int descuento = r.getInt("descuento");
					Promocion pa = new PromocionAbsoluta(nombre, nuevaLista, tipoAtraccion, descuento);
					listaPromociones.add(pa);
					
					
					break;
				case "porcentual":
					Statement statement2 = connection.createStatement();
					ResultSet r1 = statement2.executeQuery("SELECT porcentaje FROM promo_porcentual WHERE id =" + id);
					r1.next();
					Double porcentaje = r1.getDouble("porcentaje");
					Promocion pp = new PromocionPorcentual(nombre, nuevaLista, tipoAtraccion, porcentaje);
					listaPromociones.add(pp);
					
					
					break;
					
				case "aXb":
					Statement statement3 = connection.createStatement();
					ResultSet r2 = statement3.executeQuery("SELECT atraccion.nombre FROM atraccion\r\n"
							+ "JOIN promo_AxB ON promo_AxB.atraccion_id = atraccion.id\r\n"
							+ "WHERE promo_AxB.id =" + id);
					r2.next();
					Atraccion gratis = mapaAtracciones.get(r2.getString("nombre"));
					Promocion pAxB = new PromocionAxB(nombre, nuevaLista, tipoAtraccion, gratis);
					listaPromociones.add(pAxB);
					
					break;

				default:
					System.err.println("El tipo de promocion es incorrecto");
					break;
				}
            	
            	
        
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
        
        
        return listaPromociones;
    }

	public static void imprimirPromociones() {
		for (Promocion p : obtenerPromocionesDB())
			System.out.println(p);
	}

}

