package pl.sda.parametrized;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringsUtilTest {

    @ParameterizedTest
    //given
    @CsvSource(value = {" test ;TEST", "tESt   ;TEST", "   Java;JAVA"} , delimiter = ';')
    public void shouldTrimAndUpperCaseInput(String input, String expected) {

        //when
        String actual = StringsUtil.toUpperCase(input);

        //then
        Assertions.assertEquals(expected, actual);

    }

}