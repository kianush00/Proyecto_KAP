package backend_kap;

public class Arma {
	protected int puntosDeDaño;
	protected int precio;

	public Arma(int puntosDeDaño, int precio) {
		this.puntosDeDaño = puntosDeDaño;
		this.precio = precio;
	}

	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	public int getPrecio() {
		return this.precio;
	}
}