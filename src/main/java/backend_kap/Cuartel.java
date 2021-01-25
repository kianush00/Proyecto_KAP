package backend_kap;

public class Cuartel extends Tienda {
	private final int PRECIO_CARGAR_MUNICION;

	/**
	 * Constructor de Cuartel, genera la instancia de la tienda Tipo Cuartel.
	 */
	public Cuartel() {
		super.precioJeringa = 20;
		super.precioCargador = 12;
		this.PRECIO_CARGAR_MUNICION = 15;
		try{
			super.armaPrimariaEnVenta = generarArmaPrimariaAleatoriaCuartel();
			super.armaSecundariaEnVenta = generarArmaSecundariaAleatoriaCuartel();
		}catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 * Genera una instancia de Arma primaria para ponerla a la venta.
	 * @return Devuelve un objeto ArmaPrimaria.
	 * @throws IllegalArgumentException En caso que se ingrese un valor no especificado en la entrada de usuario.
	 */
	private ArmaPrimaria generarArmaPrimariaAleatoriaCuartel() throws IllegalArgumentException{
		switch(calcularIntAleatorioEntre(1,4)){
			case 1:
				return generarRevolver();
			case 2:
				return generarSubfusil();
			case 3:
				return generarRifle();
			case 4:
				return generarFrancotirador();
			default:
				throw new IllegalArgumentException("Argumento inválido.");
		}
	}

	/**
	 * Genera una instancia de Arma secundaria para ponerla a la venta.
	 * @return Devuelve un objeto ArmaSecundaria.
	 * @throws IllegalArgumentException En caso que se ingrese un valor no especificado en la entrada de usuario.
	 */
	private ArmaSecundaria generarArmaSecundariaAleatoriaCuartel() throws IllegalArgumentException{
		switch(calcularIntAleatorioEntre(1,3)){
			case 1:
				return generarCuchillo();
			case 2:
				return generarBateBeisbol();
			case 3:
				return generarHacha();
			default:
				throw new IllegalArgumentException("Argumento inválido.");
		}
	}

	/**
	 * Devuelve la cantidad de fichas que cuesta una carga de municion.
	 * @return Objeto int con el valor de fichas.
	 */
	public int getPRECIO_CARGAR_MUNICION() {
		return this.PRECIO_CARGAR_MUNICION;
	}

	/**
	 * Permite anadir balas al inventario del jugador.
	 * @param jugador Se pide objeto Jugador para asignarle la nueva cantidad de balas.
	 * @throws IllegalArgumentException En caso que se ingrese un valor no especificado por el usuario
	 */
	public void cargarMunicion(Jugador jugador) throws IllegalArgumentException{
		if(jugador.getInventario().getMunicion() == jugador.getInventario().getLIMITE_MUNICION()){
			throw new IllegalArgumentException("No necesitas cargar tu munición, está al máximo.");
		} else {
			jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.PRECIO_CARGAR_MUNICION);
			jugador.getInventario().setMunicion(jugador.getInventario().getLIMITE_MUNICION());
		}
	}

	/**
	 * Devuelve instancia de objeto ArmaPrimaria de tipo RIFLE.
	 * @return Objeto ArmaPrimaria de tipo RIFLE.
	 */
	private ArmaPrimaria generarRifle(){
		return new ArmaPrimaria(35,25,TipoArmaPrimaria.RIFLE,3);
	}

	/**
	 * Devuelve instancia de objeto ArmaPrimaria de tipo FRANCOTIRADOR.
	 * @return Objeto ArmaPrimaria de tipo FRANCOTIRADOR.
	 */
	private ArmaPrimaria generarFrancotirador(){
		return new ArmaPrimaria(50,40,TipoArmaPrimaria.FRANCOTIRADOR,1);
	}

	/**
	 * Devuelve instancia de objeto ArmaSecundaria de tipo HACHA.
	 * @return Objeto ArmaSecundaria de tipo HACHA.
	 */
	private ArmaSecundaria generarHacha(){
		return new ArmaSecundaria(19,15,TipoArmaSecundaria.HACHA);
	}
}