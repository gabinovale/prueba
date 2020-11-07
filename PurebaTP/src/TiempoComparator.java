import java.util.Comparator;

public class TiempoComparator implements Comparator<Producto> {

	@Override
	public int compare(Producto p1, Producto p2) {
		return (-1)*Double.compare(p1.getTiempo(), p2.getTiempo());
	}


}
