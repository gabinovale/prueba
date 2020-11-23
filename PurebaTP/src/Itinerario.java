import java.util.LinkedList;

public class Itinerario {
	
	private Usuario usuario;
	private LinkedList<Producto> productos;
	private int costoTotal;
	private Double tiempoTotal;
	
	public Itinerario(Usuario usuario, LinkedList<Producto> atracciones) {
		super();
		this.usuario = usuario;
		this.productos = atracciones;
		this.setCostoTotal(this.calcularCostoTotal());
		this.setTiempoTotal(this.calcularTiempoTotal());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LinkedList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}

	public int getCostoTotal() {
		return this.calcularCostoTotal();
	}

	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Double getTiempoTotal() {
		return this.calcularTiempoTotal();
	}

	public void setTiempoTotal(Double tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}
	
	private int calcularCostoTotal() {
		int total=0;
		for (Producto p : this.productos)
			total+=p.getCosto();
		return total;
	}
	
	private Double calcularTiempoTotal() {
		Double total = 0.0;
		for (Producto p : this.productos)
			total+=p.getTiempo();
		return total;
	}

	@Override
	public String toString() {
		String listado="";
		for(Producto p : this.productos) {
			
			if (p instanceof Promocion) {
				for (Atraccion a : ((Promocion) p).getAtracciones())
					listado+=a.toString()+"\n";
			}
				
			else
				listado+=p.toString()+"\n";
		}
			
		return "Itinerario de "+this.usuario.getNombre()+"\n"+listado + " [costoTotal=" + this.getCostoTotal() + ", tiempoTotal=" + this.getTiempoTotal() + "]";
	}
	
	

}
