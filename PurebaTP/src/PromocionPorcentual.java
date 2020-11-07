import java.util.LinkedList;

public class PromocionPorcentual extends Promocion {
	
	private Double porcentaje;

	public PromocionPorcentual(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo, Double porcentaje) {
		super(nombre, atracciones, costo, tiempo, cupo, tipo);
		this.setPorcentaje(porcentaje);
		this.setCosto(this.getCosto());
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
