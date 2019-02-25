package org.fifiz.training.java.basic_weather_app.own;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.eclipse.jetty.server.Response;
import org.fifiz.training.java.basic_weather_app.owm.OwmClient;
import org.fifiz.training.java.basic_weather_app.owm.SysInternal;
import org.fifiz.training.java.basic_weather_app.owm.TechnicalException;
import org.fifiz.training.java.basic_weather_app.owm.WeatherResult;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test OwmClient.
 *
 * @author bertrand
 */
public class OwmClientTest {
    @Rule
    // No-args constructor defaults to port 8080
    // 0 to dynamic port
    public WireMockRule wireMR = new WireMockRule(0);
    // Chemin de la ressource l'api current weather de OWN
    private static final String WEATHER_API_PATH = "/current";
    //
    private static final String OWN_CURRENT_RESULT_OK = "owm_current_niort_ok.json";
    //
    private static final String LOCAL_URL = "http://localhost:{port}{path}";
    //
    private static final String LOCAL_URL_PORT = "{port}";
    //
    private static final String LOCAL_URL_PATH = "{path}";

    @Test
    public void testGetWeatherNameOk() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient client = new OwmClient(new URL(LOCAL_URL
                .replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
        WeatherResult weatherResult = client.getWeather();
        assertEquals("Niort", weatherResult.getName());
    }

    @Test(expected = TechnicalException.class)
    public void testGetWeather404() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, Response.SC_NOT_FOUND)).stub();

        OwmClient client = new OwmClient(new URL(LOCAL_URL
                .replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
        client.getWeather();
    }

    /**
     * Mes Tests
     *
     * @author bertrand
     * @throws IOException
     */
    @Test
    public void testGetWeatherBaseOk() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient owmC = new OwmClient(new URL(LOCAL_URL
                .replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
        WeatherResult weatherR = owmC.getWeather();
        assertEquals("cmc stations", weatherR.getBase());
    }

    /**
     * Mes Tests
     *
     * @author bertrand
     * @throws IOException
     */
    @Test
    public void testGetWeatherSysInternalsOk() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient owmC = new OwmClient(new URL(LOCAL_URL
                .replace(LOCAL_URL_PORT, String.valueOf(wireMR.port())).replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
        WeatherResult weatherR = owmC.getWeather();

        SysInternal sysInt = new SysInternal();
        sysInt.setMessage(0.003f);
        sysInt.setCountry("FR");
        sysInt.setSunrise(1448522306);
        sysInt.setSunset(1448554781);
        assertEquals("Messages égaux.", sysInt.getMessage(), weatherR.getSys().getMessage());
        assertEquals("Country égaux.", sysInt.getCountry(), weatherR.getSys().getCountry());
        assertEquals("Sunrise égaux.", sysInt.getSunrise(), weatherR.getSys().getSunrise());
        assertEquals("Sunset égaux.", sysInt.getSunset(), weatherR.getSys().getSunset());
    }
}
