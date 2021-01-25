package backend_kap;

public class ArmaPrimaria extends Arma {
	private final TipoArmaPrimaria TIPO;
	private final int RONDA_MUNICION;

	public ArmaPrimaria(int puntosDeDaño, int precio, TipoArmaPrimaria TIPO, int RONDA_MUNICION) {
		super(puntosDeDaño,precio);
		this.TIPO = TIPO;
		this.RONDA_MUNICION = RONDA_MUNICION;
	}

	public TipoArmaPrimaria getTIPO() {
		return this.TIPO;
	}

	public int getRONDA_MUNICION() {
		return this.RONDA_MUNICION;
	}

	public int atacarEnemigo(Enemigo enemigo, Inventario inv) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDaño));
		restarMunicion(inv);
		return enemigo.getVidaActual();
	}

	public int restarMunicion(Inventario inv) {
		inv.setMunicion(inv.getMunicion() - this.RONDA_MUNICION);
		return inv.getMunicion();
	}
}