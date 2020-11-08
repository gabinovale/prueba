
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import java.util.Scanner;




public class Sistema {

	public static void cargar() {

		LinkedList<Usuario> usuarios = LecturaUsuarios.listaUsuario();

		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.listaAtracciones();

		LinkedList<Promocion> listaPromociones = LecturaPromociones.listaPromocion();
		LinkedList<Itinerario> listaItinerarios = new LinkedList<Itinerario>();

		for (Usuario u : usuarios) {
			Itinerario itinerario = new Itinerario(u,new LinkedList<Producto>());
			LinkedList<Promocion> promosPreferentes = new LinkedList<Promocion>();
			LinkedList<Promocion> otrasPromos = new LinkedList<Promocion>();
			for(Promocion p : listaPromociones) {
				if (p.getTipo().equals(u.getPreferencia()))
					promosPreferentes.add(p);
				else 
					otrasPromos.add(p);
			}
			LinkedList<Atraccion> atraccionesPreferentes = new LinkedList<Atraccion>();
			LinkedList<Atraccion> otrasAtracciones = new LinkedList<Atraccion>();

			for(Atraccion a : listaAtracciones) {
				if (a.getTipo().equals(u.getPreferencia()))
					atraccionesPreferentes.add(a);
				else 
					otrasAtracciones.add(a);
			}

			System.out.println("¡Bienvenido "+ u.getNombre() + "!");
			System.out.println("--------------------------------------------");
			System.out.println("Productos que coinciden");
			sugerir(promosPreferentes, atraccionesPreferentes, u, itinerario);
			System.out.println("--------------------------------------------");
			System.out.println("Productos que no coinciden");
			sugerir(otrasPromos, otrasAtracciones, u, itinerario);

			System.err.println("No podemos ofrecerle más productos");

			System.out.println(u);
			System.out.println(itinerario);
			listaItinerarios.add(itinerario);


			System.out.println("Presione enter para continuar");
			Scanner teclado = new Scanner(System.in);
			String opcion = teclado.nextLine();
		}

		guardarItinerario(listaItinerarios);


	}

	public static void sugerir(LinkedList<Promocion> listaP, LinkedList<Atraccion> listaA ,Usuario usuario, Itinerario itinerario) {


		Collections.sort(listaP,new TiempoComparator());
		Collections.sort(listaP,new CostoComparator());

//		for (Promocion p : listaP) {
//			System.out.println(p);
//		}

		Collections.sort(listaA,new TiempoComparator());
		Collections.sort(listaA,new CostoComparator());

//		for (Atraccion a : listaA)
//			System.out.println(a);

		for (Promocion p : listaP) {
			if(usuario.getPresupuesto()>=p.getCosto() && usuario.getTiempo()>=p.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(p);

				String compra;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				compra = teclado.nextLine();
	

				if (compra.equals("si")) {

					
					for (Atraccion a : p.getAtracciones()) {
						for (Atraccion a1 : listaA) {
							if (a.getNombre().equals(a1.getNombre())) {
								a1.setCupo(a1.getCupo()-1);
								listaA.remove(a1);
							}
						}
						a.setCupo(a.getCupo()-1);
						
					}
					p.setCupo(p.getCupo());



					itinerario.getProductos().add(p);

					usuario.setPresupuesto(usuario.getPresupuesto()-p.getCosto());
					usuario.setTiempo(usuario.getTiempo() - p.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() +"!, ud compró: " + p.getNombre() + "!");
					System.out.println("---------------------------------------");
				}

				//				teclado.close();

			}

		}


		for (Atraccion a : listaA) {
			if(usuario.getPresupuesto()>=a.getCosto() && usuario.getTiempo()>=a.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(a);

				String opcion;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				opcion = teclado.nextLine();

				if (opcion.equals("si")) {

					itinerario.getProductos().add(a);
					a.setCupo(a.getCupo()-1);


					usuario.setPresupuesto(usuario.getPresupuesto()-a.getCosto());
					usuario.setTiempo(usuario.getTiempo() - a.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() +"!, ud compró: " + a.getNombre() + "!");
					System.out.println("---------------------------------------");
				}
				//				teclado.close();
			}



		}


	}

	public static void guardarItinerario(LinkedList<Itinerario> lista) {

		FileWriter archivo = null;
		PrintWriter pw = null;
		try
		{
			archivo = new FileWriter("Itinerarios.txt");
			pw = new PrintWriter(archivo);

			for (Itinerario i : lista) {
				String linea = i.getUsuario().getNombre();
				linea+=";"+i.getCostoTotal();
				linea+=";"+i.getTiempoTotal();
				for (Producto p : i.getProductos())
					linea+=";"+p.getNombre();

				pw.println(linea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != archivo)
					archivo.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}


