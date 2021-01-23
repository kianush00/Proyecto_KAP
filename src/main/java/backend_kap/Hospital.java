package backend_kap;

public class Hospital extends Tienda {
	private int precioCurarse;

	public Hospital() {
		super.precioJeringa = 8;
		super.precioCargador = 20;
		super.armaPrimariaEnVenta = generarArmaPrimariaAleatoriaHospital();
		super.armaSecundariaEnVenta = generarArmaSecundariaAleatoriaHospital();
		this.precioCurarse = 15;
	}

	private ArmaPrimaria generarArmaPrimariaAleatoriaHospital() {

	}

	private ArmaSecundaria generarArmaSecundariaAleatoriaHospital() {

	}

	public int getPrecioCurarse() {
		return this.precioCurarse;
	}

	public void curarse(Jugador jugador) {
		jugador.setVidaActual(jugador.getVidaMaxima());
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioCurarse);
	}
}