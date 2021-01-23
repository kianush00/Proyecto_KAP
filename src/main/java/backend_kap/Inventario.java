package backend_kap;

public class Inventario {
	private int fichas;
	private int cartuchos15Balas;
	private int jeringas;
	private int municion;
	private final int LIMITE_MUNICION;
	private final int LIMITE_CARTUCHOS;
	private final int LIMITE_JERINGAS;
	private Jugador jugador;

	public Inventario(Jugador jugador) {
		this.fichas = 0;
		this.cartuchos15Balas = 0;
		this.jeringas = 0;
		this.municion = 30;
		this.LIMITE_MUNICION = 50;
		this.LIMITE_CARTUCHOS = 8;
		this.LIMITE_JERINGAS = 8;
		this.jugador = jugador;
	}

	public int getFichas() {
		return this.fichas;
	}

	public void setFichas(int fichas) {
		this.fichas = fichas;
	}

	public int getCartuchos15Balas() {
		return this.cartuchos15Balas;
	}

	public void setCartuchos15Balas(int cartuchos15Balas) {
		this.cartuchos15Balas = cartuchos15Balas;
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

	public Jugador getJugador() {
		return this.jugador;
	}

	public void usarCartucho() {
		this.municion += 15;
		this.cartuchos15Balas--;
	}

	public void usarJeringa() {
		if(this.jugador.getVidaActual() < 50){
			this.jugador.setVidaActual(this.jugador.getVidaActual()+50);
		}else{
			this.jugador.setVidaActual(this.jugador.getVidaMaxima());
		}
		this.jeringas--;
	}
}