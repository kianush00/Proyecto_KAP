package backend_kap;

/**
 * La clase Inventario está asociada a la clase Jugador, pero no conoce a tal clase.
 * @see Jugador
 */
public class Inventario {
	private int fichas;
	private int cargadores15Balas;
	private int jeringas;
	private int municion;
	private final int LIMITE_MUNICION;
	private final int LIMITE_CARGADORES;
	private final int LIMITE_JERINGAS;

	/**
	 * Constructor de la clase Inventario, inicializa sus atributos con valores predeterminados.
	 */
	public Inventario() {
		this.fichas = 0;
		this.cargadores15Balas = 0;
		this.jeringas = 0;
		this.municion = 30;
		this.LIMITE_MUNICION = 50;
		this.LIMITE_CARGADORES = 8;
		this.LIMITE_JERINGAS = 8;
	}

	/**
	 * Método que obtiene la cantidad de fichas que se encuentran actualmente en el inventario.
	 * @return fichas de tipo int
	 */
	public int getFichas() {
		return this.fichas;
	}

	/**
	 * Método que establece una nueva cantidad de fichas al inventario, la cual debe ser mayor a 0.
	 * @param fichas de tipo int
	 */
	public void setFichas(int fichas) throws IllegalArgumentException{
		if (fichas >= 0){
			this.fichas = fichas;
		}else{
			throw new IllegalArgumentException("No te quedan fichas disponibles. Inténtalo más tarde.");
		}
	}

	/**
	 * Método que obtiene la cantidad de cargadores de 15 balas que se encuentran actualmente en el inventario.
	 * @return cargadores15Balas de tipo int
	 */
	public int getCargadores15Balas() {
		return this.cargadores15Balas;
	}

	/**
	 * Método que establece una nueva cantidad de cargadores de 15 balas al inventario, la cual debe
	 * ser menor o igual al límite de cargadores que permite el inventario.
	 * @param cargadores15Balas de tipo int
	 */
	public void setCargadores15Balas(int cargadores15Balas) throws IllegalArgumentException{
		if (cargadores15Balas <= this.LIMITE_CARGADORES) {
			this.cargadores15Balas = cargadores15Balas;
		} else {
			throw new IllegalArgumentException("Te excediste del límite de cargadores que puedes llevar actualmente.");
		}

	}

	/**
	 * Método que obtiene la cantidad de jeringas que se encuentran actualmente en el inventario.
	 * @return jeringas de tipo int
	 */
	public int getJeringas() {
		return this.jeringas;
	}

	/**
	 * Método que establece una nueva cantidad de jeringas al inventario, la cual debe ser menor o igual al límite
	 * de jeringas que permite el inventario.
	 * @param jeringas de tipo int
	 */
	public void setJeringas(int jeringas) {
		if (jeringas <= this.LIMITE_JERINGAS) {
			this.jeringas = jeringas;
		} else {
			throw new IllegalArgumentException("Te excediste del límite de jeringas que puedes llevar actualmente.");
		}
	}

	/**
	 * Método que obtiene la cantidad de munición que se encuentra actualmente en el inventario.
	 * @return municion de tipo int
	 */
	public int getMunicion() {
		return this.municion;
	}

	/**
	 * Método que establece una nueva cantidad de munición al inventario.
	 * @param municion de tipo int
	 */
	public void setMunicion(int municion) {
		this.municion = municion;
	}

	/**
	 * Método que obtiene el valor del límite de munición que permite el inventario.
	 * @return LIMITE_MUNICION de tipo int
	 */
	public int getLIMITE_MUNICION() {
		return LIMITE_MUNICION;
	}

	/**
	 * Método que gasta un cargador del inventario, le agrega un valor al atributo de munición y le resta por uno
	 * el valor del atributo de los cargadores
	 * @throws IllegalArgumentException se lanza la excepción en caso de que no queden cargadores disponibles.
	 */
	public void usarCargador() throws IllegalArgumentException {
		if (this.cargadores15Balas > 0) {
			if ((this.LIMITE_MUNICION - this.municion) > 15) {
				this.municion += 15;
			} else {
				this.municion = this.LIMITE_MUNICION;
			}
			this.cargadores15Balas--;
		} else {
			throw new IllegalArgumentException("No te quedan cargadores");
		}
	}

	/**
	 * Método que gasta una jeringa del inventario, le asigna el máximo valor al atributo del parámetro pasado
	 * de tipo Jugador y le resta por uno el valor del atributo de las jeringas.
	 * @param jugador objeto de tipo Jugador al cual se le setea el atributo de la vida actual al máximo
	 * @throws IllegalArgumentException se lanza la excepción en caso de que no queden cargadores disponibles.
	 */
	public void usarJeringa(Jugador jugador) throws IllegalArgumentException {
		if (this.jeringas > 0) {
			if ((jugador.getVIDA_MAXIMA() - jugador.getVidaActual()) > 50) {
				jugador.setVidaActual(jugador.getVidaActual() + 50);
			} else {
				jugador.setVidaActual(jugador.getVIDA_MAXIMA());
			}
			this.jeringas--;
		} else {
			throw new IllegalArgumentException("No te quedan jeringas");
		}
	}
}