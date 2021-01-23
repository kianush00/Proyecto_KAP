package backend_kap;

public class Jugador extends Personaje {
	private ArmaPrimaria armaPrimaria;
	private Inventario inventario;
	private ArmaSecundaria armaSecundaria;

	public Jugador(ArmaPrimaria armaPrimaria, Inventario inventario, ArmaSecundaria armaSecundaria) {
		this.armaPrimaria=armaPrimaria;
		this.inventario=inventario;
		this.armaSecundaria=armaSecundaria;
	}

	public boolean intentarHuir() {
		int probabilidadesEscapar = (int) (Math.random() * 2);

		if (probabilidadesEscapar == 1) {   //un medio de prob. de huir exitosamente
			return true;
		}else{
			return false;
		}
	}

}