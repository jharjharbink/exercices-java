package org.example.exo.thermometre;

import static org.example.exo.thermometre.UniteTemperature.*;

public class Main {
    public static void main(String[] args) {
        Thermometre termo1 = new Thermometre(10, Celsius);

        System.out.println("température en Celsius: " + termo1.getTemperature() + "°C");
        System.out.println("température en Kelvin: " + termo1.celsiusToKelvin() + "°K");
        System.out.println("température en Fahrenheit: " + termo1.celsiusToFahrenheit() + "°F");
    }
}
