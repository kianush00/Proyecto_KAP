package backend_kap;

public class Arma {
	protected int puntosDeDaņo;
	protected int precio;

	public Arma(int puntosDeDaņo) {
		throw new UnsupportedOperationException();
	}

	public int getPuntosDeDaņo() {
		return this.puntosDeDaņo;
	}

	public void setPuntosDeDaņo(int puntosDeDaņo) {
		this.puntosDeDaņo = puntosDeDaņo;
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