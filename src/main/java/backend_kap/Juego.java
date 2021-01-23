package backend_kap;

public class Juego {
	private int nivelActual = 1;
	private final_int niveles = 29;
	private Enemigo enemigo;
	private Jugador jugador;
	private Tienda tiendaActual;

	public Juego(int nivelActual, final_int niveles, Jugador jugador) {
		throw new UnsupportedOperationException();
	}

	public int getNivelActual() {
		return this.nivelActual;
	}

	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}

	public final_int getNiveles() {
		return this.niveles;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

	public int desarrollarTurnoJugador() {
		throw new UnsupportedOperationException();
	}

	public int desarrollarTurnoEnemigo() {
		throw new UnsupportedOperationException();
	}

	public void desarrollarTerminoPelea() {
		throw new UnsupportedOperationException();
	}

	public Tienda getTiendaActual() {
		return this.tiendaActual;
	}

	public void generarNuevaTiendaAleatoria() {
		throw new UnsupportedOperationException();
	}

	private void generarNuevoCuartel() {
		throw new UnsupportedOperationException();
	}

	private void generarNuevoHospital() {
		throw new UnsupportedOperationException();
	}

	public Enemigo getEnemigo() {
		return this.enemigo;
	}

	public void generarNuevoEnemigo() {
		throw new UnsupportedOperationException();
	}

	private int calcularIntAleatorioEntre(int min, int max) {
		throw new UnsupportedOperationException();
	}

	private int calcularFichasGanadas() {
		throw new UnsupportedOperationException();
	}
}