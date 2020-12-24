import java.util.Scanner;

public class Juego {

    static Scanner input = new Scanner(System.in);    //se declara variable que guarda el valor introducido

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[6];   // 0-> vida  1-> daño ataque principal  2->ronda municion
                                                // 3-> daño ataque secundario  4-> nivelActual
        int[] inventarioJugador = new int[6];   //0-> munición  1-> jeringas  2-> fichas

        inventarioJugador[0]=100;
        estadisticasJugador[0] = 100;
        estadisticasJugador[1] = 10;
        estadisticasJugador[3] = 0;
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
        String[][] armas = { {"pistola","metralleta","rifle","francotirador"}, {"cuchillo","bate de beisbol","hacha"} };
        //armas filas: 0-> armas primarias  1-> armas secundarias
        String[] armasEnVenta = new String[2];
        int[][] armasEnVentaEstadisticas = new int[2][2];   //filas   0-> arma principal  1-> arma secundaria
                              //columnas  0-> daño  1-> ronda municion  2-> precio
        generarArmasAleatoriasHospital(armas,armasEnVenta,armasEnVentaEstadisticas);


    }

    public static void generarArmasAleatoriasHospital(String[][] armas, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        switch (getEnteroAleatorioEntre(0,3)){    //se genera un arma principal aleatoria
            case 0:
                armasEnVenta[0] = armas[0][0];
                armasEnVentaEstadisticas[0][0] = 10;   //daño
                armasEnVentaEstadisticas[0][1] = 1;     //ronda municion
                armasEnVentaEstadisticas[0][2] = 30;     //precio
            break;
            case 1:
                armasEnVenta[0] = armas[0][1];
                armasEnVentaEstadisticas[0][0] = 15;   //daño
                armasEnVentaEstadisticas[0][1] = 5;     //ronda municion
                armasEnVentaEstadisticas[0][2] = 60;     //precio
            break;
            case 2:
                armasEnVenta[0] = armas[0][2];
                armasEnVentaEstadisticas[0][0] = 20;   //daño
                armasEnVentaEstadisticas[0][1] = 5;     //ronda municion
                armasEnVentaEstadisticas[0][2] = 90;     //precio
            break;
            case 3:
                armasEnVenta[0] = armas[0][3];
                armasEnVentaEstadisticas[0][0] = 30;   //daño
                armasEnVentaEstadisticas[0][1] = 1;     //ronda municion
                armasEnVentaEstadisticas[0][2] = 150;     //precio
            break;
        }

        switch (getEnteroAleatorioEntre(0,2)){    //se genera un arma secundaria aleatoria
            case 0:
                armasEnVenta[1] = armas[1][0];
                armasEnVentaEstadisticas[1][0] = 8;   //daño
                armasEnVentaEstadisticas[1][1] = 0;     //ronda municion
                armasEnVentaEstadisticas[1][2] = 30;     //precio
                break;
            case 1:
                armasEnVenta[1] = armas[1][1];
                armasEnVentaEstadisticas[1][0] = 15;   //daño
                armasEnVentaEstadisticas[1][1] = 0;     //ronda municion
                armasEnVentaEstadisticas[1][2] = 60;     //precio
                break;
            case 2:
                armasEnVenta[1] = armas[1][2];
                armasEnVentaEstadisticas[1][0] = 20;   //daño
                armasEnVentaEstadisticas[1][1] = 0;     //ronda municion
                armasEnVentaEstadisticas[1][2] = 90;     //precio
                break;
        }
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

    public static int getEnteroAleatorioEntre(int min, int max){
        return (int) (Math.random() * ((max + 1) - min)) + min;
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