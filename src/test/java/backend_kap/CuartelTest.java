package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase CuartelTest contiene las pruebas unitarias de la clase Cuartel
 */
public class CuartelTest {
    private Juego juego;
    private Cuartel cuartel;
    private Jugador jugador;
    private int fichas;
    private Integer esperado;
    private Integer obtenido;

    /**
     * Se generan instancias que se utilizarán en cada prueba unitaria
     */
    @Before
    public void setUp() {
        juego = new Juego(29);
        //bucle do-while que genera una tienda de tipo Cuartel
        do {
            juego.generarNuevaTiendaAleatoria();
        } while (!(juego.getTiendaActual() instanceof Cuartel));
        cuartel = (Cuartel) juego.getTiendaActual();
        jugador = juego.getJugador();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        cuartel = null;
        jugador = null;
        esperado = null;
        obtenido = null;
        fichas = 0;
    }

    /**
     * Test que comprueba si el método cargarMunicion de la clase Cuartel establece correctamente el valor
     * de la munición al objeto Jugador pasado como parámetro
     */
    @Test
    public void testCargarMunicion01(){
        jugador.getInventario().setFichas(40);  //se establecen 40 fichas al jugador para realizar compra
        jugador.getInventario().setMunicion(0);     //se establece 0 de munición
        cuartel.cargarMunicion(jugador);
        esperado = jugador.getInventario().getLIMITE_MUNICION();
        obtenido = jugador.getInventario().getMunicion();
        assertEquals(esperado,obtenido);
    }

    /**
     * Test que comprueba si el método cargarMunicion de la clase Cuartel resta correctamente el valor de las
     * fichas del inventario del jugador en base a lo que cuesta cargar la munición
     */
    @Test
    public void testCargarMunicion02(){
        fichas = 40;
        jugador.getInventario().setFichas(fichas);  //se establecen 40 fichas al jugador para realizar compra
        jugador.getInventario().setMunicion(0);     //se establece 0 de munición
        cuartel.cargarMunicion(jugador);
        esperado = fichas - cuartel.getPRECIO_CARGAR_MUNICION();
        obtenido = jugador.getInventario().getFichas();
        assertEquals(esperado,obtenido);
    }

    /**
     * Test que comprueba si el método cargarMunicion de la clase Cuartel lanza una excepción de tipo
     * IllegalArgumentException cuando el jugador desee cargar munición pero este valor se encuentre al máximo
     * @see IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCargarMunicion03(){
        jugador.getInventario().setFichas(40);  //se establecen 40 fichas al jugador para realizar compra
        jugador.getInventario().setMunicion(jugador.getInventario().getLIMITE_MUNICION());
        cuartel.cargarMunicion(jugador);
    }

    /**
     * Test que comprueba si el método venderArmaPrimaria de la clase Cuartel, el cual es heredado de la clase
     * Tienda, establece correctamente el objeto del arma primaria al inventario del jugador pasado como parámetro
     */
    @Test
    public void testVenderArmaPrimaria01(){
        jugador.getInventario().setFichas(50);  //se establecen 50 fichas al jugador para realizar compra
        cuartel.venderArmaPrimaria(jugador);
        assertEquals(jugador.getArmaPrimaria(), cuartel.getArmaPrimariaEnVenta());
    }
}