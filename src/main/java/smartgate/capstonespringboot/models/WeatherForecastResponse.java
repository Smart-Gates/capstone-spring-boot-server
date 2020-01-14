package smartgate.capstonespringboot.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// including the @JsonIgnoreProperties the JSON can be mapped to the Objects
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastResponse implements Serializable {

	private static final long serialVersionUID = -6795599522573345184L;
	
	private long latitude;
	private long longitude;
	private String timezone;
	private WeatherCurrently currently;
	private WeatherDaily daily;
	
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
	public WeatherCurrently getCurrently() {
		return currently;
	}
	public void setCurrently(WeatherCurrently currently) {
		this.currently = currently;
	}
	public WeatherDaily getDaily() {
		return daily;
	}
	public void setDaily(WeatherDaily daily) {
		this.daily = daily;
	}
}
