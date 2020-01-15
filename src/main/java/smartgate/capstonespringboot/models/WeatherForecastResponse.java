package smartgate.capstonespringboot.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// including the @JsonIgnoreProperties the JSON can be mapped to the Objects
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastResponse implements Serializable {

	private static final long serialVersionUID = -6795599522573345184L;
	
	private String latitude;
	private String longitude;
	private String timezone;
	private WeatherCurrently currently;
	private WeatherDaily daily;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
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
