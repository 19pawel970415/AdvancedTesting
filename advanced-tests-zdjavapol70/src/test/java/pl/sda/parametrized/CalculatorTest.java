package pl.sda.parametrized;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @ParameterizedTest
    @ArgumentsSource(CalculatorArgumentsProvider.class)
    void shouldAdd(Integer a, Integer b) {
        int actual = calculator.add(a, b);
        int expected = a + b;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(CalculatorArgumentsProvider.class)
    void shouldSubtract(Integer a, Integer b) {
        int actual = calculator.subtract(a, b);
        int expected = a - b;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("calculatorArgumentsProvider")
    void shouldMultiply(Integer a, Integer b) {
        int actual = calculator.multiply(a, b);
        int expected = a * b;

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> calculatorArgumentsProvider() {
        return Stream.of(
                Arguments.of(4, 12),
                Arguments.of(0, 88),
                Arguments.of(-77, -5)
        );
    }

    private static Stream<Arguments> calculatorArgumentsProviderForThree() {
        return Stream.of(
                Arguments.of(4, 12, 10),
                Arguments.of(0, 88, 3),
                Arguments.of(-77, -5, 8)
        );
    }

    @ParameterizedTest
    @MethodSource("calculatorArgumentsProvider")
    void shouldDivide(int a, int b) {
        double actual = calculator.divide(a, b);
        double expected = (double) a / (double) b;

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.divide(10, 0))
                .withMessage("Cannot be divided by 0");
    }

    @ParameterizedTest
    @CsvSource({"1", "30", "16"})
    void shouldCheckIfPositive(String number) {
        boolean actual = calculator.isPositive(Integer.parseInt(number));
        assertTrue(actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data1.csv", lineSeparator = ";")
    void shouldCheckIfNegative(String number) {
        boolean actual = calculator.isNegative(Integer.parseInt(number));
        assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({"1", "29", "15", "13", "-7"})
    void shouldCheckIfOdd(String number) {
        boolean actual = calculator.isOdd(Integer.parseInt(number));
        assertTrue(actual);
    }

    @Test
    void shouldReturnMinFromTwoNumbers() {
        int a = 10;
        int b = 15;
        int actual = calculator.min(a, b);
        int expected = Math.min(a, b);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMaxFromTwoNumbers() {
        int a = 10;
        int b = 15;
        int actual = calculator.max(a, b);
        int expected = Math.max(a, b);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(CalculatorArgumentsProvider.class)
    void average(Integer a, Integer b) {
        double actual = calculator.average(a, b);
        double expected = (float) (a + b) / 2;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("calculatorArgumentsProvider")
    void power(Integer a, Integer b) {
        double actual = calculator.power(a, b);
        double expected = Math.pow(a, b);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(CalculatorArgumentsProviderForThree.class)
    void shouldAdd(Integer a, Integer b, Integer c) {
        int actual = calculator.add(a, b, c);
        int expected = a + b + c;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(CalculatorArgumentsProviderForThree.class)
    void shouldSubtract(Integer a, Integer b, Integer c) {
        int actual = calculator.subtract(a, b, c);
        int expected = a - b - c;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("calculatorArgumentsProviderForThree")
    void shouldMultiply(Integer a, Integer b, Integer c) {
        int actual = calculator.multiply(a, b, c);
        int expected = a * b * c;

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("calculatorArgumentsProviderForThree")
    void shouldDivide(int a, int b, int c) {
        double actual = calculator.divide(a, b, c);
        double expected = (double) a / (double) b / (double) c;

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForThreeNumbersSecondNumberZero() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.divide(10,0,5))
                .withMessage("Cannot be divided by 0");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForThreeNumbersThirdNumberZero() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.divide(10,9,0))
                .withMessage("Cannot be divided by 0");
    }

    @Test
    void shouldReturnMinFromThreeNumbers() {
        int a = 10;
        int b = 15;
        int c = 20;
        int actual = calculator.min(a, b, c);
        int ab = Math.min(a, b);
        int expected = Math.min(ab, c);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMaxFromThreeNumbers() {
        int a = 10;
        int b = 15;
        int c = 20;
        int actual = calculator.max(a, b, c);
        int ab = Math.max(a, b);
        int expected = Math.max(ab, c);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("calculatorArgumentsProviderForThree")
    void shouldCalculateAverage(Integer a, Integer b, Integer c) {
        double actual = calculator.average(a, b, c);
        double expected = (float) (a + b + c) / 3;

        assertEquals(actual, expected);
    }
}