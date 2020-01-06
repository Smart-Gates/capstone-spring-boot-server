package smartgate.capstonespringboot.weather.model;

import java.util.List;

public class WeatherForecastResponse {

	private long latitude;
	private long longitude;
	private String timezone;
	private WeatherCurrent weatherCurrent;
	private List<WeatherDaily> weatherDaily;
	
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public WeatherCurrent getWeatherCurrent() {
		return weatherCurrent;
	}
	public void setWeatherCurrent(WeatherCurrent weatherCurrent) {
		this.weatherCurrent = weatherCurrent;
	}
	public List<WeatherDaily> getWeatherDaily() {
		return weatherDaily;
	}
	public void setWeatherDaily(List<WeatherDaily> weatherDaily) {
		this.weatherDaily = weatherDaily;
	}
}
