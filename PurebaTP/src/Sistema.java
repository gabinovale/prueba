
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Sistema {

	public static void cargar() {

		LinkedList<Usuario> usuarios = LecturaUsuarios.obtenerUsuariosDB();
		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.obtenerAtraccionesDB();
		LinkedList<Promocion> listaPromociones = LecturaPromociones.obtenerPromocionesDB();
		LinkedList<Itinerario> listaItinerarios = new LinkedList<Itinerario>();
		LinkedList<Itinerario> listaItinerariosDB = new LinkedList<Itinerario>();
		listaItinerariosDB.addAll(LecturaItinerarios.obtenerItinerariosDB());
				
		for (Usuario u : usuarios) {

			Itinerario itinerario = new Itinerario(u, new LinkedList<Producto>());
			LinkedList<Promocion> promosPreferentes = new LinkedList<Promocion>();
			LinkedList<Promocion> otrasPromos = new LinkedList<Promocion>();
			
			
			Itinerario iUsuario = new Itinerario(u, new LinkedList<Producto>());
			for (Itinerario i : listaItinerariosDB) {
				if (i.getUsuario().getNombre().equals(u.getNombre())) {
					iUsuario = i;
				}
			}
			LinkedList<Producto> productosComprados = iUsuario.getProductos();

			
			for(Promocion promo : listaPromociones) {
				int flag = 0;
				for (Atraccion a : promo.getAtracciones()) {
					
					for (Producto prod : productosComprados) {
						
						if (prod.getNombre().equals(a.getNombre())){
							
							flag=1;
						}
					}
					
				}
				if (flag == 0) {
					if (promo.getTipo().equals(u.getPreferencia())) {
						
						promosPreferentes.add(promo);
					}
						
					else {
						
						otrasPromos.add(promo);
					}
				}
			}


			LinkedList<Atraccion> atraccionesPreferentes = new LinkedList<Atraccion>();
			LinkedList<Atraccion> otrasAtracciones = new LinkedList<Atraccion>();

			for (Atraccion a : listaAtracciones) {
				int flag = 0;
				for (Producto prod : productosComprados) {
					
					if (prod.getNombre().equals(a.getNombre())){

							flag=1;
					}
				}
				if (flag == 0) {
					if (a.getTipo().equals(u.getPreferencia())) 
						atraccionesPreferentes.add(a);
					else
						otrasAtracciones.add(a);
				}
					
				
			}

			System.out.println("¡Bienvenido " + u.getNombre() + "!");
			System.out.println("--------------------------------------------");
			System.out.println("Productos que coinciden con su preferencia:");
			sugerir(promosPreferentes, atraccionesPreferentes, u, itinerario );
			System.out.println("--------------------------------------------");
			System.out.println("Productos que no coinciden con su preferencia:");
			sugerir(otrasPromos, otrasAtracciones, u, itinerario );

			System.err.println("No podemos ofrecerle más productos");

			System.out.println(u);
			System.out.println(itinerario);
			listaItinerarios.add(itinerario);

			// Actualiza la tabla usuario de la base de datos
			Connection connection = null;
			try {
				// Crea una conexión con la base de datos
				connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30); // Límite de tiempo de query en segundos

				statement.executeUpdate("UPDATE usuario  SET tiempo = "+u.getTiempo() +", presupuesto = "+ u.getPresupuesto()+" WHERE nombre = '"+u.getNombre()+"'");

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

			System.out.println("Presione enter para continuar");
			Scanner teclado = new Scanner(System.in);
			String opcion = teclado.nextLine();
		}
		
		//Se actualizan los cupos de las tablas de promociones y atracciones
		Connection connection = null;
		try {
			// Crea una conexión con la base de datos
			connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // Límite de tiempo de query en segundos


			for(Producto p : listaPromociones) {
				statement.executeUpdate("UPDATE promocion  SET cupo = "+p.getCupo() +" WHERE nombre = '"+p.getNombre()+"'");
			}
			for(Producto p : listaAtracciones) {
				statement.executeUpdate("UPDATE atraccion  SET cupo = "+p.getCupo() +" WHERE nombre = '"+p.getNombre()+"'");
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

		guardarItinerario(listaItinerarios);

	}

	public static void sugerir(LinkedList<Promocion> listaP, LinkedList<Atraccion> listaA, Usuario usuario,	Itinerario itinerario) {
		
			
		Collections.sort(listaP, new TiempoComparator());
		Collections.sort(listaP, new CostoComparator());

		
		Collections.sort(listaA, new TiempoComparator());
		Collections.sort(listaA, new CostoComparator());

		
		//Se sugieren primero las promociones
		for (Promocion p : listaP) {
			if (usuario.getPresupuesto() >= p.getCosto() && usuario.getTiempo() >= p.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");
				System.out.println(p);
				String compra;
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				try {

					compra = teclado.nextLine();

					if (compra.equals("si")) {

						for (Atraccion a : p.getAtracciones()) {
							int sacar = 0;
							for (Atraccion a1 : listaA) {

								if (a.getNombre().equals(a1.getNombre())) {
									a1.setCupo(a1.getCupo() - 1);
									sacar = listaA.indexOf(a1);
								}
							}

							listaA.remove(sacar);
							a.setCupo(a.getCupo() - 1);

						}
						p.setCupo(p.getCupo());

						itinerario.getProductos().add(p);

						usuario.setPresupuesto(usuario.getPresupuesto() - p.getCosto());
						usuario.setTiempo(usuario.getTiempo() - p.getTiempo());
						System.out.println(
								"¡Felicidades " + usuario.getNombre() + "!, ud compró: " + p.getNombre() + "!");
						System.out.println("---------------------------------------");
					}

					// teclado.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		
		//Se sugieren las atracciones
		for (Atraccion a : listaA) {
			if (usuario.getPresupuesto() >= a.getCosto() && usuario.getTiempo() >= a.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");
				System.out.println(a);
				String opcion;
				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");
				opcion = teclado.nextLine();

				if (opcion.equals("si")) {

					itinerario.getProductos().add(a);
					a.setCupo(a.getCupo() - 1);

					usuario.setPresupuesto(usuario.getPresupuesto() - a.getCosto());
					usuario.setTiempo(usuario.getTiempo() - a.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() + "!, ud compró: " + a.getNombre() + "!");
					System.out.println("---------------------------------------");
				}
				// teclado.close();
			}

		}
	}

	public static void guardarItinerario(LinkedList<Itinerario> lista) {
		
		//		Se guarda en un archivo
		
		//		FileWriter archivo = null;
		//		PrintWriter pw = null;
		//		try
		//		{
		//			archivo = new FileWriter("Itinerarios.txt");
		//			pw = new PrintWriter(archivo);
		//
		//			for (Itinerario i : lista) {
		//				String linea = i.getUsuario().getNombre();
		//				linea+=";"+i.getCostoTotal();
		//				linea+=";"+i.getTiempoTotal();
		//				for (Producto p : i.getProductos())
		//					linea+=";"+p.getNombre();
		//
		//				pw.println(linea);
		//			}
		//
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		} finally {
		//			try {
		//
		//				if (null != archivo)
		//					archivo.close();
		//			} catch (Exception e2) {
		//				e2.printStackTrace();
		//			}
		//		}

		
		//Se guardan los itinerarios en la base de datos
		
		Connection connection = null;
		try {
			// Crea una conexión con la base de datos
			connection = DriverManager.getConnection("jdbc:sqlite:db/TierraMedia.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // Límite de tiempo de query en segundos

			for (Itinerario i : lista) {
				ResultSet rs = statement
						.executeQuery("SELECT id FROM usuario WHERE nombre = '" + i.getUsuario().getNombre() + "'");
				rs.next();
				int id = rs.getInt("id");
				statement.executeUpdate("INSERT INTO itinerario(usuario_id, costo_total, tiempo_total)\r\n" + "VALUES("
						+ id + "," + i.getCostoTotal() + "," + i.getTiempoTotal() + ")");
				ResultSet r1 = statement.executeQuery("SELECT id FROM itinerario WHERE itinerario.usuario_id = " + id
						+ " AND itinerario.costo_total = " + i.getCostoTotal());
				r1.next();
				int id_itinerario = r1.getInt("id");
				for (Producto p : i.getProductos()) {

					if (p instanceof Promocion) {
						for (Atraccion a : ((Promocion) p).getAtracciones()) {
							ResultSet r2 = statement.executeQuery(
									"SELECT id FROM atraccion WHERE atraccion.nombre = '" + a.getNombre() + "'");
							r2.next();
							int id_atraccion = r2.getInt("id");
							statement.executeUpdate(
									"INSERT INTO itinerario_atraccion(itinerario_id, atraccion_id) VALUES("
											+ id_itinerario + "," + id_atraccion + ")");
						}

					}

					else {
						ResultSet r2 = statement.executeQuery(
								"SELECT id FROM atraccion WHERE atraccion.nombre = '" + p.getNombre() + "'");
						r2.next();
						int id_atraccion = r2.getInt("id");
						statement.executeUpdate("INSERT INTO itinerario_atraccion(itinerario_id, atraccion_id) VALUES("
								+ id_itinerario + "," + id_atraccion + ")");

					}

				}
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
	}

}
