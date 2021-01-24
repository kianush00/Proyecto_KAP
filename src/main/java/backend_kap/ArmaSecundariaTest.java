package backend_kap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmaSecundariaTest {
    private ArmaPrimaria armaPrimaria;
    private int puntosDeDaño;
    private int precio;
    private TipoArmaPrimaria tipo;
    private TipoArmaSecundaria tipo2;
    private int rondaMunicion;
    private Jugador jugador;
    private Inventario inventario;
    private ArmaSecundaria armaSecundaria;
    private Enemigo enemigo;
    private Juego juego;
    @Before
    public void setUp() {
        this.puntosDeDaño=7;
        this.precio=100;
        this.tipo=TipoArmaPrimaria.PISTOLA9MM;
        this.tipo2=TipoArmaSecundaria.BATE_BEISBOL;
        this.rondaMunicion=15;
        this.armaPrimaria= new ArmaPrimaria(puntosDeDaño,precio, tipo, rondaMunicion);
        this.armaSecundaria= new ArmaSecundaria(puntosDeDaño,precio,tipo2);
        this.inventario=new Inventario(jugador);
        this.jugador= new Jugador(100,inventario,armaPrimaria,armaSecundaria);
        this.juego=new Juego(29);
        this.enemigo=new Enemigo(juego);
    }

    @Test
    public void atacarEnemigo() {
        juego.generarNuevoEnemigo();
        int expected = 70;
        assertEquals(expected, this.jugador.getArmaSecundaria().atacarEnemigo(enemigo));;
    }
}