package data;

import com.opencsv.CSVWriter;

import java.io.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GestorDatos {

	static Scanner input = new Scanner(System.in);

	public GestorDatos() {
	}
	public void GuardarPuntaje(int puntaje) throws IOException {
		System.out.println("Ingrese su nombre: ");
		String nombreJugador = input.next();
		String[] entries = {nombreJugador, String.valueOf(puntaje)};
		String fileName = "src/main/java/puntajes.csv";
		try (var fos = new FileOutputStream(fileName,true);
			 var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			 var writer = new CSVWriter(osw)) {

			writer.writeNext(entries);
		}
	}

	public void VerPuntajes() throws IOException,
			CsvValidationException {
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

	}
}