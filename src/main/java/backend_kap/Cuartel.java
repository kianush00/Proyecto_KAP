package backend_kap;

public class Cuartel extends Tienda {
	private int precioCargarMunicion;

	public Cuartel(Jugador jugador) {
		super(jugador);
		super.precioJeringa = 20;
		super.precioCartucho = 12;
		super.armaPrimariaEnVenta = generarArmaPrimariaAleatoriaCuartel();
		super.armaSecundariaEnVenta = generarArmaSecundariaAleatoriaCuartel();
		this.precioCargarMunicion = 15;
	}

	private ArmaPrimaria generarArmaPrimariaAleatoriaCuartel() {

	}

	private ArmaSecundaria generarArmaSecundariaAleatoriaCuartel() {

	}

	public int getPrecioCargarMunicion() {
		return this.precioCargarMunicion;
	}

	public void cargarMunicion() {

	}
}