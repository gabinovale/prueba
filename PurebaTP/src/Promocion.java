

import java.util.LinkedList;

public class Promocion  extends Producto{
	private String nombre;
	private LinkedList<String> atraccion;

	public Promocion(String nombre, LinkedList<String> atraccion) {
		super(nombre, 100, 100.0);
		this.nombre = nombre;
		this.atraccion = atraccion;
	}

	public String getNombre() {
		return nombre;
	}

	public LinkedList<String> getAtraccion() {
		return atraccion;
	}

}
