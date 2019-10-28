package org.fifiz.training.java.basicweatherapp;

import java.net.MalformedURLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fifiz.training.java.basicweatherapp.owm.OwmClient;
import org.fifiz.training.java.basicweatherapp.owm.TechnicalException;
import org.fifiz.training.java.basicweatherapp.owm.WeatherResult;

public class BasicWeatherAppApplication {
	private static final Logger MYLOGGER = LogManager.getLogger(BasicWeatherAppApplication.class);
    private static final Double DELTATEMP = -273.15;
    private static final String FORMATTEMP = "%.2f °C";
    private static final String FORMATSEPARATOR = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

	/**
	 * Main method, used to run the application.
	 * 
	 * @param args the command line arguments
	 * @throws MalformedURLException
	 * 
	 * @author bertrand
	 */
	public static void main(String[] args) throws MalformedURLException {
		MYLOGGER.info("Start");

		// On demande à l'utilisateur de saisir le code postal de la ville dont il veut
		// la météo
		System.out.println("Veuillez saisir le code postal de la ville dont vous souhaitez avoir la météo : ");
		Scanner sc = new Scanner(System.in);
		String codePostal = sc.nextLine();
		MYLOGGER.info("codePostal : " + codePostal);

		// appel au service de météo
		OwmClient owmC = new OwmClient(codePostal);
		MYLOGGER.info("owmC : " + owmC);
		try {
			WeatherResult cpWeather = owmC.getWeather();
			MYLOGGER.info("cpWeather : " + cpWeather);
			String msgTmpl = MessageTemplateReaderUtils.read();
			MYLOGGER.info("msgTmpl : " + msgTmpl);
			if (!"".equals(msgTmpl)) {
				System.out.println(FORMATSEPARATOR);
				System.out.println(msgTmpl.replace("{city}", cpWeather.getName()).replace("{cp}", codePostal)
						.replace("{country}", cpWeather.getSys().getCountry()).replace("{base}", cpWeather.getBase())
						.replace("{lon}", String.valueOf(cpWeather.getCoord().getLon()))
						.replace("{lat}", String.valueOf(cpWeather.getCoord().getLat()))
						.replace("{weather.main}", cpWeather.getWeather().get(0).getMain())
						.replace("{weather.description}", cpWeather.getWeather().get(0).getDescription())
						.replace("{temp}", String.format(FORMATTEMP, cpWeather.getMain().getTemp() + DELTATEMP))
						.replace("{temp-min}", String.format(FORMATTEMP, cpWeather.getMain().getTempMin() + DELTATEMP))
						.replace("{temp-max}", String.format(FORMATTEMP, cpWeather.getMain().getTempMax() + DELTATEMP))
						.replace("{humid}", String.format("%.2f", cpWeather.getMain().getHumidity()))
						.replace("{wind}", String.format("%.2f m/sec", cpWeather.getWind().getSpeed())));
				System.out.println(FORMATSEPARATOR);
			} else {
				System.out.println(FORMATSEPARATOR);
				System.out.println("Template vide !");
				System.out.println(FORMATSEPARATOR);
			}
		} catch (TechnicalException te) {
			te.printStackTrace(System.err);
		} finally {
			sc.close();
		}

		MYLOGGER.info("Finish");
	}

}
