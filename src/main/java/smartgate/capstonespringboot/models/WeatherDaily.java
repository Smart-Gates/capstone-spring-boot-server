package smartgate.capstonespringboot.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDaily {
	private String summary;
	private String icon;
	private List<WeatherData> data;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<WeatherData> getData() {
		return data;
	}
	public void setData(List<WeatherData> data) {
		this.data = data;
	}
}
