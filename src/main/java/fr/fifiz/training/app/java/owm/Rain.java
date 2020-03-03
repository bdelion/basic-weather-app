package fr.fifiz.training.app.java.owm;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe des volumes de pluie de http://api.openweathermap.org.
 * 
 * @author bertrand
 */
public class Rain {

    @JsonProperty("1h")
    private Double oneHour;
    @JsonProperty("3h")
    private Double threeHour;

    public Double getOneHour() {
        return this.oneHour;
    }

    public void setOneHour(Double oneHour) {
        this.oneHour = oneHour;
    }

    public Double getThreeHour() {
        return this.threeHour;
    }

    public void setThreeHour(Double threeHour) {
        this.threeHour = threeHour;
    }

}