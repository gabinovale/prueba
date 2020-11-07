

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaPromociones {

	public static LinkedList<Promocion> listaPromocion() {
		LinkedList<Promocion> listaPromociones = new LinkedList<Promocion>();
		LinkedList<Atraccion> listaAtracciones = LecturaAtracciones.listaAtracciones();
		Map<String, Atraccion> mapaAtracciones = new HashMap<String, Atraccion>();
		for(Atraccion a: listaAtracciones) {
			mapaAtracciones.put(a.getNombre(), a);
		}
		try {
			FileReader input = new FileReader("Promociones.txt");
			BufferedReader bufInput = new BufferedReader(input);
			String line;
			line = bufInput.readLine();
			while (line != null) {
				String[] datos;
				datos = line.split(";");
				String nombre;
				int costo=0;
				Double tiempo = 0.0;
				int cupo = 0;
				Atraccion a;
				LinkedList<Atraccion> nuevaLista = new LinkedList<Atraccion>();
				switch (datos[0] ) {
				case "Absoluta" : 
					nombre = datos[1];
					int descuento = Integer.parseInt(datos[2]);


					for (int i = 3; i < datos.length; i++) {
						a = mapaAtracciones.get(datos[i]);
						nuevaLista.add(a);
						costo+=a.getCosto();
						tiempo+=a.getTiempo();
						cupo+=a.getCupo();
					}
					Promocion pa = new PromocionAbsoluta(nombre, nuevaLista, costo, tiempo, cupo, nuevaLista.get(0).getTipo(), descuento);
					listaPromociones.add(pa);
					break;
					

				case "Porcentual":
					nombre = datos[1];
					Double porcentaje = Double.parseDouble((datos[2]));

					for (int i = 3; i < datos.length; i++) {
						a = mapaAtracciones.get(datos[i]);
						nuevaLista.add(a);
						costo+=a.getCosto();
						tiempo+=a.getTiempo();
						cupo+=a.getCupo();
					}
					Promocion pp = new PromocionPorcentual(nombre, nuevaLista, costo, tiempo, cupo, nuevaLista.get(0).getTipo(), porcentaje);
					listaPromociones.add(pp);
					break;
					


				case "AxB":
					nombre = datos[1];
					Atraccion gratis = mapaAtracciones.get((datos[2]));

					for (int i = 3; i < datos.length; i++) {
	
						a = mapaAtracciones.get(datos[i]);
						nuevaLista.add(a);
						costo+=a.getCosto();
						tiempo+=a.getTiempo();
						cupo+=a.getCupo();
					}
					Promocion pAxB = new PromocionAxB(nombre, nuevaLista, costo, tiempo, cupo, nuevaLista.get(0).getTipo(), gratis);
					listaPromociones.add(pAxB);
				
					break;
				default: 
					System.err.println("El tipo de promocion es incorrecto");

				}

				line = bufInput.readLine();

			}
			bufInput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPromociones;
	}

//	public static void main(String[] args) {
//
//		LinkedList<Promocion> listaPromociones = listaPromocion();
//		for(Promocion p : listaPromociones)
//			System.out.println(p);
//	}

}

