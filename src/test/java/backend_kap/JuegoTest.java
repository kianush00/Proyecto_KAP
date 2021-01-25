package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase JuegoTest contiene las pruebas unitarias de la clase Juego
 */
public class JuegoTest {
    private Juego juego;
    private Enemigo enemigo;
    private Integer esperado;
    private Integer obtenido;

    /**
     * Se generan instancias que se utilizarán en cada prueba unitaria
     */
    @Before
    public void setUp() {
        juego = new Juego(29);
        juego.generarNuevoEnemigo();
        enemigo = juego.getEnemigoActual();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        enemigo = null;
        esperado = null;
        obtenido = null;
    }

    /**
     * Test que comprueba si el método calcularFichasGanadas funciona correctamente
     */
    @Test
    public void testCalcularFichasGanadas() {
        esperado = (enemigo.getVIDA_MAXIMA() / 3) + (enemigo.getpuntosDeDano() / 2);
        obtenido = juego.calcularFichasGanadas();
        assertEquals(esperado,obtenido);
    }
}