package backend_kap;

public class Tienda {
	protected int precioJeringa;
	protected int precioCargador;
	protected ArmaPrimaria armaPrimariaEnVenta;
	protected ArmaSecundaria armaSecundariaEnVenta;

	public Tienda() {}

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

	public int getPrecioCargador() {
		return this.precioCargador;
	}

	public void setPrecioCargador(int precioCartucho) {
		this.precioCargador = precioCartucho;
	}

	public void venderArmaPrimaria(Jugador jugador) {
		jugador.setArmaPrimaria(this.armaPrimariaEnVenta);
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - jugador.getArmaPrimaria().getPrecio());
	}

	public void venderArmaSecundaria(Jugador jugador) {
		jugador.setArmaSecundaria(this.armaSecundariaEnVenta);
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - jugador.getArmaSecundaria().getPrecio());
	}

	public void venderJeringa(Jugador jugador) {
		jugador.getInventario().setJeringas(jugador.getInventario().getJeringas() + 1);
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioJeringa);
	}

	public void venderCargador(Jugador jugador) {
		jugador.getInventario().setCargadores15Balas(jugador.getInventario().getCargadores15Balas() + 1);
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioCargador);
	}

	protected ArmaPrimaria generarRevolver(){
		return new ArmaPrimaria();
	}

	protected ArmaPrimaria generarSubfusil(){
		return new ArmaPrimaria();
	}

	protected ArmaSecundaria generarCuchillo(){
		return new ArmaSecundaria();
	}

	protected ArmaSecundaria generarBateBeisbol(){
		return new ArmaSecundaria();
	}

	protected int calcularIntAleatorioEntre(int min, int max) {
		return (int) (Math.random() * ((max + 1) - min)) + min;
	}
}