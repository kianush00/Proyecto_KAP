import java.util.Scanner;

public class Juego {

    static Scanner input = new Scanner(System.in);    //se declara variable que guarda el valor introducido

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[4];   // 0-> vida  1-> ataque  2-> nivelActual  3-> fichas
        int[] inventarioJugador = new int[6];    //
        estadisticasJugador[0] = 100;   //Vida inicial del jugador
        estadisticasJugador[1] = 10;   //Vida inicial del jugador
        estadisticasJugador[2] = 1;     //Nivel inicial del jugador
        estadisticasJugador[3] = 0;     //Fichas iniciales del jugador


        for(int i=1; i <= 30; i++){
            //Se desarrolla el juego
            //...
            intentarAbrirTienda(estadisticasJugador,inventarioJugador);
        }
    }

    public static void intentarAbrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        if(estadisticasJugador[2] % 3 == 0){     //Si el nivel actual es múltiplo de tres se abre la tienda
            abrirTienda(estadisticasJugador,inventarioJugador);
        }
    }

    public static void abrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        String[] tipoTienda = {"hospital","cuartel"};       //La tienda puede ser un hospital o un cuartel
        boolean salirTienda = false;        //bandera que indica si el jugador desea salir de la tienda
        int opcion;        //la opción que se elige dentro de las que correspodnan
        String tiendaActual = generarTiendaActual(tipoTienda);   //Se genera la tienda de forma aleatoria

        darBienvenidaTienda(tiendaActual);
        while(!salirTienda){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesTienda();    //1.-Comprar  2.-Vender  3.-Salir
            switch (elegirOpcionYValidar(1,3)){
                case 1:
                    opcionUnoTienda(estadisticasJugador,inventarioJugador);
                break;
                case 2:
                    opcionDosTienda(estadisticasJugador,inventarioJugador);
                break;
                case 3:
                    salirTienda = true;
                break;
            }
        }
    }

    public static void opcionUnoTienda(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void opcionDosTienda(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static String generarTiendaActual(String[] tipoTienda){
        return tipoTienda[(int) (Math.random()*2)];
    }

    public static void darBienvenidaTienda(String tiendaActual){
        println("\nBienvenido al " + tiendaActual + ". ¿Qué desea?");
    }

    public static void mostrarOpcionesTienda(){
        println("Elige una opción: ");
        println("1. Comprar");
        println("2. Vender");
        println("3. Salir");
    }

    public static int elegirOpcionYValidar(int minimaOpcion, int maximaOpcion){
        int opcion = -1;    //se inicializa la opción
        boolean opcionEsValida = false;

        while(!opcionEsValida){
            validarOpcionEntera();
            opcion = ingresarEntero();
            opcionEsValida = opcionEstaDentroDelRango(opcion,minimaOpcion,maximaOpcion);
        }

        return opcion;
    }

    public static void validarOpcionEntera(){
        while(!input.hasNextInt()){
            System.err.println("La opción ingresada no es un entero. Intenta nuevamente:");
            input.next();   //pasa al siguiente iterador
        }
    }

    public static boolean opcionEstaDentroDelRango(int opcion, int minimaOpcion, int maximaOpcion){
        if((opcion < minimaOpcion) || (opcion > maximaOpcion)){
            System.err.println("La opción ingresada no se encuentra dentro del rango. Intenta nuevamente:");
            return false;
        }else{
            return true;
        }
    }

    public static void mostrarFichasActuales(int[] inventarioJugador){
        println("\nFichas actuales: " + inventarioJugador[3]);
    }

    public static int ingresarEntero(){
        return input.nextInt();
    }

    public static void println(String mensaje){
        System.out.println(mensaje);
    }

    public static void print(String mensaje){
        System.out.print(mensaje);
    }
}
