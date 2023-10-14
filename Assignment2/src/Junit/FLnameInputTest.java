package Junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import application.Exception.RetypeException;
import application.Model.ChangeFirstName;
import application.Model.ChangeLastName;

class FLnameInputTest {
	static ChangeFirstName FN;
	static ChangeLastName LN;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		FN = ChangeFirstName.getInstance();
		LN = ChangeLastName.getInstance();
	}

	@Test
	void whiteSpace1() {
		assertEquals(false, FN.checkWhiteSpace("A"));
		assertEquals(false, LN.checkWhiteSpace("A"));
	}

	@Test
	void whiteSpace2() {
		assertEquals(true, FN.checkWhiteSpace("A "));
		assertEquals(true, LN.checkWhiteSpace(" A"));
	}

	@Test
	void whiteSpace3() {
		assertEquals(true, FN.checkWhiteSpace("A A"));
		assertEquals(true, LN.checkWhiteSpace("A A"));
	}

	@Test
	void whiteSpace4() {
		assertEquals(true, FN.checkWhiteSpace(" "));
		assertEquals(true, LN.checkWhiteSpace(" "));
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields1() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FN.checkMatchingRetypeFirstName("NEWFN", "newFn ");
		});

		Throwable exception2 = assertThrows(RetypeException.class, () -> {
			LN.checkMatchingRetypeLastName("NEWLN", "newL ");
		});
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields2() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FN.checkMatchingRetypeFirstName("a", "A");
		});

		Throwable exception2 = assertThrows(RetypeException.class, () -> {
			LN.checkMatchingRetypeLastName("B", "b");
		});
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields3() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FN.checkMatchingRetypeFirstName("A A", "AA ");
		});

		Throwable exception2 = assertThrows(RetypeException.class, () -> {
			LN.checkMatchingRetypeLastName("BBB", "bbb");
		});
	}

	/**
	 * if 2 fields do not match, throw the exception.
	 */
	@Test
	void matchingFields4() {
		Throwable exception = assertThrows(RetypeException.class, () -> {
			FN.checkMatchingRetypeFirstName("AAAA", "aaaa");
		});

		Throwable exception2 = assertThrows(RetypeException.class, () -> {
			LN.checkMatchingRetypeLastName("B  A", "A  B ");
		});
	}
}
