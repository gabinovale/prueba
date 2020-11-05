import java.util.LinkedList;

import java.util.Scanner;

public class Sistema {

	public static void main(String[] args) {
		
		Usuario frodo = new Usuario("Frodo", 10, 8.0, "Aventura");
		
		Producto moria = new Atraccion("Moria", 10, 2.0, 6, "Aventura");
		Producto minas = new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje");
		Producto comarca = new Atraccion("La Comarca", 3, 6.5, 150, "Degustacion");
		
		Atraccion mordor = new Atraccion("Mordor", 25, 3.0 , 4, "Aventura");
		Atraccion abismo = new Atraccion("Abismo de Heim", 5, 2.0, 15, "Paisaje");
		Atraccion lothlorien = new Atraccion("Lothlórien", 35, 1.0, 30, "Degustacion");
			
		
		LinkedList<Producto> lista = new LinkedList<Producto>();
		lista.add(moria);
		lista.add(minas);
		lista.add(comarca);
		lista.add(mordor);
		lista.add(abismo);
		lista.add(lothlorien);
		
		LinkedList<String> listaString = new LinkedList<String>();
		listaString.add(moria.getNombre());
		listaString.add(minas.getNombre());
		listaString.add(comarca.getNombre());
		
		
		Producto packAventura = new Promocion("Pack Aventura", listaString);
		
		LinkedList<String> listaString2 = new LinkedList<String>();
		listaString.add(mordor.getNombre());
		listaString.add(lothlorien.getNombre());
		listaString.add(abismo.getNombre());
		
		Producto packNuevo = new Promocion("Pack Aventura", listaString2);
		
		lista.add(packAventura);
		lista.add(packNuevo);
		
		
		
		for (Producto p : lista) {
			System.out.println(p);
			System.out.println("¿Quiere comprar este producto?");
			String opcion;

	        Scanner teclado = new Scanner(System.in);
	        System.out.print("Introduzca su opcion: ");
	        opcion = teclado.nextLine();
	        if (opcion.equals("si")) {
	        	System.out.println("¡Felicidades, ud compró: " + p.getNombre() + "!");
	        	
	        }
	        
		}
		

	}

}
