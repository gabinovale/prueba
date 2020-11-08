
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LecturaAtracciones {

	public static LinkedList<Atraccion> listaAtracciones() {
		LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
		try {
			FileReader input = new FileReader("Atracciones.txt");
			BufferedReader bufInput = new BufferedReader(input);
			String line;
			line = bufInput.readLine();
			while (line != null) {
				String[] datos;
				datos = line.split(";");
				String nombre = datos[0];
				int costo = Integer.parseInt(datos[1]);
				Double time = Double.parseDouble(datos[2]);
				int cupo = Integer.parseInt(datos[3]);
				String tipo = datos[4];
				Atraccion a = new Atraccion(nombre, costo, time,cupo, tipo);
				listaAtracciones.add(a);

				line = bufInput.readLine();

			}
			bufInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaAtracciones;
	}
	
	public static void imprimirAtracciones() {
		for (Atraccion a : listaAtracciones())
			System.out.println(a);
	}

}
