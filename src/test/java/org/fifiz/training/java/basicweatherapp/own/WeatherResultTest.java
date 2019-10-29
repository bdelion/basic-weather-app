package org.fifiz.training.java.basicweatherapp.own;

import static org.junit.Assert.assertEquals;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.fifiz.training.java.basicweatherapp.owm.WeatherResult;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for WeatherResult.
 *
 * @author bertrand
 */
public class WeatherResultTest {

  private ObjectMapper objectMapper;
  
  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();
    // attention à  la configuration du mapper
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  public void whenReadFromFile_thanCorrect() throws Exception {
      File resource = new File("src/test/resources/owm_ok.json");

      WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

      assertEquals(new Integer("0"), fromFile.getId());
      assertEquals("Paris", fromFile.getName());
      assertEquals(new Integer("200"), fromFile.getCod());
      assertEquals("stations", fromFile.getBase());
      assertEquals(new Integer("10000"), fromFile.getVisibility());
      assertEquals(new Integer("1572333005"), fromFile.getDt());
      assertEquals(new Float("48.85"), fromFile.getCoord().getLat());
      assertEquals(new Float("2.35"), fromFile.getCoord().getLon());
      assertEquals("500", fromFile.getWeather().get(0).getId());
      assertEquals("Rain", fromFile.getWeather().get(0).getMain());
      assertEquals("light rain", fromFile.getWeather().get(0).getDescription());
      assertEquals("10d", fromFile.getWeather().get(0).getIcon());
      assertEquals(new Double("280.69"), fromFile.getMain().getTemp());
      assertEquals(new Double("1020"), fromFile.getMain().getPressure());
      assertEquals(new Double("87"), fromFile.getMain().getHumidity());
      assertEquals(new Double("279.82"), fromFile.getMain().getTempMin());
      assertEquals(new Double("281.48"), fromFile.getMain().getTempMax());
      assertEquals(new Double("4.1"), fromFile.getWind().getSpeed());
      assertEquals(new Double("40"), fromFile.getWind().getDeg());
      assertEquals(new Integer("100"), fromFile.getClouds().getAll());
      assertEquals(new Integer("1"), fromFile.getSys().getType());
      assertEquals(new Integer("6550"), fromFile.getSys().getId());
      assertEquals("FR", fromFile.getSys().getCountry());
      assertEquals(new Integer("1572330714"), fromFile.getSys().getSunrise());
      assertEquals(new Integer("1572366995"), fromFile.getSys().getSunset());
  }

  @Test
  public void whenReadFromFile_thanCityNotFound() throws Exception {
      File resource = new File("src/test/resources/owm_citynotfound.json");

      WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);
      
      assertEquals("Mauvais code retour", new Integer("404"), fromFile.getCod());
  }
}