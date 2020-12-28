import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JuegoTest {
    private int[] estadisticasJugador;
    private int[] inventarioJugador;
    private int[] estadisticasEnemigo;
    private int opcion;
    private int minimaOpcion;
    private int maximaOpcion;
    private int random;
    private String[] tiendas;
    private String tiendaGenerada;
    private int precio;
    private int valorEsperado;
    private String cadenaEsperada;
    private String[][] armasPosibles;
    private String[] armasEnVenta;
    private int[][] armasEnVentaEstadisticas;

    @Before
    public void setUp() {
        estadisticasJugador = new int[8];   //posiciones  0-> vida actual  1-> daño ataque principal  2->ronda municion
        // 3-> daño ataque secundario  4-> nivel actual  5-> municion actual
        // 6-> vida máxima  7-> munición máxima
        inventarioJugador = new int[3];     //posiciones   0-> cartuchos de 15 balas  1-> jeringas  2-> fichas
        estadisticasEnemigo = new int[3];   //posiciones   0-> vida inicial   1-> daño de ataque   2-> vida actual
        armasPosibles = new String[][]{ {"revolver","subfusil","rifle","francotirador"}, {"cuchillo","bate de beisbol","hacha"} };
        armasEnVenta = new String[2];
        armasEnVentaEstadisticas = new int[2][3];   //filas   0-> arma principal  1-> arma secundaria
        //columnas  0-> daño   1-> precio   2-> ronda municion (solo para armas principales)
    }

    @After
    public void tearDown() {
        opcion = 0;
        minimaOpcion = 0;
        maximaOpcion = 0;
        random = 0;
        tiendas = null;
        tiendaGenerada = null;
        estadisticasJugador = null;
        inventarioJugador = null;
        estadisticasEnemigo = null;
        precio = 0;
        valorEsperado = 0;
        cadenaEsperada = null;
        armasPosibles = null;
        armasEnVenta = null;
        armasEnVentaEstadisticas = null;
    }

    @Test
    //El test pasa si se verifica que la opcion si está dentro del rango
    public void testOpcionEstaDentroDelRango01() {
        opcion = 3;
        minimaOpcion = 1;
        maximaOpcion = 5;
        assertTrue(Juego.opcionEstaDentroDelRango(opcion, minimaOpcion, maximaOpcion));
    }

    @Test
    //El test pasa si se verifica que la opcion no está dentro del rango
    public void testOpcionEstaDentroDelRango02() {
        opcion = 8;
        minimaOpcion = 1;
        maximaOpcion = 5;
        assertFalse(Juego.opcionEstaDentroDelRango(opcion, minimaOpcion, maximaOpcion));
    }

    @Test
    //El test pasa si el valor que entrega el método se encuentra dentro del rango
    public void testGetEnteroAleatorio01() {
        minimaOpcion = 1;
        maximaOpcion = 5;

        for (int i = 0; i < 1000; i++) {
            random = Juego.getEnteroAleatorioEntre(minimaOpcion, maximaOpcion);
            assertTrue("Se encuentra dentro del rango", (random >= minimaOpcion) && (random <= maximaOpcion));
        }
    }

    @Test
    //El test pasa si la tienda generada corresponde a una de las opciones del arreglo
    public void testGenerarTiendaActual01() {
        tiendas = new String[]{"hospital", "cuartel"};
        for (int i = 0; i < 1000; i++) {
            tiendaGenerada = Juego.generarTiendaActual(tiendas);
            assertTrue(tiendaGenerada.equals(tiendas[0]) || tiendaGenerada.equals(tiendas[1]));
        }
    }

    @Test
    //El test pasa si la resta se realiza de forma correcta
    public void testRestarFichas01() {
        inventarioJugador[2] = 15;   //al jugador se le asigna esa cantidad de fichas
        precio = 10;
        valorEsperado = 5;
        Juego.restarFichas(inventarioJugador, precio); //el método es void, por lo que inventarioJugador[2] cambia de valor
        assertEquals(valorEsperado, inventarioJugador[2]);
    }

    @Test
    //El test pasa si el calculo de fichas obtenidas se realiza de forma correcta
    public void testObtenerFichas01(){
        inventarioJugador[2] = 0;   //al jugador se le asigna esa cantidad de fichas
        estadisticasEnemigo[0] = 30;    //la vida inicial del enemigo
        estadisticasEnemigo[1] = 10;    //el ataque del enemigo
        valorEsperado = estadisticasEnemigo[0]/3 + estadisticasEnemigo[1]/2;
        Juego.obtenerFichas(inventarioJugador,estadisticasEnemigo);
        //el método es void, por lo que inventarioJugador[2] cambia de valor
        assertEquals(valorEsperado,inventarioJugador[2]);
    }

    @Test
    //El test pasa si se verifica que el jugador gane si el enemigo muere
    public void testDesarrollarTerminoPelea01(){
        estadisticasJugador[0] = 100;   //vida actual del jugador
        estadisticasEnemigo[2] = 0;     //vida actual del enemigo
        Juego.desarrollarTerminoPelea(estadisticasJugador,inventarioJugador,estadisticasEnemigo,false);
        assertTrue(estadisticasEnemigo[2] <= 0);
    }

    @Test
    //El test pasa si la vida del enemigo pasa de negativa a 0 correctamente
    public void testCalcularVidaActualEnemigo01(){
        estadisticasEnemigo[2] = -10;   //vida actual del enemigo
        valorEsperado = 0;
        Juego.calcularVidaActualEnemigo(estadisticasEnemigo);
        assertEquals(valorEsperado,estadisticasEnemigo[2]);
    }

    @Test
    //El test pasa si se cambia correctamente la estadistica del nombre del arma en venta
    public void testSetEstadisticasRevolverEnVenta01(){
        armasEnVenta[0] = "";   //empieza con un valor vacío
        cadenaEsperada = armasPosibles[0][0];     //revolver
        Juego.setEstadisticasRevolverEnVenta(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        assertEquals(cadenaEsperada,armasEnVenta[0]);
    }

    @Test
    //El test pasa si se cambia correctamente la estadistica del daño del arma en venta
    public void testSetEstadisticasRevolverEnVenta02(){
        armasEnVentaEstadisticas[0][0] = 0;   //empieza con un valor cero el valor del daño
        valorEsperado = 22;
        Juego.setEstadisticasRevolverEnVenta(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        assertEquals(valorEsperado,armasEnVentaEstadisticas[0][0]);
    }

    @Test
    //El test pasa si se cambia correctamente la estadistica de la ronda de munición del arma en venta
    public void testSetEstadisticasRevolverEnVenta03(){
        armasEnVentaEstadisticas[0][2] = 0;   //empieza con un valor cero el valor de la ronda de munición
        valorEsperado = 1;
        Juego.setEstadisticasRevolverEnVenta(armasPosibles,armasEnVenta,armasEnVentaEstadisticas);
        assertEquals(valorEsperado,armasEnVentaEstadisticas[0][2]);
    }
}