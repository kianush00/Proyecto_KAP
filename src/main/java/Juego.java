public class Juego {

    public static void lanzarJuego(){
        int[] estadisticasJugador = new int[4];   // 0-> vida  1-> ataque  2-> nivelActual  3-> fichas
        int[] inventarioJugador = new int[6];    //
        estadisticasJugador[0] = 100;   //Vida inicial del jugador
        estadisticasJugador[1] = 10;   //Vida inicial del jugador
        estadisticasJugador[2] = 1;     //Nivel inicial del jugador
        estadisticasJugador[3] = 0;     //Fichas iniciales del jugador


        for(int i=1; i <= 30; i++){
            //Se desarrolla el juego

        }
    }

    public static void intentarAbrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        if(estadisticasJugador[2] % 3 == 0){     //Si el nivel actual es múltiplo de tres se abre la tienda
            abrirTienda(estadisticasJugador,inventarioJugador);
        }
    }

    public static void abrirTienda(int[] estadisticasJugador, int[] inventarioJugador){
        String[] tipoTienda = {"Hospital","Cuartel"};    //La tienda puede ser un hospital o un cuartel
        darBienvenidaTienda();

    }

    public static void darBienvenidaTienda(){
        println("\nBienvenido a la tienda. ¿Qué desea?");
        println("1. Comprar");
        println("2. Vender");
        println("3. Salir");
    }

    public static void mostrarFichasActuales(int[] inventarioJugador){
        println("\nFichas actuales: " + inventarioJugador[3]);
    }

    public static void println(String mensaje){
        System.out.println(mensaje);
    }

    public static void print(String mensaje){
        System.out.print(mensaje);
    }
}
