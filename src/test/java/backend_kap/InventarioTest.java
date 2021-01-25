package backend_kap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * La clase InventarioTest contiene las pruebas unitarias de la clase Inventario
 */
public class InventarioTest {
    private Juego juego;
    private Inventario inventario;

    /**
     * Se generan instancias que se utilizarán en cada prueba unitaria
     */
    @Before
    public void setUp() {
        juego = new Juego(29);
        inventario = juego.getJugador().getInventario();
    }

    /**
     * Se eliminan las instancias al término de cada prueba unitaria para liberar memoria
     */
    @After
    public void tearDown() {
        inventario = null;
    }

    /**
     * Test que lanza una excepción de tipo IllegalArgumentException si el valor que se establece a las
     * fichas es negativo
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetFichas() {
        inventario.setFichas(-1);
    }

    /**
     * Test que lanza una excepción de tipo IllegalArgumentException si el valor que se establece a la
     * cantidad de cargadores supera el límite de cargadores que permite el inventario. El valor del límite
     * se encuentra instanciado con un valor predeterminado en el constructor de Inventario, su valor es 8.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCargadores15Balas() {
        inventario.setCargadores15Balas(1000);
    }

    /**
     * Test que lanza una excepción de tipo IllegalArgumentException si el valor que se establece a la
     * cantidad de jeringas supera el límite de jeringas que permite el inventario. El valor del límite
     * se encuentra instanciado con un valor predeterminado en el constructor de Inventario, su valor es 8.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetJeringas() {
        inventario.setJeringas(1000);
    }

    /**
     * Test que lanza una excepción de tipo IllegalArgumentException si el jugador al usar el método
     * usarCargador no posee cargadores
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUsarCargador() {
        inventario.setCargadores15Balas(0);
        inventario.usarCargador();
    }

    /**
     * Test que lanza una excepción de tipo IllegalArgumentException si el jugador al usar el método
     * usarJeringa no posee jeringas
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUsarJeringa() {
        inventario.setJeringas(0);
        inventario.usarJeringa(juego.getJugador());
    }
}