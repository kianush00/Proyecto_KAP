package backend_kap;

/**
 * La clase Tienda es la clase padre de Hospital y Cuartel.
 * La clase Tienda está asociada a la clase Juego, pero no conoce a tal clase.
 * @see ArmaPrimaria
 * @see TipoArmaPrimaria
 * @see ArmaSecundaria
 * @see TipoArmaSecundaria
 * @see Jugador
 */
public class Tienda {
	protected int precioJeringa;
	protected int precioCargador;
	protected ArmaPrimaria armaPrimariaEnVenta;
	protected ArmaSecundaria armaSecundariaEnVenta;

	/**
	 * Constructor de Tienda, genera la instancia de la Tienda.
	 */
	public Tienda() {}

	/**
	 * Devuelve el objeto de tipo ArmaPrimaria que está a la venta.
	 * @return Objeto de tipo ArmaPrimaria.
	 */
	public ArmaPrimaria getArmaPrimariaEnVenta() {
		return this.armaPrimariaEnVenta;
	}

	/**
	 * Devuelve el objeto de tipo ArmaSecundaria que está a la venta.
	 * @return Objeto de tipo ArmaSecundaria.
	 */
	public ArmaSecundaria getArmaSecundariaEnVenta() {
		return this.armaSecundariaEnVenta;
	}

	/**
	 * Devuelve el precio de cada jeringa que está a la venta.
	 * @return precio de cada jeringa de tipo int.
	 */
	public int getPrecioJeringa() {
		return this.precioJeringa;
	}

	/**
	 * Devuelve el precio de cada cargador que está a la venta.
	 * @return precio de cada cargador de tipo int.
	 */
	public int getPrecioCargador() {
		return this.precioCargador;
	}

	/**
	 * Método que desarrolla la venta del arma primaria de la tienda al jugador que se pasa como parámetro.
	 * Primero se restan las fichas al jugador en relación al precio del producto y se le asigna tal producto a su
	 * inventario asociado.
	 * @param jugador contiene los atributos y métodos de Jugador que permiten realizar la venta.
	 *  @throws IllegalArgumentException Si las fichas del jugador resultan ser negativas se lanza una excepcion.
	 */
	public void venderArmaPrimaria(Jugador jugador) throws IllegalArgumentException{
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.armaPrimariaEnVenta.getPrecio());
		jugador.setArmaPrimaria(this.armaPrimariaEnVenta);
	}

	/**
	 * Método que desarrolla la venta del arma secundaria de la tienda al jugador que se pasa como parámetro.
	 * Primero se restan las fichas al jugador en relación al precio del producto y se le asigna tal producto a su
	 * inventario asociado.
	 * @param jugador contiene los atributos y métodos de Jugador que permiten realizar la venta.
	 *  @throws IllegalArgumentException Si las fichas del jugador resultan ser negativas se lanza una excepcion.
	 */
	public void venderArmaSecundaria(Jugador jugador) throws IllegalArgumentException{
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.armaSecundariaEnVenta.getPrecio());
		jugador.setArmaSecundaria(this.armaSecundariaEnVenta);
	}

	/**
	 * Método que desarrolla la venta de la unidad de jeringa al jugador que se pasa como parámetro.
	 * Primero se restan las fichas al jugador en relación al precio del producto y se le asigna tal producto a su
	 * inventario asociado.
	 * @param jugador contiene los atributos y métodos de Jugador que permiten realizar la venta.
	 * @throws IllegalArgumentException Si las fichas del jugador resultan ser negativas se lanza una excepcion.
	 */
	public void venderJeringa(Jugador jugador) throws IllegalArgumentException{
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioJeringa);
		jugador.getInventario().setJeringas(jugador.getInventario().getJeringas() + 1);
	}

	/**
	 * Método que desarrolla la venta de la unidad de cargador al jugador que se pasa como parámetro.
	 * Primero se restan las fichas al jugador en relación al precio del producto y se le asigna tal producto a su
	 * inventario asociado.
	 * @param jugador contiene los atributos y métodos de Jugador que permiten realizar la venta.
	 * @throws IllegalArgumentException Si las fichas del jugador resultan ser negativas se lanza una excepcion.
	 */
	public void venderCargador(Jugador jugador) throws IllegalArgumentException{
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioCargador);
		jugador.getInventario().setCargadores15Balas(jugador.getInventario().getCargadores15Balas() + 1);
	}

	/**
	 * Devuelve instancia de objeto ArmaPrimaria de tipo REVOLVER.
	 * @return Objeto ArmaPrimaria de tipo REVOLVER.
	 */
	protected ArmaPrimaria generarRevolver(){
		return new ArmaPrimaria(22,15,TipoArmaPrimaria.REVOLVER,1);
	}

	/**
	 * Devuelve instancia de objeto ArmaPrimaria de tipo SUBFUSIL.
	 * @return Objeto ArmaPrimaria de tipo SUBFUSIL.
	 */
	protected ArmaPrimaria generarSubfusil(){
		return new ArmaPrimaria(30,20,TipoArmaPrimaria.SUBFUSIL,5);
	}

	/**
	 * Devuelve instancia de objeto ArmaSecundaria de tipo CUCHILLO.
	 * @return Objeto ArmaSecundaria de tipo CUCHILLO.
	 */
	protected ArmaSecundaria generarCuchillo(){
		return new ArmaSecundaria(11,8,TipoArmaSecundaria.CUCHILLO);
	}

	/**
	 * Devuelve instancia de objeto ArmaSecundaria de tipo BATE_BEISBOL.
	 * @return Objeto ArmaSecundaria de tipo BATE_BEISBOL.
	 */
	protected ArmaSecundaria generarBateBeisbol(){
		return new ArmaSecundaria(13,11,TipoArmaSecundaria.BATE_BEISBOL);
	}

	protected int calcularIntAleatorioEntre(int min, int max) {
		return (int) (Math.random() * ((max + 1) - min) + min);
	}
}