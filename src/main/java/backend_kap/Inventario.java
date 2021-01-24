package backend_kap;

public class Inventario {
	private int fichas;
	private int cargadores15Balas;
	private int jeringas;
	private int municion;
	private final int LIMITE_MUNICION;
	private final int LIMITE_CARTUCHOS;
	private final int LIMITE_JERINGAS;
	private Jugador jugador;

	public Inventario(Jugador jugador) {
		this.jugador = jugador;
		this.fichas = 0;
		this.cargadores15Balas = 0;
		this.jeringas = 0;
		this.municion = 30;
		this.LIMITE_MUNICION = 50;
		this.LIMITE_CARTUCHOS = 8;
		this.LIMITE_JERINGAS = 8;
	}

	public int getFichas() {
		return this.fichas;
	}

	public void setFichas(int fichas) throws IllegalArgumentException{
		if (fichas >= 0){
			this.fichas = fichas;
		}else{
			throw new IllegalArgumentException("No te quedan fichas disponibles. Inténtalo más tarde.");
		}
	}

	public int getCargadores15Balas() {
		return this.cargadores15Balas;
	}

	public void setCargadores15Balas(int cargadores15Balas) {
		this.cargadores15Balas = cargadores15Balas;
	}

	public int getJeringas() {
		return this.jeringas;
	}

	public void setJeringas(int jeringas) {
		this.jeringas = jeringas;
	}

	public int getMunicion() {
		return this.municion;
	}

	public void setMunicion(int municion) {
		this.municion = municion;
	}

	public int getLIMITE_MUNICION() {
		return LIMITE_MUNICION;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

	public void usarCartucho() throws IllegalArgumentException {
		if (this.cargadores15Balas > 0) {
			if ((this.LIMITE_MUNICION - this.municion) > 15) {
				this.municion += 15;
			} else {
				this.municion = this.LIMITE_MUNICION;
			}
			this.cargadores15Balas--;
		} else {
			throw new IllegalArgumentException("No te quedan cargadores");
		}
	}

	public void usarJeringa() throws IllegalArgumentException {
		if (this.jeringas > 0) {
			if ((this.jugador.getVIDA_MAXIMA() - this.jugador.getVidaActual()) > 50) {
				this.jugador.setVidaActual(this.jugador.getVidaActual() + 50);
			} else {
				this.jugador.setVidaActual(this.jugador.getVIDA_MAXIMA());
			}
			this.jeringas--;
		} else {
			throw new IllegalArgumentException("No te quedan jeringas");
		}
	}
}