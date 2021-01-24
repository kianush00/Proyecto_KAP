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

	private ArmaPrimaria generarArmaPrimariaAleatoriaCuartel() throws IllegalArgumentException{
		switch(calcularIntAleatorioEntre(1,4)){
			case 1:
				return generarRevolver();
			case 2:
				return generarSubfusil();
			case 3:
				return generarRifle();
			case 4:
				return generarFrancotirador();
			default:
				throw new IllegalArgumentException("Argumento inválido.");
		}
	}

	private ArmaSecundaria generarArmaSecundariaAleatoriaCuartel() throws IllegalArgumentException{
		switch(calcularIntAleatorioEntre(1,3)){
			case 1:
				return generarCuchillo();
			case 2:
				return generarBateBeisbol();
			case 3:
				return generarHacha();
			default:
				throw new IllegalArgumentException("Argumento inválido.");
		}
	}

	public int getPRECIO_CARGAR_MUNICION() {
		return this.PRECIO_CARGAR_MUNICION;
	}

	public void cargarMunicion(Jugador jugador) throws IllegalArgumentException{
		if(jugador.getInventario().getMunicion() != jugador.getInventario().getLIMITE_MUNICION()){
			jugador.getInventario().setMunicion(jugador.getInventario().getLIMITE_MUNICION());
			jugador.getInventario().setFichas(jugador.getInventario().getFichas() - this.PRECIO_CARGAR_MUNICION);
		}else{
			throw new IllegalArgumentException("No necesitas curarte, tu vida está al máximo.");
		}
	}

	private ArmaPrimaria generarRifle(){
		return new ArmaPrimaria(35,25,TipoArmaPrimaria.RIFLE,3);
	}

	private ArmaPrimaria generarFrancotirador(){
		return new ArmaPrimaria(50,40,TipoArmaPrimaria.FRANCOTIRADOR,1);
	}

	private ArmaSecundaria generarHacha(){
		return new ArmaSecundaria(19,15,TipoArmaSecundaria.HACHA);
	}
}