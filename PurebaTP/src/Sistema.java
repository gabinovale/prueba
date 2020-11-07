
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

		LinkedList<Atraccion> listaAtraccionesCompradas = new LinkedList<Atraccion>();

		for (Promocion p : listaP) {
			if(usuario.getPresupuesto()>p.getCosto() && usuario.getTiempo()>p.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(p);

				String opcion;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				opcion = teclado.nextLine();

				if (opcion.equals("si")) {

					for (Atraccion a : p.getAtracciones()) {
						listaAtraccionesCompradas.add(a);
						listaA.remove(a);
						a.setCupo(a.getCupo()-1);
					}
					usuario.setPresupuesto(usuario.getPresupuesto()-p.getCosto());
					usuario.setTiempo(usuario.getTiempo() - p.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() +"!, ud compró: " + p.getNombre() + "!");
					System.out.println("---------------------------------------");
				}

				//				teclado.close();

			}

		}

		for (Atraccion a : listaA) {
			if(usuario.getPresupuesto()>a.getCosto() && usuario.getTiempo()>a.getTiempo()) {
				System.out.println("¿Quiere comprar este producto?:");

				System.out.println(a);

				String opcion;

				Scanner teclado = new Scanner(System.in);
				System.out.print("Introduzca su opcion: ");

				opcion = teclado.nextLine();

				if (opcion.equals("si")) {
					listaAtraccionesCompradas.add(a);
					a.setCupo(a.getCupo()-1);

					usuario.setPresupuesto(usuario.getPresupuesto()-a.getCosto());
					usuario.setTiempo(usuario.getTiempo() - a.getTiempo());
					System.out.println("¡Felicidades " + usuario.getNombre() +"!, ud compró: " + a.getNombre() + "!");
					System.out.println("---------------------------------------");
				}
				//				teclado.close();
			}



		}
		Itinerario itinerario = new Itinerario(usuario,listaAtraccionesCompradas);
		System.out.println(usuario);
		System.out.println(itinerario);
		Scanner teclado = new Scanner(System.in);
		String opcion = teclado.nextLine();
	}

}


