package backend_kap;

public class Arma {
	protected int puntosDeDaño;
	protected int precio;

	/**
	 * Constructor de Arma, padre de Arma primaria y secundaria.
	 * @param puntosDeDaño Puntos de año del arma a asociar.
	 * @param precio Valor en fichas del arma.
	 */
	public Arma(int puntosDeDaño, int precio) {
		this.puntosDeDaño = puntosDeDaño;
		this.precio = precio;
	}

	/**
	 *Obtiene los puntos de daño del arma asociada.
	 * @return puntos de daño del arma asociada.
	 */
	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	/**
	 *Obtiene el precio del arma asociada.
	 * @return precio del arma asociada.
	 */
	public int getPrecio() {
		return this.precio;
	}
}