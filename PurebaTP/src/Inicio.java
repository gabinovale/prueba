
import java.util.Scanner;


public class Inicio {

	public static void main(String[] args) {

		Scanner opc = new Scanner(System.in);
		int opcion = -1;

		while (opcion != 0) {

			System.out.println(
					"     MINISTERIO DE TURISMO EN TIERRA MEDIA\n"
							+ "____________________MENU_____________________\n\n"
							+ "1- USUARIOS ACTUALES.\n"
							+ "2- ATRACCIONES QUE PUEDEN VISITAR.\n"
							+ "3- PROMOCIONES VIGENTES.\n"
							+ "4- OFRECER PRODUCTOS.\n"
							+ "5- ITINERARIOS REALIZADOS.\n"
							+ "0- SALIR.\n"
							+ "_____________________________________________\n"
							+ "Selecciona una opcion para continuar: ");
			try {
				opcion = opc.nextInt();

				switch (opcion) {
				case 1:
					System.out.println("LISTA DE USUARIOS ACTUALES:");
					LecturaUsuarios.imprimirUsuarios();
					break;

				case 2:
					System.out.println("LISTA DE ATRACCIONES:");
					LecturaAtracciones.imprimirAtracciones();
					break;

				case 3:
					System.out.println("LISTA DE PROMOCIONES:");
					LecturaPromociones.imprimirPromociones();
					break;      
				case 4:

					Sistema.cargar();
					break;  
				case 5:
					System.out.println("LISTA DE ITINERARIOS:");
					LecturaItinerarios.obtenerItinerariosDB();
					break;
				case 0:
					System.err.println("FIN");
					break;
				default:
					System.out.println(
							"NO TE HAGAS EL LOQUILLO\n"
									+ "SELECCIONA UNA OPCION CORRECTA."); 
					break;

				}

			} catch (Exception e) {
				System.err.println("El dato ingresado no es un entero");
				break;
			}
			finally {
				System.out.println("----------------------------------------------------------");

			}



		}	
	}
}