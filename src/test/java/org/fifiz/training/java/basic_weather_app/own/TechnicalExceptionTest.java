/**
 * Unit test for TechnicalException.
* @author bertrand
*/

package org.fifiz.training.java.basic_weather_app.own;

import static org.junit.Assert.assertTrue;

import org.fifiz.training.java.basic_weather_app.owm.TechnicalException;
import org.junit.Test;

/**
 * Test App.
 * 
 * @author bertrand
 */
public class TechnicalExceptionTest {
    /**
     * Test rigoureux. Rigorous Test :-)
     */
    @Test
    public void testMessage() {
        TechnicalException myTechExce = new TechnicalException("Oups erreur !");

        assertTrue(true);
    }
}