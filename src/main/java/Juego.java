public class Juego {

    public static void lanzarJuego(){

    }
    void pelear(int[] jugador){
        int[] enemigo= new int[2];
        enemigo[0]= (int) Math.floor(Math.random()*70+30);
        enemigo[1]= (int) Math.floor(Math.random()*7+3);
        boolean turno=true;
        while(jugador[0]>0 && enemigo[0]>0){
            if(turno){
                enemigo[0]=enemigo[0]-jugador[1];
                turno=!turno;
            }
            else{
                jugador[0]=jugador[0]-enemigo[1];
                turno=!turno;
            }
        }
        if(jugador[0]<0){
            morir();
        }
    }
    void morir(){
        System.err.println("Te moriste, fin del juego.");
        System.exit(1);
    }
    void escapeFinal(){
        System.out.println("Has llegado al final de la aventura, felicidades.");
        System.out.println("Ya puedes escapar.");
        System.exit(1);
    }
}
