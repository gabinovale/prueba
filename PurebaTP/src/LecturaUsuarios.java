

import java.util.LinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LecturaUsuarios {

	public static LinkedList<Usuario> listaUsuario() {
		LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();

		try {
			FileReader input = new FileReader("Usuarios.txt");
			BufferedReader bufInput = new BufferedReader(input);
			String line;
			line = bufInput.readLine();
			while (line != null) {
				String[] datos;
				datos = line.split(";");
				String nombre = datos[0];
				int presupuesto = Integer.parseInt(datos[1]);
				Double tiempo = Double.parseDouble(datos[2]);
				String avPreferencia = datos[3];
				Usuario u=new Usuario(nombre, presupuesto, tiempo, avPreferencia);
				listaUsuarios.add(u);

				line = bufInput.readLine();

			}
			bufInput.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}


}