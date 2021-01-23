package backend_kap;

public class ArmaPrimaria extends Arma {
	private TipoArmaPrimaria tipo;
	private int rondaMunicion;

	public ArmaPrimaria(int puntosDeDaño, int precio, TipoArmaPrimaria tipo, int rondaMunicion) {
		super(puntosDeDaño,precio);
		this.tipo = tipo;
		this.rondaMunicion = rondaMunicion;
	}

	public TipoArmaPrimaria getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoArmaPrimaria tipo) {
		this.tipo = tipo;
	}

	public int getRondaMunicion() {
		return this.rondaMunicion;
	}

	public void setRondaMunicion(int rondaMunicion) {
		this.rondaMunicion = rondaMunicion;
	}

	public void atacarEnemigo(Enemigo enemigo, Inventario inv) {
		enemigo.setVidaActual((enemigo.getVidaActual() - this.puntosDeDaño));
		restarMunicion(inv);
	}

	private void restarMunicion(Inventario inv) {
		inv.setMunicion(inv.getMunicion() - this.rondaMunicion);
	}
}