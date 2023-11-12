package pl.sda.parametrized;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalculatorArgumentsProviderForThree implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(4, 12, 10),
                Arguments.of(0, 88, 2),
                Arguments.of(-1, 0, 1),
                Arguments.of(-77, -5, -21)
        );
    }
}
