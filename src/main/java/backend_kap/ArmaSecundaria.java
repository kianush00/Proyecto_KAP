package backend_kap;

public class ArmaSecundaria extends Arma {
	private final TipoArmaSecundaria TIPO;

	public ArmaSecundaria(int puntosDeDaño, int precio, TipoArmaSecundaria TIPO) {
		super(puntosDeDaño,precio);
		this.TIPO = TIPO;
	}

	public TipoArmaSecundaria getTIPO() {
		return this.TIPO;
	}

	public void atacarEnemigo(Enemigo enemigo) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDaño));
	}
}