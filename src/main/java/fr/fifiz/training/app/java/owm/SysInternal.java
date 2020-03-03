package fr.fifiz.training.app.java.owm;

/**
 * Classe des information système et interne météo de http://api.openweathermap.org.
 * @author bertrand
 */
public class SysInternal {
    private Integer type;
    private Integer id;
    private Float message;
    private String country;
    private Integer sunrise;
    private Integer sunset;

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMessage() {
        return this.message;
    }

    public void setMessage(Float message) {
        this.message = message;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return this.sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return this.sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }
}
