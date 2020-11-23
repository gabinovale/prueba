

public abstract class Producto {
	private String nombre;
	private int costo;
	private Double tiempo;
	private int cupo;
	private String tipo;

	public Producto(String nombre, int costo, Double tiempo, int cupo, String tipo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
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
	
	

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return ": nombre=" + nombre + ", costo=" + costo + ", tiempo=" + tiempo + ", cupo=" + cupo + ", tipo=" + tipo + "]";
	}
	
	

}
