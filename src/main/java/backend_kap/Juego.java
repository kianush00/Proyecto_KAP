package backend_kap;

public class Juego {
	private int nivelActual;
	private final int NIVELES;
	private Enemigo enemigo;
	private Jugador jugador;
	private Tienda tiendaActual;

	public Juego(int nivelActual, final int NIVELES, Jugador jugador) {
		this.nivelActual = 1;
		this.NIVELES = 29;
		this.jugador = jugador;
	}

	public int getNivelActual() {
		return this.nivelActual;
	}

	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}

	public final int getNIVELES() {
		return this.NIVELES;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

	public Tienda getTiendaActual() {
		return this.tiendaActual;
	}

	public void generarNuevaTiendaAleatoria() {
		if(calcularIntAleatorioEntre(1,2) == 1){
			this.tiendaActual = new Hospital();
		}
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
		return (int) (Math.random() * ((max + 1) - min)) + min;
	}

	private int calcularFichasGanadas() {
		throw new UnsupportedOperationException();
	}
}