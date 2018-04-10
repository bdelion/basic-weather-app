/**
 * 
 */
package org.fifiz.training.java.basic_weather_app;

import java.util.Scanner;

import org.fifiz.training.java.basic_weather_app.owm.OwmClient;
import org.fifiz.training.java.basic_weather_app.owm.TechnicalException;
import org.fifiz.training.java.basic_weather_app.owm.WeatherResult;

/**
 * @author bertrand
 *
 */
public class WeatherApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// To run this application from the command line, try:
		// java -jar javaBasicTraining.jar

		// On demande à l'utilisateur de saisir le code postal de la ville dont il veut
		// la météo
		System.out.println("Veuillez saisir le code postal de la ville dont vous souhaitez avoir la météo : ");
		Scanner sc = new Scanner(System.in);
		String codePostal = sc.nextLine();
		// [TODEL] System.out.println("Code postal de la ville recherchée : " +
		// codePostal);

		// appel au service de météo
		OwmClient owmc = new OwmClient(null);
		try {
			// [TODEL] owmc.getWeather();
			// [TODEL] System.out.println(MessageTemplateReader.read());
			WeatherResult cpWeather = owmc.getWeather(codePostal);

			String msgTmpl = MessageTemplateReader.read();

			if (!"".equals(msgTmpl)) {
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println(msgTmpl.replace("{city}", cpWeather.getName()).replace("{cp}", codePostal)
						.replace("{temp}", String.format("%.2f °C", cpWeather.getMain().getTemp() - 273.15))
						.replace("{temp-min}", String.format("%.2f °C", cpWeather.getMain().getTemp_min() - 273.15))
						.replace("{temp-max}", String.format("%.2f °C", cpWeather.getMain().getTemp_max() - 273.15))
						.replace("{humid}", String.format("%.2f", cpWeather.getMain().getHumidity()))
						.replace("{wind}", String.format("%.2f m/sec", cpWeather.getWind().getSpeed())));
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			} else {
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				System.out.println("Template vide !");
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
			}
		} catch (TechnicalException te) {
			te.printStackTrace(System.err);
		}
	}
}
