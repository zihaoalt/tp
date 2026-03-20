package seedu.finbro.commands;

import seedu.finbro.utils.ExpenseList;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.exception.FinbroException;
import seedu.finbro.utils.Expense;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    @Override
    public void execute(String input, ExpenseList expenses, Ui ui, Storage storage) throws FinbroException {
        String[] parts = input.split(" ");

        if (parts.length < 4) {
            logger.log(Level.WARNING, "Invalid command format");
            throw new FinbroException("Usage: add <amount> <category> <date>");
        }

        logger.log(Level.INFO,
                "Attempting to add expense amount {0}, category {1}, date {2}",
                new Object[]{parts[1], parts[2], parts[3]});
        try {
            double amount = Double.parseDouble(parts[1]);
            if (amount < 0) {
                logger.log(Level.WARNING, "Invalid amount (amount is negative)");
                throw new FinbroException("Amount must be a positive number.");
            }
            String category = parts[2];
            String dateInput = parts[3];
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            LocalDate parsedDate;
            
            try {
                parsedDate = LocalDate.parse(dateInput, inputFormatter);
            } catch (DateTimeParseException e) {
                logger.log(Level.WARNING, "Invalid date format");
                throw new FinbroException("Invalid date format! Use yyyy-MM-dd");
            }

            String formattedDate = parsedDate.format(outputFormatter);
            Expense expense = new Expense(amount, category, formattedDate);
            expenses.add(expense);
            logger.log(Level.INFO, "Successfully added expense in category " + category + " $" + amount);
            ui.showExpenseAdded(expense, expenses.size());
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid number in expense amount");
            throw new FinbroException("Amount must be a number.");
        }
    }

    @Override
    public String getHelpMessage() {
        return """
                Adds a new expense entry.
                Format: add <amount> <category> <date>
                Use: Records an expense under the given category on the given date.
                Note: amount must be positive and date must be in yyyy-mm-dd format.""";
    }
}
