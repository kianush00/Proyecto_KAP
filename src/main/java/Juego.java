import java.util.Scanner;

public class Juego {

    static Scanner input = new Scanner(System.in);    //se declara variable que guarda el valor introducido

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[5];   // 0-> vida  1-> ataque  2-> nivelActual  3-> fichas
        int[] inventarioJugador = new int[6];
        inventarioJugador[0]=100;   //
        estadisticasJugador[0] = 100;   //Vida inicial del jugador
        estadisticasJugador[1] = 10;   //Vida inicial del jugador
        estadisticasJugador[3] = 0;     //Fichas iniciales del jugador
        estadisticasJugador[4] = 5;
        for(int i=1; i <= 30; i++){
            estadisticasJugador[2] = i;     //Nivel actual del jugador
            //Se desarrolla el juego
            //...
            intentarGenerarYAbrirTienda(estadisticasJugador,inventarioJugador);
        }
    }

    public static void intentarGenerarYAbrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        if(estadisticasJugador[2] % 3 == 0){     //Si el nivel actual es múltiplo de tres se abre la tienda
            generarYAbrirTienda(estadisticasJugador,inventarioJugador);
        }
    }

    public static void generarYAbrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        String[] tipoTienda = {"hospital","cuartel"};       //La tienda puede ser un hospital o un cuartel
        String tiendaActual = generarTiendaActual(tipoTienda);   //Se genera la tienda de forma aleatoria

        darBienvenidaTienda(tiendaActual);
        if(tiendaActual.equals(tipoTienda[0])){     //se abre el hospital
            abrirHospital(estadisticasJugador,inventarioJugador);
        }
        if(tiendaActual.equals(tipoTienda[1])){     //se abre el cuartel
            abrirCuartel(estadisticasJugador,inventarioJugador);
        }
    }

    public static void abrirHospital(int[] estadisticasJugador, int[] inventarioJugador){
        boolean salirTienda = false;        //bandera que indica si el jugador desea salir de la tienda

        while(!salirTienda){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesHospital();    //1.-Comprar  2.-Vender  3.-Curarse 4.-Salir
            switch (elegirOpcionYValidar(1,4)){
                case 1:
                    comprarEnHospital(estadisticasJugador,inventarioJugador);
                    break;
                case 2:
                    vender(estadisticasJugador,inventarioJugador);
                    break;
                case 3:
                    curarse(estadisticasJugador);
                    break;
                case 4:
                    salirTienda = true;
                    break;
            }
        }
    }

    public static void abrirCuartel(int[] estadisticasJugador, int[] inventarioJugador){
        boolean salirTienda = false;        //bandera que indica si el jugador desea salir de la tienda

        while(!salirTienda){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesCuartel();    //1.-Comprar  2.-Vender  3.-Cargar municion 4.-Salir
            switch (elegirOpcionYValidar(1,4)){
                case 1:
                    comprarEnCuartel(estadisticasJugador,inventarioJugador);
                    break;
                case 2:
                    vender(estadisticasJugador,inventarioJugador);
                    break;
                case 3:
                    cargarMunicion(estadisticasJugador);
                    break;
                case 4:
                    salirTienda = true;
                    break;
            }
        }
    }

    public static void comprarEnHospital(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void comprarEnCuartel(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void vender(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void curarse(int[] estadisticasJugador){

    }

    public static void cargarMunicion(int[] estadisticasJugador){

    }

    public static String generarTiendaActual(String[] tipoTienda){
        return tipoTienda[(int) (Math.random()*2)];
    }

    public static void darBienvenidaTienda(String tiendaActual){
        println("\nBienvenido al " + tiendaActual + ". ¿Qué desea?");
    }

    public static void mostrarOpcionesHospital(){
        println("Elige una opción: ");
        println("1. Comprar");
        println("2. Vender");
        println("3. Curarse");
        println("4. Salir");
    }

    public static void mostrarOpcionesCuartel(){
        println("Elige una opción: ");
        println("1. Comprar");
        println("2. Vender");
        println("3. Cargar munición");
        println("4. Salir");
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
