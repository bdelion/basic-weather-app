package org.fifiz.training.java.basicweatherapp;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicWeatherAppApplication {
	private static final Logger MYLOGGER = LogManager.getLogger(BasicWeatherAppApplication.class);
	
	/**
     * @param args
     * @throws MalformedURLException
     */
	public static void main(String[] args) throws MalformedURLException {
		MYLOGGER.info("Begin");
		MYLOGGER.info("End");
	}

}
