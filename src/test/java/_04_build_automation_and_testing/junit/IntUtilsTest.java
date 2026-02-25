package _04_build_automation_and_testing.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatNonPrimesReturnFalse() {

        assertFalse(IntUtils.isPrime(0));
        assertFalse(IntUtils.isPrime(1));
        assertFalse(IntUtils.isPrime(4));
        assertFalse(IntUtils.isPrime(6));

        // Negatives are not prime
        assertFalse(IntUtils.isPrime(-5));
        assertFalse(IntUtils.isPrime(-7));
        assertFalse(IntUtils.isPrime(-121990));

        // Large non-prime
        assertFalse(IntUtils.isPrime(8000));
    }

    @Test
    void testThatPrimesReturnTrue() {
        assertTrue(IntUtils.isPrime(2));
        assertTrue(IntUtils.isPrime(3));
        assertTrue(IntUtils.isPrime(5));
        assertTrue(IntUtils.isPrime(7));
        assertTrue(IntUtils.isPrime(11));

        // Large prime
        assertTrue(IntUtils.isPrime(7919));
    }

    @Test
    void testThatParseIntWorksForValidInput() throws Exception {
        assertEquals(123, IntUtils.parseInt("123"));
        assertEquals(-456, IntUtils.parseInt("-456"));
        assertEquals(0, IntUtils.parseInt("0"));
    }

    @Test
    void testThatParseIntThrowsForInvalidInput() {
        assertThrows(ParseException.class, () -> IntUtils.parseInt("abc"));
        assertThrows(ParseException.class, () -> IntUtils.parseInt("12.34"));
        assertThrows(ParseException.class, () -> IntUtils.parseInt(""));
    }

}