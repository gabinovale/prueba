import java.util.Comparator;

public class CostoComparator implements Comparator<Producto> {

	@Override
	public int compare(Producto p1, Producto p2) {
		return (-1)*Integer.compare(p1.getCosto(), p2.getCosto());
	}

}
