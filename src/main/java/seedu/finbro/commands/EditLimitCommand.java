package seedu.finbro.commands;

import seedu.finbro.utils.ExpenseList;
import seedu.finbro.parser.Parser;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.exception.FinbroException;
import seedu.finbro.utils.Limit;

import java.util.logging.Logger;
import java.util.logging.Level;

public class EditLimitCommand extends Command {

    private static final Logger logger = Logger.getLogger(EditLimitCommand.class.getName());

    @Override
    public void execute(String input, ExpenseList expenseList, Ui ui, Storage storage) throws FinbroException {
        double currentLimit = Limit.getLimit();
        logger.log(Level.INFO, "Starting EditLimitCommand. Current limit: " + currentLimit);

        ui.showLimitEditMenu(currentLimit);
        String choice = ui.readCommand().trim();
        logger.log(Level.INFO, "User selected option: " + choice);

        double newLimit;

        switch (choice) {
        case "1":
            ui.showEnterAmountPrompt("increase");
            newLimit = currentLimit + Parser.parsePositiveAmount(ui.readCommand().trim());
            break;
        case "2":
            ui.showEnterAmountPrompt("decrease");
            newLimit = currentLimit - Parser.parsePositiveAmount(ui.readCommand().trim());
            if (newLimit < 0) {
                throw new FinbroException("Monthly spending limit must be at least $0");
            }
            break;
        case "3":
            ui.showEnterAmountPrompt("replace");
            newLimit = Parser.parsePositiveAmount(ui.readCommand().trim());
            break;
        default:
            throw new FinbroException("Please enter 1, 2, or 3.");
        }

        Limit.setLimit(newLimit, ui);
        ui.showLimit();
    }

    @Override
    public String getHelpMessage() {
        return """
                Edits the existing monthly spending limit.
                Format: edit limit
                Use: Starts the process to update the current budget limit.""";
    }
}
