package org.fifiz.training.java.basicweatherapp.own;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
//TODEL import org.eclipse.jetty.server.Response;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.fifiz.training.java.basicweatherapp.owm.OwmClient;
import org.fifiz.training.java.basicweatherapp.owm.TechnicalException;
import org.fifiz.training.java.basicweatherapp.owm.WeatherResult;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit test for OwmClient.
 *
 * @author bertrand
 */
public class OwmClientTest {
  // Chemin de la ressource l'api current weather de OWN
  private static final String WEATHER_API_PATH = "/current";
  //
  private static final String LOCAL_URL = "http://localhost:{port}{path}";
  //
  private static final String LOCAL_URL_MALFORMED = "http://localhost-{port}{path}";
  //
  private static final String LOCAL_URL_PATH = "{path}";
  //
  private static final String LOCAL_URL_PORT = "{port}";
  //
  private static final String OWN_RESULT_OK = "owm_ok.json";
  //
  private static final String OWN_RESULT_CITY_NOT_FOUND = "owm_citynotfound.json";

  @Rule
  // No-args constructor defaults to port 8080
  // 0 to dynamic port
  public WireMockRule wireMR = new WireMockRule(0);

  @Test
  public void testCouldNotConnectToClientIfUrlIsMalformed() throws IOException {
    (new WeatherResultStub(WEATHER_API_PATH, HttpURLConnection.HTTP_NOT_FOUND)).stub();
    OwmClient client = new OwmClient(new URL(LOCAL_URL_MALFORMED.replace(LOCAL_URL_PORT, String.valueOf(wireMR.port()))
        .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
    try {
      WeatherResult weatherResult = client.getWeather();
      fail("Should of thrown an MalformedURLException");
    } catch (TechnicalException ex) {
      assertEquals("Oups ! Impossible de se connecter à l'URL fournie par le client.", ex.getMessage());
    }
  }

  @Test
  public void testJsonReturnIfCityNotFound() throws IOException {
    (new WeatherResultStub(WEATHER_API_PATH, HttpURLConnection.HTTP_OK, OWN_RESULT_CITY_NOT_FOUND)).stub();
    OwmClient client = new OwmClient(new URL(
        LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));

    WeatherResult weatherResult = null;

    weatherResult = client.getWeather();
    assertEquals(new Integer("404"), weatherResult.getCod());
    assertEquals("city not found", weatherResult.getMessage());
  }

  @Test
  public void testTechnicalExceptionIfCityNotFound() throws IOException {
    (new WeatherResultStub(WEATHER_API_PATH, HttpURLConnection.HTTP_NOT_FOUND, OWN_RESULT_CITY_NOT_FOUND)).stub();
    OwmClient client = new OwmClient(new URL(
        LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));

    try {
      client.getWeather();
      fail("Should of thrown an TechnicalException");
    } catch (TechnicalException ex) {
      assertEquals("Statut de la réponse invalide (code retour = '" + HttpURLConnection.HTTP_NOT_FOUND
          + "' / message = 'Not Found')", ex.getMessage());
    }
  }

  @Test
  public void testResultReturnIfCityFoundByUrl() throws IOException {
    (new WeatherResultStub(WEATHER_API_PATH, HttpURLConnection.HTTP_OK, OWN_RESULT_OK)).stub();
    OwmClient client = new OwmClient(new URL(
        LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
    WeatherResult weatherResult = client.getWeather();

    assertEquals(new Integer("0"), weatherResult.getId());
    assertEquals("Paris", weatherResult.getName());
    assertEquals(new Integer("200"), weatherResult.getCod());
    assertEquals("stations", weatherResult.getBase());
    assertEquals(new Integer("10000"), weatherResult.getVisibility());
    assertEquals(new Integer("1572333005"), weatherResult.getDt());
    assertEquals(new Float("48.85"), weatherResult.getCoord().getLat());
    assertEquals(new Float("2.35"), weatherResult.getCoord().getLon());
    assertEquals("500", weatherResult.getWeather().get(0).getId());
    assertEquals("Rain", weatherResult.getWeather().get(0).getMain());
    assertEquals("light rain", weatherResult.getWeather().get(0).getDescription());
    assertEquals("10d", weatherResult.getWeather().get(0).getIcon());
    assertEquals(new Double("280.69"), weatherResult.getMain().getTemp());
    assertEquals(new Double("1020"), weatherResult.getMain().getPressure());
    assertEquals(new Double("87"), weatherResult.getMain().getHumidity());
    assertEquals(new Double("279.82"), weatherResult.getMain().getTempMin());
    assertEquals(new Double("281.48"), weatherResult.getMain().getTempMax());
    assertEquals(new Double("4.1"), weatherResult.getWind().getSpeed());
    assertEquals(new Double("40"), weatherResult.getWind().getDeg());
    assertEquals(new Integer("100"), weatherResult.getClouds().getAll());
    assertEquals(new Integer("1"), weatherResult.getSys().getType());
    assertEquals(new Integer("6550"), weatherResult.getSys().getId());
    assertEquals("FR", weatherResult.getSys().getCountry());
    assertEquals(new Integer("1572330714"), weatherResult.getSys().getSunrise());
    assertEquals(new Integer("1572366995"), weatherResult.getSys().getSunset());
  }

  @Test
  public void testResultReturnIfCityFoundByZipCode() throws IOException {
    (new WeatherResultStub(WEATHER_API_PATH, HttpURLConnection.HTTP_OK, OWN_RESULT_OK)).stub();
    OwmClient client = new OwmClient("79430");
    WeatherResult weatherResult = client.getWeather();

    assertEquals(new Integer("0"), weatherResult.getId());
    assertEquals("Paris", weatherResult.getName());
    assertEquals(new Integer("200"), weatherResult.getCod());
    assertEquals("stations", weatherResult.getBase());
    assertEquals(new Integer("10000"), weatherResult.getVisibility());
    assertEquals(new Integer("1572333005"), weatherResult.getDt());
    assertEquals(new Float("48.85"), weatherResult.getCoord().getLat());
    assertEquals(new Float("2.35"), weatherResult.getCoord().getLon());
    assertEquals("500", weatherResult.getWeather().get(0).getId());
    assertEquals("Rain", weatherResult.getWeather().get(0).getMain());
    assertEquals("light rain", weatherResult.getWeather().get(0).getDescription());
    assertEquals("10d", weatherResult.getWeather().get(0).getIcon());
    assertEquals(new Double("280.69"), weatherResult.getMain().getTemp());
    assertEquals(new Double("1020"), weatherResult.getMain().getPressure());
    assertEquals(new Double("87"), weatherResult.getMain().getHumidity());
    assertEquals(new Double("279.82"), weatherResult.getMain().getTempMin());
    assertEquals(new Double("281.48"), weatherResult.getMain().getTempMax());
    assertEquals(new Double("4.1"), weatherResult.getWind().getSpeed());
    assertEquals(new Double("40"), weatherResult.getWind().getDeg());
    assertEquals(new Integer("100"), weatherResult.getClouds().getAll());
    assertEquals(new Integer("1"), weatherResult.getSys().getType());
    assertEquals(new Integer("6550"), weatherResult.getSys().getId());
    assertEquals("FR", weatherResult.getSys().getCountry());
    assertEquals(new Integer("1572330714"), weatherResult.getSys().getSunrise());
    assertEquals(new Integer("1572366995"), weatherResult.getSys().getSunset());
  }
}