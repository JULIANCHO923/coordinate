package co.com.ceiba.julian.henao.coordinate.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.ceiba.julian.henao.coordinate.dto.Coordinate;
import co.com.ceiba.julian.henao.coordinate.model.Constantes;
import co.com.ceiba.julian.henao.coordinate.model.FileOperations;
import co.com.ceiba.julian.henao.coordinate.model.Operations;

@RestController("/coordinates")
public class CoordinateController {

	@PostMapping
	public String calculateDistanceAndWriteIntoFile(@RequestBody List<Coordinate> coordinates) {
		double resultDistance = new Operations().calculateDistanceBetweenCoordinates(coordinates.get(0), coordinates.get(1));		
		new FileOperations().escribirArchivo(coordinates, resultDistance, Constantes.FILE_NAME) ;
		return ""+resultDistance;
	}
	
	
}
