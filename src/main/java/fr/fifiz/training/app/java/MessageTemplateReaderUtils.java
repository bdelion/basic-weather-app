package fr.fifiz.training.app.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe de lecture des messages à partir d'un template.
 *
 * @author bertrand
 */
public final class MessageTemplateReaderUtils {
    private static final Logger MYLOGGER = LogManager.getLogger(MessageTemplateReaderUtils.class);

    private MessageTemplateReaderUtils() {
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
                MYLOGGER.error("Oups ! Pb à la lecture du flux.", io);
            } finally {
                try {
                    inS.close();
                    MYLOGGER.info("Fermeture Stream");
                } catch (IOException ex) {
                    MYLOGGER.error("Oups ! Pb à la fermeture du flux.", ex);
                }
            }
        }
        return resultat;
    }
}
