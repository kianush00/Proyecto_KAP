import java.util.Scanner;

public class Juego {

    public static void lanzarJuego(){

    }
    void pelear(int[] estadisticasJugador , int[] inventarioJugador){
        int[] enemigo= new int[2];
        Scanner entrada = new Scanner(System.in);
        enemigo[0]= (int) Math.floor(Math.random()*70+30);
        enemigo[1]= (int) Math.floor(Math.random()*7+3);
        boolean turno=true;

        System.out.println("Inicia la batalla del nivel " + estadisticasJugador [3]);

        while(estadisticasJugador [0]>0 && enemigo[0]>0){
            if(estadisticasJugador [0]<0){
                morir();
            }
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
