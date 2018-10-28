/**
 * Classe de lecture des messages à partir d'un template
 * @author bertrand
 */

package org.fifiz.training.java.basic_weather_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;

/**
 * MessageTemplateReader.
 * @author bertrand
 */
public final class MessageTemplateReader {
    private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(MessageTemplateReader.class);

    private MessageTemplateReader() {
        // not called
    }

    /**
     * Retourne le contenu du fichier result.tpl ;-).
     *
     * @return String
     */
    public static String read() {
        // ouverture du fichier
        InputStream inS = Thread.currentThread().getContextClassLoader().getResourceAsStream("result.tpl");
        // [TODEL]
        LOG.info("Ouverture Stream");

        // ouverture buffer pour lecture
        String resultat = "";
        BufferedReader buffer = null;
        if (inS != null) {
            buffer = new BufferedReader(new InputStreamReader(inS));
            // boucle sur le contenu
            String line;
            try {
                while ((line = buffer.readLine()) != null) {
                    resultat = resultat.concat(line).concat(System.getProperty("line.separator"));
                }
            } catch (IOException io) {
                LOG.error("Oups ! Pb à la lecture du flux.", io);
            } finally {
                try {
                    if (inS != null) {
                        inS.close();
                    }
                } catch (IOException ex) {
                    LOG.error("Oups ! Pb à la fermeture du flux.", ex);
                }
            }
        }

        return resultat;
    }
}
