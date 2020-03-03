package fr.fifiz.training.app.java;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

/**
 * Unit test for BasicWeatherAppApplication simple App.
 *
 * @author bertrand
 */
public class BasicWeatherAppApplicationTests {

	@Test
	public void testMain() throws IOException {
		String[] args = null;
		final InputStream original = System.in;
		ByteArrayInputStream in = new ByteArrayInputStream("79000".getBytes());
		System.setIn(in);
		BasicWeatherAppApplication.main(args);
		System.setIn(original);
	}
}
