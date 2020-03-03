package fr.fifiz.training.app.java.own;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URL;

import com.github.tomakehurst.wiremock.WireMockServer;

import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.fifiz.training.app.java.owm.OwmClient;
import fr.fifiz.training.app.java.owm.SysInternal;
import fr.fifiz.training.app.java.owm.TechnicalException;
import fr.fifiz.training.app.java.owm.WeatherResult;

/**
 * Test OwmClient.
 *
 * @author bertrand
 */
public class OwmClientTest {
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
    //
    private static final Float TEST_MSG = 0.003f;
    //
    private static final String TEST_COUNTRY = "FR";
    //
    private static final Integer TEST_SUNRISE = 1448522306;
    //
    private static final Integer TEST_SUNSET = 1448554781;

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

    /**
     * Test OwmClient Rule.
     *
     * @author bertrand
     */
    /*
     * @Rule // No-args constructor defaults to port 8080 // 0 to dynamic port
     * public WireMockRule wireMR = new WireMockRule(0);
     */

    @Test
    public void testGetWeatherURLOk() throws IOException {
        (new WeatherStub(wireMockServer, WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient client = new OwmClient(
                new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port())).replace(LOCAL_URL_PATH,
                        WEATHER_API_PATH)));
        WeatherResult weatherResult = client.getWeather();
        assertEquals("Niort", weatherResult.getName());
    }

    @Test
    public void testGetWeatherCPOk() throws IOException {
        (new WeatherStub(wireMockServer, WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient client = new OwmClient("49000");
        WeatherResult weatherResult = client.getWeather();
        assertEquals("Écouflant", weatherResult.getName());
    }

    @Test
    public void testGetWeather404() throws IOException {
        (new WeatherStub(wireMockServer, WEATHER_API_PATH, Response.SC_NOT_FOUND)).stub();

        assertThrows(TechnicalException.class, () -> {
            OwmClient client = new OwmClient(
                    new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port()))
                            .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
            client.getWeather();
        });
    }

    /**
     * Mes Tests.
     *
     * @author bertrand
     * @throws IOException
     */
    @Test
    public void testGetWeatherBaseOk() throws IOException {
        (new WeatherStub(wireMockServer, WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient owmC = new OwmClient(new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port()))
                .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
        WeatherResult weatherR = owmC.getWeather();
        assertEquals("cmc stations", weatherR.getBase());
    }

    /**
     * Mes Tests.
     *
     * @author bertrand
     * @throws IOException
     */
    @Test
    public void testGetWeatherSysInternalsOk() throws IOException {
        (new WeatherStub(wireMockServer, WEATHER_API_PATH, Response.SC_OK, OWN_CURRENT_RESULT_OK)).stub();

        OwmClient owmC = new OwmClient(new URL(LOCAL_URL.replace(LOCAL_URL_PORT, String.valueOf(wireMockServer.port()))
                .replace(LOCAL_URL_PATH, WEATHER_API_PATH)));
        WeatherResult weatherR = owmC.getWeather();

        SysInternal sysInt = new SysInternal();
        sysInt.setMessage(TEST_MSG);
        sysInt.setCountry(TEST_COUNTRY);
        sysInt.setSunrise(TEST_SUNRISE);
        sysInt.setSunset(TEST_SUNSET);
        assertEquals(sysInt.getMessage(), weatherR.getSys().getMessage(), "Messages égaux.");
        assertEquals(sysInt.getCountry(), weatherR.getSys().getCountry(), "Country égaux.");
        assertEquals(sysInt.getSunrise(), weatherR.getSys().getSunrise(), "Sunrise égaux.");
        assertEquals(sysInt.getSunset(), weatherR.getSys().getSunset(), "Sunset égaux.");
    }
}
