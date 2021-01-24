package backend_kap;

public class Enemigo extends Personaje {
	private int puntosDeDaño;

	public Enemigo(Juego juego) {
		super.VIDA_MAXIMA=(int) ((Math.random() * 70) + (juego.getNivelActual()*0.75) + 30);
		super.vidaActual=super.VIDA_MAXIMA;
		this.puntosDeDaño=(int) ((Math.random() * 7) + (juego.getNivelActual()*0.75) + 3);
	}

	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	public void setPuntosDeDaño(int puntosDeDaño) {
		this.puntosDeDaño = puntosDeDaño;
	}

	public int atacarJugador(Jugador jugador) {
		jugador.setVidaActual(jugador.getVidaActual() - this.puntosDeDaño);
		return jugador.getVidaActual();
	}
}