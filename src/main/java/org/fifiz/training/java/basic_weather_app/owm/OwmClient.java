package org.fifiz.training.java.basic_weather_app.owm;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe client d'appel à openweathermap.
 * @author bertrand
 */
// Open weather map api key :
// appid=8c05dfed7d5d0d8ba3a2bc70b83b227f
public class OwmClient {

    private static final Logger LOG = LogManager.getLogger(OwmClient.class.getName());

    /**
     * URL du serveur.
     */
    private final URL owmUrlClient;

    private ObjectMapper jsonMapper;

    /**
     * Constructor
     * @author bertrand
     */
    public OwmClient(URL owmUrl) {
        LOG.info("Entering OwmClient : " + owmUrl);

        this.owmUrlClient = owmUrl;
        this.jsonMapper = new ObjectMapper();
        // attention à  la configuration du mapper
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        LOG.info("Exiting OwmClient.");
    }

    /**
     * @author bertrand
     * @return weatherResult d'une apple
     */
    public WeatherResult getWeather(String codePostal) {
        LOG.info("Entering getWeather : " + codePostal);

        String urlApiOwm = "http://api.openweathermap.org/data/2.5/weather?zip=" + codePostal
                + ",fr&APPID=8c05dfed7d5d0d8ba3a2bc70b83b227f";

        // déclarations de variables locales
        WeatherResult weatherResult = null;
        HttpURLConnection owmConnection = null;

        // lire le flux et le convertir en objet
        try {
            URL owmUrl = new URL(urlApiOwm);
            owmConnection = (HttpURLConnection) owmUrl.openConnection();
            // sortie en erreur si le code retour est KO <>200
            if (owmConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new TechnicalException(
                        "Statut de la réponse invalide (code retour = '" + owmConnection.getResponseCode()
                                + "' / message = '" + owmConnection.getResponseMessage() + "')");
            }
            // pour avoir une sortie structurée du flux : http://json.parser.online.fr/
            weatherResult = this.jsonMapper.readValue(owmConnection.getInputStream(), WeatherResult.class);
        } catch (MalformedURLException ex) {
            throw new TechnicalException("Oups ! Pb sur l'URL", ex);
        } catch (IOException ex) {
            throw new TechnicalException("Oups ! I/O erreur", ex);
        } finally {
            if (owmConnection != null) {
                owmConnection.disconnect();
            }
        }
        return weatherResult;
    }

    public URL getOwnUrl() {
        return owmUrlClient;
    }

    public ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

}
