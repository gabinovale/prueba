

public class Usuario {
	private String nombre;
	private int presupuesto;
	private Double tiempo;
	private String preferencia;

	public Usuario(String nombre, int presupuesto, Double tiempo, String preferencia) {
		super();
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
		this.preferencia = preferencia;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public String getPreferencia() {
		return preferencia;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

}
