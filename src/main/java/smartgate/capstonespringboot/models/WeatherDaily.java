package smartgate.capstonespringboot.models;

public class WeatherDaily {
	
	private int time;
	private String summary;
	private String icon; 
	private double precipIntensityMax;
	private double precipIntensityMaxTime;
	private double precipProbability;
	private String precipType;
	  
	private double temperatureMin;
	private double temperatureMinTime;
	private double temperatureMax;
	private double temperatureMaxTime;
	
	private double humidity;
	private double cloudCover;
	private double visibility;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
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
	public double getPrecipIntensityMax() {
		return precipIntensityMax;
	}
	public void setPrecipIntensityMax(double precipIntensityMax) {
		this.precipIntensityMax = precipIntensityMax;
	}
	public double getPrecipIntensityMaxTime() {
		return precipIntensityMaxTime;
	}
	public void setPrecipIntensityMaxTime(double precipIntensityMaxTime) {
		this.precipIntensityMaxTime = precipIntensityMaxTime;
	}
	public double getPrecipProbability() {
		return precipProbability;
	}
	public void setPrecipProbability(double precipProbability) {
		this.precipProbability = precipProbability;
	}
	public String getPrecipType() {
		return precipType;
	}
	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}
	public double getTemperatureMin() {
		return temperatureMin;
	}
	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}
	public double getTemperatureMinTime() {
		return temperatureMinTime;
	}
	public void setTemperatureMinTime(double temperatureMinTime) {
		this.temperatureMinTime = temperatureMinTime;
	}
	public double getTemperatureMax() {
		return temperatureMax;
	}
	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}
	public double getTemperatureMaxTime() {
		return temperatureMaxTime;
	}
	public void setTemperatureMaxTime(double temperatureMaxTime) {
		this.temperatureMaxTime = temperatureMaxTime;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getCloudCover() {
		return cloudCover;
	}
	public void setCloudCover(double cloudCover) {
		this.cloudCover = cloudCover;
	}
	public double getVisibility() {
		return visibility;
	}
	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}


   
}
