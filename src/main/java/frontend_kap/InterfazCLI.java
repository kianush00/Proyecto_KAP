package frontend_kap;

import backend_kap.Tienda;
import backend_kap.Jugador;
import backend_kap.Hospital;
import backend_kap.Cuartel;
import backend_kap.Juego;
import backend_kap.ArmaPrimaria;
import backend_kap.Inventario;
import backend_kap.Enemigo;

import java.util.Scanner;

public class InterfazCLI {

	public void lanzarVentana() {
		Juego juego = new Juego(29);
		darBienvenida();
		for (juego.getNivelActual(); juego.getNivelActual() <= juego.getNIVELES();
			 juego.setNivelActual(juego.getNivelActual() + 1)) {
			if(juego.getNivelActual() % 3 != 0){
				desarrollarPelea(juego);
			}else{
				desarrollarTienda(juego);
			}
		}
		anunciarEscapeFinal();
	}

	private void desarrollarPelea(Juego juego){
		juego.generarNuevoEnemigo();
		boolean turnoJugador = true;
		boolean huir = false;
		anunciarInicioPelea(juego);
		mostrarJeringasYCartuchosActuales(juego.getJugador().getInventario());
		while ((juego.getJugador().getVidaActual() > 0) && (juego.getEnemigoActual().getVidaActual() > 0) && (!huir)) {
			//se desarrolla pelea dentro del bucle while; se detiene cuando alguien muere o el jugador logra huir
			if (turnoJugador) {
				System.out.println("Opcion 1 para atacar, 2 para huir, 3 para curarte y finalmente 4 para usar un cartucho: ");
				int opcion = elegirOpcionYValidar(1,4);
				switch (opcion) {
					case 1:
						mostrarAtaqueDelJugador(juego);
						break;
					case 2:
						huir = juego.getJugador().intentarHuir();   //si el jugador logra huir, retorna true. Viceversa retorna false
						break;
					case 3:
						try {
							juego.getJugador().getInventario().usarJeringa();
						}
						catch (IllegalArgumentException iae){
							System.err.println(iae.getMessage());
						}
						break;
					case 4:
						try{
						juego.getJugador().getInventario().usarCartucho();}
						catch (IllegalArgumentException iae){
							System.err.println(iae.getMessage());
						}
						break;
				}
			} else {
				mostrarAtaqueDelEnemigo(juego);
			}
			turnoJugador = !turnoJugador;   //se cambia el turno
		}
		desarrollarTerminoPelea(juego,huir);
		}

private void desarrollarTerminoPelea(Juego juego, boolean huir){
		if(!huir){
			if (juego.getJugador().getVidaActual() <= 0) {
				anunciarMuerteJugador(juego);
			} else {
				System.out.println("Ganaste la batalla!");
				juego.calcularFichasGanadas();
			}
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

	private void darBienvenidaTienda(Tienda tienda) {

	}

	private void mostrarOpcionesHospital() {

	}

	private void accederOpcionesHospital(Jugador jugador) {

	}

	private void mostrarOpcionesCuartel() {

	}

	private void accederOpcionesCuartel(Jugador jugador) {

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
		Scanner input = new Scanner(System.in);

		while(!input.hasNextInt()){
			System.err.println("La opción ingresada no es un entero. Intenta nuevamente: ");
			input.next();   //pasa al siguiente iterador
		}
	}

	private int ingresarInt() {
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}

	private boolean opcionEstaDentroDeRango(int opcion, int min, int max) {
		if((opcion < min) || (opcion > max)){
			System.err.println("La opción ingresada no se encuentra dentro del rango. Intenta nuevamente: ");
			return false;
		}else{
			return true;
		}
	}

	private void comprarEnTienda(Tienda tienda) {

	}

	private void ofrecerProductosTienda(Tienda tienda) {

	}

	private void mostrarOpcionesComprarEnTienda() {

	}

	private void elegirOpcionYDesarrollarCompra(Tienda tienda) {

	}

	private void comprarArmaPrimaria(Tienda tienda) {

	}

	private void comprarArmaSecundaria(Tienda tienda) {

	}

	private void comprarJeringa(Tienda tienda) {

	}

	private void comprarCartucho15balas(Tienda tienda) {

	}

	private void mostrarPrecioCurarse(Hospital hospital) {

	}

	private void mostrarPrecioCargarMunicion(Cuartel cuartel) {

	}

	private void realizarCompraCurarse(Hospital hospital) {

	}

	private void realizarCompraCargarMunicion(Cuartel cuartel) {

	}

	private int gestionarCompra(Jugador jugador, int precio) {

	}

	private void confirmarCompra() {

	}

	private void anularCompra() {

	}

	private void mostrarExitoCompra() {
	}

	private void mostrarOpcionesSiNo() {

	}

	private void anunciarInicioPelea(Juego juego) {
		System.out.println("\nInicia la batalla del nivel " + juego.getNivelActual());;
	}

	private void mostrarBalasGastadas(ArmaPrimaria armaPrimaria) {
		System.out.println("Gastaste "+armaPrimaria.getRondaMunicion()+ " balas");
	}

	private void mostrarMunicionActual(Inventario inventario) {
		System.out.println("Tienes "+ inventario.getMunicion()+ " balas");
	}

	private void anunciarMuerteJugador(Juego juego) {
		System.err.println("Moriste, fin de la aventura.");
		juego.getJugador().morir();
	}

	private void anunciarEscapeFinal() {
		System.out.println("\nHas llegado al final de la aventura, felicidades.");
		System.out.println("Ya puedes escapar.");
		System.exit(1);
	}

	private void mostrarVidaActualEnemigo(Enemigo enemigo) {
		System.out.println("La vida actual del enemigo es " +enemigo.getVidaActual());
	}

	private void mostrarVidaActualJugador(Jugador jugador) {
		System.out.println("Tu vida actual es " +jugador.getVidaActual());
	}

	private void mostrarAtaqueDelEnemigo(Juego juego) {
		int probabilidadesAtacar = (int) (Math.random() * 3);

		if (probabilidadesAtacar == 0) {    // un tercio de prob. de que el enemigo falle
			System.out.println("El enemigo ha fallado.");
		} else {
			juego.getEnemigoActual().atacarJugador(juego.getJugador());
			System.out.println("Te han atacado con " + juego.getEnemigoActual().getPuntosDeDaño() + " Puntos de daño.");
			mostrarVidaActualJugador(juego.getJugador());
		}
	}

	private void mostrarAtaqueDelJugador(Juego juego) {
		if (juego.getJugador().getInventario().getMunicion() >= juego.getJugador().getArmaPrimaria().getRondaMunicion()) {
			//si la municion del jugador es mayor o igual que la ronda de municion
			juego.getJugador().getArmaPrimaria().atacarEnemigo(juego.getEnemigoActual(), juego.getJugador().getInventario());
			mostrarBalasGastadas(juego.getJugador().getArmaPrimaria());
			mostrarVidaActualEnemigo(juego.getEnemigoActual());
		} else {
			juego.getJugador().getArmaSecundaria().atacarEnemigo(juego.getEnemigoActual());
			mostrarVidaActualEnemigo(juego.getEnemigoActual());
		}
	}

	private void mostrarFichasActuales() {

	}

	private void mostrarJeringasYCartuchosActuales(Inventario inventario) {
		System.out.println("Tus recursos actuales son: ");
		System.out.println("-" + inventario.getCargadores15Balas() + " cartucho(s) de 15 balas.");
		System.out.println("-" + inventario.getJeringas() + " jeringa(s).");
	}
}