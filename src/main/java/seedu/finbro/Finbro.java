package seedu.finbro;

import seedu.finbro.commands.Command;
import seedu.finbro.exception.FinbroException;
import seedu.finbro.parser.Parser;
import seedu.finbro.storage.Storage;
import seedu.finbro.ui.Ui;
import seedu.finbro.utils.BudgetWarningService;
import seedu.finbro.utils.Expense;
import seedu.finbro.utils.ExpenseList;
import seedu.finbro.utils.LogFormatter;

import java.util.List;

public class Finbro {
    private final Storage storage;
    private final Ui ui;
    private final BudgetWarningService budgetWarningService;
    private ExpenseList expenses;

    public Finbro(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        budgetWarningService = new BudgetWarningService();
        try {
            LogFormatter.initLogger();
            List<Expense> loadedExpenses = storage.load();
            expenses = new ExpenseList(loadedExpenses);
        } catch (FinbroException e) {
            ui.showError("Error loading expenses. Starting with empty list.");
            expenses = new ExpenseList();
        }
    }
    
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                budgetWarningService.checkAndShowWarnings(expenses, ui);
                String input = ui.readCommand();
                if (input.equalsIgnoreCase("exit")) {
                    storage.save(expenses.getAll());
                    ui.showGoodbye();
                    isExit = true;
                } else {
                    Command command = Parser.parse(input);
                    command.execute(expenses, ui, storage);
                    storage.save(expenses.getAll());
                }

            } catch (FinbroException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Finbro("./data/finbro.txt").run();
    }
}
