import java.util.LinkedList;

public class PromocionAbsoluta extends Promocion {
	
	private int descuento;

	public PromocionAbsoluta(String nombre, LinkedList<String> atraccion, int descuento ) {
		super(nombre, atraccion);
		this.setDescuento(descuento);
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
