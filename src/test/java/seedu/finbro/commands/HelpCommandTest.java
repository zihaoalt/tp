package seedu.finbro.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.finbro.exception.FinbroException;
import seedu.finbro.ui.Ui;
import seedu.finbro.finances.ExpenseList;

public class HelpCommandTest {
    //@@author zihaoalt
    @Test
    public void execute_validCommandArgument_showsSpecificCommandHelp() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();

        HelpCommand command = new HelpCommand("add");
        command.execute(expenses, ui, null);

        assertEquals(0, ui.helpMessageCount);
        assertEquals(1, ui.commandHelpMessageCount);
        assertEquals(AddCommand.class, ui.lastCommand.getClass());
        assertEquals(new AddCommand("").getHelpMessage(), ui.lastHelpMessage);
    }
    //@@author zihaoalt
    @Test
    public void execute_validMultiWordMixedCaseArgument_showsSpecificCommandHelp() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();

        HelpCommand command = new HelpCommand("EDIT LIMIT");
        command.execute(expenses, ui, null);

        assertEquals(0, ui.helpMessageCount);
        assertEquals(1, ui.commandHelpMessageCount);
        assertEquals(EditCommand.class, ui.lastCommand.getClass());
        assertEquals(new EditCommand("limit").getHelpMessage(), ui.lastHelpMessage);
    }
    //@@author zihaoalt
    @Test
    public void execute_helpArgument_showsHelpCommandHelp() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();

        HelpCommand command = new HelpCommand("help");
        command.execute(expenses, ui, null);

        assertEquals(0, ui.helpMessageCount);
        assertEquals(1, ui.commandHelpMessageCount);
        assertEquals(HelpCommand.class, ui.lastCommand.getClass());
        assertEquals(new HelpCommand("").getHelpMessage(), ui.lastHelpMessage);
    }
    //@@author zihaoalt
    @Test
    public void execute_invalidCommandArgument_showsDefaultHelpMessage() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();

        HelpCommand command = new HelpCommand("unknown");
        command.execute(expenses, ui, null);

        assertEquals(1, ui.helpMessageCount);
        assertEquals(0, ui.commandHelpMessageCount);
        assertEquals(new HelpCommand("unknown").getHelpMessage(), ui.lastHelpMessage);
    }
    //@@author zihaoalt
    @Test
    public void execute_blankArgument_showsDefaultHelpMessage() throws FinbroException {
        ExpenseList expenses = new ExpenseList();
        TestUi ui = new TestUi();

        HelpCommand command = new HelpCommand("");
        command.execute(expenses, ui, null);

        assertEquals(1, ui.helpMessageCount);
        assertEquals(0, ui.commandHelpMessageCount);
        assertEquals(new HelpCommand("").getHelpMessage(), ui.lastHelpMessage);
    }
    //@@author zihaoalt
    private static class TestUi extends Ui {
        private int helpMessageCount = 0;
        private int commandHelpMessageCount = 0;
        private String lastHelpMessage = "";
        private Command lastCommand;
        //@@author zihaoalt
        @Override
        public void showHelpMessage(String message) {
            helpMessageCount++;
            lastHelpMessage = message;
        }
        //@@author zihaoalt
        @Override
        public void showCommandHelpMessage(Command command) {
            commandHelpMessageCount++;
            lastCommand = command;
            lastHelpMessage = command.getHelpMessage();
        }
    }
}
