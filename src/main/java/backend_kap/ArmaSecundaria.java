package backend_kap;

public class ArmaSecundaria extends Arma {
	private TipoArmaSecundaria tipo;

	public ArmaSecundaria(int puntosDeDaño, TipoArmaSecundaria tipo) {
		throw new UnsupportedOperationException();
	}

	public TipoArmaSecundaria getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoArmaSecundaria tipo) {
		this.tipo = tipo;
	}
}