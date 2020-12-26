import java.util.Scanner;

public class Juego {

    public static void lanzarJuego() {

    }


    public static void intentarPelear(int[] estadisticasJugador, int[] inventarioJugador) {
        if (estadisticasJugador[2] % 3 != 0) {     //Si el nivel actual no es multiplo de 3 inicia la pelea
            pelear(estadisticasJugador, inventarioJugador);
        }
    }

    public static void pelear(int[] estadisticasJugador, int[] inventarioJugador) {
        int[] enemigo = new int[2];
        Scanner entrada = new Scanner(System.in);
        enemigo[0] = (int) (Math.floor(Math.random() * 70 + 30) + (estadisticasJugador[2] / 2));
        System.out.println(enemigo[0]);
        enemigo[1] = (int) Math.floor((Math.random() * 7 + 3) + (estadisticasJugador[2] / 2));
        System.out.println(enemigo[1]);
        boolean turno = true;

        System.out.println("Inicia la batalla del nivel " + estadisticasJugador[2]);
        boolean huir = false;
        while (estadisticasJugador[0] > 0 && enemigo[0] > 0 && huir == false) {

            if (turno) {
                System.out.print("Opcion 1 para atacar, 2 para pasar, 3 para huir, finalmente 4 para curarte: ");

                int opcion = elegirOpcionYValidar(1, 4);
                switch (opcion) {
                    case 1:
                        if (inventarioJugador[0] > 0) {
                            enemigo[0] = enemigo[0] - estadisticasJugador[1];
                            inventarioJugador[0] = inventarioJugador[0] - estadisticasJugador[4];
                            System.out.println("Has atacado al enemigo con " + estadisticasJugador[1] + " Puntos de daño, su vida actual es " + enemigo[0]);
                        } else {
                            System.out.println("Solo tienes tus puños, suerte.");
                            enemigo[0] = enemigo[0] - estadisticasJugador[1];
                            inventarioJugador[0] = inventarioJugador[0] - estadisticasJugador[4];
                            System.out.println("Has atacado al enemigo con " + estadisticasJugador[1] + " Puntos de daño, su vida actual es " + enemigo[0]);
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
                    System.out.println(estadisticasJugador[0]);
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
            inventarioJugador[0] = inventarioJugador[0] - 1;
        }
        System.out.println("Ahora tu vida actual es " + estadisticasJugador[0]);
    }

    public static void obtenerDinero(int[] estadisticasJugador, int[] inventarioJugador, int[] enemigo) {
        estadisticasJugador[0] = estadisticasJugador[0] + enemigo[0]/3+enemigo[1]/2;
        inventarioJugador[0] = inventarioJugador[0] - 1;
        System.out.println("Has ganado " + estadisticasJugador[0] + "monedas");
    }
}
