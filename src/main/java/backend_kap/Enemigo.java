package backend_kap;

public class Enemigo extends Personaje {
	private int puntosDeDaño;
	public Enemigo(Juego juego) {
		super.vidaMaxima=(int) ((Math.random() * 70) + (juego.getNivelActual()*0.75) + 30);
		super.vidaActual=super.vidaMaxima;
		this.puntosDeDaño=(int) ((Math.random() * 7) + (juego.getNivelActual()*0.75) + 3);
	}

	public int getPuntosDeDaño() {
		return this.puntosDeDaño;
	}

	public void setPuntosDeDaño(int puntosDeDaño) {
		this.puntosDeDaño = puntosDeDaño;
	}

	public void atacarJugador(Jugador jugador) {


	}
}