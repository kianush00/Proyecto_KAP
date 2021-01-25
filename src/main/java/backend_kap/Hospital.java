package backend_kap;

/**
 * La clase Hospital hereda de la clase Tienda.
 */
public class Hospital extends Tienda {
	private final int PRECIO_CURARSE;

	/**
	 * Constructor de Hospital, genera la instancia de la tienda tipo Hospital.
	 */
	public Hospital() {
		super.precioJeringa = 8;
		super.precioCargador = 20;
		this.PRECIO_CURARSE = 15;
		try{
			super.armaPrimariaEnVenta = generarArmaPrimariaAleatoriaHospital();
			super.armaSecundariaEnVenta = generarArmaSecundariaAleatoriaHospital();
		}catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 * Genera una instancia de Arma primaria para ponerla a la venta. Contiene menos opciones aleatorias que la
	 * clase Cuartel.
	 * @return Devuelve un objeto ArmaPrimaria.
	 * @throws IllegalArgumentException En caso que se ingrese un valor no especificado en la entrada de usuario.
	 */
	private ArmaPrimaria generarArmaPrimariaAleatoriaHospital() throws IllegalArgumentException{
		switch(calcularIntAleatorioEntre(1,2)){
			case 1:
				return generarRevolver();
            case 2:
				return generarSubfusil();
            default:
                throw new IllegalArgumentException("Argumento inválido.");
        }
    }

	/**
	 * Genera una instancia de Arma secundaria para ponerla a la venta. Contiene menos opciones aleatorias que la
	 * clase Cuartel.
	 * @return Devuelve un objeto ArmaSecundaria.
	 * @throws IllegalArgumentException En caso que se ingrese un valor no especificado en la entrada de usuario.
	 */
	private ArmaSecundaria generarArmaSecundariaAleatoriaHospital() throws IllegalArgumentException{
		switch(calcularIntAleatorioEntre(1,2)){
			case 1:
				return generarCuchillo();
			case 2:
				return generarBateBeisbol();
            default:
                throw new IllegalArgumentException("Argumento inválido.");
		}
	}

	/**
	 * Devuelve la cantidad de fichas que cuesta curarse.
	 * @return Objeto int con el valor de fichas.
	 */
	public int getPRECIO_CURARSE() {
		return this.PRECIO_CURARSE;
	}

	/**
	 * Permite llenar los puntos de vida actuales del jugador.
	 * @param jugador Se pide objeto Jugador para llenar sus puntos de vida actuales.
	 * @throws IllegalArgumentException En caso que se ingrese un valor no especificado por el usuario
	 */
	public void curarse(Jugador jugador) throws IllegalArgumentException{
		if(jugador.getVidaActual() == jugador.getVIDA_MAXIMA()){
			throw new IllegalArgumentException("No necesitas curarte, tu vida está al máximo.");
		} else {
			jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.PRECIO_CURARSE);
			jugador.setVidaActual(jugador.getVIDA_MAXIMA());
		}
	}
}
