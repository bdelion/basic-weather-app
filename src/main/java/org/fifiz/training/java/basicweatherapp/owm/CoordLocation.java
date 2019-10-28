package org.fifiz.training.java.basicweatherapp.owm;

/**
 * Classe des coordonnées de localisation météo de http://api.openweathermap.org.
 * @author bertrand
 */
public class CoordLocation {
    private Float lon;
    private Float lat;

    public Float getLon() {
        return this.lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return this.lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

}
