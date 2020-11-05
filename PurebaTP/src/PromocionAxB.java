import java.util.LinkedList;

public class PromocionAxB extends Promocion {
	
	private Atraccion atraccionGratis;

	public PromocionAxB(String nombre, LinkedList<String> atraccion, Atraccion atraccionGratis) {
		super(nombre, atraccion);
		this.setAtraccionGratis(atraccionGratis);
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}
	
	

}
