package backend_kap;

public class Enemigo extends Personaje {
	private int puntosDeDa�o;

	public Enemigo(int vidaActual, final_int vidaMaxima, int puntosDeDa�o) {
		throw new UnsupportedOperationException();
	}

	public int getPuntosDeDa�o() {
		return this.puntosDeDa�o;
	}

	public void setPuntosDeDa�o(int puntosDeDa�o) {
		this.puntosDeDa�o = puntosDeDa�o;
	}

	public void atacarJugador(Jugador jugador) {
		throw new UnsupportedOperationException();
	}
}