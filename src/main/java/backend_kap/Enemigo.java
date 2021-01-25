package backend_kap;

public class Enemigo extends Personaje {
	private int puntosDeDano;

	/**
	 * Constructor de clase Enemigo.
	 * @param juego Pide objeto tipo Juego para generar la instancia en base al nivel actual.
	 */
	public Enemigo(Juego juego) {
		super.VIDA_MAXIMA=(int) ((Math.random() * 70) + (juego.getNivelActual()*0.75) + 30);
		super.vidaActual=super.VIDA_MAXIMA;
		this.puntosDeDano=(int) ((Math.random() * 7) + (juego.getNivelActual()*0.75) + 3);
	}

	/**
	 * Devuelve el ataque del enemigo.
	 * @return Objeto tipo int que corresponde a los puntos de dano del enemigo.
	 */
	public int getpuntosDeDano() {
		return this.puntosDeDano;
	}

	/**
	 * A través de este método el enemigo ataca al jugador, le resta puntos de vida.
	 * @param jugador Objeto tipo Jugador para acceder a su variable de puntos de vida.
	 * @return Devuelve la vida actual del jugador, despues de atacarlo.
	 */
	public int atacarJugador(Jugador jugador) {
		int probabilidadesAtacar = (int) (Math.random() * 3);

		if (probabilidadesAtacar > 0){	// un tercio de prob. de que el enemigo falle
			jugador.setVidaActual(jugador.getVidaActual() - this.puntosDeDano);
		}
		return jugador.getVidaActual();
	}
}