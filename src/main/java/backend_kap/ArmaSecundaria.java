package backend_kap;

public class ArmaSecundaria extends Arma {
	private TipoArmaSecundaria tipo;

	public ArmaSecundaria(int puntosDeDaño, int precio, TipoArmaSecundaria tipo) {
		super(puntosDeDaño,precio);
		this.tipo = tipo;
	}

	public TipoArmaSecundaria getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoArmaSecundaria tipo) {
		this.tipo = tipo;
	}

	public int atacarEnemigo(Enemigo enemigo) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDaño));
		return enemigo.getVidaActual();
	}
}