package com.basu.temperature;

public class Converter {
	
	public static double converCelsiusToFahrenheit(double celsius) {
		return ((celsius - 32) * 5 / 9);
	}
	
	public static double convertFahrenheitToCelsius(double fahrenheit) {
		return ((fahrenheit * 9) / 5) + 32;
	}

}
