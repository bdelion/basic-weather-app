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

    assertEquals(Integer.parseInt("0"), fromFile.getId());
    assertEquals("Paris", fromFile.getName());
    assertEquals(Integer.parseInt("200"), fromFile.getCod());
    assertEquals("stations", fromFile.getBase());
    assertEquals(Integer.parseInt("10000"), fromFile.getVisibility());
    assertEquals(Integer.parseInt("1572333005"), fromFile.getDt());
    assertEquals(Float.parseFloat("48.85"), fromFile.getCoord().getLat());
    assertEquals(Float.parseFloat("2.35"), fromFile.getCoord().getLon());
    assertEquals("500", fromFile.getWeather().get(0).getId());
    assertEquals("Rain", fromFile.getWeather().get(0).getMain());
    assertEquals("light rain", fromFile.getWeather().get(0).getDescription());
    assertEquals("10d", fromFile.getWeather().get(0).getIcon());
    assertEquals(Double.parseDouble("280.69"), fromFile.getMain().getTemp());
    assertEquals(Double.parseDouble("1020"), fromFile.getMain().getPressure());
    assertEquals(Double.parseDouble("87"), fromFile.getMain().getHumidity());
    assertEquals(Double.parseDouble("279.82"), fromFile.getMain().getTempMin());
    assertEquals(Double.parseDouble("281.48"), fromFile.getMain().getTempMax());
    assertEquals(Double.parseDouble("4.1"), fromFile.getWind().getSpeed());
    assertEquals(Double.parseDouble("40"), fromFile.getWind().getDeg());
    assertEquals(Integer.parseInt("100"), fromFile.getClouds().getAll());
    assertEquals(Integer.parseInt("1"), fromFile.getSys().getType());
    assertEquals(Integer.parseInt("6550"), fromFile.getSys().getId());
    assertEquals("FR", fromFile.getSys().getCountry());
    assertEquals(Integer.parseInt("1572330714"), fromFile.getSys().getSunrise());
    assertEquals(Integer.parseInt("1572366995"), fromFile.getSys().getSunset());
  }

  @Test
  public void testDataReturnIfCityNotFound() throws Exception {
    File resource = new File("src/test/resources/owm_citynotfound.json");

    WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

    assertEquals(Integer.parseInt("404"), fromFile.getCod(), "Mauvais code retour");
  }

  @Test
  public void testRainDataReturnIfCityFound() throws Exception {
    File resource = new File("src/test/resources/owm_data_rain.json");

    WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

    assertEquals("Rain", fromFile.getWeather().get(0).getMain());
    assertEquals("légère pluie", fromFile.getWeather().get(0).getDescription());
    assertEquals(Double.parseDouble("0.25"), fromFile.getRain().getOneHour());
    assertEquals(Double.parseDouble("0.35"), fromFile.getRain().getThreeHour());
  }

  @Test
  public void testSnowDataReturnIfCityFound() throws Exception {
    File resource = new File("src/test/resources/owm_data_snow.json");

    WeatherResult fromFile = objectMapper.readValue(resource, WeatherResult.class);

    assertEquals("Snow", fromFile.getWeather().get(0).getMain());
    assertEquals("légères chutes de neige", fromFile.getWeather().get(0).getDescription());
    assertEquals(Double.parseDouble("0.03"), fromFile.getSnow().getOneHour());
    assertEquals(Double.parseDouble("0.13"), fromFile.getSnow().getThreeHour());
  }
}