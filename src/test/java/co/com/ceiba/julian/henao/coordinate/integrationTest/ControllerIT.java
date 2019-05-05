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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;
import co.com.ceiba.julian.henao.coordinate.model.Constantes;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIT {

	private final static Logger LOGGER = Logger.getLogger("ControllerIT");
	
	@Autowired
	private TestRestTemplate restTemplate;

	private String url;

	@Before
	public void inicializandoUrl() {
		url = "/coordinates";
	}

	@Test
	public void WhenMethodPostSendListWithTwoCoordinatesThenDistanceIsCalculated() {
		// arrange
		double x1 = 1, y1 = 2, x2 = 1, y2 = -2, distanceExpected = 4.0;
		Coordinate coordinate1 = new Coordinate(x1, y1);
		Coordinate coordinate2 = new Coordinate(x2, y2);
		List<Coordinate> coordinates = new ArrayList<Coordinate>();
		coordinates.add(coordinate1);
		coordinates.add(coordinate2);

		Path path = Paths.get(Constantes.FILE_NAME);
		try {			
				Files.delete(path);			
		} catch (IOException e) {			
			LOGGER.log(Level.INFO, "El archivo no pudo ser eliminado porque no existe, las pruebas siguen correctamente");
		}

		// act
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, coordinates, String.class);

		// Assert
		int numberLinesActual = 0;
		try {
			numberLinesActual = Files.readAllLines(path, Charset.defaultCharset()).size();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Error: El archivo no pudo ser leido");		
			fail(e.getMessage());
		}
		
		int numberLinesExpected = 1;
		assertEquals("Se esperaba una linea en el archivo", numberLinesExpected, numberLinesActual);
		
		double distanceNow = Double.parseDouble(responseEntity.getBody().toString());
		double deltaExpected = 0.0;		
		assertEquals("El calculo de la distancia NO fue correcto", distanceExpected, distanceNow, deltaExpected);
	}

}
