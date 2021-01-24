package backend_kap;

public class Hospital extends Tienda {
	private final int PRECIO_CURARSE;

	public Hospital() {
		super.precioJeringa = 8;
		super.precioCargador = 20;
		super.armaPrimariaEnVenta = generarArmaPrimariaAleatoriaHospital();
		super.armaSecundariaEnVenta = generarArmaSecundariaAleatoriaHospital();
		this.PRECIO_CURARSE = 15;
	}

	private ArmaPrimaria generarArmaPrimariaAleatoriaHospital() {
		switch(calcularIntAleatorioEntre(1,2)){
			case 1:
				return generarRevolver();
				break;
			case 2:
				return generarSubfusil();
				break;
		}
	}

	private ArmaSecundaria generarArmaSecundariaAleatoriaHospital() {
		switch(calcularIntAleatorioEntre(1,2)){
			case 1:
				return generarCuchillo();
				break;
			case 2:
				return generarBateBeisbol();
				break;
		}
	}

	public int getPRECIO_CURARSE() {
		return this.PRECIO_CURARSE;
	}

	public void curarse(Jugador jugador) {
		jugador.setVidaActual(jugador.getVIDA_MAXIMA());
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.PRECIO_CURARSE);
	}
}