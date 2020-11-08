import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class LecturaItinerarios {

	public static void listaItinerarios() {
		
		try {
			FileReader input = new FileReader("Itinerarios.txt");
			BufferedReader bufInput = new BufferedReader(input);
			String line;
			line = bufInput.readLine();
			while (line != null) {
				String[] datos;
				datos = line.split(";");
				String itinerario = "Usuario: "+ datos[0];
				itinerario+= " - Costo Total: " + datos[1];
				itinerario+= " - Tiempo Total: " + datos[2];
				itinerario+=" - Compras: ";
				for (int i = 3; i < datos.length; i++) {
					itinerario+=datos[i]+", ";
				}

				System.out.println(itinerario);
				line = bufInput.readLine();

			}
			bufInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}