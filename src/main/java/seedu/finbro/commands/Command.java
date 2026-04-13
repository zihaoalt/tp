package seedu.finbro.commands;

import seedu.finbro.exception.FinbroException;
import seedu.finbro.finances.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;

public abstract class Command {
    public abstract void execute(ExpenseList expenseList, Ui ui, Storage storage) throws FinbroException;

    public abstract String getHelpMessage();
    
    public boolean checksBudget() {
        return false;
    }
}
