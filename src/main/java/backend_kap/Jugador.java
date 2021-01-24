package backend_kap;

public class Jugador extends Personaje {
	private Inventario inventario;
	private ArmaPrimaria armaPrimaria;
	private ArmaSecundaria armaSecundaria;

	public Jugador(Inventario inventario, ArmaPrimaria armaPrimaria, ArmaSecundaria armaSecundaria) {
		this.inventario = inventario;
		this.armaPrimaria = armaPrimaria;
		this.armaSecundaria = armaSecundaria;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public ArmaPrimaria getArmaPrimaria() {
		return armaPrimaria;
	}

	public void setArmaPrimaria(ArmaPrimaria armaPrimaria) {
		this.armaPrimaria = armaPrimaria;
	}

	public ArmaSecundaria getArmaSecundaria() {
		return armaSecundaria;
	}

	public void setArmaSecundaria(ArmaSecundaria armaSecundaria) {
		this.armaSecundaria = armaSecundaria;
	}

	public boolean intentarHuir() {
		int probabilidadesEscapar = (int) (Math.random() * 2);

		if (probabilidadesEscapar == 0) {   // 1/2 prob. de huir exitosamente
			return true;
		}else{
			return false;
		}
	}

	public void morir() {
		System.exit(1);
	}
}