package seedu.finbro.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

class VisualCommandTest {

    @Test
    void createLabel_validYearMonth_validStringForm() {
        YearMonth ym = YearMonth.of(2026, 12);
        assertEquals("Dec 2026", VisualCommand.createLabel(ym));
    }
}
