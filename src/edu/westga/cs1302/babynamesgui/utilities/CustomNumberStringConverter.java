package edu.westga.cs1302.babynamesgui.utilities;

import javafx.util.converter.NumberStringConverter;

/**
 * My customized number string converter which deals with pesky errors and
 * implementation problems.
 * 
 * @version Spring 2024
 * @author Colby
 */

public class CustomNumberStringConverter extends NumberStringConverter {

	@Override
	public Number fromString(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

	@Override
	public String toString(Number number) {
		if (number != null) {
			try {
				return number.toString();
			} catch (Exception ex) {
				System.out.println("hey");
				return "";
			}
		} else {
			return "";
		}
	}

}
