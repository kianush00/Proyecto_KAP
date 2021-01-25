package backend_kap;

public class Jugador extends Personaje {
	private Inventario inventario;
	private ArmaPrimaria armaPrimaria;
	private ArmaSecundaria armaSecundaria;

	/**
	 * Constructor de clase Jugador, heredado de Personaje.
	 * @param vidaMaxima Variable heredada de Personaje, se asigna en la instanciación.
	 * @param inventario Se asocia un objeto tipo Inventario.
	 * @param armaPrimaria Se asocia un objeto tipo ArmaPrimaria.
	 * @param armaSecundaria Se asocia un objeto tipo ArmaSecundaria.
	 */
	public Jugador(int vidaMaxima, Inventario inventario, ArmaPrimaria armaPrimaria, ArmaSecundaria armaSecundaria) {
		super.VIDA_MAXIMA = vidaMaxima;
		super.vidaActual = super.VIDA_MAXIMA;
		this.inventario = inventario;
		this.armaPrimaria = armaPrimaria;
		this.armaSecundaria = armaSecundaria;
	}

	/**
	 * Devuelve el objeto Inventario asociado al jugador.
	 * @return Objeto tipo Inventario.
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * Devuelve el objeto ArmaPrimaria asociado al jugador.
	 * @return Objeto tipo ArmaPrimaria.
	 */
	public ArmaPrimaria getArmaPrimaria() {
		return armaPrimaria;
	}

	/**
	 * Asigna el tipo de ArmaPrimaria del jugador.
	 * @param armaPrimaria Se pide el objeto ArmaPrimaria a asociar.
	 */
	public void setArmaPrimaria(ArmaPrimaria armaPrimaria) {
		this.armaPrimaria = armaPrimaria;
	}

	/**
	 * Devuelve el objeto ArmaSecundaria asociado al jugador.
	 * @return Objeto tipo ArmaSecundaria.
	 */
	public ArmaSecundaria getArmaSecundaria() {
		return armaSecundaria;
	}

	/**
	 * Asigna el tipo de ArmaSecundaria del jugador.
	 * @param armaSecundaria Se pide el objeto ArmaSecundaria a asociar.
	 */
	public void setArmaSecundaria(ArmaSecundaria armaSecundaria) {
		this.armaSecundaria = armaSecundaria;
	}

	/**
	 * Funcion matematica que permite obtener un booleano de forma aleatoria, define si el jugador escapa de la pelea o no.
	 * @return Objeto tipo Booleano.
	 */
	public boolean intentarHuir() {
		int probabilidadesEscapar = (int) (Math.random() * 2);

		// 1/2 prob. de huir exitosamente
		return probabilidadesEscapar == 0;
	}

	/**
	 * Finaliza la ejecución del programa debido a que el jugador pereció ante el enemigo.
	 */
	public void morir() {
		System.exit(1);
	}
}