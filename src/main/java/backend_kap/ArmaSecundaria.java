package backend_kap;

public class ArmaSecundaria extends Arma {
	private TipoArmaSecundaria tipo;

	public ArmaSecundaria(int puntosDeDaño, TipoArmaSecundaria tipo) {
		super(puntosDeDaño);
		this.tipo=tipo;
	}

	public TipoArmaSecundaria getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoArmaSecundaria tipo) {
		this.tipo = tipo;
	}
}