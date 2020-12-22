import java.util.Scanner;

public class Juego {

    public static void lanzarJuego(){

    }
    void pelear(int[] jugador){
        int[] enemigo= new int[2];
        Scanner entrada = new Scanner(System.in);
        enemigo[0]= (int) Math.floor(Math.random()*70+30);
        enemigo[1]= (int) Math.floor(Math.random()*7+3);
        boolean turno=true;
        while(jugador[0]>0 && enemigo[0]>0){
            System.out.println("Inicia la batalla del nivel " + jugador[3]);
            if(turno){
                System.out.print("Opcion 1 para atacar, 2 para pasar, 3 para huir: ");
                while (!entrada.hasNextInt()) {
                    System.err.println("Debe ser un numero");
                    System.out.print("Intentalo de nuevo: ");
                    entrada.next();
                }
                int opcion = entrada.nextInt();
                if(opcion==1) {
                    enemigo[0] = enemigo[0] - jugador[1];
                    System.out.println("Has atacado al enemigo con " + jugador[1] + "Puntos de daño, su vida actual es" + enemigo[0]);
                }
                if(opcion==2){
                    System.out.println("Has pasado");
                }
                if(opcion==3) {
                    int escapar=(int) Math.floor(Math.random()*2+1);
                    if(escapar==1){
                        break;
                    }

                }
                turno = !turno;
            }

            else{
                int opcion=(int) Math.floor(Math.random()*1);
                if(opcion==0){
                    System.out.println("El enemigo ha fallado.");
                }
                else
                jugador[0]=jugador[0]-enemigo[1];
                System.out.println("Te han atacado con "+enemigo[1] + "Puntos de daño, tu vida actual es"+ jugador[0]);
                turno=!turno;
            }
        }
        if(jugador[0]<0){
            morir();
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
}
