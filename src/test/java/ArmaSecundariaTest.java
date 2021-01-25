import backend_kap.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmaSecundariaTest {
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
        int expected = 70;
        juego.generarNuevoEnemigo();
        assertEquals(expected, juego.getJugador().getArmaSecundaria().atacarEnemigo(juego.getEnemigoActual()));;
    }
}