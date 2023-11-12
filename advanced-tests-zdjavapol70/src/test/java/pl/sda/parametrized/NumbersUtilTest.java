package pl.sda.parametrized;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class NumbersUtilTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10111, 23535, 74543})
    void shouldReturnTrueForOddNumbers(int input) {
        assertTrue(NumbersUtil.isOdd(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 101112, 235352, 745432})
    void shouldReturnFalseForEvenNumbers(int input) {
        assertFalse(NumbersUtil.isOdd(input));
    }

    private static Stream getNumberAndOddBoolean() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(2, false),
                Arguments.of(3, true),
                Arguments.of(4, false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "getNumberAndOddBoolean")
    void shouldReturnFalseForEvenNumbersAndTrueForOddNumbersFromMethod(int input, boolean expectedIsOdd) {
        boolean actual = NumbersUtil.isOdd(input);

        assertEquals(expectedIsOdd, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(NumberWithParityArgumentsProvider.class)
    void shouldReturnFalseForEvenNumbersAndTrueForOddNumbersFromClass(int input, boolean expectedIsOdd) {
        boolean actual = NumbersUtil.isOdd(input);

        assertEquals(expectedIsOdd, actual);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenDevidedByZero() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> NumbersUtil.divide(33, 0))
                .withMessage("dividend can't be 0");
    }

}