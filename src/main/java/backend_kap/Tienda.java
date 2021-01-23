package backend_kap;

public class Tienda {
	protected int precioJeringa;
	protected int precioCartucho;
	protected ArmaPrimaria armaPrimariaEnVenta;
	protected ArmaSecundaria armaSecundariaEnVenta;
	protected Jugador jugador;

	public Tienda(Jugador jugador) {
		this.jugador = jugador;
	}

	public ArmaPrimaria getArmaPrimariaEnVenta() {
		return this.armaPrimariaEnVenta;
	}

	public ArmaSecundaria getArmaSecundariaEnVenta() {
		return this.armaSecundariaEnVenta;
	}

	public int getPrecioJeringa() {
		return this.precioJeringa;
	}

	public void setPrecioJeringa(int precioJeringa) {
		this.precioJeringa = precioJeringa;
	}

	public int getPrecioCartucho() {
		return this.precioCartucho;
	}

	public void setPrecioCartucho(int precioCartucho) {
		this.precioCartucho = precioCartucho;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

	public void venderArmaPrimaria() {

	}

	public void venderArmaSecundaria() {

	}

	public void venderJeringa() {

	}

	public void venderCartucho() {

	}
}