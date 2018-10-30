package org.fifiz.training.java.basic_weather_app.own;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

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
    private final static String WEATHER_API_PATH = "/current";

    @Test
    public void testGetWeatherNameOk() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, 200, "owm_current_niort_ok.json")).stub();

        OwmClient client = new OwmClient(new URL("http://localhost:{port}{path}"
                .replace("{port}", String.valueOf(wireMR.port())).replace("{path}", WEATHER_API_PATH)));
        WeatherResult weatherResult = client.getWeather();
        assertEquals("Niort", weatherResult.getName());
    }

    @Test(expected = TechnicalException.class)
    public void testGetWeather404() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, 404)).stub();

        OwmClient client = new OwmClient(new URL("http://localhost:{port}{path}"
                .replace("{port}", String.valueOf(wireMR.port())).replace("{path}", WEATHER_API_PATH)));
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
        (new WeatherStub(WEATHER_API_PATH, 200, "owm_current_niort_ok.json")).stub();

        OwmClient owmC = new OwmClient(new URL("http://localhost:{port}{path}"
                .replace("{port}", String.valueOf(wireMR.port())).replace("{path}", WEATHER_API_PATH)));
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
    public void testGetWeather_Ok_SysInternals() throws IOException {
        (new WeatherStub(WEATHER_API_PATH, 200, "owm_current_niort_ok.json")).stub();

        OwmClient owmC = new OwmClient(new URL("http://localhost:{port}{path}"
                .replace("{port}", String.valueOf(wireMR.port())).replace("{path}", WEATHER_API_PATH)));
        WeatherResult weatherR = owmC.getWeather();

        SysInternal sysInt = new SysInternal();
        sysInt.setMessage( 0.003f);
        sysInt.setCountry("FR");
        sysInt.setSunrise(1448522306);
        sysInt.setSunset(1448554781);
        //assertEquals("Les 2 objets sont égaux.", sysInt, weatherR.getSys());
    }
}