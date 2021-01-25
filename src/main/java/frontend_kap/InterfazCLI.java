package frontend_kap;

import backend_kap.Tienda;
import backend_kap.Jugador;
import backend_kap.Hospital;
import backend_kap.Cuartel;
import backend_kap.Juego;
import backend_kap.ArmaPrimaria;
import backend_kap.Inventario;
import backend_kap.Enemigo;
import data.GestorDatos;
import java.util.Scanner;

/**
 * La clase InterfazCLI es la interfaz entre el usuario y el backend del Proyecto KAP
 */
public class InterfazCLI {

	/**
	 * Atributo de tipo static que contiene los datos que se ingresan por consola.
	 * @see Scanner
	 */
	static Scanner input = new Scanner(System.in);

	/**
	 * Este método es el único método público de la clase, por lo que contiene una generalización de todos
	 * los métodos del programa. Acá se encuentra el desarrollo del juego en base a las opciones elegidas
	 * por el usuario y los mensajes que se muestran por pantalla. Se utilizan los recursos del paquete backend_kap
	 * por medio de una instancia de la clase Juego.
	 * Por último se guardan las fichas restantes y el nombre del jugador en caso de que el juego sea completado.
	 * @see backend_kap.Arma
	 * @see ArmaPrimaria
	 * @see backend_kap.ArmaSecundaria
	 * @see Cuartel
	 * @see Enemigo
	 * @see Hospital
	 * @see Inventario
	 * @see Juego
	 * @see Jugador
	 * @see backend_kap.Personaje
	 * @see Tienda
	 * @see backend_kap.TipoArmaPrimaria
	 * @see backend_kap.TipoArmaSecundaria
	 * @see GestorDatos
	 */
	public void lanzarVentana(){
		Juego juego = new Juego(29);
		GestorDatos gestor= new GestorDatos();
		//Se muestra mensaje de bienvenida
		darBienvenida();
		for (juego.getNivelActual(); juego.getNivelActual() <= juego.getNIVELES();
			 juego.setNivelActual(juego.getNivelActual() + 1)) {
			//Bucle for recorre todos los niveles del juego. Se intercalan los métodos de pelea y tienda.
			if(juego.getNivelActual() % 3 != 0){
				desarrollarPelea(juego);
			}else{
				desarrollarTienda(juego);
			}

		}
		//Se muestra mensaje final
		anunciarEscapeFinal();
		//Se guardan los datos del jugador, los cuales son su nombre ingresado y las fichas que restaron.
		gestor.GuardarPuntaje(juego.getJugador().getInventario().getFichas());
		gestor.VerPuntajes();
	}

	/**
	 *  Método que contiene toda la interacción con el usuario en la etapa del desarrollo de la pelea.
	 *  Permite la ejecución de la pelea entre el jugador y el enemigo, se instancia el objeto Enemigo
	 *  en la clase Juego.
	 *  @param juego Se pide un Objeto tipo Juego para acceder al jugador y enemigo.
	 */
	private void desarrollarPelea(Juego juego){
		boolean turnoJugador = true;
		boolean huir = false;

		juego.generarNuevoEnemigo();
		anunciarInicioPelea(juego);
		mostrarDatosJugador(juego.getJugador());
		while ((juego.getJugador().getVidaActual() > 0) && (juego.getEnemigoActual().getVidaActual() > 0) && (!huir)) {
			//se desarrolla pelea dentro del bucle while; se detiene cuando alguien muere o el jugador logra huir
			if (turnoJugador) {
				mostrarOpcionesPelea();
				switch (elegirOpcionYValidar(1,4)) {
					case 1:		//atacar
						desarrollarAtaqueDelJugador(juego);
						break;
					case 2:		//huir
						huir = desarrollarHuida(juego.getJugador());  //si el jugador logra huir->true. Viceversa->false
						break;
					case 3:		//usar jeringa
						desarrollarUsarJeringa(juego.getJugador());
						break;
					case 4:		//usar cargador
						desarrollarUsarCargador(juego.getJugador());
						break;
				}
			} else {
				desarrollarAtaqueDelEnemigo(juego);
			}
			turnoJugador = !turnoJugador;   //se cambia de turno
		}
		desarrollarTerminoPelea(juego,huir);	//si el jugador gana recibe fichas por parte de Juego
	}

	/**
	 * Método que contiene toda la interacción con el usuario en la etapa del desarrollo de la tienda.
	 * El usuario elige los objetos generados aleatoriamente que desee comprar con las fichas que posee
	 * @param juego Se pide un Objeto tipo Juego para acceder al jugador, al inventario y a la tienda.
	 */
	private void desarrollarTienda(Juego juego){
		boolean salirTienda = false;

		juego.generarNuevaTiendaAleatoria();
		darBienvenidaTienda(juego.getTiendaActual());
		while(!salirTienda){
			if(juego.getTiendaActual() instanceof Hospital){
				mostrarOpcionesHospital();
				switch (elegirOpcionYValidar(1,3)){
					case 1:		//comprar
						desarrollarCompraTienda(juego);
						break;
					case 2:		//curarse
						desarrollarCompraCurarse(juego);
						break;
					case 3:		//salir de la tienda
						salirTienda = true;
						break;
				}
			}else{
				mostrarOpcionesCuartel();
				switch (elegirOpcionYValidar(1,3)){
					case 1:		//comprar
						desarrollarCompraTienda(juego);
						break;
					case 2:		//cargar munición
						desarrollarCompraCargarMunicion(juego);
						break;
					case 3:		//salir de la tienda
						salirTienda = true;
						break;
				}
			}
		}
	}

	/**
	 *	Método que muestra el mensaje de bienvenida antes de desarrollarse la tienda.
	 * @param tienda Se pide el objeto tienda para acceder a sus atributos
	 */
	private void darBienvenidaTienda(Tienda tienda) {
		System.out.println("\nBienvenido al " + tienda.getClass().getSimpleName() + ". ¿Qué desea?");
	}

	/**
	 * Método que muestra un menú que contiene las opciones disponibles del desarrollo del hospital.
	 */
	private void mostrarOpcionesHospital() {
		System.out.println("Elige una opción: ");
		System.out.println("1. Comprar");
		System.out.println("2. Curarse");
		System.out.println("3. Salir");
	}

	/**
	 * Método que muestra un menú que contiene las opciones disponibles del desarrollo del cuartel.
	 */
	private void mostrarOpcionesCuartel() {
		System.out.println("Elige una opción: ");
		System.out.println("1. Comprar");
		System.out.println("2. Cargar munición");
		System.out.println("3. Salir");
	}

	/**
	 * Método que contiene la interacción con el usuario durante la opción elegida de compra. Se muestran
	 * las opciones que el usuario puede elegir durante el desarrollo de la compra.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraTienda(Juego juego) {
		boolean salirCompra = false;

		ofrecerProductosTienda(juego.getTiendaActual());
		do {
			mostrarFichasActuales(juego.getJugador());
			mostrarOpcionesComprarEnTienda();
			switch (elegirOpcionYValidar(1,5)){
				case 1:		//comprar arma principal
					desarrollarCompraArmaPrimaria(juego);
					break;
				case 2:		//comprar arma secundaria
					desarrollarCompraArmaSecundaria(juego);
					break;
				case 3:		//comprar jeringa
					desarrollarCompraJeringa(juego);
					break;
				case 4:		//comprar cargador de 15 balas
					desarrollarCompraCargador15balas(juego);
					break;
				case 5:		//salir de la compra
					salirCompra = true;
					break;
			}
		} while (!salirCompra);
	}

	/**
	 * Método que muestra todos los productos que ofrece la tienda, junto con sus atributos como precio y danio.
	 * @param tienda Se pide el objeto tienda para acceder a sus atributos
	 */
	private void ofrecerProductosTienda(Tienda tienda) {
		System.out.println("Arma primaria disponible: " + tienda.getArmaPrimariaEnVenta().getTIPO());
		System.out.println("-Danio: " + tienda.getArmaPrimariaEnVenta().getpuntosDeDano());
		System.out.println("-Ronda de munición: " + tienda.getArmaPrimariaEnVenta().getRONDA_MUNICION());
		System.out.println("-Precio: " + tienda.getArmaPrimariaEnVenta().getPrecio() + " fichas.");
		System.out.println("\nArma secundaria disponible: " + tienda.getArmaSecundariaEnVenta().getTIPO());
		System.out.println("-Danio: " + tienda.getArmaSecundariaEnVenta().getpuntosDeDano());
		System.out.println("-Precio: " + tienda.getArmaSecundariaEnVenta().getPrecio() + " fichas.");
		System.out.println("\nPrecio por cada jeringa: " + tienda.getPrecioJeringa() + " fichas.");
		System.out.println("\nPrecio por cada cargador con 15 balas: " + tienda.getPrecioCargador() + " fichas.");
	}

	/**
	 * Método que muestra un menú que contiene las opciones que corresponden al desarrollo de la compra en la tienda.
	 */
	private void mostrarOpcionesComprarEnTienda() {
		System.out.println("\nElige una opción: ");
		System.out.println("1. Comprar arma primaria");
		System.out.println("2. Comprar arma secundaria");
		System.out.println("3. Comprar jeringa");
		System.out.println("4. Comprar cargador de 15 balas");
		System.out.println("5. Salir");
	}

	/**
	 *  Método que contiene el desarrollo de la opción que corresponde a la compra del arma primaria.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraArmaPrimaria(Juego juego) {
		try {
			if(confirmarCompra() == 1){
				juego.getTiendaActual().venderArmaPrimaria(juego.getJugador());
				mostrarExitoCompra();
			}
		} catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 *  Método que contiene el desarrollo de la opción que corresponde a la compra del arma secundaria.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraArmaSecundaria(Juego juego) {
		try {
			if(confirmarCompra() == 1){
				juego.getTiendaActual().venderArmaSecundaria(juego.getJugador());
				mostrarExitoCompra();
			}
		} catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 *  Método que contiene el desarrollo de la opción que corresponde a la compra de la jeringa.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraJeringa(Juego juego) {
		try {
			if(confirmarCompra() == 1){
				juego.getTiendaActual().venderJeringa(juego.getJugador());
				mostrarExitoCompra();
			}
		} catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 *  Método que contiene el desarrollo de la opción que corresponde a la compra del cargador.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraCargador15balas(Juego juego) {
		try {
			if(confirmarCompra() == 1){
				juego.getTiendaActual().venderCargador(juego.getJugador());
				mostrarExitoCompra();
			}
		} catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 * Método que muestra el precio que cuesta elegir la opción de restablecer la vida del jugador por completo.
	 * @param hospital Si pide el objeto Hospital para acceder al precio de curarse.
	 */
	private void mostrarPrecioCurarse(Hospital hospital) {
		System.out.println("Precio para restablecer vida por completo: " + hospital.getPRECIO_CURARSE() + " fichas.");
	}

	/**
	 * Método que muestra el precio que cuesta elegir la opción de llenar de munición el inventario.
	 * @param cuartel Si pide el objeto Hospital para acceder al precio de cargar la munición.
	 */
	private void mostrarPrecioCargarMunicion(Cuartel cuartel) {
		System.out.println("Precio para llenar de munición el inventario: " + cuartel.getPRECIO_CARGAR_MUNICION()
				+ " fichas.");
	}

	/**
	 *  Método que contiene el desarrollo de la opción de curarse dentro de la tienda de tipo Hospital.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraCurarse(Juego juego) {
		try{
			mostrarPrecioCurarse((Hospital) juego.getTiendaActual());
			mostrarFichasActuales(juego.getJugador());
			mostrarVidaActualJugador(juego.getJugador());
			if(confirmarCompra() == 1){
				((Hospital) juego.getTiendaActual()).curarse(juego.getJugador());
				mostrarExitoCompra();
			}
		}catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 *  Método que contiene el desarrollo de la opción de cargar munición dentro de la tienda de tipo Cuartel.
	 * @param juego Se pide el objeto Juego para acceder a los atributos y métodos del juego.
	 */
	private void desarrollarCompraCargarMunicion(Juego juego) {
		try{
			mostrarPrecioCargarMunicion((Cuartel) juego.getTiendaActual());
			mostrarFichasActuales(juego.getJugador());
			mostrarMunicionActual(juego.getJugador());
			if(confirmarCompra() == 1){
				((Cuartel) juego.getTiendaActual()).cargarMunicion(juego.getJugador());
				mostrarExitoCompra();
			}
		}catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 * Método que muestra un menú que contiene las opciones si y no los cuales confirman la compra y retorna
	 * el valor ingresado por teclado.
	 * @return opción ingresada por el usuario.
	 */
	private int confirmarCompra() {
		System.out.println("¿Estás seguro de realizar la compra?");
		System.out.println("Elige una opción: ");
		System.out.println("1. Si");
		System.out.println("2. No");
		return elegirOpcionYValidar(1,2);
	}

	/**
	 * Método que muestra un mensaje, el cual indica que la compra ha sido realizada exitosamente.
	 */
	private void mostrarExitoCompra() {
		System.out.println("La compra ha sido exitosa.");
	}

	/**
	 * Método que ejecuta un ataque contra el jugador basado en el dano del enemigo.
	 * @param juego Se pide el objeto Juego para acceder a los atributos del enemigo y jugador.
	 */
	private void desarrollarAtaqueDelEnemigo(Juego juego) {
		if (juego.getEnemigoActual().atacarJugador(juego.getJugador()) == 0){ // 1/3 de prob. de que el enemigo falle
			System.out.println("El enemigo ha fallado.");
		} else {
			System.out.println("Te han atacado con " + juego.getEnemigoActual().getpuntosDeDano() + " Puntos de dano.");
			mostrarVidaActualJugador(juego.getJugador());
		}
	}

	/**
	 * Método que ejecuta un ataque contra el enemigo basado en el dano del arma del jugador, puede ser la principal
	 * o la secundaria.
	 * @param juego Se pide el objeto Juego para acceder a los atributos del enemigo y jugador.
	 */
	private void desarrollarAtaqueDelJugador(Juego juego) {
		if (juego.getJugador().getInventario().getMunicion() >= juego.getJugador().getArmaPrimaria()
				.getRONDA_MUNICION()) {
			//si la municion del jugador es mayor o igual que la ronda de municion
			juego.getJugador().getArmaPrimaria().atacarEnemigo(juego.getEnemigoActual(), juego.getJugador()
					.getInventario());
			mostrarBalasGastadas(juego.getJugador().getArmaPrimaria());
		} else {
			juego.getJugador().getArmaSecundaria().atacarEnemigo(juego.getEnemigoActual());
		}
		mostrarVidaActualEnemigo(juego.getEnemigoActual());
	}

	/**
	 * Método que comprueba si el jugador murió o gano la batalla del nivel.
	 * @param juego se Pide el objeto juego para comprobar la vida del jugador si no es cero, significa que
	 *                gano la batalla.
	 * @param huir el parametro huir comprueba si escapo de la batalla del nivel.
	 */
	private void desarrollarTerminoPelea(Juego juego, boolean huir){
		if(!huir){
			if (juego.getJugador().getVidaActual() <= 0) {
				anunciarMuerteJugador(juego);
			} else {
				System.out.println("Ganaste la batalla!");
				mostrarFichasGanadas(juego);
			}
		}
	}

	/**
	 * Método que asigna valor a la variable huir en desarrollarPelea(), si el booleano es True, se escapa
	 * de la pelea del nivel.
	 * @param jugador Se pide el objeto jugador para acceder al metodo intentarHuir().
	 * @return valor booleano que indica si el jugador logra huir de la batalla.
	 */
	private boolean desarrollarHuida(Jugador jugador){
		if(jugador.intentarHuir()){
			System.out.println("Huiste de la batalla.");
			return true;
		} else {
			System.out.println("¡Falló tu intento de huir!");
			return false;
		}
	}

	/**
	 * Método que permite utilizar una jeringa para aumentar los puntos de vida, siempre y cuando esta se
	 * encuentre disponible en inventario.
	 * @param jugador Se pide como parametro el objeto Jugador para acceder a inventario.
	 */
	private void desarrollarUsarJeringa(Jugador jugador){
		try {
			jugador.getInventario().usarJeringa(jugador);
		}
		catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 * Método que permite utilizar un cargador para aumentar las balas disponibles, siempre y cuando este se
	 * encuentre disponible en inventario.
	 * @param jugador Se pide como parametro el objeto Jugador para acceder a inventario.
	 */
	private void desarrollarUsarCargador(Jugador jugador){
		try{
			jugador.getInventario().usarCargador();}
		catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	/**
	 * Método que muestra un menú que contiene las opciones disponibles en cada iteración de la pelea.
	 */
	private void mostrarOpcionesPelea(){
		System.out.println("Elige una opción: ");
		System.out.println("1. Atacar");
		System.out.println("2. Huir");
		System.out.println("3. Usar jeringa");
		System.out.println("4. Usar cargador");
	}

	/**
	 * Método de bienvenida del comienzo de la pelea del nivel.
	 * @param juego Pide el objeto juego para acceder al nivel actual.
	 */
	private void anunciarInicioPelea(Juego juego) {
		System.out.println("\nInicia la batalla del nivel " + juego.getNivelActual());
	}

	/**
	 * Método que muestra la cantidad de balas gastadas por el arma al momento de atacar.
	 * @param armaPrimaria Toma como parametro el Arma Primaria para obtener el valor de la ronda.
	 */
	private void mostrarBalasGastadas(ArmaPrimaria armaPrimaria) {
		System.out.println("Gastaste "+armaPrimaria.getRONDA_MUNICION()+ " bala(s).");
	}

	/**
	 * Método que muestra la munición disponible en el momento
	 * @param jugador Toma como parametro el Objeto Jugador para acceder a Inventario.
	 */
	private void mostrarMunicionActual(Jugador jugador) {
		System.out.println("Munición actual: " + jugador.getInventario().getMunicion());
	}

	/**
	 * Método que muestra la muerte del jugador
	 * @param juego Pide como parametro el objeto tipo Juego para acceder a jugador y al método morir().
	 */
	private void anunciarMuerteJugador(Juego juego) {
		System.err.println("Moriste, fin de la aventura.");
		juego.getJugador().morir();
	}

	/**
	 * Método que muestra la vida actual del enemigo.
	 * @param enemigo Toma como parametro el objeto Enemigo para acceder a su atributo de vida actual.
	 */
	private void mostrarVidaActualEnemigo(Enemigo enemigo) {
		System.out.println("La vida actual del enemigo es: " + enemigo.getVidaActual());
	}

	/**
	 * Método que muestra la vida actual del jugador.
	 * @param jugador Toma como parametro el objeto Jugador para acceder a su atributo de las fichas actuales.
	 */
	private void mostrarVidaActualJugador(Jugador jugador) {
		System.out.println("Tu vida actual es: " + jugador.getVidaActual());
	}

	/**
	 * Método que muestra la cantidad de fichas actuales que posee el jugador.
	 * @param jugador Toma como parametro el objeto Jugador para acceder a su atributo de vida actual.
	 */
	private void mostrarFichasActuales(Jugador jugador) {
		System.out.println("Fichas actuales: " + jugador.getInventario().getFichas());
	}

	/**
	 * Método que muestra los datos del jugador que corresponden a su atributo de vida y los de su inventario actual.
	 * @param jugador Toma como parametro el objeto Jugador para acceder a sus datos.
	 */
	private void mostrarDatosJugador(Jugador jugador) {
		System.out.println("Tus datos actuales son: ");
		System.out.println("-Vida actual: " + jugador.getVidaActual());
		System.out.println("-Arma primaria: " + jugador.getArmaPrimaria().getTIPO());
		System.out.println("-Balas: " + jugador.getInventario().getMunicion());
		System.out.println("-Arma secundaria: " + jugador.getArmaSecundaria().getTIPO());
		System.out.println("-Cargadores de 15 balas: " + jugador.getInventario().getCargadores15Balas());
		System.out.println("-Jeringas: " + jugador.getInventario().getJeringas());
	}

	/**
	 * Método que muestra la cantidad de fichas que se ganaron al terminar la pelea con el enemigo.
	 * @param juego Pide como parametro el objeto Juego para acceder a Jugador e Inventario.
	 */
	private void mostrarFichasGanadas(Juego juego){
		juego.getJugador().getInventario().setFichas(juego.getJugador().getInventario().getFichas()
				+ juego.calcularFichasGanadas());
		System.out.println("Ganase " + juego.calcularFichasGanadas() + " fichas.");
	}

	/**
	 * Método que permite al usuario ingresar un valor entero que debe ubicarse dentro del rango entre los parámetros
	 * min y max. En caso de que no se cumpla se valida la opción hasta que se entregue un resultado satisfactorio
	 * por teclado.
	 * @param min Mínimo valor aceptado dentro del rango de enteros
	 * @param max Máximo valor aceptado dentro del rango de enteros
	 */
	private int elegirOpcionYValidar(int min, int max) {
		int opcion = -1;    //se inicializa la opción
		boolean opcionEsValida = false;

		while(!opcionEsValida){
			validarOpcionInt();
			opcion = ingresarInt();
			opcionEsValida = opcionEstaDentroDeRango(opcion,min,max);
		}

		return opcion;
	}

	private void validarOpcionInt() {
		while(!input.hasNextInt()){
			System.err.println("La opción ingresada no es un entero. Intenta nuevamente:");
			input.next();   //pasa al siguiente iterador
		}
	}

	private int ingresarInt() {
		return input.nextInt();
	}

	private boolean opcionEstaDentroDeRango(int opcion, int min, int max) {
		if((opcion < min) || (opcion > max)){
			System.err.println("La opción ingresada no se encuentra dentro del rango. Intenta nuevamente:");
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Método que muestra el mensaje de bienvenida al usuario antes de que el desarrollo del juego empiece.
	 */
	private void darBienvenida() {
		System.out.println("\nVarios anios después de una abominable pandemia...");
		System.out.println("Bienvenido al apocalipsis, sobreviviente...");
		System.out.println("Tu misión es sobrevivir, actualmente posees una pistola de 9mm con 30 balas que recogiste" +
				" del suelo y un par de punios.");
		System.out.println("Usa bien tu inventario y municiones, son escasas.");
		System.out.println("Mucha suerte...\n");
	}

	/**
	 * Método que muestra el mensaje del escape final al usuario después de completar exitosamente el juego.
	 */
	private void anunciarEscapeFinal() {
		System.out.println("\nHas llegado al final de la aventura, felicidades.");
		System.out.println("Ya puedes escapar.");
	}
}