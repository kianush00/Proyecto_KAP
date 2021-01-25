package frontend_kap;

import backend_kap.Tienda;
import backend_kap.Jugador;
import backend_kap.Hospital;
import backend_kap.Cuartel;
import backend_kap.Juego;
import backend_kap.ArmaPrimaria;
import backend_kap.Inventario;
import backend_kap.Enemigo;
import com.opencsv.exceptions.CsvValidationException;
import data.GestorDatos;
import java.io.IOException;
import java.util.Scanner;

public class InterfazCLI {

	static Scanner input = new Scanner(System.in);

	public void lanzarVentana() throws IOException, CsvValidationException {
		Juego juego = new Juego(29);
		GestorDatos gestor= new GestorDatos();
		darBienvenida();
		for (juego.getNivelActual(); juego.getNivelActual() <= juego.getNIVELES();
			 juego.setNivelActual(juego.getNivelActual() + 1)) {
			//bucle for que recorre todos los niveles del juego. Se intercalan los métodos de pelea y tienda.
			if(juego.getNivelActual() % 3 != 0){
				desarrollarPelea(juego);
			}else{
				desarrollarTienda(juego);
			}

		}
		gestor.GuardarPuntaje(juego.getJugador().getInventario().getFichas());
		gestor.VerPuntajes();
		anunciarEscapeFinal();

	}

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
						huir = desarrollarHuida(juego.getJugador());   //si el jugador logra huir, retorna true. Viceversa retorna false
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
			turnoJugador = !turnoJugador;   //se cambia el turno
		}
		desarrollarTerminoPelea(juego,huir);
	}

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

	private void darBienvenidaTienda(Tienda tienda) {
		System.out.println("\nBienvenido al " + tienda.getClass().getSimpleName() + ". ¿Qué desea?");
	}

	private void mostrarOpcionesHospital() {
		System.out.println("Elige una opción: ");
		System.out.println("1. Comprar");
		System.out.println("2. Curarse");
		System.out.println("3. Salir");
	}

	private void mostrarOpcionesCuartel() {
		System.out.println("Elige una opción: ");
		System.out.println("1. Comprar");
		System.out.println("2. Cargar munición");
		System.out.println("3. Salir");
	}

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

	private void ofrecerProductosTienda(Tienda tienda) {
		System.out.println("Arma primaria disponible: " + tienda.getArmaPrimariaEnVenta().getTIPO());
		System.out.println("-Daño: " + tienda.getArmaPrimariaEnVenta().getPuntosDeDaño());
		System.out.println("-Ronda de munición: " + tienda.getArmaPrimariaEnVenta().getRONDA_MUNICION());
		System.out.println("-Precio: " + tienda.getArmaPrimariaEnVenta().getPrecio() + " fichas.");
		System.out.println("\nArma secundaria disponible: " + tienda.getArmaSecundariaEnVenta().getTIPO());
		System.out.println("-Daño: " + tienda.getArmaSecundariaEnVenta().getPuntosDeDaño());
		System.out.println("-Precio: " + tienda.getArmaSecundariaEnVenta().getPrecio() + " fichas.");
		System.out.println("\nPrecio por cada jeringa: " + tienda.getPrecioJeringa() + " fichas.");
		System.out.println("\nPrecio por cada cargador con 15 balas: " + tienda.getPrecioCargador() + " fichas.");
	}

	private void mostrarOpcionesComprarEnTienda() {
		System.out.println("\nElige una opción: ");
		System.out.println("1. Comprar arma primaria");
		System.out.println("2. Comprar arma secundaria");
		System.out.println("3. Comprar jeringa");
		System.out.println("4. Comprar cargador de 15 balas");
		System.out.println("5. Salir");
	}

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

	private void mostrarPrecioCurarse(Hospital hospital) {
		System.out.println("Precio para restablecer vida por completo: " + hospital.getPRECIO_CURARSE() + " fichas.");
	}

	private void mostrarPrecioCargarMunicion(Cuartel cuartel) {
		System.out.println("Precio para llenar de munición el inventario: " + cuartel.getPRECIO_CARGAR_MUNICION()
				+ " fichas.");
	}

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

	private int confirmarCompra() {
		System.out.println("¿Estás seguro de realizar la compra?");
		System.out.println("Elige una opción: ");
		System.out.println("1. Si");
		System.out.println("2. No");
		return elegirOpcionYValidar(1,2);
	}

	private void mostrarExitoCompra() {
		System.out.println("La compra ha sido exitosa.");
	}

	private void desarrollarAtaqueDelEnemigo(Juego juego) {
		if (juego.getEnemigoActual().atacarJugador(juego.getJugador()) == 0){	// un tercio de prob. de que el enemigo falle
			System.out.println("El enemigo ha fallado.");
		} else {
			System.out.println("Te han atacado con " + juego.getEnemigoActual().getPuntosDeDaño() + " Puntos de daño.");
			mostrarVidaActualJugador(juego.getJugador());
		}
	}

	private void desarrollarAtaqueDelJugador(Juego juego) {
		if (juego.getJugador().getInventario().getMunicion() >= juego.getJugador().getArmaPrimaria().getRONDA_MUNICION()) {
			//si la municion del jugador es mayor o igual que la ronda de municion
			juego.getJugador().getArmaPrimaria().atacarEnemigo(juego.getEnemigoActual(), juego.getJugador().getInventario());
			mostrarBalasGastadas(juego.getJugador().getArmaPrimaria());
		} else {
			juego.getJugador().getArmaSecundaria().atacarEnemigo(juego.getEnemigoActual());
		}
		mostrarVidaActualEnemigo(juego.getEnemigoActual());
	}

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

	private boolean desarrollarHuida(Jugador jugador){
		if(jugador.intentarHuir()){
			System.out.println("Huiste de la batalla.");
			return true;
		} else {
			System.out.println("¡Falló tu intento de huir!");
			return false;
		}
	}

	private void desarrollarUsarJeringa(Jugador jugador){
		try {
			jugador.getInventario().usarJeringa(jugador);
		}
		catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	private void desarrollarUsarCargador(Jugador jugador){
		try{
			jugador.getInventario().usarCargador();}
		catch (IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
	}

	private void mostrarOpcionesPelea(){
		System.out.println("Elige una opción: ");
		System.out.println("1. Atacar");
		System.out.println("2. Huir");
		System.out.println("3. Usar jeringa");
		System.out.println("4. Usar cargador");
	}

	private void anunciarInicioPelea(Juego juego) {
		System.out.println("\nInicia la batalla del nivel " + juego.getNivelActual());
	}

	private void mostrarBalasGastadas(ArmaPrimaria armaPrimaria) {
		System.out.println("Gastaste "+armaPrimaria.getRONDA_MUNICION()+ " bala(s).");
	}

	private void mostrarMunicionActual(Jugador jugador) {
		System.out.println("Munición actual: " + jugador.getInventario().getMunicion());
	}

	private void anunciarMuerteJugador(Juego juego) {
		System.err.println("Moriste, fin de la aventura.");
		juego.getJugador().morir();
	}

	private void mostrarVidaActualEnemigo(Enemigo enemigo) {
		System.out.println("La vida actual del enemigo es: " + enemigo.getVidaActual());
	}

	private void mostrarVidaActualJugador(Jugador jugador) {
		System.out.println("Tu vida actual es: " + jugador.getVidaActual());
	}

	private void mostrarFichasActuales(Jugador jugador) {
		System.out.println("Fichas actuales: " + jugador.getInventario().getFichas());
	}

	private void mostrarDatosJugador(Jugador jugador) {
		System.out.println("Tus datos actuales son: ");
		System.out.println("-Vida actual: " + jugador.getVidaActual());
		System.out.println("-Arma primaria: " + jugador.getArmaPrimaria().getTIPO());
		System.out.println("-Balas: " + jugador.getInventario().getMunicion());
		System.out.println("-Arma secundaria: " + jugador.getArmaSecundaria().getTIPO());
		System.out.println("-Cargadores de 15 balas: " + jugador.getInventario().getCargadores15Balas());
		System.out.println("-Jeringas: " + jugador.getInventario().getJeringas());
	}

	private void mostrarFichasGanadas(Juego juego){
		juego.getJugador().getInventario().setFichas(juego.getJugador().getInventario().getFichas()
				+ juego.calcularFichasGanadas());
		System.out.println("Ganase " + juego.calcularFichasGanadas() + " fichas.");
	}

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

	private void darBienvenida() {
		System.out.println("\nVarios años después de una abominable pandemia...");
		System.out.println("Bienvenido al apocalipsis, sobreviviente...");
		System.out.println("Tu misión es sobrevivir, actualmente posees una pistola de 9mm con 30 balas que recogiste" +
				" del suelo y un par de puños.");
		System.out.println("Usa bien tu inventario y municiones, son escasas.");
		System.out.println("Mucha suerte...\n");
	}

	private void anunciarEscapeFinal() {
		System.out.println("\nHas llegado al final de la aventura, felicidades.");
		System.out.println("Ya puedes escapar.");
		System.exit(1);
	}
}