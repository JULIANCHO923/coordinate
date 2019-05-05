package co.com.ceiba.julian.henao.coordinate.integrationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;
import co.com.ceiba.julian.henao.coordinate.model.Constantes;
import co.com.ceiba.julian.henao.coordinate.model.FileOperations;

public class FileIT {
	
	private final static Logger LOGGER = Logger.getLogger("FileIT");
        
	@Test(expected = Test.None.class)
	public void WhenWriteThenCreateNewFile() {
		//arrange
		double x1 = 1, y1 = 2, x2= 1, y2 = -2, distanceExpected = 4.0;
		Coordinate coordinate1 = new Coordinate(x1, y1);
		Coordinate coordinate2 = new Coordinate(x2, y2);
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(coordinate1);
		coordinates.add(coordinate2);
		FileOperations fileOperations = new FileOperations();
				
		//act				
		fileOperations.escribirArchivo(coordinates, distanceExpected, Constantes.TEST_FILE_NAME);		
	}
	
	@Test(expected = Test.None.class)
	public void WhenDeleteFileThenIsCreatedNewFile() {
		//arrange		
		double x1 = 1, y1 = 2, x2= 1, y2 = -2, distanceExpected = 4.0;
		Coordinate coordinate1 = new Coordinate(x1, y1);
		Coordinate coordinate2 = new Coordinate(x2, y2);
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(coordinate1);
		coordinates.add(coordinate2);
		FileOperations fileOperations = new FileOperations();
		
		// I create file and expect that this will be delete
		fileOperations.escribirArchivo(coordinates, distanceExpected, Constantes.TEST_FILE_NAME);
		
		Path path = Paths.get(Constantes.TEST_FILE_NAME);
		try {
			Files.delete(path);
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "El archivo no pudo ser eliminado porque no existe, las pruebas siguen correctamente");						
		}
		
		//act -> Create New				
		fileOperations.escribirArchivo(coordinates, distanceExpected, Constantes.TEST_FILE_NAME);
		
		//Assert
		int numberLinesActual = 0;
		try {
			numberLinesActual = Files.readAllLines(path, Charset.defaultCharset()).size();
		} catch (IOException e) {			//
			LOGGER.log(Level.WARNING, "Error: El archivo no pudo ser leido");		
			fail(e.getMessage());
		}
		int numberLinesExpected = 1;
		assertEquals("Se esperaba una linea en el archivo",numberLinesExpected, numberLinesActual);		
	}
	
	
	@Test(expected = Test.None.class)
	public void WhenWriteIntoFileTwoTimesThenExpectTwoLines() {
		//arrange		
		double x1 = 1, y1 = 2, x2= 1, y2 = -2, distanceExpected = 4.0;
		Coordinate coordinate1 = new Coordinate(x1, y1);
		Coordinate coordinate2 = new Coordinate(x2, y2);
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(coordinate1);
		coordinates.add(coordinate2);
		FileOperations fileOperations = new FileOperations();
		
		// I delete the file, i want to start from zero
		Path path = Paths.get(Constantes.TEST_FILE_NAME);
		try {
			Files.delete(path);
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "El archivo no pudo ser eliminado porque no existe, las pruebas siguen correctamente");											
		}
		
		//act				
		fileOperations.escribirArchivo(coordinates, distanceExpected, Constantes.TEST_FILE_NAME);						
		fileOperations.escribirArchivo(coordinates, distanceExpected, Constantes.TEST_FILE_NAME);
		
		//Assert
		int numberLinesActual = 0;		
		try {
			numberLinesActual = Files.readAllLines(path, Charset.defaultCharset()).size();
		} catch (IOException e) {			// 
			LOGGER.log(Level.WARNING, "Error: El archivo no pudo ser leido");	
			fail(e.getMessage());
		}
		int numberLinesExpected = 2;
		assertEquals("Se esperaban dos lineas en el archivo",numberLinesExpected, numberLinesActual);		
	}
	
	
}
