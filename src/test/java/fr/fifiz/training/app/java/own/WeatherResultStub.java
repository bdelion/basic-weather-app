package fr.fifiz.training.app.java.own;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import org.apache.commons.io.IOUtils;

/**
 * WeatherResultStub.
 *
 * @author bertrand
 */
public class WeatherResultStub implements StubCase {
    private final WireMockServer wireMockServer;
    private final String path;
    private final int status;
    private final String jsonFileName;

    /**
     * WeatherResultStub without json file.
     *
     * @author bertrand
     */
    public WeatherResultStub(final WireMockServer wireMockServer, String path, int status) {
        this.wireMockServer = wireMockServer;
        this.path = path;
        this.status = status;
        this.jsonFileName = null;
    }

    /**
     * WeatherResultStub with json file.
     *
     * @author bertrand
     */
    public WeatherResultStub(final WireMockServer wireMockServer, String path, int status, String jsonFileName) {
        this.wireMockServer = wireMockServer;
        this.path = path;
        this.status = status;
        this.jsonFileName = jsonFileName;
    }

    @Override
    public void stub() {
        try {
            ResponseDefinitionBuilder response = aResponse().withStatus(status).withHeader("Content-type",
                    "application/json");

            if (this.jsonFileName != null) {
                response.withBody(IOUtils.toByteArray(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream(this.jsonFileName)));
            }

            this.wireMockServer.stubFor(get(urlEqualTo(path)).willReturn(response));
        } catch (IOException ex) {
            throw new TestException(ex);
        }
    }

}