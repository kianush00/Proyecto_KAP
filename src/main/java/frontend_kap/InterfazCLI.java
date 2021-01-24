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

	static Scanner input = new Scanner(System.in);

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

	}

	private void desarrollarTienda(Juego juego){
		boolean salir = false;

		juego.generarNuevaTiendaAleatoria();
		darBienvenidaTienda(juego.getTiendaActual());
		while(!salir){
			if(juego.getTiendaActual() instanceof Hospital){
				mostrarOpcionesHospital();
				switch (elegirOpcionYValidar(1,3)){
					case 1:
						desarrollarCompraTienda(juego);
						break;
					case 2:
						desarrollarCompraCurarse(juego);
						break;
					case 3:
						salir = true;
						break;
				}
			}else{
				mostrarOpcionesCuartel();
				switch (elegirOpcionYValidar(1,3)){
					case 1:
						desarrollarCompraTienda(juego);
						break;
					case 2:
						desarrollarCompraCargarMunicion(juego);
						break;
					case 3:
						salir = true;
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

	}

	private void ofrecerProductosTienda(Tienda tienda) {
		throw new UnsupportedOperationException();
	}

	private void mostrarOpcionesComprarEnTienda() {
		throw new UnsupportedOperationException();
	}

	private void comprarArmaPrimaria(Tienda tienda) {
		throw new UnsupportedOperationException();
	}

	private void comprarArmaSecundaria(Tienda tienda) {
		throw new UnsupportedOperationException();
	}

	private void comprarJeringa(Tienda tienda) {
		throw new UnsupportedOperationException();
	}

	private void comprarCartucho15balas(Tienda tienda) {
		throw new UnsupportedOperationException();
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
			mostrarFichasActuales(juego.getJugador().getInventario());
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
			mostrarFichasActuales(juego.getJugador().getInventario());
			mostrarMunicionActual(juego.getJugador().getInventario());
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
		System.out.println("1. Si");
		System.out.println("2. No");
		return elegirOpcionYValidar(1,2);
	}

	private void mostrarExitoCompra() {
		System.out.println("La compra ha sido exitosa.");
	}

	private void anunciarInicioPelea(Juego juego) {
		throw new UnsupportedOperationException();
	}

	private void mostrarBalasGastadas(ArmaPrimaria armaPrimaria) {
		throw new UnsupportedOperationException();
	}

	private void mostrarMunicionActual(Inventario inventario) {
		System.out.println("Munición actual: " + inventario.getMunicion());
	}

	private void anunciarMuerteJugador() {
		throw new UnsupportedOperationException();
	}

	private void mostrarVidaActualEnemigo(Enemigo enemigo) {
		System.out.println("La vida actual del enemigo es: " + enemigo.getVidaActual());
	}

	private void mostrarVidaActualJugador(Jugador jugador) {
		System.out.println("Tu vida actual es: " + jugador.getVidaActual());
	}

	private void mostrarAtaqueDelEnemigo(int dañoCausado) {

	}

	private void mostrarAtaqueDelJugador(int dañoCausado) {

	}

	private void mostrarFichasActuales(Inventario inventario) {
		System.out.println("Fichas actuales: " + inventario.getFichas());
	}

	private void mostrarJeringasYCartuchosActuales(Inventario inventario) {
		throw new UnsupportedOperationException();
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

	public static void darBienvenida() {
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