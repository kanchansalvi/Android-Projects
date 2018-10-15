package model;

public class CurrentCondition {

    private int weatherID;
    private String condition;
    private String description;
    private String icon;
    private Float pressure;
    private Float humidity;
    private Float maxtemp;
    private Float mintemp;

    public int getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(int weatherID) {
        this.weatherID = weatherID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(Float maxtemp) {
        this.maxtemp = maxtemp;
    }

    public Float getMintemp() {
        return mintemp;
    }

    public void setMintemp(Float mintemp) {
        this.mintemp = mintemp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    private double temperature;
}
