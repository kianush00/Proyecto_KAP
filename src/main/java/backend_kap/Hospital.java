package backend_kap;

public class Hospital extends Tienda {
	private final int PRECIO_CURARSE;

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

	public int getPRECIO_CURARSE() {
		return this.PRECIO_CURARSE;
	}

	public void curarse(Jugador jugador) throws IllegalArgumentException{
		if(jugador.getVidaActual() == jugador.getVIDA_MAXIMA()){
			throw new IllegalArgumentException("No necesitas curarte, tu vida está al máximo.");
		} else {
			jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.PRECIO_CURARSE);
			jugador.setVidaActual(jugador.getVIDA_MAXIMA());
		}
	}
}
