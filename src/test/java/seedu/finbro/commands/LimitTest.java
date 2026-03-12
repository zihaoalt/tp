package seedu.finbro.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LimitTest {

    // methodToTest_input_expectedOutput
    @Test
    void toFileFormat_twoDP_twoDP() {
        Limit.initLimit(450.00);
        assertEquals("450.00\n", Limit.toFileFormat());
    }

    @Test
    void toFileFormat_twoDP_roundedTwoDP() {
        Limit.initLimit(450.019);
        assertEquals("450.02\n",  Limit.toFileFormat());
    }
}
