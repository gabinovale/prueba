

public class Atraccion extends Producto {
	private int cupo;
	private String tipo;

	public Atraccion(String nombre, int costo, Double tiempo, int cupo, String tipo) {
		super(nombre, costo, tiempo);
		this.cupo = cupo;
		this.tipo = tipo;
	}

	public int getCupo() {
		return cupo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		
		return super.toString() + ", cupo=" + cupo + ", tipo=" + tipo;
	}
	
	

}
