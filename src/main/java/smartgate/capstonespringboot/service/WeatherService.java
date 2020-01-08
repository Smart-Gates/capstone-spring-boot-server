package smartgate.capstonespringboot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import smartgate.capstonespringboot.models.WeatherForecastResponse;

@Service
public class WeatherService {
	
   
	@Value("${weather.darksky.url}")
	private String weatherUrlPath;
	
	private final WebClient webClient = WebClient.create(weatherUrlPath);
	
	public Mono<WeatherForecastResponse> forecastRequest(double latitude, double longitude) {
		Mono<WeatherForecastResponse> weatherForecast = webClient.get()
				.uri("/{latitude}/{longitude}?exclude=minutely,hourly", latitude, longitude)
				.accept(MediaType.APPLICATION_JSON)
		        .retrieve()
		        .bodyToMono(WeatherForecastResponse.class);
				
		return weatherForecast;
	}
	
}
