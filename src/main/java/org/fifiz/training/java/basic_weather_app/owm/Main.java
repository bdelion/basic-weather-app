package org.fifiz.training.java.basic_weather_app.owm;

/**
 * Classe principale.
 *
 * @author bertrand
 */
public class Main {

    private Double temp;
    private Double pressure;
    private Double humidity;
    private Double temp_min;
    private Double temp_max;
    private Double sea_level;
    private Double grnd_level;

    public Double getTemp() {
        return this.temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return this.pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return this.temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getSea_level() {
        return this.sea_level;
    }

    public void setSea_level(Double sea_level) {
        this.sea_level = sea_level;
    }

    public Double getGrnd_level() {
        return this.grnd_level;
    }

    public void setGrnd_level(Double grnd_level) {
        this.grnd_level = grnd_level;
    }
}
