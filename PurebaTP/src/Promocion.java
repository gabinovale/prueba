

import java.util.LinkedList;

public class Promocion  extends Producto{

	private LinkedList<Atraccion> atracciones;

	public Promocion(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo) {
		super(nombre, costo, tiempo, cupo, tipo);
		this.atracciones = atracciones;
		this.setCupo(this.calcularCupo());
	}

	public LinkedList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(LinkedList<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	@Override
	public int getCupo() {
		return this.calcularCupo();
	}
	
	private int calcularCupo() {
		int cupoMaximo = this.getAtracciones().get(0).getCupo();
		for (Atraccion a : this.getAtracciones()) {
			if (a.getCupo() < cupoMaximo)
				cupoMaximo=a.getCupo();
			
		}
		return cupoMaximo;
	}
	
	@Override
	public String toString() {
		String atracciones="\nIncluye las atracciones:";
		for (Atraccion a : this.getAtracciones())
			atracciones+=" "+a.getNombre()+",";
		return super.toString() + atracciones;
	}

}
