package backend_kap;

public class Personaje {
	protected int vidaActual;
	protected int vidaMaxima;

	public Personaje() {


	}

	public int getVidaActual() {
		return this.vidaActual;
	}

	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}

	public int getVidaMaxima() {
		return this.vidaMaxima;
	}

	public void morir() {
		System.exit(1);
	}
}