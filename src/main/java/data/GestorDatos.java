package data;
import com.opencsv.CSVWriter;
import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GestorDatos {

	/**
	 * Atributo de tipo static que contiene los datos que se ingresan por consola.
	 * @see Scanner
	 */
	static Scanner input = new Scanner(System.in);

	/**
	 * Constructor del Gestor de Datos, inicializa el objeto.
	 */
	public GestorDatos() {
	}

	/**
	 * Método que permite guardar los datos al final de la partida, pide como parametro el "puntaje" que corresponde a
	 * las fichas actuales al momento de terminar la partida.
	 * @param puntaje Objeto tipo int que corresponde a las fichas disponibles al momento de terminar la partida.
	 * @see FileOutputStream
	 * @see OutputStreamWriter
	 * @see CSVWriter
	 */
	public void GuardarPuntaje(int puntaje) {
		try {
			System.out.println("Ingrese su nombre: ");
			String nombreJugador = input.next();
			String[] entries = {nombreJugador, String.valueOf(puntaje)};
			String fileName = "src/main/java/puntajes.csv";
			try (var fos = new FileOutputStream(fileName, true);
				 var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				 var writer = new CSVWriter(osw)) {

				writer.writeNext(entries);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Método que permite ver la tabla de puntajes al leer el archivo generado por la funcion GuardarDatos()
	 * @see FileReader
	 * @see StandardCharsets
	 * @see CSVReader
	 */
	public void VerPuntajes() {
		try {
			var fileName = "src/main/java/puntajes.csv";

			try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
				 var reader = new CSVReader(fr)) {

				String[] nextLine;
				System.out.println("Tabla de puntajes (Nombre Puntaje)");
				while ((nextLine = reader.readNext()) != null) {

					for (var e : nextLine) {
						System.out.format("%s ", e);
					}
					System.out.println("");
				}
			}
		} catch (IOException | CsvValidationException ioe) {
			ioe.printStackTrace();
		}
	}
}