package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogHandler {
	
	
	static SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy");
    
	
	
	public void logValue(String variation, String message) {

		try {
			Date date = new Date();
			
			String dateString = simpleFormat.format(date);

			String fileName = dateString + "_" + variation + ".log";
			
			// obtengo el archivo
			File file = new File(fileName);

			// si no existe, lo creo
			if (!file.exists()) {
				file.createNewFile();
			}

			// instancio objetos para escribir
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);

			// Escribo
			bw.write("\n" + message + "\n");

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
