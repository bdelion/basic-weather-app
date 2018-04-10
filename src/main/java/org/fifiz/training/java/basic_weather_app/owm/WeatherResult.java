/**
 * 
 */
package org.fifiz.training.java.basic_weather_app.owm;

import java.util.List;

/**
 * @author bertrand
 *
 */
public class WeatherResult {

	private String name;
	private Main main;
	private Wind wind;
	private List<Weather> weather;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

}
