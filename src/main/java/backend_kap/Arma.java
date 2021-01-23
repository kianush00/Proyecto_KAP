package backend_kap;

public class Arma {
	protected int puntosDeDaño;
	protected int precio;

	public Arma(int puntosDeDaño) {
		this.puntosDeDaño=puntosDeDaño;
	}



	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	public void setPuntosDeDaño(int puntosDeDaño) {
		this.puntosDeDaño = puntosDeDaño;
	}

	public void atacarEnemigo(Enemigo enem) {
		enem.setVidaActual((enem.getVidaActual()-this.puntosDeDaño));
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
}