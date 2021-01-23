package backend_kap;

public class Personaje {
	protected int vidaActual;
	protected final_int vidaMaxima;

	public Personaje() {
		throw new UnsupportedOperationException();
	}

	public Personaje(int vidaActual, final_int vidaMaxima) {
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