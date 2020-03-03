package fr.fifiz.training.app.java.own;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import org.apache.commons.io.IOUtils;

/**
 * WeatherStub.
 *
 * @author bertrand
 */
public class WeatherStub implements StubCase {
    private final WireMockServer wireMockServer;
    private final String path;
    private final int status;
    private final String jsonFileName;

    /**
     * WeatherStub without json file.
     *
     * @author bertrand
     */
    public WeatherStub(final WireMockServer wireMockServer, final String path, final int status) {
        this.wireMockServer = wireMockServer;
        this.path = path;
        this.status = status;
        this.jsonFileName = null;
    }

    /**
     * WeatherStub with json file.
     *
     * @author bertrand
     */
    public WeatherStub(final WireMockServer wireMockServer, final String path, final int status,
            final String jsonFileName) {
        this.wireMockServer = wireMockServer;
        this.path = path;
        this.status = status;
        this.jsonFileName = jsonFileName;
    }

    @Override
    public void stub() {
        try {
            final ResponseDefinitionBuilder response = aResponse().withStatus(status).withHeader("Content-type",
                    "application/json");

            if (this.jsonFileName != null) {
                response.withBody(IOUtils.toByteArray(
                        Thread.currentThread().getContextClassLoader().getResourceAsStream(this.jsonFileName)));
            }
            this.wireMockServer.stubFor(get(urlEqualTo(path)).willReturn(response));
        } catch (final IOException ex) {
            throw new TestException(ex);
        }
    }

}
