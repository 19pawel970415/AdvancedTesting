package pl.sda.parametrized;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringsUtilTest {

    @ParameterizedTest
    @CsvSource(value = {" test .TEST", "tEsT   .TEST", "   javA.JAVA"}, delimiter = '.')
    void shouldUpperCaseTrimInput(String input, String expected) {

        String actual = StringsUtil.toUpperCase(input);

        assertEquals(expected, actual);

    }



    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1, delimiter = ',', lineSeparator = ";")
    void shouldUpperCaseTrimInputFromFile(String input, String expected) {

        String actual = StringsUtil.toUpperCase(input);

        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @NullAndEmptySource
//    @NullSource
//    @EmptySource
    void shouldCheckIfEmpty(String input) {

        boolean actual = StringsUtil.isBlank(input);

        assertTrue(actual);

    }





}