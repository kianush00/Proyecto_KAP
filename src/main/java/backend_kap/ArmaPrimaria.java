package backend_kap;

public class ArmaPrimaria extends Arma {
	private int rondaMunicion;
	private TipoArmaPrimaria tipo;

	public ArmaPrimaria(int puntosDeDaño, TipoArmaPrimaria tipo, int rondaMunicion) {
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}
}