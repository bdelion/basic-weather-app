package fr.fifiz.training.app.java.owm;

/**
 * Classe des données sur le vent de http://api.openweathermap.org.
 * 
 * @author bertrand
 */
public class Wind {

    private Double speed;
    private Double deg;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

  public Double getDeg() {
    return deg;
  }

    public void setDeg(Double deg) {
        this.deg = deg;
    }
}
