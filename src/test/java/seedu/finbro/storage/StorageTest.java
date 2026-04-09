package seedu.finbro.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.finbro.exception.FinbroException;

class StorageTest {
    @Test
    void verifyDateFormat_validDate_correctFormat() throws FinbroException {
        String input = "4 April 2026";
        Storage storage = new Storage("");
        String output = storage.verifyDateFormat(input);
        assertEquals("4 April 2026", output);
    }

    @Test
    void verifyDateFormat_invalidDate_throwsException() {
        String input = "abc";
        Storage storage = new Storage("");

        Exception e = assertThrows(FinbroException.class, () -> {
            storage.verifyDateFormat(input);
        });

        String expectedMessage = "Invalid date format";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyDateFormat_nullString_throwsException() {
        String input = null;
        Storage storage = new Storage("");

        Exception e = assertThrows(FinbroException.class, () -> {
            storage.verifyDateFormat(input);
        });

        String expectedMessage = "Null string";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void verifyDateFormat_emptyString_throwsException() {
        String input = "";
        Storage storage = new Storage("");

        Exception e = assertThrows(FinbroException.class, () -> {
            storage.verifyDateFormat(input);
        });

        String expectedMessage = "Invalid date format";
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseAmount_validDouble_inputAsDouble() {
        String input = "10.0";
        Storage storage = new Storage("");

        double output =  storage.parseAmount(input);
        assertEquals(10.0, output);
    }

    @Test
    void parseAmount_invalidDouble_zeroDouble() {
        String input = "abc";
        Storage storage = new Storage("");

        double output = storage.parseAmount(input);
        assertEquals(0.0, output);
    }

    @Test
    void parseAmount_nullString_zeroDouble() {
        Storage storage = new Storage("");

        double output = storage.parseAmount(null);
        assertEquals(0.0, output);
    }

    @Test
    void parseAmount_emptyString_zeroDouble() {
        Storage storage = new Storage("");

        double output = storage.parseAmount("");
        assertEquals(0.0, output);
    }
}
