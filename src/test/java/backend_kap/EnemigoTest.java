package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase EnemigoTest contiene las pruebas unitarias de la clase Enemigo
 */
public class EnemigoTest {
    private Juego juego;
    private Enemigo enemigo;
    private Jugador jugador;
    private Integer esperado;
    private Integer obtenido;

    /**
     * Se generan instancias que se utilizarán en cada prueba unitaria
     */
    @Before
    public void setUp()  {
        juego = new Juego(29);
        juego.generarNuevoEnemigo();
        enemigo = juego.getEnemigoActual();
        jugador = juego.getJugador();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        enemigo = null;
        jugador = null;
        esperado = null;
        obtenido = null;
    }

    /**
     * Test que comprueba si el método atacarJugador funciona correctamente
     */
    @Test
    public void testAtacarJugador() {
        esperado = jugador.getVidaActual() - enemigo.getpuntosDeDano();
        obtenido = enemigo.atacarJugador(jugador);
        assertEquals(esperado,obtenido);
    }
}