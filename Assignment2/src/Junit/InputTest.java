package Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.Exception.InvalidDateTimeFormatException;
import application.Exception.NegativeNumberException;
import application.Model.Input;

class InputTest {

	static Input ip;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ip = Input.getInstance();
	}

	@Test
	void integerInput() {
		try {
			assertEquals(true, ip.acceptIntegerInput("9"));
		} catch (NumberFormatException | NegativeNumberException e) {
			System.out.println("fail");
		}
	}

	@Test
	void integerInput2() {
		try {
			assertEquals(false, ip.acceptIntegerInput("A"));
		} catch (NumberFormatException | NegativeNumberException e) {
			System.out.println("fail");
		}
	}

	@Test
	void integerInput3() {
		try {
			assertEquals(false, ip.acceptIntegerInput("00A"));
		} catch (NumberFormatException | NegativeNumberException e) {
			System.out.println("fail");
		}
	}

	@Test
	void integerInput4() {
		try {
			assertEquals(true, ip.acceptIntegerInput("123213"));
		} catch (NumberFormatException | NegativeNumberException e) {
			System.out.println("fail");
		}
	}

	@Test
	void dateTimeInput1() {
		try {
			assertEquals(true, ip.acceptDateTime("2/2/2 00:00"));
		} catch (InvalidDateTimeFormatException e) {
			System.out.println("fail");
		}
	}

	@Test
	void dateTimeInput2() {
		try {
			assertEquals(true, ip.acceptDateTime("02/02/2222 23:59"));
		} catch (InvalidDateTimeFormatException e) {
			System.out.println("fail");
		}
	}

	@Test
	void dateTimeInput3() {
		try {
			assertEquals(false, ip.acceptDateTime("02/12/2900 aa:a2"));
		} catch (InvalidDateTimeFormatException e) {
			System.out.println("faie3l");
		}
	}

	@Test
	void dateTimeInput4() {
		try {
			assertEquals(false, ip.acceptDateTime("A/2/2222 12:22"));
		} catch (InvalidDateTimeFormatException e) {
			System.out.println("fail4");
		}
	}
}
