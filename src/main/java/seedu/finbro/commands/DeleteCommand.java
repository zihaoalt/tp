package seedu.finbro.commands;

import seedu.finbro.utils.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.exception.FinbroException;
import seedu.finbro.utils.Expense;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    @Override
    public void execute(String input, ExpenseList expenses, Ui ui, Storage storage) throws FinbroException {
        String[] parts = input.split(" ");

        if (parts.length < 3) {
            logger.log(Level.WARNING, "Invalid command format");
            throw new FinbroException("Usage: delete <category> #<number>");
        }

        logger.log(Level.INFO, "Attempting to delete expense in category " + parts[1] + " #" + parts[2]);
        try {
            int number = Integer.parseInt(parts[2]);
            String category = parts[1];

            Expense expense = expenses.removeByCategoryIndex(category, number);
            logger.log(Level.INFO, "Successfully deleted expense in category " + category + " #" + number);
            ui.showExpenseRemoved(expense, expenses.size());
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid category index number");
            throw new FinbroException("Expense number must be a number.");
        } catch (FinbroException e) {
            logger.log(Level.WARNING, e.getMessage());
            throw e;
        }
    }

    @Override
    public String getHelpMessage() {
        return """
                Deletes a specific expense from a category.
                Format: delete <category> <number>
                Use: Permanently removes the numbered expense in that category.
                Note: use the displayed index number, such as 2.""";
    }
}
