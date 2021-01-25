import backend_kap.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JugadorTest {
    private Juego juego;

    @Before
    public void setUp()  {
        juego = new Juego(29);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void intentarHuir() {
        boolean expected = true;
        assertEquals(expected, juego.getJugador().intentarHuir());
    }
}