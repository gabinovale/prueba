import java.util.LinkedList;

public class PromocionPorcentual extends Promocion {
	
	private Double porcentaje;

	public PromocionPorcentual(String nombre, LinkedList<String> atraccion, Double porcentaje) {
		super(nombre, atraccion);
		this.setPorcentaje(porcentaje);
	}

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	@Override
	public int getCosto() {
		return (int) (super.getCosto() * (100 - this.porcentaje)/100);
	}
}
