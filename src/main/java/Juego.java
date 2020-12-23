import java.util.Scanner;

public class Juego {

    static Scanner input = new Scanner(System.in);    //se declara variable que guarda el valor introducido

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[4];   // 0-> vida  1-> ataque  2-> nivelActual  3-> fichas
        int[] inventarioJugador = new int[6];    //
        estadisticasJugador[0] = 100;   //Vida inicial del jugador
        estadisticasJugador[1] = 10;   //Vida inicial del jugador
        estadisticasJugador[3] = 0;     //Fichas iniciales del jugador

        for(int i=1; i <= 30; i++){
            estadisticasJugador[2] = i;     //Nivel actual del jugador
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
        String tiendaActual = generarTiendaActual(tipoTienda);   //Se genera la tienda de forma aleatoria

        darBienvenidaTienda(tiendaActual);
        while(!salirTienda){    //se ejecuta bucle mientras el jugador no desee salir de la tienda
            mostrarOpcionesTienda(tiendaActual,tipoTienda);    //1.-Comprar  2.-Vender  3.-Salir
            switch (elegirOpcionYValidar(1,4)){
                case 1:
                    comprar(estadisticasJugador,inventarioJugador);
                    break;
                case 2:
                    vender(estadisticasJugador,inventarioJugador);
                    break;
                case 3:
                    if(tiendaActual.equals(tipoTienda[0])){   //si la tienda es un hospital
                        curarase(estadisticasJugador);
                    }
                    if(tiendaActual.equals(tipoTienda[1])){     //si la tienda es un cuartel
                        cargarMunicion(estadisticasJugador);
                    }
                    break;
                case 4:
                    salirTienda = true;
                    break;
            }
        }
    }

    public static void comprar(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void vender(int[] estadisticasJugador, int[] inventarioJugador){

    }

    public static void curarase(int[] estadisticasJugador){

    }

    public static void cargarMunicion(int[] estadisticasJugador){

    }

    public static String generarTiendaActual(String[] tipoTienda){
        return tipoTienda[(int) (Math.random()*2)];
    }

    public static void darBienvenidaTienda(String tiendaActual){
        println("\nBienvenido al " + tiendaActual + ". ¿Qué desea?");
    }

    public static void mostrarOpcionesTienda(String tiendaActual, String[] tipoTienda){
        println("Elige una opción: ");
        println("1. Comprar");
        println("2. Vender");
        if(tiendaActual.equals(tipoTienda[0])){   //si la tienda es un hospital
            println("3. Curarse");
        }
        if(tiendaActual.equals(tipoTienda[1])){     //si la tienda es un cuartel
            println("3. Cargar munición");
        }
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

    void pelear(int[] estadisticasJugador , int[] inventarioJugador){
        int[] enemigo= new int[2];
        Scanner entrada = new Scanner(System.in);
        enemigo[0]= (int) Math.floor(Math.random()*70+30);
        enemigo[1]= (int) Math.floor(Math.random()*7+3);
        boolean turno=true;

        System.out.println("Inicia la batalla del nivel " + estadisticasJugador [2]);

        while(estadisticasJugador [0]>0 && enemigo[0]>0){

            if(turno){
                System.out.print("Opcion 1 para atacar, 2 para pasar, 3 para huir, finalmente 4 para curarte: ");
                while (!entrada.hasNextInt()) {
                    System.err.println("Debe ser un numero");
                    System.out.print("Intentalo de nuevo: ");
                    entrada.next();
                }
                int opcion = entrada.nextInt();
                switch(opcion) {
                    case 1:
                        if(inventarioJugador[0]>0) {
                            enemigo[0] = enemigo[0] - estadisticasJugador[1];
                            inventarioJugador[0] = inventarioJugador[0] - estadisticasJugador[4];
                            System.out.println("Has atacado al enemigo con " + estadisticasJugador[1] + "Puntos de daño, su vida actual es" + enemigo[0]);
                        }
                        else {
                            System.err.println("no te quedan balas, no puedes atacar");
                        }
                        break;
                    case 2:
                        System.out.println("Has pasado");
                        break;
                    case 3:
                        int escapar=(int) Math.floor(Math.random()*2+1);
                        if(escapar==1){
                            break;
                        }
                        break;
                    case 4:
                        curar(estadisticasJugador [0]);
                        break;
                }
            }

            else{
                int opcion=(int) Math.floor(Math.random()*1);
                if(opcion==0){
                    System.out.println("El enemigo ha fallado.");
                }
                else
                    estadisticasJugador [0]=estadisticasJugador [0]-enemigo[1];
                System.out.println("Te han atacado con "+enemigo[1] + "Puntos de daño, tu vida actual es"+ estadisticasJugador [0]);
            }
            turno = !turno;

        }
        if(estadisticasJugador [0]<0){
            morir();
        }
        else{
            System.out.println("Ganaste la batalla");
        }
    }
    void morir(){
        System.err.println("Te moriste, fin de la aventura.");
        System.exit(1);
    }
    void escapeFinal(){
        System.out.println("Has llegado al final de la aventura, felicidades.");
        System.out.println("Ya puedes escapar.");
        System.exit(1);
    }
    void curar(int vida){
        vida=vida+50;
        System.out.println("Ahora tu vida actual es " + vida);
    }
}
