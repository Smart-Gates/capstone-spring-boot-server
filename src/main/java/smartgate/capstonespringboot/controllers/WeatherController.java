package smartgate.capstonespringboot.controllers;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.service.WeatherService;



@RestController
public class WeatherController {
	private final WeatherService weatherService = null;
	
	@GetMapping("/weather/{latitude}/{longitude}") 
	String getWeather(@PathVariable long latitude, @PathVariable long longitude) {
		
	    return null;

	}
}
