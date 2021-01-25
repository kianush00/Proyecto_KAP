package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase ArmaPrimariaTest contiene las pruebas unitarias de la clase ArmaPrimaria
 */
public class ArmaPrimariaTest {
    private Juego juego;
    private ArmaPrimaria armaPrimaria;
    private Enemigo enemigo;
    private Inventario inventario;
    private Integer esperado;
    private Integer obtenido;

    /**
     * Se generan instancias que se utilizarán en cada prueba unitaria
     */
    @Before
    public void setUp() {
        juego = new Juego(29);
        juego.generarNuevoEnemigo();
        armaPrimaria = juego.getJugador().getArmaPrimaria();
        enemigo = juego.getEnemigoActual();
        inventario = juego.getJugador().getInventario();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        armaPrimaria = null;
        enemigo = null;
        inventario = null;
        esperado = null;
        obtenido = null;
    }

    /**
     * Test que comprueba si el método atacarEnemigo de la clase ArmaPrimaria funciona correctamente
     */
    @Test
    public void testAtacarEnemigo() {
        obtenido = enemigo.getVidaActual() - armaPrimaria.getpuntosDeDano();
        esperado = armaPrimaria.atacarEnemigo(enemigo,inventario);
        assertEquals(esperado,obtenido);
    }

    /**
     * Test que comprueba si el método restarMunicion funciona correctamente
     */
    @Test
    public void testRestarMunicion() {
        obtenido = inventario.getMunicion() - armaPrimaria.getRONDA_MUNICION();
        esperado = armaPrimaria.restarMunicion(inventario);
        assertEquals(esperado,obtenido);
    }
}