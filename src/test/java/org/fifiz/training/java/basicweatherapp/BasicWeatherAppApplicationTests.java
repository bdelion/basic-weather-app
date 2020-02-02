package org.fifiz.training.java.basicweatherapp;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * Unit test for BasicWeatherAppApplication simple App.
 *
 * @author bertrand
 */
public class BasicWeatherAppApplicationTests {

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void contextLoads() {
		assertTrue(true);
	}

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
