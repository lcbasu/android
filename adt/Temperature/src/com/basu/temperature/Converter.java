package com.basu.temperature;

public class Converter {
	
	public static float converCelsiusToFahrenheit(float celsius) {
		return ((celsius - 32) * 5 / 9);
	}
	
	public static float convertFahrenheitToCelsius(float fahrenheit) {
		return ((fahrenheit * 9) / 5) + 32;
	}

}
