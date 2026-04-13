package seedu.finbro.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import seedu.finbro.exception.FinbroException;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.finances.ExpenseList;

class SetLimitCommandTest {

    //@@author natmloclam
    @Test
    void execute_validInput_noException() {
        String input = "500";

        // dummy objects to fill input
        ExpenseList expenseList = new ExpenseList();
        Ui ui = new Ui();
        Storage storage = new Storage("");

        assertDoesNotThrow(() -> {
            new SetLimitCommand(input).execute(expenseList,ui,storage);
        });
    }

    //@@author natmloclam
    @Test
    void execute_nonNumber_exception() {
        String input = "abc";

        ExpenseList expenseList = new ExpenseList();
        Ui ui = new Ui();
        Storage storage = new Storage("");

        Exception e = assertThrows(FinbroException.class, () -> {
            new SetLimitCommand(input).execute(expenseList,ui,storage);
        });

        String expectedMessage = "Monthly spending limit must be a number";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //@@author natmloclam
    @Test
    void execute_outOfRange_exception() {
        String input = "-4";

        ExpenseList expenseList = new ExpenseList();
        Ui ui = new Ui();
        Storage storage = new Storage("");

        Exception e = assertThrows(FinbroException.class, () -> {
            new SetLimitCommand(input).execute(expenseList,ui,storage);
        });

        String expectedMessage = "Monthly spending limit must be positive";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void execute_lessThanOneCent_exception() {
        String input = "0.001";

        ExpenseList expenseList = new ExpenseList();
        Ui ui = new Ui();
        Storage storage = new Storage("");

        Exception e = assertThrows(FinbroException.class, () -> {
            new SetLimitCommand(input).execute(expenseList,ui,storage);
        });

        String expectedMessage = "Monthly spending limit must be greater than $0.00 (rounded to 2 decimal places)";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
