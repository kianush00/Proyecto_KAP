package backend_kap;

public class Enemigo extends Personaje {
	private int puntosDeDaņo;

	public Enemigo(int vidaActual, final_int vidaMaxima, int puntosDeDaņo) {
		throw new UnsupportedOperationException();
	}

	public int getPuntosDeDaņo() {
		return this.puntosDeDaņo;
	}

	public void setPuntosDeDaņo(int puntosDeDaņo) {
		this.puntosDeDaņo = puntosDeDaņo;
	}

	public void atacarJugador(Jugador jugador) {
		throw new UnsupportedOperationException();
	}
}