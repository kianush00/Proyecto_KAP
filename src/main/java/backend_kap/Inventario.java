package backend_kap;

public class Inventario {
	private int fichas = 0;
	private int cartuchos15Balas = 0;
	private int jeringas = 0;
	private int municion = 30;
	private final_int limiteMunicion = 50;
	private final_int limiteCartuchos = 8;
	private final_int limiteJeringas = 8;
	private Jugador jugador;

	public Inventario() {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	public void usarJeringa() {
		throw new UnsupportedOperationException();
	}
}