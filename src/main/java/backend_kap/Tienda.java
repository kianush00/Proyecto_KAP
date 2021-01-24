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

	public int getPrecioCargador() {
		return this.precioCargador;
	}

	public void venderArmaPrimaria(Jugador jugador) {
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.armaPrimariaEnVenta.getPrecio());
		jugador.setArmaPrimaria(this.armaPrimariaEnVenta);
	}

	public void venderArmaSecundaria(Jugador jugador) {
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.armaSecundariaEnVenta.getPrecio());
		jugador.setArmaSecundaria(this.armaSecundariaEnVenta);
	}

	public void venderJeringa(Jugador jugador) {
		try {
			jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioJeringa);
			jugador.getInventario().setJeringas(jugador.getInventario().getJeringas() + 1);
		} catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	public void venderCargador(Jugador jugador) {
		try {
			jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.precioCargador);
			jugador.getInventario().setCargadores15Balas(jugador.getInventario().getCargadores15Balas() + 1);
		} catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	protected ArmaPrimaria generarRevolver(){
		return new ArmaPrimaria(22,15,TipoArmaPrimaria.REVOLVER,1);
	}

	protected ArmaPrimaria generarSubfusil(){
		return new ArmaPrimaria(30,20,TipoArmaPrimaria.SUBFUSIL,5);
	}

	protected ArmaSecundaria generarCuchillo(){
		return new ArmaSecundaria(11,8,TipoArmaSecundaria.CUCHILLO);
	}

	protected ArmaSecundaria generarBateBeisbol(){
		return new ArmaSecundaria(13,11,TipoArmaSecundaria.BATE_BEISBOL);
	}

	protected int calcularIntAleatorioEntre(int min, int max) {
		return (int) (Math.random() * ((max + 1) - min) + min);
	}
}