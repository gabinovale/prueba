
import java.util.Collections;
import java.util.LinkedList;

import java.util.Scanner;




public class Sistema {

	public static void main(String[] args) {

		LinkedList<Usuario> usuarios = LecturaUsuarios.listaUsuario();

		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.listaAtracciones();

		LinkedList<Promocion> listaPromociones = LecturaPromociones.listaPromocion();

//		for (Atraccion a : listaAtracciones)
//			System.out.println(a);
//	
//		Collections.sort(listaAtracciones,new TiempoComparator());
//		System.out.println("Lista ordenada por tiempo");
//		for (Atraccion a : listaAtracciones)
//			System.out.println(a);
//		
//		Collections.sort(listaAtracciones,new CostoComparator());
//		
//		System.out.println("Lista ordenada por costo");
//		for (Atraccion a : listaAtracciones)
//			System.out.println(a);
		
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
			
			sugerir(promosPreferentes, atraccionesPreferentes, u, itinerario);
			sugerir(otrasPromos, otrasAtracciones, u, itinerario);
			
			System.err.println("No podemos ofrecerle más productos");
			
			System.out.println(u);
			System.out.println(itinerario);

			
			System.out.println("Presione enter para continuar");
			Scanner teclado = new Scanner(System.in);
			String opcion = teclado.nextLine();
		}
			
			

	}

	public static void sugerir(LinkedList<Promocion> listaP, LinkedList<Atraccion> listaA ,Usuario usuario, Itinerario itinerario) {

		

		//LinkedList<Producto> listaProductosComprados = new LinkedList<Producto>();
		Collections.sort(listaP,new TiempoComparator());
		Collections.sort(listaP,new CostoComparator());
		for (Promocion p : listaP) {
			if(usuario.getPresupuesto()>=p.getCosto() && usuario.getTiempo()>=p.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(p);

				String opcion;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				opcion = teclado.nextLine();

				if (opcion.equals("si")) {
					
					int x=0;
					for (Atraccion a : p.getAtracciones()) {
						a.setCupo(a.getCupo()-1);
						listaA.remove(x);
						x++;
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
		Collections.sort(listaA,new TiempoComparator());
		Collections.sort(listaA,new CostoComparator());

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
//		Itinerario itinerario = new Itinerario(usuario,listaProductosComprados);
//		System.out.println(usuario);
//		System.out.println(itinerario);
//		System.err.println("No podemos ofrecerle más productos");
//		System.out.println("Presione enter para continuar");
//		Scanner teclado = new Scanner(System.in);
//		String opcion = teclado.nextLine();
	}

}


