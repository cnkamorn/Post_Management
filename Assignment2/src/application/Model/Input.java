package application.Model;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import application.Exception.InvalidDateTimeFormatException;
import application.Exception.NegativeNumberException;

/**
 * This class is an input model class
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class Input extends Exception {

	private static Input Instance;

	private Input() {
	};

	public static Input getInstance() {
		if (Instance == null) {
			Instance = new Input();
		}
		return Instance;
	}

	public boolean acceptIntegerInput(String input) throws NumberFormatException, NegativeNumberException {
		try {
			int intInput = Integer.parseInt(input);
			if (intInput < 0) {
				throw new NegativeNumberException("Negative number Exception");
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			throw new NumberFormatException("Invalid arguament, please input only number");
		}
		return true;
	}

	public boolean acceptDateTime(String input) throws InvalidDateTimeFormatException {
		// (DD/MM/YYYY HH:MM)
		try {
			String[] splitString = input.split("[/: ]");
			int[] dateTimeSplit = new int[splitString.length];
			for (int i = 0; i < splitString.length; i++) {
				dateTimeSplit[i] = Integer.parseInt(splitString[i]);
			}
			LocalDateTime dateTime = LocalDateTime.of(dateTimeSplit[2], dateTimeSplit[1], dateTimeSplit[0],
					dateTimeSplit[3], dateTimeSplit[4]);
		} catch (DateTimeException e) {
			throw new InvalidDateTimeFormatException(
					"Invalid input format. Please input the format of DD/MM/YYYY HH:MM");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidDateTimeFormatException(
					"Invalid input format. Please input the format of DD/MM/YYYY HH:MM");
		} catch (NumberFormatException e) {
			throw new InvalidDateTimeFormatException(
					"Invalid input format. Please input the format of DD/MM/YYYY HH:MM");
		}
		return true;
	}

}
