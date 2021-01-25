package backend_kap;

public class Personaje {
	protected int vidaActual;
	protected int VIDA_MAXIMA;

	/**
	 * Constructor de personaje, clase padre de Jugador y Enemigo.
	 */
	public Personaje() {}

	/**
	 * Obtiene la vida actual del personaje.
	 * @return Devuelve objeto tipo int con la vida del jugador.
	 */
	public int getVidaActual() {
		return this.vidaActual;
	}

	/**
	 * Asigna la vida del personaje, no permite que baje de 0.
	 * @param vidaActual Toma como parametro un objeto int con el valor a asignar.
	 */
	public void setVidaActual(int vidaActual) {
		this.vidaActual = Math.max(vidaActual, 0);
	}

	/**
	 * Retorna la vida maxima del Personaje.
	 * @return Devuelve un objeto int con la vida maxima del Personaje.
	 */
	public int getVIDA_MAXIMA() {
		return this.VIDA_MAXIMA;
	}
}