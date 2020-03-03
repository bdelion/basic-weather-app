package fr.fifiz.training.app.java.own;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.fifiz.training.app.java.owm.OwmClient;
import fr.fifiz.training.app.java.owm.TechnicalException;
import fr.fifiz.training.app.java.owm.WeatherResult;

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

  WireMockServer wireMockServer;

  @BeforeEach
  public void setup() {
    wireMockServer = new WireMockServer(0);
    wireMockServer.start();
  }

  @AfterEach
  public void teardown() {
    wireMockServer.stop();
  }

  @Test
  public void testCouldNotConnectToClientIfUrlIsMalformed() throws IOException {
    (new WeatherResultStub(wireMockServer, WEATHER_API_PATH, HttpURLConnection.HTTP_NOT_FOUND)).stub();
    OwmClient client = new OwmClient(new URL(LOCAL_URL_MALFORMED
        .replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
    try {
      WeatherResult weatherResult = client.getWeather();
      fail("Should of thrown an MalformedURLException");
    } catch (TechnicalException ex) {
      assertEquals("Oups ! Impossible de se connecter à l'URL fournie par le client.", ex.getMessage());
    }
  }

  @Test
  public void testJsonReturnIfCityNotFound() throws IOException {
    (new WeatherResultStub(wireMockServer, WEATHER_API_PATH, HttpURLConnection.HTTP_OK, OWN_RESULT_CITY_NOT_FOUND))
        .stub();
    OwmClient client = new OwmClient(new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port()))
        .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));

    WeatherResult weatherResult = null;

    weatherResult = client.getWeather();
    assertEquals(new Integer("404"), weatherResult.getCod());
    assertEquals("city not found", weatherResult.getMessage());
  }

  @Test
  public void testTechnicalExceptionIfCityNotFound() throws IOException {
    (new WeatherResultStub(wireMockServer, WEATHER_API_PATH, HttpURLConnection.HTTP_NOT_FOUND,
        OWN_RESULT_CITY_NOT_FOUND)).stub();
    OwmClient client = new OwmClient(new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port()))
        .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));

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
    (new WeatherResultStub(wireMockServer, WEATHER_API_PATH, HttpURLConnection.HTTP_OK, OWN_RESULT_OK)).stub();
    OwmClient client = new OwmClient(new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port()))
        .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
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
    String zipCode = "75000";
    String apiUrl = "/data/2.5/weather?zip=" + zipCode
        + ",fr&units=metric&lang=fr&APPID=8c05dfed7d5d0d8ba3a2bc70b83b227f";

    (new WeatherResultStub(wireMockServer, apiUrl, HttpURLConnection.HTTP_OK, OWN_RESULT_OK)).stub();
    OwmClient client = new OwmClient(zipCode);
    client.setOwmUrlClient(new URL(
        LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port())).replace(LOCAL_URL_PATH, apiUrl)));
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
