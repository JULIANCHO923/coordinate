package co.com.ceiba.julian.henao.coordinate.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;
import co.com.ceiba.julian.henao.coordinate.model.Operations;

public class OperationTest {

	@Test
	public void WhenSendTwoCoordinatesThenCalculateDistanceBetweenTheseCoordinates() {
		//arrange
		double x1 = 1, y1 = 2, x2= 1, y2 = -2, distanceExpected = 4.0;
		Coordinate coordinate1 = new Coordinate(x1, y1);
		Coordinate coordinate2 = new Coordinate(x2, y2);
		Operations operation = new Operations();
				
		// act
		double distanceNow = operation.calculateDistanceBetweenCoordinates(coordinate1, coordinate2);
		
		//assert
		double deltaExpected = 0.0;
		assertEquals("La distancia entre las coordenadas NO fue la esperada", distanceExpected, distanceNow, deltaExpected);			
	}
	
}
