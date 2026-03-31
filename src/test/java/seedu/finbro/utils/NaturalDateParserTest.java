package seedu.finbro.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

import seedu.finbro.exception.FinbroException;
//@@author WangZX2001
public class NaturalDateParserTest {

    @Test
    void parse_today() throws FinbroException {
        assertEquals(LocalDate.now(), NaturalDateParser.parse("today"));
    }

    @Test
    void parse_daysAgo() throws FinbroException {
        assertEquals(LocalDate.now().minusDays(2),
                NaturalDateParser.parse("2 days ago"));
    }

    @Test
    void parse_daysLater() throws FinbroException {
        assertEquals(LocalDate.now().plusDays(3),
                NaturalDateParser.parse("3 days later"));
    }

    @Test
    void parse_lastMonday() throws FinbroException {
        assertEquals(
                LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY)),
                NaturalDateParser.parse("last monday")
        );
    }

    @Test
    void parse_isoDate() throws FinbroException {
        assertEquals(LocalDate.of(2026, 1, 1),
                NaturalDateParser.parse("2026-01-01"));
    }

    @Test
    void parse_invalid_throwsException() {
        assertThrows(FinbroException.class,
                () -> NaturalDateParser.parse("invalid input"));
    }
}
