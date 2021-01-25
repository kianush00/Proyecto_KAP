package backend_kap;

/**
 * La clase Juego contiene el desarrollo de la parte lógica del programa. Está compuesta por la clase
 * Jugador y está asociada unidireccionalmente a las clases Tienda y Enemigo, los cuales se generan
 * dependiendo del nivel actual en que se encuentre esta clase.
 * @see Enemigo
 * @see Jugador
 * @see Tienda
 * @see Hospital
 * @see Cuartel
 * @see Inventario
 * @see ArmaPrimaria
 * @see ArmaSecundaria
 */
public class Juego {
	private int nivelActual;
	private final int NIVELES;
	private Enemigo enemigoActual;
	private Jugador jugador;
	private Tienda tiendaActual;

	/**
	 * Constructor de la clase Juego, el cual requiere la cantidad total de niveles.
	 * Dentro del constructor se instancia el objeto Jugador con valores predeterminados por el constructor.
	 * @param NIVELES valor int que corresponde a la cantidad total de niveles que poseerá el juego
	 */
	public Juego(final int NIVELES) {
		this.NIVELES = NIVELES;
		this.nivelActual = 1;
		jugador = new Jugador(100,new Inventario(),
				new ArmaPrimaria(15,0,TipoArmaPrimaria.PISTOLA9MM,1),
				new ArmaSecundaria(7,0,TipoArmaSecundaria.PUNOS));
	}

	/**
	 * Método que obtiene el valor del nivel actual.
	 * @return entero que corresponde al nivel actual del juego.
	 */
	public int getNivelActual() {
		return this.nivelActual;
	}

	/**
	 * Método que establece el valor del nivel actual por medio del parámetro.
	 * @param nivelActual entero que corresponde al nivel actual que se quiere establecer.
	 */
	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}

	/**
	 * Método que obtiene el valor de la cantidad total de niveles del juego.
	 * @return NIVELES entero que corresponde a la cantidad total de niveles.
	 */
	public final int getNIVELES() {
		return this.NIVELES;
	}

	/**
	 * Método que obtiene el puntero del jugador asociado al juego.
	 * @return jugador puntero de tipo Jugador.
	 */
	public Jugador getJugador() {
		return this.jugador;
	}

	/**
	 * Método que obtiene el puntero de la tienda actual asociada al juego.
	 * @return tiendaActual puntero de tipo Tienda.
	 */
	public Tienda getTiendaActual() {
		return this.tiendaActual;
	}

	/**
	 * Método que genera una nueva instancia de tipo Tienda asociada al juego. El tipo de tienda es
	 * aleatorio, Cuartel y Hospital son equiprobables.
	 */
	public void generarNuevaTiendaAleatoria() {
		if(calcularIntAleatorioEntre(1,2) == 1){
			generarNuevoHospital();
		}else{
			generarNuevoCuartel();
		}
	}

	/**
	 * Método que genera una nueva instancia de tipo Cuartel que luego se asocia al juego.
	 */
	private void generarNuevoCuartel() {
		this.tiendaActual = new Hospital();
	}

	/**
	 * Método que genera una nueva instancia de tipo Hospital que luego se asocia al juego.
	 */
	private void generarNuevoHospital() {
		this.tiendaActual = new Cuartel();
	}

	/**
	 * Método que obtiene el puntero del enemigo actual asociado al juego.
	 * @return enemigoActual puntero de tipo Enemigo
	 */
	public Enemigo getEnemigoActual() {
		return this.enemigoActual;
	}

	/**
	 * Método que genera una nueva instancia de tipo Enemigo que luego se asocia al juego.
	 */
	public void generarNuevoEnemigo() {
		this.enemigoActual = new Enemigo(this);
	}

	private int calcularIntAleatorioEntre(int min, int max) {
		return (int) (Math.random() * ((max + 1) - min) + min);
	}

	/**
	 * Método que calcula las fichas que el jugador ganó en la pelea (desarrollada en la clase InterfazCLI)
	 * luego de matar al enemigo. Se calcula en base a los puntos de vida y ataque del enemigo asociado al juego.
	 * @return fichas que ganó el jugador.
	 */
	public int calcularFichasGanadas() {
		return (this.enemigoActual.getVIDA_MAXIMA() / 3) + (this.enemigoActual.getpuntosDeDano()/2);
	}
}