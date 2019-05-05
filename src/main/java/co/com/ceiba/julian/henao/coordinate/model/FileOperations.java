package co.com.ceiba.julian.henao.coordinate.model;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;

public class FileOperations {
	
	private final static Logger LOGGER = Logger.getLogger("FileOperations");

	public void escribirArchivo(List<Coordinate> coordinates, double resultDistance,String fileName) {
		
		String line = "(" + coordinates.get(0).getX() + "," + coordinates.get(0).getY() + ") - ("
				+ coordinates.get(1).getX() + "," + coordinates.get(1).getY() + ") -> Distance: " + resultDistance;
	
		BufferedWriter br = null;
		Path path = Paths.get(fileName);
					   
		try {
			try {
				br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW);
			} catch (FileAlreadyExistsException e) {
				br = Files.newBufferedWriter(path, Charset.defaultCharset(), StandardOpenOption.APPEND);
			}	
						
			int numberLines = Files.readAllLines(path, Charset.defaultCharset()).size();								
			br.append((++numberLines) +"."+line);
			br.newLine();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error al ingresar nueva linea al archivo --> "+e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e2) {
					LOGGER.log(Level.INFO, "El archivo no pudo ser cerrado -->" + e2.getMessage());
				}
			}
		}
	}
}