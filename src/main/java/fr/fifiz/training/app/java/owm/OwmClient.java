package fr.fifiz.training.app.java.owm;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe client d'appel à openweathermap.
 *
 * @author bertrand
 */
// Open weather map api key :
// appid=8c05dfed7d5d0d8ba3a2bc70b83b227f
public class OwmClient {

    private static final Logger LOG = LogManager.getLogger(OwmClient.class.getName());

    /**
     * URL du serveur.
     */
    private URL owmUrlClient;

    private ObjectMapper jsonMapper;

    /**
     * Constructor.
     *
     * @author bertrand
     */
    public OwmClient(URL urlClient) {
        LOG.debug("urlClient : " + urlClient);

        this.owmUrlClient = urlClient;
        this.jsonMapper = new ObjectMapper();
        // attention à  la configuration du mapper
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        LOG.debug("this.owmUrlClient : " + this.getOwmUrlClient());
    }

    /**
     * Constructor.
     *
     * @author bertrand
     * @throws MalformedURLException
     */
    public OwmClient(String codePostal) throws MalformedURLException {
        String urlApiOwm = "http://api.openweathermap.org/data/2.5/weather?zip={codePostal},fr&units=metric&lang=fr&APPID=8c05dfed7d5d0d8ba3a2bc70b83b227f";

        LOG.debug("codePostal : " + codePostal);
        urlApiOwm = urlApiOwm.replace("{codePostal}", codePostal);
        LOG.info("urlApiOwm.replace : " + urlApiOwm);

        this.owmUrlClient = new URL(urlApiOwm);
        this.jsonMapper = new ObjectMapper();
        // attention à  la configuration du mapper
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        LOG.debug("this.owmUrlClient : " + this.getOwmUrlClient());
    }

    /**
     * @author bertrand
     * @return weatherResult d'une apple
     */
    public WeatherResult getWeather() {
        // déclarations de variables locales
        WeatherResult weatherResult = null;
        HttpURLConnection owmConnection = null;

        LOG.debug("this.owmUrlClient : " + this.getOwmUrlClient());

        // lire le flux et le convertir en objet
        try {
            owmConnection = (HttpURLConnection) this.owmUrlClient.openConnection();
            // sortie en erreur si le code retour est KO <>200
            if (owmConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                LOG.warn("Connection response code <> 200: " + owmConnection.getResponseCode());
                throw new TechnicalException(
                        "Statut de la réponse invalide (code retour = '" + owmConnection.getResponseCode()
                                + "' / message = '" + owmConnection.getResponseMessage() + "')");
            }
            // pour avoir une sortie structurée du flux : http://json.parser.online.fr/
            weatherResult = this.jsonMapper.readValue(owmConnection.getInputStream(), WeatherResult.class);
        } catch (ConnectException ex) {
            LOG.warn("Could not connect to client supplied url: " + this.getOwmUrlClient(), ex);
            throw new TechnicalException("Oups ! Impossible de se connecter à l'URL fournie par le client.", ex);
        } catch (MalformedURLException ex) {
            LOG.error("Malformed client supplied url: " + this.getOwmUrlClient(), ex);
            throw new TechnicalException("Oups ! URL fournie par le client mal formée", ex);
        } catch (IOException ex) {
            LOG.warn("Could not connect to client supplied url: " + this.getOwmUrlClient(), ex);
            throw new TechnicalException("Oups ! Impossible de se connecter à l'URL fournie par le client.", ex);
        } finally {
            if (owmConnection != null) {
                owmConnection.disconnect();
            }
        }
        return weatherResult;
    }

    public URL getOwmUrlClient() {
        return this.owmUrlClient;
    }

    public void setOwmUrlClient(URL owmUrlClient) {
        this.owmUrlClient = owmUrlClient;
    }

    public ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

}
