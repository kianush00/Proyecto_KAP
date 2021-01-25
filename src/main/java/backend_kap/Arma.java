package backend_kap;

/**
 * La clase Arma es la clase padre de ArmaPrimaria y ArmaSecundaria
 */
public class Arma {
	protected int puntosDeDano;
	protected int precio;

	/**
	 * Constructor de Arma, padre de Arma primaria y secundaria.
	 * @param puntosDeDano Puntos de ano del arma a asociar.
	 * @param precio Valor en fichas del arma.
	 */
	public Arma(int puntosDeDano, int precio) {
		this.puntosDeDano = puntosDeDano;
		this.precio = precio;
	}

	/**
	 *Obtiene los puntos de dano del arma asociada.
	 * @return puntos de dano del arma asociada.
	 */
	public int getpuntosDeDano() {
		return this.puntosDeDano;
	}

	/**
	 *Obtiene el precio del arma asociada.
	 * @return precio del arma asociada.
	 */
	public int getPrecio() {
		return this.precio;
	}
}