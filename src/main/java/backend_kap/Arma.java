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

	public void setPuntosDeDaño(int puntosDeDaño) {
		this.puntosDeDaño = puntosDeDaño;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
}