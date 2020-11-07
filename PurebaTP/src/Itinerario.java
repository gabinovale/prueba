import java.util.LinkedList;

public class Itinerario {
	
	private Usuario usuario;
	private LinkedList<Atraccion> atracciones;
	private int costoTotal;
	private Double tiempoTotal;
	
	public Itinerario(Usuario usuario, LinkedList<Atraccion> atracciones) {
		super();
		this.usuario = usuario;
		this.atracciones = atracciones;
		this.setCostoTotal(this.calcularCostoTotal());
		this.setTiempoTotal(this.calcularTiempoTotal());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LinkedList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(LinkedList<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Double getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(Double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	private int calcularCostoTotal() {
		int total=0;
		for (Atraccion a : this.atracciones)
			total+=a.getCosto();
		return total;
	}
	
	private Double calcularTiempoTotal() {
		Double total = 0.0;
		for (Atraccion a : this.atracciones)
			total+=a.getTiempo();
		return total;
	}

	@Override
	public String toString() {
		String listado="";
		for(Atraccion a : this.atracciones)
			listado+=a.toString()+"\n";
		return "Itinerario de "+this.usuario.getNombre()+"\n"+listado + " [costoTotal=" + costoTotal + ", tiempoTotal=" + tiempoTotal + "]";
	}
	
	

}
