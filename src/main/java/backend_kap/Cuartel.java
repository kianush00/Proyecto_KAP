package backend_kap;

public class Cuartel extends Tienda {
	private final int PRECIO_CARGAR_MUNICION;

	public Cuartel() {
		super.precioJeringa = 20;
		super.precioCargador = 12;
		super.armaPrimariaEnVenta = generarArmaPrimariaAleatoriaCuartel();
		super.armaSecundariaEnVenta = generarArmaSecundariaAleatoriaCuartel();
		this.PRECIO_CARGAR_MUNICION = 15;
	}

	private ArmaPrimaria generarArmaPrimariaAleatoriaCuartel() {
		switch(calcularIntAleatorioEntre(1,4)){
			case 1:
				return generarRevolver();
			break;
			case 2:
				return generarSubfusil();
			break;
			case 3:
				return generarRifle();
				break;
			case 4:
				return generarFrancotirador();
				break;
		}
	}

	private ArmaSecundaria generarArmaSecundariaAleatoriaCuartel() {
		switch(calcularIntAleatorioEntre(1,3)){
			case 1:
				return generarCuchillo();
			break;
			case 2:
				return generarBateBeisbol();
			break;
			case 3:
				return generarHacha();
				break;
		}
	}

	public int getPRECIO_CARGAR_MUNICION() {
		return this.PRECIO_CARGAR_MUNICION;
	}

	public void cargarMunicion(Jugador jugador) {
		jugador.getInventario().setMunicion(jugador.getInventario().getLIMITE_MUNICION());
		jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.PRECIO_CARGAR_MUNICION);
	}

	private ArmaPrimaria generarRifle(){
		return new ArmaPrimaria();
	}

	private ArmaPrimaria generarFrancotirador(){
		return new ArmaPrimaria();
	}

	private ArmaSecundaria generarHacha(){
		return new ArmaSecundaria();
	}
}