package backend_kap;

public class ArmaPrimaria extends Arma {
	private int rondaMunicion;
	private TipoArmaPrimaria tipo;

	public ArmaPrimaria(int puntosDeDaño, TipoArmaPrimaria tipo, int rondaMunicion) {
		super(puntosDeDaño);
		this.tipo=tipo;
		this.rondaMunicion=rondaMunicion;
	}

	public int getRondaMunicion() {
		return this.rondaMunicion;
	}

	public void setRondaMunicion(int rondaMunicion) {
		this.rondaMunicion = rondaMunicion;
	}

	public TipoArmaPrimaria getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoArmaPrimaria tipo) {
		this.tipo = tipo;
	}

	public void restarMunicion(Inventario inv) {
		inv.setMunicion(inv.getMunicion()-this.rondaMunicion);
	}

	public void atacarEnemigo(Enemigo enem, Inventario inv) {
		super.atacarEnemigo(enem);
		restarMunicion(inv);
	}
}