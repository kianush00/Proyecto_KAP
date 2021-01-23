package backend_kap;

public class Hospital extends Tienda {
	private int precioCurarse;

	public Hospital(Jugador jugador) {
		super(jugador);
		super.precioJeringa = 8;
		super.precioCartucho = 20;
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

	public void curarse() {

	}
}