package pl.sda.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArraysUtilTest {

    @ParameterizedTest
    @NullAndEmptySource
    void shouldCheckIfEmpty(List<String> values) {
        assertFalse(ArraysUtil.isValid(values));
    }

}