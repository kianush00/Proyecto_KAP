package backend_kap;

public class Arma {
	protected int puntosDeDaño;
	protected int precio;

	public Arma(int puntosDeDaño) {
		throw new UnsupportedOperationException();
	}

	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	public void setPuntosDeDaño(int puntosDeDaño) {
		this.puntosDeDaño = puntosDeDaño;
	}

	public void atacarEnemigo(Enemigo enem) {
		throw new UnsupportedOperationException();
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
}