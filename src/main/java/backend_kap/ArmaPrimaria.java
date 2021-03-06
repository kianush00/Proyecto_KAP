package backend_kap;

/**
 * La clase ArmaPrimaria hereda de Arma
 * La clase ArmaPrimaria está asociada a la clase Jugador, pero no conoce a tal clase.
 * @see Arma
 * @see TipoArmaPrimaria
 * @see Enemigo
 * @see Inventario
 */
public class ArmaPrimaria extends Arma {
	private final TipoArmaPrimaria TIPO;
	private final int RONDA_MUNICION;

	/**
	 * Constructor de arma primaria, inicializa sus atributos con valores pasados por parámetros.
	 * @param puntosDeDano Variable heredada de Arma, puntos de ataque del arma primaria.
	 * @param precio Variable heredada de Arma, valor en fichas del arma primaria.
	 * @param TIPO Tipo de arma, se define por enumeración en TipoDeArmaPrimaria.
	 * @param RONDA_MUNICION cantidad de balas que gasta cada vez que ataca.
	 */
	public ArmaPrimaria(int puntosDeDano, int precio, TipoArmaPrimaria TIPO, int RONDA_MUNICION) {
		super(puntosDeDano,precio);
		this.TIPO = TIPO;
		this.RONDA_MUNICION = RONDA_MUNICION;
	}

	/**
	 * Devuelve que tipo de arma se esta utilizando.
	 * @return Variable tipo TipoArmaPrimaria, corresponde al nombre del arma.
	 */
	public TipoArmaPrimaria getTIPO() {
		return this.TIPO;
	}

	/**
	 * Devuelve la cantidad de balas que utiliza el arma por turno.
	 * @return cantidad de balas que utiliza el arma por turno.
	 */
	public int getRONDA_MUNICION() {
		return this.RONDA_MUNICION;
	}

	/**
	 * Resta puntos de vida al enemigo en base al dano del arma principal.
	 * @param enemigo Objeto enemigo al cual accede para restarle puntos de vida.
	 * @param inv Objeto Inventario necesario para restar balas.
	 * @return Devuelve la vida actual del enemigo.
	 */
	public int atacarEnemigo(Enemigo enemigo, Inventario inv) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDano));
		restarMunicion(inv);
		return enemigo.getVidaActual();
	}

	/**
	 * Resta balas del inventario al momento de atacar con el arma principal.
	 * @param inv Objeto inventario necesario para acceder a la cantidad de balas disponibles.
	 * @return Devuelve la muninición actual disponible.
	 */
	public int restarMunicion(Inventario inv) {
		inv.setMunicion(inv.getMunicion() - this.RONDA_MUNICION);
		return inv.getMunicion();
	}
}