package co.com.ceiba.julian.henao.coordinate.unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;

public class TestCoordinate {

	@Test
	public void WhenInsertXAndYThenCreateCoordinate() {
		//arrange
		double xExpected = 2, yExpected = 4;
		Coordinate coordinate;
		
		// act
		coordinate = new Coordinate(xExpected,yExpected);
		
		//assert
		double deltaExpected = 0.0;
		double xNow = coordinate.getX();
		double yNow = coordinate.getY();
		assertEquals("La variable 'X' ingresada NO quedo registrada", xExpected, xNow, deltaExpected);
		assertEquals("La variable 'Y' ingresada NO quedo registrada", yExpected, yNow, deltaExpected);		
	}
	
}
