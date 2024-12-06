package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "95, A",
            "85, B",
            "75, C",
            "65, D",
            "55, F"
    })
    void testGetGrade_ValidScores(int score, String expectedGrade) {
        String grade = GradeCalculator.getGrade(score);

        assertEquals(expectedGrade, grade, "Grade should match the expected value for the score");
    }

    @Test
    void testGetGrade_InvalidScores() {
        assertThrows(IllegalArgumentException.class, () -> GradeCalculator.getGrade(-5), "Throw exception for score < 0");
        assertThrows(IllegalArgumentException.class, () -> GradeCalculator.getGrade(105), "Throw exception for score > 100");
    }
}
