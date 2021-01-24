import frontend_kap.InterfazCLI;

public class Launcher {

	public static void main(String[] args) {
		//InterfazCLI interfaz = new InterfazCLI();
		//interfaz.lanzarVentana();
		int random = (int) (Math.random() * ((2 + 1) - 1)) + 1;
		System.out.println(random);
	}
}