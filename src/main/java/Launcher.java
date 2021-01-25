import com.opencsv.exceptions.CsvValidationException;
import frontend_kap.InterfazCLI;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Launcher {
	public static void main(String[] args) throws IOException, CsvValidationException {
		InterfazCLI interfaz = new InterfazCLI();
		interfaz.lanzarVentana();
	}
}