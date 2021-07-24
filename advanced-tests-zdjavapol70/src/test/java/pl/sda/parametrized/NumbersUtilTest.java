package pl.sda.parametrized;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

class NumbersUtilTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10111, 23535, 74543})
    public void shouldReturnTrueForOddNumbers(int input) {
        Assertions.assertTrue(NumbersUtil.isOdd(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 101112, 235352, 745432})
    public void shouldReturnFalseForEvenNumbers(int input) {
        Assertions.assertFalse(NumbersUtil.isOdd(input));
    }

    private static Stream<Arguments> provideInfoWithNumbersParity() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(12, false),
                Arguments.of(11, true),
                Arguments.of(100, false)
        );
    }

    @ParameterizedTest
    //given
    @MethodSource(value = "provideInfoWithNumbersParity")
    public void shouldReturnExpectedValueForInput(int input, boolean expected) {

        //when
        boolean actual = NumbersUtil.isOdd(input);

        //then
        Assertions.assertEquals(expected, actual);

    }

    @ParameterizedTest
    @ArgumentsSource(value = NumberWithParityArgumentsProvider.class)
    public void shouldReturnExpectedValueForInputArgumentsSource(int input, boolean expected) {

        //when
        boolean actual = NumbersUtil.isOdd(input);

        //then
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDivideByZero() {

        org.assertj.core.api.Assertions
                .assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> NumbersUtil.divide(10, 0))
                .withMessage("dividend can't be 0");
    }



}