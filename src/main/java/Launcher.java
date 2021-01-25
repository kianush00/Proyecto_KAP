
import frontend_kap.InterfazCLI;

/**
 * @author Kianush Atighi-Moghaddam
 * @author Arturo Avendaño
 */

public class Launcher {
	/**
	 * Método principal que ejecuta el programa por medio de una instancia de la interfaz.
	 * @param args parámetro obligatorio para el método main
	 * @see InterfazCLI
	 */
	public static void main(String[] args) {
		InterfazCLI interfaz = new InterfazCLI();
		interfaz.lanzarVentana();
	}
}