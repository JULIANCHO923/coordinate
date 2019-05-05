package co.com.ceiba.julian.henao.coordinate.model;

import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;

public class Operations {

	public double calculateDistanceBetweenCoordinates(Coordinate coordinate1, Coordinate coordinate2) {
		return Math.sqrt(Math.pow(coordinate2.getX() - coordinate1.getX(), 2) + Math.pow(coordinate2.getY() - coordinate1.getY(), 2));
	}
	
}
