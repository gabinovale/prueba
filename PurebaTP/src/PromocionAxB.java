import java.util.LinkedList;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionGratis;

	public PromocionAxB(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo, Atraccion atraccionGratis) {
		super(nombre, atracciones, costo, tiempo, cupo, tipo);
		this.setAtraccionGratis(atraccionGratis);
		this.getAtracciones().add(this.atraccionGratis);
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}
	
	

}
