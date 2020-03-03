package fr.fifiz.training.app.java.own;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.fifiz.training.app.java.owm.WeatherResult;

/**
 * Unit test for WeatherResult.
 *
 * @author bertrand
 */
public class WeatherResultTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
    // attention à  la configuration du mapper
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Test
  public void testDataReturnIfCityFound() throws Exception {
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
  public void testDataReturnIfCityNotFound() throws Exception {
    File resource = new File("src/test/resources/owm_citynotfound.json");

    WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

    assertEquals(new Integer("404"), fromFile.getCod(), "Mauvais code retour");
  }

  @Test
  public void testRainDataReturnIfCityFound() throws Exception {
    File resource = new File("src/test/resources/owm_data_rain.json");

    WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

    assertEquals("Rain", fromFile.getWeather().get(0).getMain());
    assertEquals("légère pluie", fromFile.getWeather().get(0).getDescription());
    assertEquals(new Double("0.25"), fromFile.getRain().getOneHour());
    assertEquals(new Double("0.35"), fromFile.getRain().getThreeHour());
  }

  @Test
  public void testSnowDataReturnIfCityFound() throws Exception {
    File resource = new File("src/test/resources/owm_data_snow.json");

    WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

    assertEquals("Snow", fromFile.getWeather().get(0).getMain());
    assertEquals("légères chutes de neige", fromFile.getWeather().get(0).getDescription());
    assertEquals(new Double("0.03"), fromFile.getSnow().getOneHour());
    assertEquals(new Double("0.13"), fromFile.getSnow().getThreeHour());
  }
}