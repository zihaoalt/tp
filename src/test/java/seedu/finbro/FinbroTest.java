package seedu.finbro;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.finbro.finances.Expense;
import seedu.finbro.finances.ExpenseList;
import seedu.finbro.finances.Limit;


class FinbroTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    //@@author AK47ofCode
    @Test
    void run_remainingBudgetAboveLimit_showsOverspendingWarning() {
        ExpenseList list = new ExpenseList();
        list.add(new Expense(180, "entertainment", "2026-01-01"));
        Limit.setLimit(200);

        assertTrue(list.getRemainingExpenditure() <= 20);
    }
    //@@author AK47ofCode
    @Test
    void run_remainingBudgetBelowLimit_doesNotShowOverspendingWarning() {
        ExpenseList list = new ExpenseList();
        list.add(new Expense(179, "entertainment", "2026-01-01"));
        Limit.setLimit(200);

        assertFalse(list.getRemainingExpenditure() <= 20);
    }
}
