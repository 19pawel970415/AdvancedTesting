package pl.sda.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    @ParameterizedTest
    @EnumSource(TemperatureConverter.class)
    void shouldreturnConvertedTempNotLowerthan(TemperatureConverter converter) {
        assertTrue(converter.convertTemp(0) >= -274);
    }

    @ParameterizedTest
    @EnumSource(value = TemperatureConverter.class, names = "CELSIUS_KELVIN",mode = EnumSource.Mode.INCLUDE)
    void shouldConvvertTemp(TemperatureConverter converter) {
        assertTrue(converter.convertTemp(0) >= -274);
    }
}