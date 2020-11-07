import java.util.LinkedList;

public class PromocionAbsoluta extends Promocion {
	
	private int descuento;

	public PromocionAbsoluta(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo, int descuento ) {
		super(nombre, atracciones, costo, tiempo, cupo, tipo);
		
		this.setDescuento(descuento);
		this.setCosto(this.getCosto());
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	@Override
	public int getCosto() {
		return super.getCosto() - this.descuento;
	}
	

}
