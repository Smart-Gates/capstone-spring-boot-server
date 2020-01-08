package smartgate.capstonespringboot.models;

class WeatherCurrent {
	
	private int time;
	private String summary;
	private String icon;
	private double precipIntensity;
	private double precipProbability;
	private String precipType;
	private double temperature;
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
	public double getPrecipIntensity() {
		return precipIntensity;
	}
	public void setPrecipIntensity(double precipIntensity) {
		this.precipIntensity = precipIntensity;
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
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
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
