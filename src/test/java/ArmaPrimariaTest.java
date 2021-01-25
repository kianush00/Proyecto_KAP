import backend_kap.*;
import junit.framework.JUnit4TestAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmaPrimariaTest {
    private Juego juego;

    @Before
    public void setUp() {
        juego = new Juego(29);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void atacarEnemigo() {
        juego.generarNuevoEnemigo();
        int esperado = 70;
        assertEquals(esperado, juego.getJugador().getArmaPrimaria().atacarEnemigo(juego.getEnemigoActual(),
                juego.getJugador().getInventario()));
    }

    @Test
    public void restarMunicion() {
        int expected = 15;
        assertEquals(expected, juego.getJugador().getArmaPrimaria().restarMunicion(juego.getJugador().getInventario()));
    }
}