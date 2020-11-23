

public class Atraccion extends Producto {


	public Atraccion(String nombre, int costo, Double tiempo, int cupo, String tipo) {
		super(nombre, costo, tiempo, cupo, tipo);
	}


	@Override
	public String toString() {
		
		return "ATRACCION"+super.toString();
	}
	
	

}
