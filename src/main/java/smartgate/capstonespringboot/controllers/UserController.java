package smartgate.capstonespringboot.controllers;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import smartgate.capstonespringboot.models.WeatherForecastResponse;
@RequestMapping("/api")
@RestController
public class UserController {


	// remove duplicate getMapping once mobile app is updated
	@PostMapping("/wweather/{latitude}/{longitude}") 
	WeatherForecastResponse getWeather(@PathVariable long latitude, @PathVariable long longitude) {
		return null;
	
	}
	
	// returns the nested JSON as the WeatherForecastResponse object, this is to remove unneeded values
	@GetMapping("/awpi/weather/{latitude}/{longitude}") 
	WeatherForecastResponse getWeatherApi(@PathVariable String latitude, @PathVariable String longitude) {
		return null;
	
	}
}
