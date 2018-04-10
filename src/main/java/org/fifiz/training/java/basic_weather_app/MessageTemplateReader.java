/**
 * 
 */
package org.fifiz.training.java.basic_weather_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;

/**
 * @author bertrand
 *
 */
public class MessageTemplateReader {
	private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(MessageTemplateReader.class);

	/**
	 * Retourne le contenu du fichier result.tpl ;-)
	 *
	 * @return
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
				// Logger.getLogger(MessageTemplateReader.class.getName()).log(Level.SEVERE,
				// null, ex);
				LOG.error("Oups", io);
			} finally {
				try {
					if (inS != null) {
						inS.close();
					}
				} catch (IOException ex) {
					// Logger.getLogger(MessageTemplateReader.class.getName()).log(Level.SEVERE,
					// null, ex);
					LOG.error("Oups", ex);
				}
			}
		}

		return resultat;
	}
}
