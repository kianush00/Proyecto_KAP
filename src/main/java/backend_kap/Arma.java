package backend_kap;

public class Arma {
	protected int puntosDeDa�o;
	protected int precio;

	public Arma(int puntosDeDa�o) {
		throw new UnsupportedOperationException();
	}

	public int getPuntosDeDa�o() {
		return this.puntosDeDa�o;
	}

	public void setPuntosDeDa�o(int puntosDeDa�o) {
		this.puntosDeDa�o = puntosDeDa�o;
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