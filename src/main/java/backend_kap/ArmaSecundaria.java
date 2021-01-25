package backend_kap;

public class ArmaSecundaria extends Arma {
	private final TipoArmaSecundaria TIPO;

	/**
	 * Constructor
	 * @param puntosDeDaño
	 * @param precio
	 * @param TIPO
	 */
	public ArmaSecundaria(int puntosDeDaño, int precio, TipoArmaSecundaria TIPO) {
		super(puntosDeDaño,precio);
		this.TIPO = TIPO;
	}

	/**
	 *
	 * @return
	 */
	public TipoArmaSecundaria getTIPO() {
		return this.TIPO;
	}

	/**
	 *
	 * @param enemigo
	 * @return
	 */
	public int atacarEnemigo(Enemigo enemigo) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDaño));
		return enemigo.getVidaActual();
	}
}