package smartgate.capstonespringboot.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import smartgate.capstonespringboot.models.WeatherForecastResponse;

@RestController
public class WeatherController {
	// get values from application.properties file
	@Value("${weather.url}")
	private String weatherUrl;
	@Value("${weather.secret}")
	private String weatherSecret;
	// restTemplate is self explanatory, used for outgoing RESTAPI requests
	private final RestTemplate restTemplate = new RestTemplate();
	
	// returns the nested JSON as the WeatherForecastResponse object, this is to remove unneeded values
	@GetMapping("/api/weather/{latitude}/{longitude}") 
	WeatherForecastResponse getWeatherApi(@PathVariable String latitude, @PathVariable String longitude) {
		String url = this.weatherUrl + this.weatherSecret +"/"+latitude+","+longitude+"?exclude=minutely,hourly,alerts,flags&units=si";
		return this.restTemplate.getForObject(url, WeatherForecastResponse.class);
	}
}
