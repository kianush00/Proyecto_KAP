package backend_kap;

public class Enemigo extends Personaje {
	private int puntosDeDaño;

	public Enemigo(Juego juego) {

	}

	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	public void setPuntosDeDaño(int puntosDeDaño) {
		this.puntosDeDaño = puntosDeDaño;
	}

	public void atacarJugador(Jugador jugador) {
		throw new UnsupportedOperationException();
	}
}