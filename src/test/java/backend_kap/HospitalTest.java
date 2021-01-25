package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase HospitalTest contiene las pruebas unitarias de la clase Hospital
 */
public class HospitalTest {
    private Juego juego;
    private Hospital hospital;
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
        //bucle do-while que genera una tienda de tipo Hospital
        do {
            juego.generarNuevaTiendaAleatoria();
        } while (!(juego.getTiendaActual() instanceof Hospital));
        hospital = (Hospital) juego.getTiendaActual();
        jugador = juego.getJugador();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        hospital = null;
        jugador = null;
        esperado = null;
        obtenido = null;
        fichas = 0;
    }

    /**
     * Test que comprueba si el método curarse de la clase Hospital establece correctamente el valor
     * de la vida actual al objeto Jugador pasado como parámetro
     */
    @Test
    public void testCurarse01() {
        jugador.getInventario().setFichas(40);  //se establecen 40 fichas al jugador para realizar compra
        jugador.setVidaActual(60);     //se establece 60 de vida al jugador
        hospital.curarse(jugador);
        esperado = jugador.getVIDA_MAXIMA();
        obtenido = jugador.getVidaActual();
        assertEquals(esperado,obtenido);
    }

    /**
     * Test que comprueba si el método curarse de la clase Hospital resta correctamente el valor de las
     * fichas del inventario del jugador en base a lo que cuesta curarse
     */
    @Test
    public void testCurarse02() {
        fichas = 40;
        jugador.getInventario().setFichas(fichas);  //se establecen 40 fichas al jugador para realizar compra
        jugador.setVidaActual(60);     //se establece 60 de vida al jugador
        hospital.curarse(jugador);
        esperado = fichas - hospital.getPRECIO_CURARSE();
        obtenido = jugador.getInventario().getFichas();
        assertEquals(esperado,obtenido);
    }

    /**
     * Test que comprueba si el método curarse de la clase Hospital lanza una excepción de tipo
     * IllegalArgumentException cuando el jugador desee curarse pero este valor se encuentre al máximo
     * @see IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCurarse03() {
        jugador.getInventario().setFichas(40);  //se establecen 40 fichas al jugador para realizar compra
        jugador.setVidaActual(jugador.getVIDA_MAXIMA());
        hospital.curarse(jugador);
    }
}