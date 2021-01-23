package data;

import com.opencsv.CSVWriter;
import java.io.FileOutputStream;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class GestorDatos {
	private String nombreArchivo;

	public void GuardarPuntaje()throws IOException {

		String[] entries = { "book", "coin", "pencil", "cup" };
		String fileName = "src/main/resources/items.csv";

		try (var fos = new FileOutputStream(fileName,true);
			 var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			 var writer = new CSVWriter(osw)) {
			writer.writeNext(entries);
		}
	}


	public void VerPuntajes() throws IOException,
			CsvValidationException {
		var fileName = "src/main/resources/items.csv";

		try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
			 var reader = new CSVReader(fr)) {

			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {

				for (var e : nextLine) {
					System.out.format("%s ", e);


				}
			}
		}

	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}