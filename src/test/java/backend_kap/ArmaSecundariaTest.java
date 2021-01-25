package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase ArmaSecundariaTest contiene las pruebas unitarias de la clase ArmaSecundaria
 */
public class ArmaSecundariaTest {
    private Juego juego;
    private ArmaSecundaria armaSecundaria;
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
        armaSecundaria = juego.getJugador().getArmaSecundaria();
        enemigo = juego.getEnemigoActual();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        armaSecundaria = null;
        enemigo = null;
        esperado = null;
        obtenido = null;
    }

    /**
     * Test que comprueba si el método atacarEnemigo de la clase ArmaSecundaria funciona correctamente
     */
    @Test
    public void testAtacarEnemigo() {
        obtenido = enemigo.getVidaActual() - armaSecundaria.getpuntosDeDano();
        esperado = armaSecundaria.atacarEnemigo(enemigo);
        assertEquals(esperado,obtenido);
    }
}