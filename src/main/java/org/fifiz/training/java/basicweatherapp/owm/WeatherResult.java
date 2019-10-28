package org.fifiz.training.java.basicweatherapp.owm;

import java.util.List;

/**
 * Class résultat météo de http://api.openweathermap.org.
 * @author bertrand
 */
public class WeatherResult {

    private Integer id;
    private String name;
    private Integer cod;
    private String base;
    private Integer visibility;
    private Integer dt;
    private CoordLocation coord;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private SysInternal sys;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return this.cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Integer getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getDt() {
        return this.dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public CoordLocation getCoord() {
        return this.coord;
    }

    public void setCoord(CoordLocation coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return this.wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public SysInternal getSys() {
        return this.sys;
    }

    public void setSys(SysInternal sys) {
        this.sys = sys;
    }
}
