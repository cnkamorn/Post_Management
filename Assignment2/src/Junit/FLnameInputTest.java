package Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.Exception.RetypeException;
import application.Model.ChangeFLName;

class FLnameInputTest {
	static ChangeFLName FLN;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FLN = ChangeFLName.getInstance();
	}

	@Test
	void whiteSpace1() {
		assertEquals(false, FLN.checkWhiteSpace("A"));
	}

	@Test
	void whiteSpace2() {
		assertEquals(true, FLN.checkWhiteSpace("A "));
	}

	@Test
	void whiteSpace3() {
		assertEquals(true, FLN.checkWhiteSpace("A A"));
	}

	@Test
	void whiteSpace4() {
		assertEquals(true, FLN.checkWhiteSpace(" "));
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields1() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FLN.checkMatchingRetype("NEWFN", "newFn ", true);
		});
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields2() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FLN.checkMatchingRetype("a", "A", true);
		});

	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields3() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FLN.checkMatchingRetype("A A", "AA ", true);
		});
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields4() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FLN.checkMatchingRetype("AAAA", "aaaa", true);
		});
	}
}
