package pl.sda.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    @ParameterizedTest
    @EnumSource(value = TemperatureConverter.class)
    public void shouldConvertTemperature(TemperatureConverter converter) {
        assertTrue(converter.convertTemp(0) >= -273.15f);
    }


    @ParameterizedTest
    @EnumSource(value = TemperatureConverter.class, names = {".+CELSIUS"}, mode = EnumSource.Mode.MATCH_ANY)
    public void shouldConvertTemperatureV2(TemperatureConverter converter) {
        assertTrue(converter.convertTemp(0) >= -273.15f);
    }

}