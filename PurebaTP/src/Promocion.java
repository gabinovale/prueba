

import java.util.LinkedList;

public class Promocion  extends Producto{

	private LinkedList<Atraccion> atracciones;

	public Promocion(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo) {
		super(nombre, costo, tiempo, cupo, tipo);
		this.atracciones = atracciones;
		this.setCupo(this.calcularCupo());
	}
	
	public Promocion(String nombre, LinkedList<Atraccion> atracciones, String tipo) {
		super(nombre, 0, 0.0, 0, tipo);
		this.atracciones = atracciones;
		//this.setCosto(this.getCosto());
		this.setTiempo(this.getTiempo());
		this.setCupo(this.getCupo());
		
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
	public Double getTiempo() {
		return this.calcularTiempo();
	}
	
	private Double calcularTiempo() {
		Double tiempo = 0.0;
		for (Atraccion a : this.getAtracciones()) {
			tiempo+=a.getTiempo();
		}
		return tiempo;
	}
	
	@Override
	public int getCosto() {
		return this.calcularCosto();
	}
	
	private int calcularCosto() {
		int costo = 0;
		for (Atraccion a : this.getAtracciones()) {
			costo+=a.getCosto();
		}
		return costo;
	}
	
	@Override
	public String toString() {
		String atracciones="\nIncluye las atracciones:";
		for (Atraccion a : this.getAtracciones())
			atracciones+=" "+a.getNombre()+",";
		return "PROMOCION"+super.toString() + atracciones;
	}

}
