

import java.util.LinkedList;

public class Promocion  extends Producto{

	private LinkedList<Atraccion> atracciones;

	public Promocion(String nombre, LinkedList<Atraccion> atracciones, int costo, Double tiempo, int cupo, String tipo) {
		super(nombre, costo, tiempo, cupo, tipo);
		this.atracciones = atracciones;
	}

	public LinkedList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(LinkedList<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	@Override
	public int getCosto() {
		// TODO Auto-generated method stub
		return super.getCosto();
	}
	
	@Override
	public Double getTiempo() {
		// TODO Auto-generated method stub
		return super.getTiempo();
	}
	
	@Override
	public int getCupo() {
		// TODO Auto-generated method stub
		return super.getCupo();
	}
	
	

}
