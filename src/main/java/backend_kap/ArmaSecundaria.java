package backend_kap;

/**
 * La clase ArmaSecundaria hereda de Arma
 * La clase ArmaSecundaria está asociada a la clase Jugador, pero no conoce a tal clase.
 * @see Arma
 * @see TipoArmaSecundaria
 * @see Enemigo
 */
public class ArmaSecundaria extends Arma {
	private final TipoArmaSecundaria TIPO;

	/**
	 * Constructor de clase ArmaSecundaria, inicializa sus atributos con valores pasados por parámetros.
	 * @param puntosDeDano variable heredada de Arma.
	 * @param precio variable heredada de Arma.
	 * @param TIPO variable tipo TipoArmaSecundaria, define por enumeración los tipos de arma.
	 */
	public ArmaSecundaria(int puntosDeDano, int precio, TipoArmaSecundaria TIPO) {
		super(puntosDeDano,precio);
		this.TIPO = TIPO;
	}

	/**
	 *Devuelve el tipo de ArmaSecundaria, permite saber que tipo de arma se esta utilizando.
	 * @return TipoArmaSecundaria.
	 */
	public TipoArmaSecundaria getTIPO() {
		return this.TIPO;
	}

	/**
	 * Resta puntos de vida al enemigo, depende del ataque del arma asociada.
	 * @param enemigo Se pide como parametro el objeto Enemigo.
	 * @return Devuelve la vida actual del enemigo.
	 */
	public int atacarEnemigo(Enemigo enemigo) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDano));
		return enemigo.getVidaActual();
	}
}