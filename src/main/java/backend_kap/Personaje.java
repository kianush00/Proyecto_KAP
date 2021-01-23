package backend_kap;

public class Personaje {
	protected int vidaActual;
	protected final_int vidaMaxima;

	public Personaje() {

	}

	public Personaje(Juego juego) {
		throw new UnsupportedOperationException();
	}

	public int getVidaActual() {
		return this.vidaActual;
	}

	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}

	public int getVidaMaxima() {
		throw new UnsupportedOperationException();
	}

	public void atacar() {
		throw new UnsupportedOperationException();
	}

	public void morir() {
		throw new UnsupportedOperationException();
	}
}