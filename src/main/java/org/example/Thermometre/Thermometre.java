package org.example.Thermometre;

public class Thermometre {
    double temperature;
    UniteTemperature unit;
    double CELSIUS_OFFSET = 273.15;
    double FAHREINHEIT_OFFSET = 32;
    double KELVIN_FAHREINHEIT_OFFSET = 459.67;
    double FAHREINHEIT_CONVERTER = 9/5;

    // Constructor
    public Thermometre(double temperatureKelvin, UniteTemperature unit) {
        this.temperature = temperatureKelvin;
        this.unit = unit;
    }

    // Useful methods
    public double kelvinToCelsius(){
        return temperature - CELSIUS_OFFSET;
    }

    public double celsiusToKelvin(){
        return temperature + CELSIUS_OFFSET;
    }

    public double celsiusToFahrenheit(){
        return temperature * (1/FAHREINHEIT_CONVERTER) + FAHREINHEIT_OFFSET;
    }

    public double fahrenheitToCelsius(){
        return (temperature - FAHREINHEIT_OFFSET) * FAHREINHEIT_CONVERTER;
    }

    public double kelvinToFahrenheit(){
        return temperature * (1/FAHREINHEIT_CONVERTER) - KELVIN_FAHREINHEIT_OFFSET;
    }

    public double fahrenheitToKelvin(){
        return (temperature + KELVIN_FAHREINHEIT_OFFSET) * FAHREINHEIT_CONVERTER;
    }

    // Getter & Setter
    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public UniteTemperature getUnit() {
        return unit;
    }

    public void setUnit(UniteTemperature unit) {
        this.unit = unit;
    }
}
