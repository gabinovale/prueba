

public abstract class Producto {
	private String nombre;
	private int costo;
	private Double tiempo;

	public Producto(String nombre, int costo, Double tiempo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCosto() {
		return costo;
	}

	public Double getTiempo() {
		return tiempo;
	}

	@Override
	public String toString() {
		return "Producto nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo;
	}
	
	

}
