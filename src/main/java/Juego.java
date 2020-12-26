import java.util.Scanner;

public class Juego {

    static Scanner input = new Scanner(System.in);    //se declara variable que guarda el valor introducido

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[8];   // 0-> vida actual  1-> daño ataque principal  2->ronda municion
        // 3-> daño ataque secundario  4-> nivel actual  5-> municion actual
        // 6-> vida máxima  7-> munición máxima
        int[] inventarioJugador = new int[3];   //0-> cartuchos de 15 balas  1-> jeringas  2-> fichas

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
        estadisticasJugador[1] = 15;    //daño ataque principal   (empezará con una pistola 9mm)
        estadisticasJugador[2] = 1;     //ronda municion    (empezará con una pistola 9mm)
        estadisticasJugador[3] = 7;    //daño ataque secundario     (empezará usando los puños)
        estadisticasJugador[5] = 30;    //munición inicial
        estadisticasJugador[6] = 100;    //vida máxima
        estadisticasJugador[7] = 50;    //munición máxima
        inventarioJugador[0]=0;    //cartuchos de 15 balas
        inventarioJugador[1]=0;     //jeringas
        inventarioJugador[2]=0;     //fichas
    }

    public static void intentarGenerarYAbrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        if(estadisticasJugador[4] % 3 == 0){     //Si el nivel actual es múltiplo de tres se abre la tienda
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
        boolean[] opcionesHospital = {false,false,false};
        // posiciones 0-> se salio del hospital  1-> se intentó comprar  2-> se intentó curar

        while(!opcionesHospital[0]){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesHospital();    //1.-Comprar   2.-Curarse   3.-Salir
            accederOpcionesHospital(estadisticasJugador,inventarioJugador,opcionesHospital);
        }
    }

    public static void abrirCuartel(int[] estadisticasJugador, int[] inventarioJugador){
        boolean[] opcionesCuartel = {false,false,false};
        // posiciones 0-> se salio del cuartel  1-> se intentó comprar  2-> se intentó cargar munición

        while(!opcionesCuartel[0]){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesCuartel();    //1.-Comprar   2.-Cargar municion   3.-Salir
            accederOpcionesCuartel(estadisticasJugador,inventarioJugador,opcionesCuartel);
        }
    }


    public static void accederOpcionesHospital(int[] estadisticasJugador, int[] inventarioJugador, boolean[] opcionesHospital){
        switch (elegirOpcionYValidar(1,3)){
            case 1:
                if(!opcionesHospital[1]){   //si no ha comprado
                    comprarEnHospital(estadisticasJugador,inventarioJugador);
                    opcionesHospital[1] = true;     //se indica que se compró
                }else{
                    opcionAgotada();
                }
                break;
            case 2:
                if(!opcionesHospital[2]){   //si no se ha curado
                    curarse(estadisticasJugador,inventarioJugador);
                    opcionesHospital[2] = true;     //se indica que se curó
                }else{
                    opcionAgotada();
                }
                break;
            case 3:
                opcionesHospital[0] = true;     //se indica que se quiere salir del hospital
                break;
        }
    }

    public static void accederOpcionesCuartel(int[] estadisticasJugador, int[] inventarioJugador, boolean[] opcionesCuartel){
        switch (elegirOpcionYValidar(1,3)){
            case 1:
                if(!opcionesCuartel[1]){   //si no ha comprado
                    comprarEnCuartel(estadisticasJugador,inventarioJugador);
                    opcionesCuartel[1] = true;     //se indica que se compró
                }else{
                    opcionAgotada();
                }
                break;
            case 2:
                if(!opcionesCuartel[2]){   //si no se ha curado
                    cargarMunicion(estadisticasJugador,inventarioJugador);
                    opcionesCuartel[2] = true;     //se indica que se cargó munición
                }else{
                    opcionAgotada();
                }
                break;
            case 3:
                opcionesCuartel[0] = true;     //se indica que se quiere salir del cuartel
                break;
        }
    }

    //En el hospital la munición y armas son más caras y escasas, pero las jeringas son más baratas
    public static void comprarEnHospital(int[] estadisticasJugador, int[] inventarioJugador){
        /*    Armas primarias           Armas secundarias      */
        String[][] armasPosibles = { {"revolver","subfusil"}, {"cuchillo","bate de beisbol"} };
        String[] armasEnVenta = new String[2];   //una sola arma en venta de cada tipo
        int[][] armasEnVentaEstadisticas = new int[3][3];   //filas   0-> arma principal  1-> arma secundaria
        //columnas  0-> daño   1-> precio   2-> ronda municion (solo para armas principales)
        int[] preciosUtilidades = {8,20};   // 0-> precio jeringa   1-> precio cartucho de 15 balas

        generarArmasAleatoriasHospital(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        mostrarDatosTienda(inventarioJugador,armasEnVenta,armasEnVentaEstadisticas,preciosUtilidades);
        elegirOpcionYDesarrollarCompra(estadisticasJugador,inventarioJugador,armasEnVentaEstadisticas,preciosUtilidades);
    }

    //En el cuartel la munición y armas son más baratas y tienen más opciones, pero las jeringas son más caras
    public static void comprarEnCuartel(int[] estadisticasJugador, int[] inventarioJugador){
        /*    Armas primarias           Armas secundarias      */
        String[][] armasPosibles = { {"revolver","subfusil","rifle","francotirador"}, {"cuchillo","bate de beisbol","hacha"} };
        String[] armasEnVenta = new String[2];   //una sola arma en venta de cada tipo
        int[][] armasEnVentaEstadisticas = new int[3][3];   //filas   0-> arma principal  1-> arma secundaria
        //columnas  0-> daño   1-> precio   2-> ronda municion (solo para armas principales)
        int[] preciosUtilidades = {20,12};   // 0-> precio jeringa   1-> precio cartucho de 15 balas

        generarArmasAleatoriasCuartel(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        mostrarDatosTienda(inventarioJugador,armasEnVenta,armasEnVentaEstadisticas,preciosUtilidades);
        elegirOpcionYDesarrollarCompra(estadisticasJugador,inventarioJugador,armasEnVentaEstadisticas,preciosUtilidades);
    }

    public static void elegirOpcionYDesarrollarCompra(int[] estadisticasJugador, int[] inventarioJugador, int[][] armasEnVentaEstadisticas, int[] preciosUtilidades){
        int precio;     //precio de la opcion seleccionada
        int opcionTienda;   //opcion seleccionada al momento de comprar

        do{
            mostrarOpcionesComprarTienda();
            opcionTienda = elegirOpcionYValidar(1,5);
            switch (opcionTienda){
                case 1:     //comprar arma principal
                    precio = armasEnVentaEstadisticas[0][1];
                    realizarCompraArmaPrimaria(estadisticasJugador,inventarioJugador,precio,armasEnVentaEstadisticas);
                    break;
                case 2:     //comprar arma secundaria
                    precio = armasEnVentaEstadisticas[1][1];
                    realizarCompraArmaSecundaria(estadisticasJugador,inventarioJugador,precio,armasEnVentaEstadisticas);
                    break;
                case 3:     //comprar jeringa
                    precio = preciosUtilidades[0];
                    realizarCompraJeringa(inventarioJugador,precio);
                    break;
                case 4:     //comprar cartucho de 15 balas
                    precio = preciosUtilidades[1];
                    realizarCompraCartucho15Balas(inventarioJugador,precio);
                    break;
                case 5:     //salir (no hacer nada)
                    break;
            }
        }while(opcionTienda != 5);
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

    public static void mostrarDatosTienda(int[] inventarioJugador, String[] armasEnVenta, int[][] armasEnVentaEstadisticas, int[] preciosUtilidades){
        ofrecerArmasEnVenta(armasEnVenta,armasEnVentaEstadisticas);
        ofrecerJeringasYMunicion(preciosUtilidades);
        mostrarFichasActuales(inventarioJugador);
    }

    public static void mostrarOpcionesComprarTienda(){
        println("\nElige una opción: ");
        println("1. Comprar arma primaria");
        println("2. Cargar arma secundaria");
        println("3. Comprar jeringa");
        println("4. Comprar cartucho de 15 balas");
        println("5. Salir");
    }

    public static void ofrecerArmasEnVenta(String[] armasEnVenta, int[][] armasEnVentaEstadisticas){
        println("Arma primaria disponible: " + armasEnVenta[0]);
        println("-Daño: " + armasEnVentaEstadisticas[0][0]);
        println("-Ronda de munición: " + armasEnVentaEstadisticas[0][2]);
        println("-Precio: " + armasEnVentaEstadisticas[0][1] + " fichas");
        println("\nArma secundaria disponible: " + armasEnVenta[1]);
        println("-Daño: " + armasEnVentaEstadisticas[1][0]);
        println("-Precio: " + armasEnVentaEstadisticas[1][1] + " fichas\n");
    }

    public static void ofrecerJeringasYMunicion(int[] preciosUtilidades){
        println("Precio por cada jeringa: " + preciosUtilidades[0] + " fichas");
        println("Precio por cada cartucho de 15 balas: " + preciosUtilidades[1] + " fichas\n");
    }

    public static void curarse(int[] estadisticasJugador, int[] inventarioJugador){
        int precio = 15;   //cuantas fichas cuesta curarse

        mostrarPrecioCurarse(precio);
        mostrarFichasActuales(inventarioJugador);
        realizarCompraCurarse(estadisticasJugador,inventarioJugador,precio);
    }

    public static void cargarMunicion(int[] estadisticasJugador, int[] inventarioJugador){
        int precio = 15;   //cuantas fichas cuesta curarse

        mostrarPrecioCargarMunicion(precio);
        mostrarFichasActuales(inventarioJugador);
        realizarCompraCargarMunicion(estadisticasJugador,inventarioJugador,precio);
    }

    public static void realizarCompraCurarse(int[] estadisticasJugador, int[] inventarioJugador, int precio){
        int opcionElegida = gestionarCompra(inventarioJugador,precio);    // 1. Si  2. No

        if(opcionElegida == 1){
            estadisticasJugador[0] = estadisticasJugador[6];    //se llena la vida del jugador
            restarFichas(inventarioJugador,precio);
            mostrarExitoCompra();
        }
    }

    public static void realizarCompraCargarMunicion(int[] estadisticasJugador, int[] inventarioJugador, int precio){
        int opcionElegida = gestionarCompra(inventarioJugador,precio);    // 1. Si  2. No

        if(opcionElegida == 1){
            estadisticasJugador[5] = estadisticasJugador[7];   //se llena la munición del jugador
            restarFichas(inventarioJugador,precio);
            mostrarExitoCompra();
        }
    }

    public static void realizarCompraArmaPrimaria(int[] estadisticasJugador, int[] inventarioJugador, int precio, int[][] armasEnVentaEstadisticas){
        int opcionElegida = gestionarCompra(inventarioJugador,precio);    // 1. Si  2. No

        if(opcionElegida == 1){
            estadisticasJugador[1] = armasEnVentaEstadisticas[0][0];    //se cambia el valor de daño principal
            estadisticasJugador[2] = armasEnVentaEstadisticas[0][2];    //se cambia el valor de ronda de municion
            restarFichas(inventarioJugador,precio);
            mostrarExitoCompra();
        }
    }

    public static void realizarCompraArmaSecundaria(int[] estadisticasJugador, int[] inventarioJugador, int precio, int[][] armasEnVentaEstadisticas){
        int opcionElegida = gestionarCompra(inventarioJugador,precio);    // 1. Si  2. No

        if(opcionElegida == 1){
            estadisticasJugador[3] = armasEnVentaEstadisticas[1][0];    //se cambia el valor de daño principal
            restarFichas(inventarioJugador,precio);
            mostrarExitoCompra();
        }
    }

    public static void realizarCompraJeringa(int[] inventarioJugador, int precio){
        int opcionElegida = gestionarCompra(inventarioJugador,precio);    // 1. Si  2. No

        if(opcionElegida == 1){
            inventarioJugador[1]++;    //se agrega la jeringa al inventario
            restarFichas(inventarioJugador,precio);
            mostrarExitoCompra();
        }
    }

    public static void realizarCompraCartucho15Balas(int[] inventarioJugador, int precio){
        int opcionElegida = gestionarCompra(inventarioJugador,precio);    // 1. Si  2. No

        if(opcionElegida == 1){
            inventarioJugador[0]++;    //se agrega el cartucho al inventario
            restarFichas(inventarioJugador,precio);
            mostrarExitoCompra();
        }
    }

    public static int gestionarCompra(int[] inventarioJugador, int precio){
        if(inventarioJugador[2] > precio) {     //solo se puede comprar si se dispone de las fichas
            confirmarCompra();
            return elegirOpcionYValidar(1,2);
        }else{
            anularCompra();
            return -1;
        }
    }

    public static void mostrarFichasActuales(int[] inventarioJugador){
        println("Fichas actuales: " + inventarioJugador[2]);
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
        println("2. Curarse");
        println("3. Salir");
    }

    public static void mostrarOpcionesCuartel(){
        println("Elige una opción: ");
        println("1. Comprar");
        println("2. Cargar munición");
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

    public static void opcionAgotada(){
        println("La opción ya ha sido seleccionada una vez. Inténtalo más tarde.");
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

    public static void intentarPelear(int[] estadisticasJugador, int[] inventarioJugador) {
        if (estadisticasJugador[4] % 3 != 0) {     //Si el nivel actual no es multiplo de 3 inicia la pelea
            pelear(estadisticasJugador, inventarioJugador);
        }
    }

    public static void pelear(int[] estadisticasJugador, int[] inventarioJugador) {
        int[] enemigo = new int[2];
        Scanner entrada = new Scanner(System.in);
        enemigo[0] = (int) (Math.floor(Math.random() * 70 + 30) + (estadisticasJugador[4] / 2));
        System.out.println(enemigo[0]);
        enemigo[1] = (int) Math.floor((Math.random() * 7 + 3) + (estadisticasJugador[4] / 2));
        System.out.println(enemigo[1]);
        boolean turno = true;

        System.out.println("Inicia la batalla del nivel " + estadisticasJugador[4]);
        boolean huir = false;
        while (estadisticasJugador[0] > 0 && enemigo[0] > 0 && huir == false) {

            if (turno) {
                System.out.print("Opcion 1 para atacar, 2 para pasar, 3 para huir, finalmente 4 para curarte: ");

                int opcion = elegirOpcionYValidar(1, 4);
                switch (opcion) {
                    case 1:
                        if (inventarioJugador[5] > 0) {
                            enemigo[0] = enemigo[0] - estadisticasJugador[1];
                            inventarioJugador[0] = inventarioJugador[0] - estadisticasJugador[2];
                            System.out.println("Has atacado al enemigo con " + estadisticasJugador[1] + " Puntos de daño, su vida actual es " + enemigo[0]);
                        } else {
                            System.out.println("Solo tienes tus puños, suerte.");
                            enemigo[0] = enemigo[0] - estadisticasJugador[3];
                            System.out.println("Has atacado al enemigo con " + estadisticasJugador[3] + " Puntos de daño, su vida actual es " + enemigo[0]);
                        }
                        break;
                    case 2:
                        System.out.println("Has pasado");
                        break;
                    case 3:
                        int escapar = (int) (Math.random() * 2);
                        if (escapar == 1) {
                            huir = true;
                        }
                        break;
                    case 4:
                        curar(estadisticasJugador, inventarioJugador);
                        break;
                }
            } else {
                int opcion = (int) (Math.random() * 2);
                System.out.println(opcion);
                if (opcion == 0) {
                    System.out.println("El enemigo ha fallado.");
                } else {
                    estadisticasJugador[0] = estadisticasJugador[0] - enemigo[1];
                    System.out.println("Te han atacado con " + enemigo[1] + " Puntos de daño, tu vida actual es " + estadisticasJugador[0]);
                }


            }
            turno = !turno;
        }
        if (estadisticasJugador[0] < 0) {
            morir();
        } else {
            if (huir == true) {
                System.out.println("Huiste de la batalla");
            } else {
                System.out.println("Ganaste la batalla");
            }
        }
    }

    public static void morir() {
        System.err.println("Te moriste, fin de la aventura.");
        System.exit(1);
    }

    public static void escapeFinal() {
        System.out.println("Has llegado al final de la aventura, felicidades.");
        System.out.println("Ya puedes escapar.");
        System.exit(1);
    }

    public static void bienvenida() {
        System.out.println("Felicidades sobreviviente, bienvenido al apocalipsis.");
        System.out.println("Tu mision es sobrevivir, usa bien tu inventario y municiones, son escasas.");
        System.out.println("Mucha suerte.");
    }

    public static void curar(int[] estadisticasJugador, int[] inventarioJugador) {
        if (inventarioJugador[0] > 0) {
            estadisticasJugador[0] = estadisticasJugador[0] + 50;
            inventarioJugador[1] = inventarioJugador[1] - 1;
        }
        System.out.println("Ahora tu vida actual es " + estadisticasJugador[0]);
    }

    public static void obtenerDinero(int[] inventarioJugador, int[] enemigo) {
        int dinero=enemigo[0]/3+enemigo[1]/2;
        inventarioJugador[2] = inventarioJugador[2] + dinero;
        System.out.println("Has ganado " + dinero + " monedas");
    }
}
