

public class Compra {

	private Usuario usuario;
	private Producto producto;
	public Compra(Usuario usuario, Producto producto) {
		super();
		this.usuario = usuario;
		this.producto = producto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	@Override
	public String toString() {
		return "Compra [usuario=" + usuario + ", producto=" + producto + "]";
	}
	
	
	
}