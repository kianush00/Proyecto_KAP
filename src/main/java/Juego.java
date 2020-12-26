import java.util.Scanner;

public class Juego {

    static Scanner input = new Scanner(System.in);    //se declara variable que guarda el valor introducido

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[7];   // 0-> vida actual  1-> daño ataque principal  2->ronda municion
                              // 3-> daño ataque secundario  4-> nivelActual  5-> vida máxima  6-> munición máxima
        int[] inventarioJugador = new int[3];   //0-> munición actual  1-> jeringas  2-> fichas

        inicializarJugador(estadisticasJugador,inventarioJugador);
        for(int i=1; i <= 30; i++){
            estadisticasJugador[4] = i;     //Nivel actual del jugador
            //Se desarrolla el juego
            //...
            intentarGenerarYAbrirTienda(estadisticasJugador,inventarioJugador);
        }
    }

    public static void inicializarJugador(int[] estadisticasJugador, int[] inventarioJugador){
        //El jugador empezará con las siguientes estadísticas e inventario iniciales:
        estadisticasJugador[0] = 100;   //vida
        estadisticasJugador[1] = 15;    //daño ataque principal
        estadisticasJugador[2] = 1;     //ronda municion
        estadisticasJugador[3] = 7;    //daño ataque secundario
        estadisticasJugador[5] = 100;    //vida máxima
        estadisticasJugador[6] = 50;    //munición máxima
        inventarioJugador[0]=30;    //munición
        inventarioJugador[1]=0;     //jeringas
        inventarioJugador[2]=0;     //fichas
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
        }else{     //se abre el cuartel
            abrirCuartel(estadisticasJugador,inventarioJugador);
        }
    }

    public static void abrirHospital(int[] estadisticasJugador, int[] inventarioJugador){
        boolean salirHospital = false;        //bandera que indica si el jugador desea salir de la tienda

        while(!salirHospital){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesHospital();    //1.-Comprar  2.-Vender  3.-Curarse 4.-Salir
            switch (elegirOpcionYValidar(1,4)){
                case 1:
                    comprarEnHospital(estadisticasJugador,inventarioJugador);
                    break;
                case 2:
                    vender(estadisticasJugador,inventarioJugador);
                    break;
                case 3:
                    curarse(estadisticasJugador,inventarioJugador);
                    break;
                case 4:
                    salirHospital = true;
                    break;
            }
        }
    }

    public static void abrirCuartel(int[] estadisticasJugador, int[] inventarioJugador){
        boolean salirCuartel = false;        //bandera que indica si el jugador desea salir de la tienda

        while(!salirCuartel){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesCuartel();    //1.-Comprar  2.-Vender  3.-Cargar municion 4.-Salir
            switch (elegirOpcionYValidar(1,4)){
                case 1:
                    comprarEnCuartel(estadisticasJugador,inventarioJugador);
                    break;
                case 2:
                    vender(estadisticasJugador,inventarioJugador);
                    break;
                case 3:
                    cargarMunicion(estadisticasJugador,inventarioJugador);
                    break;
                case 4:
                    salirCuartel = true;
                    break;
            }
        }
    }

    //En el hospital las armas son más caras y escasas, pero las jeringas son más baratas
    public static void comprarEnHospital(int[] estadisticasJugador, int[] inventarioJugador){
                                    /*    Armas primarias           Armas secundarias      */
        String[][] armasPosibles = { {"revolver","subfusil"}, {"cuchillo","bate de beisbol"} };
        String[] armasEnVenta = new String[2];   //una sola arma en venta de cada tipo
        int[][] armasEnVentaEstadisticas = new int[2][2];   //filas   0-> arma principal  1-> arma secundaria
                              //columnas  0-> daño   1-> precio   2-> ronda municion (solo para armas principales)
        generarArmasAleatoriasHospital(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        ofrecerArmasEnVentaEstadisticas(armasEnVenta,armasEnVentaEstadisticas);
    }

    //En el cuartel las armas son más baratas y tienen más opciones, pero las jeringas son más caras
    public static void comprarEnCuartel(int[] estadisticasJugador, int[] inventarioJugador){
                                     /*    Armas primarias           Armas secundarias      */
        String[][] armasPosibles = { {"revolver","subfusil","rifle","francotirador"}, {"cuchillo","bate de beisbol","hacha"} };
        String[] armasEnVenta = new String[2];   //una sola arma en venta de cada tipo
        int[][] armasEnVentaEstadisticas = new int[2][2];   //filas   0-> arma principal  1-> arma secundaria
                            //columnas  0-> daño   1-> precio   2-> ronda municion (solo para armas principales)
        generarArmasAleatoriasCuartel(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        ofrecerArmasEnVentaEstadisticas(armasEnVenta,armasEnVentaEstadisticas);
    }

    public static void generarArmasAleatoriasHospital(String[][] armasPosibles, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        generarArmaPrincipalHospital(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        generarArmaSecundariaHospital(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
    }

    public static void generarArmaPrincipalHospital(String[][] armasPosibles, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        switch (getEnteroAleatorioEntre(0,1)){    //se genera un arma principal aleatoria
            case 0:
                armasEnVenta[0] = armasPosibles[0][0];  //revolver
                armasEnVentaEstadisticas[0][0] = 25;   //daño
                armasEnVentaEstadisticas[0][1] = 20;     //precio
                armasEnVentaEstadisticas[0][2] = 1;     //ronda municion
                break;
            case 1:
                armasEnVenta[0] = armasPosibles[0][1];  //subfusil
                armasEnVentaEstadisticas[0][0] = 35;   //daño
                armasEnVentaEstadisticas[0][1] = 25;     //precio
                armasEnVentaEstadisticas[0][2] = 5;     //ronda municion
                break;
        }
    }

    public static void generarArmaSecundariaHospital(String[][] armasPosibles, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        switch (getEnteroAleatorioEntre(0,1)){    //se genera un arma secundaria aleatoria
            case 0:
                armasEnVenta[1] = armasPosibles[1][0];  //cuchillo
                armasEnVentaEstadisticas[1][0] = 11;   //daño
                armasEnVentaEstadisticas[1][1] = 10;     //precio
                break;
            case 1:
                armasEnVenta[1] = armasPosibles[1][1];  //bate de beisbol
                armasEnVentaEstadisticas[1][0] = 13;   //daño
                armasEnVentaEstadisticas[1][1] = 13;     //precio
                break;
        }
    }

    public static void generarArmasAleatoriasCuartel(String[][] armasPosibles, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        generarArmaPrincipalCuartel(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        generarArmaSecundariaCuartel(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
    }

    public static void generarArmaPrincipalCuartel(String[][] armasPosibles, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        switch (getEnteroAleatorioEntre(0,3)){    //se genera un arma principal aleatoria
            case 0:
                armasEnVenta[0] = armasPosibles[0][0];  //revolver
                armasEnVentaEstadisticas[0][0] = 25;   //daño
                armasEnVentaEstadisticas[0][1] = 15;     //precio
                armasEnVentaEstadisticas[0][2] = 1;     //ronda municion
                break;
            case 1:
                armasEnVenta[0] = armasPosibles[0][1];  //subfusil
                armasEnVentaEstadisticas[0][0] = 35;   //daño
                armasEnVentaEstadisticas[0][1] = 20;     //precio
                armasEnVentaEstadisticas[0][2] = 5;     //ronda municion
                break;
            case 2:
                armasEnVenta[0] = armasPosibles[0][2];  //rifle
                armasEnVentaEstadisticas[0][0] = 40;   //daño
                armasEnVentaEstadisticas[0][1] = 25;     //precio
                armasEnVentaEstadisticas[0][2] = 3;     //ronda municion
                break;
            case 3:
                armasEnVenta[0] = armasPosibles[0][3];  //francotirador
                armasEnVentaEstadisticas[0][0] = 60;   //daño
                armasEnVentaEstadisticas[0][1] = 40;     //precio
                armasEnVentaEstadisticas[0][2] = 1;     //ronda municion
                break;
        }
    }

    public static void generarArmaSecundariaCuartel(String[][] armasPosibles, String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        switch (getEnteroAleatorioEntre(0,2)){    //se genera un arma secundaria aleatoria
            case 0:
                armasEnVenta[1] = armasPosibles[1][0];  //cuchillo
                armasEnVentaEstadisticas[1][0] = 11;   //daño
                armasEnVentaEstadisticas[1][1] = 8;     //precio
                break;
            case 1:
                armasEnVenta[1] = armasPosibles[1][1];  //bate de beisbol
                armasEnVentaEstadisticas[1][0] = 13;   //daño
                armasEnVentaEstadisticas[1][1] = 11;     //precio
                break;
            case 2:
                armasEnVenta[1] = armasPosibles[1][2];  //hacha
                armasEnVentaEstadisticas[1][0] = 20;   //daño
                armasEnVentaEstadisticas[1][1] = 15;     //precio
                break;
        }
    }

    public static void ofrecerArmasEnVentaEstadisticas(String[] armasEnVenta, int[][] armasEnVentaEstadisticas){

    }

    public static void vender(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void curarse(int[] estadisticasJugador, int[] inventarioJugador){
        int precio = 15;   //cuantas fichas cuesta curarse

        mostrarFichasActuales(inventarioJugador);
        mostrarPrecioCurarse(precio);
        realizarCompraCurarse(estadisticasJugador,inventarioJugador,precio);
    }

    public static void cargarMunicion(int[] estadisticasJugador, int[] inventarioJugador){
        int precio = 15;   //cuantas fichas cuesta curarse

        mostrarFichasActuales(inventarioJugador);
        mostrarPrecioCargarMunicion(precio);
        realizarCompraCargarMunicion(estadisticasJugador,inventarioJugador,precio);
    }

    public static void realizarCompraCurarse(int[] estadisticasJugador, int[] inventarioJugador, int precio){
        int opcionElegida;   //1. Si  2. No

        if(inventarioJugador[2] > precio){
            confirmarCompra();
            opcionElegida = elegirOpcionYValidar(1,2);
            if(opcionElegida == 1){
                estadisticasJugador[0] = estadisticasJugador[5];    //se llena la vida del jugador
                restarFichas(inventarioJugador,precio);
                mostrarExitoCompra();
            }
        }else{
            anularCompra();
        }
    }

    public static void realizarCompraCargarMunicion(int[] estadisticasJugador, int[] inventarioJugador, int precio){
        int opcionElegida;   //1. Si  2. No

        if(inventarioJugador[2] > precio){
            confirmarCompra();
            opcionElegida = elegirOpcionYValidar(1,2);
            if(opcionElegida == 1){
                inventarioJugador[0] = estadisticasJugador[6];   //se llena la munición del jugador
                restarFichas(inventarioJugador,precio);
                mostrarExitoCompra();
            }
        }else{
            anularCompra();
        }
    }

    public static void mostrarFichasActuales(int[] inventarioJugador){
        println("\nFichas actuales: " + inventarioJugador[3]);
    }

    public static void restarFichas(int[] inventarioJugador, int precio){
        inventarioJugador[2] -= precio;
    }

    public static void confirmarCompra(){
        println("¿Estás seguro de realizar la compra?");
        mostrarOpcionesSiNo();
    }

    public static void anularCompra(){
        println("No quedan fichas disponibles. Inténtalo más tarde.");
    }

    public static void mostrarExitoCompra(){
        println("La compra ha sido exitosa.");
    }

    public static void mostrarOpcionesSiNo(){
        println("Elige una opción: ");
        println("1. Si");
        println("2. No");
    }

    public static void mostrarPrecioCurarse(int precio){
        println("Precio para restablecer vida por completo: " + precio + " fichas.");
    }

    public static void mostrarPrecioCargarMunicion(int precio){
        println("Precio para llenar de munición el inventario: " + precio + " fichas.");
    }

    public static void darBienvenidaTienda(String tiendaActual){
        println("\nBienvenido al " + tiendaActual + ". ¿Qué desea?");
    }

    public static String generarTiendaActual(String[] tipoTienda){
        return tipoTienda[(int) (Math.random()*2)];
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

    public static int ingresarEntero(){
        return input.nextInt();
    }

    public static int getEnteroAleatorioEntre(int min, int max){
        return (int) (Math.random() * ((max + 1) - min)) + min;
    }

    public static void println(String mensaje){
        System.out.println(mensaje);
    }

    public static void print(String mensaje){
        System.out.print(mensaje);
    }
}