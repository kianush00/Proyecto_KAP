package backend_kap;

public class Personaje {
	protected int vidaActual;
	protected int VIDA_MAXIMA;

	public Personaje() {}

	public int getVidaActual() {
		return this.vidaActual;
	}

	public void setVidaActual(int vidaActual) {
		this.vidaActual = Math.max(vidaActual, 0);
	}

	public int getVIDA_MAXIMA() {
		return this.VIDA_MAXIMA;
	}
}