
import java.util.LinkedList;

import java.util.Scanner;


public class Sistema {

	public static void main(String[] args) {

		LinkedList<Usuario> usuarios = LecturaUsuarios.listaUsuario();

		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.listaAtracciones();

		LinkedList<Promocion> listaPromociones = LecturaPromociones.listaPromocion();


		for (Usuario u : usuarios)
			sugerir(listaPromociones, listaAtracciones, u);

	}

	public static void sugerir(LinkedList<Promocion> listaP, LinkedList<Atraccion> listaA ,Usuario usuario) {

		System.out.println("¡Bienvenido "+ usuario.getNombre() + "!");

		LinkedList<Producto> listaProductosComprados = new LinkedList<Producto>();
		
		//primero sugiere solo las promociones
		//acá se debería ordenar las promociones
		for (Promocion p : listaP) {
			if(usuario.getPresupuesto()>=p.getCosto() && usuario.getTiempo()>=p.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(p);

				String opcion;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				opcion = teclado.nextLine();

				if (opcion.equals("si")) {
					
					
					for (Atraccion a : p.getAtracciones()) {
						
						a.setCupo(a.getCupo()-1);
						
						listaA.remove(a);
						
					}
					p.setCupo(p.getCupo());
					
					listaProductosComprados.add(p);
					usuario.setPresupuesto(usuario.getPresupuesto()-p.getCosto());
					usuario.setTiempo(usuario.getTiempo() - p.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() +"!, ud compró: " + p.getNombre() + "!");
					System.out.println("---------------------------------------");
				}

				//				teclado.close();

			}

		}
		
		//acá se debería ordenar las atracciones
		for (Atraccion a : listaA) {
			if(usuario.getPresupuesto()>=a.getCosto() && usuario.getTiempo()>=a.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(a);

				String opcion;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				opcion = teclado.nextLine();

				if (opcion.equals("si")) {
					listaProductosComprados.add(a);
					a.setCupo(a.getCupo()-1);

					usuario.setPresupuesto(usuario.getPresupuesto()-a.getCosto());
					usuario.setTiempo(usuario.getTiempo() - a.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() +"!, ud compró: " + a.getNombre() + "!");
					System.out.println("---------------------------------------");
				}
				//				teclado.close();
			}



		}
		Itinerario itinerario = new Itinerario(usuario,listaProductosComprados);
		System.out.println(usuario);
		System.out.println(itinerario);
		System.err.println("No podemos ofrecerle más productos");
		System.out.println("Presione enter para continuar");
		Scanner teclado = new Scanner(System.in);
		String opcion = teclado.nextLine();
	}

}


