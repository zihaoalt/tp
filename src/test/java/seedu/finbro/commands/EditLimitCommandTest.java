package seedu.finbro.commands;

import org.junit.jupiter.api.Test;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.utils.ExpenseList;
import seedu.finbro.utils.Limit;
import seedu.finbro.exception.FinbroException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditLimitCommandTest {
    //@@author WangZX2001
    @Test
    public void execute_replaceLimit_success() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();
        Storage storage = new Storage("./data/test-finbro.txt");

        // 3 = replace
        // 800 = new limit
        // yes = confirm
        ui.setInputs("3", "800", "yes");

        Limit.setLimit(500.0);

        EditCommand command = new EditCommand("limit");
        command.execute(expenses, ui, storage);

        assertEquals(800.0, Limit.getLimit());
    }
    //@@author WangZX2001
    @Test
    public void execute_increaseLimit_success() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();
        Storage storage = new Storage("./data/test-finbro.txt");

        // 1 = increase
        // 100 = amount
        // yes = confirm
        ui.setInputs("1", "100", "yes");

        Limit.setLimit(500.0);

        EditCommand command = new EditCommand("limit");
        command.execute(expenses, ui, storage);

        assertEquals(600.0, Limit.getLimit());
    }
    //@@author WangZX2001
    @Test
    public void execute_decreaseLimit_success() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();
        Storage storage = new Storage("./data/test-finbro.txt");

        // 2 = decrease
        // 200 = amount
        // yes = confirm
        ui.setInputs("2", "200", "yes");

        Limit.setLimit(500.0);

        EditCommand command = new EditCommand("limit");
        command.execute(expenses, ui, storage);

        assertEquals(300.0, Limit.getLimit());
    }
    //@@author WangZX2001
    private static class TestUi extends Ui {
        private String[] inputs;
        private int index = 0;

        public void setInputs(String... inputs) {
            this.inputs = inputs;
            this.index = 0;
        }

        @Override
        public String readCommand() {
            return inputs[index++];
        }

        @Override
        public void showLimitEditMenu(double currentLimit) { }

        @Override
        public void showEnterAmountPrompt(String action) { }

        @Override
        public void showLimit() { }
    }
}