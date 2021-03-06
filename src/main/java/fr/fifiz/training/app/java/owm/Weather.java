package fr.fifiz.training.app.java.owm;

/**
 * Class résumé des données météo de http://api.openweathermap.org.
 * 
 * @author bertrand
 */
public class Weather {

    private String id;
    private String main;
    private String description;
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String pid) {
        this.id = pid;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String pmain) {
        this.main = pmain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pdescription) {
        this.description = pdescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String picon) {
        this.icon = picon;
    }
}
