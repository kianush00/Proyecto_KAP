package backend_kap;

public class Cuartel extends Tienda {
	private int precioCargarMunicion;

	public Cuartel() {
		super.precioJeringa = 20;
		super.precioCargador = 12;
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

	public void cargarMunicion(Jugador jugador) {
		jugador.getInventario().setMunicion(jugador.getInventario().getLIMITE_MUNICION());
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioCargarMunicion);
	}
}