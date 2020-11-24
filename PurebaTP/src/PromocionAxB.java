import java.util.LinkedList;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionGratis;

	public PromocionAxB(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo, Atraccion atraccionGratis) {
		super(nombre, atracciones, costo, tiempo, cupo, tipo);
		this.setAtraccionGratis(atraccionGratis);
		this.getAtracciones().add(this.atraccionGratis);
		this.setTiempo(this.getTiempo());
	}
	
	public PromocionAxB(String nombre, LinkedList<Atraccion> atracciones, String tipo, Atraccion gratis) {
		super(nombre, atracciones, tipo);
		this.setAtraccionGratis(gratis);
		this.setCosto(this.calcularCosto());
	}
	
	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}
	
	@Override
	public int getCosto() {
		return this.calcularCosto();
	}
	
	private int calcularCosto() {
		return super.getCosto() - this.atraccionGratis.getCosto();
	}
	
	

}
