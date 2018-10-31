package org.fifiz.training.java.basic_weather_app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fifiz.training.java.basic_weather_app.owm.OwmClient;
import org.fifiz.training.java.basic_weather_app.owm.TechnicalException;
import org.fifiz.training.java.basic_weather_app.owm.WeatherResult;

/**
 * Application Java basique et autonome donnant la meteo pour un code postal en
 * France.
 * 
 * @author bertrand
 */
class WeatherApp {
    private static final Logger MYLOGGER = LogManager.getLogger(WeatherApp.class);
    private static final Double DELTATEMP = -273.15;
    private static final String FORMATTEMP = "%.2f °C";
    private static final String FORMATSEPARATOR = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";

    /**
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        // To run this application from the command line, try:
        // java -jar javaBasicTraining.jar

        MYLOGGER.info("Start");

        // On demande à l'utilisateur de saisir le code postal de la ville dont il veut
        // la météo
        System.out.println("Veuillez saisir le code postal de la ville dont vous souhaitez avoir la météo : ");
        Scanner sc = new Scanner(System.in);
        String codePostal = sc.nextLine();

        // appel au service de météo
        // OwmClient owmc = new OwmClient(codePostal);
        String urlApiOwm = "http://api.openweathermap.org/data/2.5/weather?zip={codePostal},fr&APPID=8c05dfed7d5d0d8ba3a2bc70b83b227f";

        MYLOGGER.info("codePostal : " + codePostal);
        MYLOGGER.info("urlApiOwm : " + urlApiOwm);
        urlApiOwm = urlApiOwm.replace("{codePostal}", codePostal);
        MYLOGGER.info("urlApiOwm.replace : " + urlApiOwm);

        URL owmUrlClient = new URL(urlApiOwm);
        OwmClient owmc = new OwmClient(owmUrlClient);
        try {
            WeatherResult cpWeather = owmc.getWeather();
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
                        .replace("{temp-min}", String.format(FORMATTEMP, cpWeather.getMain().getTemp_min() + DELTATEMP))
                        .replace("{temp-max}", String.format(FORMATTEMP, cpWeather.getMain().getTemp_max() + DELTATEMP))
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
    }
}
