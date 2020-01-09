package smartgate.capstonespringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import smartgate.capstonespringboot.service.WeatherService;



@RestController
public class WeatherController {
	private final WeatherService weatherService = null;
	
	@GetMapping("/weather/{latitude}/{longitude}") 
	String getWeather(@PathVariable long latitude, @PathVariable long longitude) {
		
	    return "{\"latitude\":42.3601,\"longitude\":-71.0589,\"timezone\":\"America/New_York\",\"currently\":{\"time\":1578471376,\"summary\":\"Overcast\",\"icon\":\"cloudy\",\"nearestStormDistance\":10,\"nearestStormBearing\":339,\"precipIntensity\":0,\"precipProbability\":0,\"temperature\":34.27,\"apparentTemperature\":30.2,\"dewPoint\":29.78,\"humidity\":0.83,\"pressure\":1009.4,\"windSpeed\":4.51,\"windGust\":6.26,\"windBearing\":305,\"cloudCover\":1,\"uvIndex\":0,\"visibility\":7.368,\"ozone\":388.7},\"daily\":{\"summary\":\"Mixed precipitation throughout the week.\",\"icon\":\"sleet\",\"data\":[{\"time\":1578459600,\"summary\":\"Partly cloudy throughout the day.\",\"icon\":\"snow\",\"sunriseTime\":1578485640,\"sunsetTime\":1578519000,\"moonPhase\":0.44,\"precipIntensity\":0.0019,\"precipIntensityMax\":0.0087,\"precipIntensityMaxTime\":1578463320,\"precipProbability\":0.46,\"precipType\":\"snow\",\"precipAccumulation\":0.3,\"temperatureHigh\":41.16,\"temperatureHighTime\":1578511860,\"temperatureLow\":18.34,\"temperatureLowTime\":1578571860,\"apparentTemperatureHigh\":33.23,\"apparentTemperatureHighTime\":1578512220,\"apparentTemperatureLow\":5.15,\"apparentTemperatureLowTime\":1578571020,\"dewPoint\":24.91,\"humidity\":0.69,\"pressure\":1011.3,\"windSpeed\":11.23,\"windGust\":36.28,\"windGustTime\":1578529020,\"windBearing\":281,\"cloudCover\":0.54,\"uvIndex\":1,\"uvIndexTime\":1578502320,\"visibility\":9.447,\"ozone\":404.6,\"temperatureMin\":25.84,\"temperatureMinTime\":1578546000,\"temperatureMax\":41.16,\"temperatureMaxTime\":1578511860,\"apparentTemperatureMin\":12.95,\"apparentTemperatureMinTime\":1578546000,\"apparentTemperatureMax\":34.91,\"apparentTemperatureMaxTime\":1578459600},{\"time\":1578546000,\"summary\":\"Clear throughout the day.\",\"icon\":\"clear-day\",\"sunriseTime\":1578572040,\"sunsetTime\":1578605460,\"moonPhase\":0.47,\"precipIntensity\":0,\"precipIntensityMax\":0,\"precipIntensityMaxTime\":1578546000,\"precipProbability\":0,\"temperatureHigh\":30.62,\"temperatureHighTime\":1578601980,\"temperatureLow\":25.51,\"temperatureLowTime\":1578620820,\"apparentTemperatureHigh\":23.11,\"apparentTemperatureHighTime\":1578604440,\"apparentTemperatureLow\":20.02,\"apparentTemperatureLowTime\":1578634440,\"dewPoint\":9.79,\"humidity\":0.53,\"pressure\":1035.1,\"windSpeed\":10.7,\"windGust\":32.66,\"windGustTime\":1578556740,\"windBearing\":298,\"cloudCover\":0.18,\"uvIndex\":1,\"uvIndexTime\":1578588840,\"visibility\":10,\"ozone\":351.4,\"temperatureMin\":18.34,\"temperatureMinTime\":1578571860,\"temperatureMax\":30.62,\"temperatureMaxTime\":1578601980,\"apparentTemperatureMin\":5.15,\"apparentTemperatureMinTime\":1578571020,\"apparentTemperatureMax\":23.11,\"apparentTemperatureMaxTime\":1578604440},{\"time\":1578632400,\"summary\":\"Mostly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1578658440,\"sunsetTime\":1578691920,\"moonPhase\":0.51,\"precipIntensity\":0.0001,\"precipIntensityMax\":0.0003,\"precipIntensityMaxTime\":1578712140,\"precipProbability\":0.07,\"precipType\":\"snow\",\"precipAccumulation\":0,\"temperatureHigh\":49.16,\"temperatureHighTime\":1578682380,\"temperatureLow\":43.66,\"temperatureLowTime\":1578700920,\"apparentTemperatureHigh\":43.2,\"apparentTemperatureHighTime\":1578682560,\"apparentTemperatureLow\":38.16,\"apparentTemperatureLowTime\":1578700800,\"dewPoint\":33.2,\"humidity\":0.77,\"pressure\":1032.6,\"windSpeed\":11.7,\"windGust\":38.63,\"windGustTime\":1578668700,\"windBearing\":218,\"cloudCover\":0.83,\"uvIndex\":1,\"uvIndexTime\":1578675240,\"visibility\":9.58,\"ozone\":324.8,\"temperatureMin\":26.54,\"temperatureMinTime\":1578632400,\"temperatureMax\":49.16,\"temperatureMaxTime\":1578682380,\"apparentTemperatureMin\":20.02,\"apparentTemperatureMinTime\":1578634440,\"apparentTemperatureMax\":43.2,\"apparentTemperatureMaxTime\":1578682560},{\"time\":1578718800,\"summary\":\"Possible drizzle overnight.\",\"icon\":\"wind\",\"sunriseTime\":1578744840,\"sunsetTime\":1578778380,\"moonPhase\":0.55,\"precipIntensity\":0.0004,\"precipIntensityMax\":0.0047,\"precipIntensityMaxTime\":1578805200,\"precipProbability\":0.3,\"precipType\":\"rain\",\"temperatureHigh\":62.56,\"temperatureHighTime\":1578787080,\"temperatureLow\":45.23,\"temperatureLowTime\":1578831000,\"apparentTemperatureHigh\":62.06,\"apparentTemperatureHighTime\":1578787080,\"apparentTemperatureLow\":38.15,\"apparentTemperatureLowTime\":1578830940,\"dewPoint\":53.06,\"humidity\":0.88,\"pressure\":1021.9,\"windSpeed\":14.94,\"windGust\":47.79,\"windGustTime\":1578805200,\"windBearing\":214,\"cloudCover\":0.94,\"uvIndex\":1,\"uvIndexTime\":1578761640,\"visibility\":9.08,\"ozone\":313.6,\"temperatureMin\":46.95,\"temperatureMinTime\":1578718800,\"temperatureMax\":63.99,\"temperatureMaxTime\":1578805200,\"apparentTemperatureMin\":42.4,\"apparentTemperatureMinTime\":1578718800,\"apparentTemperatureMax\":63.56,\"apparentTemperatureMaxTime\":1578805200},{\"time\":1578805200,\"summary\":\"Light rain and dangerously windy in the morning.\",\"icon\":\"rain\",\"sunriseTime\":1578831180,\"sunsetTime\":1578864840,\"moonPhase\":0.58,\"precipIntensity\":0.0052,\"precipIntensityMax\":0.0236,\"precipIntensityMaxTime\":1578829260,\"precipProbability\":0.88,\"precipType\":\"rain\",\"temperatureHigh\":46.79,\"temperatureHighTime\":1578834000,\"temperatureLow\":28.75,\"temperatureLowTime\":1578899280,\"apparentTemperatureHigh\":38.91,\"apparentTemperatureHighTime\":1578834000,\"apparentTemperatureLow\":22.09,\"apparentTemperatureLowTime\":1578898980,\"dewPoint\":42.83,\"humidity\":0.92,\"pressure\":1015.8,\"windSpeed\":14.83,\"windGust\":56.11,\"windGustTime\":1578818700,\"windBearing\":239,\"cloudCover\":0.51,\"uvIndex\":2,\"uvIndexTime\":1578848220,\"visibility\":9.47,\"ozone\":299.2,\"temperatureMin\":31.52,\"temperatureMinTime\":1578891600,\"temperatureMax\":64.17,\"temperatureMaxTime\":1578807960,\"apparentTemperatureMin\":25.49,\"apparentTemperatureMinTime\":1578891600,\"apparentTemperatureMax\":63.86,\"apparentTemperatureMaxTime\":1578808320},{\"time\":1578891600,\"summary\":\"Overcast throughout the day.\",\"icon\":\"cloudy\",\"sunriseTime\":1578917580,\"sunsetTime\":1578951300,\"moonPhase\":0.62,\"precipIntensity\":0.0062,\"precipIntensityMax\":0.0298,\"precipIntensityMaxTime\":1578953400,\"precipProbability\":0.36,\"precipType\":\"rain\",\"temperatureHigh\":39.49,\"temperatureHighTime\":1578952080,\"temperatureLow\":37.12,\"temperatureLowTime\":1578995820,\"apparentTemperatureHigh\":35.94,\"apparentTemperatureHighTime\":1578960000,\"apparentTemperatureLow\":33.62,\"apparentTemperatureLowTime\":1579002360,\"dewPoint\":35.2,\"humidity\":0.96,\"pressure\":1028.1,\"windSpeed\":5.03,\"windGust\":13.65,\"windGustTime\":1578895620,\"windBearing\":74,\"cloudCover\":0.95,\"uvIndex\":1,\"uvIndexTime\":1578934440,\"visibility\":9.667,\"ozone\":297.5,\"temperatureMin\":28.75,\"temperatureMinTime\":1578899280,\"temperatureMax\":39.93,\"temperatureMaxTime\":1578970260,\"apparentTemperatureMin\":22.09,\"apparentTemperatureMinTime\":1578898980,\"apparentTemperatureMax\":39.42,\"apparentTemperatureMaxTime\":1578970800},{\"time\":1578978000,\"summary\":\"Light rain in the evening and overnight.\",\"icon\":\"rain\",\"sunriseTime\":1579003920,\"sunsetTime\":1579037760,\"moonPhase\":0.66,\"precipIntensity\":0.0044,\"precipIntensityMax\":0.0171,\"precipIntensityMaxTime\":1579057500,\"precipProbability\":0.78,\"precipType\":\"rain\",\"temperatureHigh\":45.11,\"temperatureHighTime\":1579030140,\"temperatureLow\":27.42,\"temperatureLowTime\":1579089420,\"apparentTemperatureHigh\":41.28,\"apparentTemperatureHighTime\":1579030500,\"apparentTemperatureLow\":21.31,\"apparentTemperatureLowTime\":1579089720,\"dewPoint\":38.48,\"humidity\":0.95,\"pressure\":1021.5,\"windSpeed\":5.83,\"windGust\":16.01,\"windGustTime\":1579052940,\"windBearing\":48,\"cloudCover\":1,\"uvIndex\":1,\"uvIndexTime\":1579020600,\"visibility\":9.189,\"ozone\":328.9,\"temperatureMin\":37.12,\"temperatureMinTime\":1578995820,\"temperatureMax\":45.11,\"temperatureMaxTime\":1579030140,\"apparentTemperatureMin\":33.1,\"apparentTemperatureMinTime\":1579057320,\"apparentTemperatureMax\":41.28,\"apparentTemperatureMaxTime\":1579030500},{\"time\":1579064400,\"summary\":\"Possible light rain in the morning.\",\"icon\":\"rain\",\"sunriseTime\":1579090320,\"sunsetTime\":1579124220,\"moonPhase\":0.7,\"precipIntensity\":0.0036,\"precipIntensityMax\":0.0178,\"precipIntensityMaxTime\":1579075260,\"precipProbability\":0.78,\"precipType\":\"rain\",\"temperatureHigh\":46.32,\"temperatureHighTime\":1579114920,\"temperatureLow\":36.3,\"temperatureLowTime\":1579147200,\"apparentTemperatureHigh\":44.52,\"apparentTemperatureHighTime\":1579121220,\"apparentTemperatureLow\":32.22,\"apparentTemperatureLowTime\":1579153920,\"dewPoint\":35.44,\"humidity\":0.93,\"pressure\":1020,\"windSpeed\":4.79,\"windGust\":27.34,\"windGustTime\":1579091820,\"windBearing\":242,\"cloudCover\":0.49,\"uvIndex\":1,\"uvIndexTime\":1579107420,\"visibility\":9.812,\"ozone\":359.6,\"temperatureMin\":27.42,\"temperatureMinTime\":1579089420,\"temperatureMax\":46.32,\"temperatureMaxTime\":1579114920,\"apparentTemperatureMin\":21.31,\"apparentTemperatureMinTime\":1579089720,\"apparentTemperatureMax\":44.52,\"apparentTemperatureMaxTime\":1579121220}]},\"flags\":{\"sources\":[\"nwspa\",\"cmc\",\"gfs\",\"hrrr\",\"icon\",\"isd\",\"madis\",\"nam\",\"sref\",\"darksky\",\"nearest-precip\"],\"nearest-station\":0.462,\"units\":\"us\"},\"offset\":-5}\r\n" + 
	    		"";

	}
}
