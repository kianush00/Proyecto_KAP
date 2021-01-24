package backend_kap;

public class Juego {
	private int nivelActual;
	private final int NIVELES;
	private Enemigo enemigoActual;
	private Jugador jugador;
	private Tienda tiendaActual;

	public Juego(final int NIVELES) {
		this.NIVELES = NIVELES;
		this.nivelActual = 1;
		jugador = new Jugador(100,new Inventario(),
				new ArmaPrimaria(15,0,TipoArmaPrimaria.PISTOLA9MM,1),
				new ArmaSecundaria(7,0,TipoArmaSecundaria.PUÑOS));
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
			generarNuevoHospital();
		}else{
			generarNuevoCuartel();
		}
	}

	private void generarNuevoCuartel() {
		this.tiendaActual = new Hospital();
	}

	private void generarNuevoHospital() {
		this.tiendaActual = new Cuartel();
	}

	public Enemigo getEnemigoActual() {
		return this.enemigoActual;
	}

	public void generarNuevoEnemigo() {
		this.enemigoActual = new Enemigo(this);
	}

	private int calcularIntAleatorioEntre(int min, int max) {
		return (int) (Math.random() * ((max + 1) - min) + min);
	}

	public int calcularFichasGanadas() {
		return (this.enemigoActual.getVIDA_MAXIMA() / 3) + (this.enemigoActual.getPuntosDeDaño()/2);
	}
}