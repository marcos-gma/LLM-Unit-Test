import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Testes para ReverseString")
class ReverseStringDeepSeekTest {

    // Testes para o método reverse()
    @ParameterizedTest(name = "reverse({0}) deve retornar {1}")
    @MethodSource("provideStringsForReverse")
    void testReverse(String input, String expected) {
        assertThat(ReverseString.reverse(input))
            .as("Verificando reversão da string %s", input)
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void testReverse_EdgeCases(String input) {
        assertThat(ReverseString.reverse(input))
            .isEqualTo(input);
    }

    // Testes para o método reverse2()
    @ParameterizedTest(name = "reverse2({0}) deve retornar {1}")
    @MethodSource("provideStringsForReverse")
    void testReverse2(String input, String expected) {
        assertThat(ReverseString.reverse2(input))
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("reverse2 deve retornar null para input null")
    void testReverse2_NullInput() {
        assertThat(ReverseString.reverse2(null))
            .isNull();
    }

    // Testes para o método reverse3()
    @ParameterizedTest(name = "reverse3({0}) deve retornar {1}")
    @MethodSource("provideStringsForReverse")
    void testReverse3(String input, String expected) {
        assertThat(ReverseString.reverse3(input))
            .isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testReverse3_EmptyAndNull(String input) {
        assertThat(ReverseString.reverse3(input))
            .isEqualTo(input);
    }

    // Testes para o método reverseStringUsingStack()
    @ParameterizedTest(name = "reverseStringUsingStack({0}) deve retornar {1}")
    @MethodSource("provideStringsForReverse")
    void testReverseStringUsingStack(String input, String expected) {
        assertThat(ReverseString.reverseStringUsingStack(input))
            .isEqualTo(expected);
    }

    @Test
    @DisplayName("reverseStringUsingStack deve lançar exceção para input null")
    void testReverseStringUsingStack_NullInput() {
        assertThatThrownBy(() -> ReverseString.reverseStringUsingStack(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input string cannot be null");
    }

    // Testes comparativos entre todos os métodos
    @ParameterizedTest
    @MethodSource("provideStringsForAllMethods")
    void testAllMethodsConsistency(String input) {
        assertAll(
            () -> assertThat(ReverseString.reverse(input))
                .isEqualTo(ReverseString.reverse2(input))
                .isEqualTo(ReverseString.reverse3(input))
                .isEqualTo(ReverseString.reverseStringUsingStack(input))
        );
    }

    // Métodos auxiliares para fornecer dados de teste
    private static Stream<Arguments> provideStringsForReverse() {
        return Stream.of(
            Arguments.of("hello", "olleh"),
            Arguments.of("world", "dlrow"),
            Arguments.of("Java", "avaJ"),
            Arguments.of("12345", "54321"),
            Arguments.of("a", "a"),
            Arguments.of("madam", "madam"),
            Arguments.of("hello world", "dlrow olleh"),
            Arguments.of("with-hyphen", "nehyp-htiw"),
            Arguments.of("with\nnewline", "enilwen\nhtiw")
        );
    }

    private static Stream<Arguments> provideStringsForAllMethods() {
        return Stream.concat(
            provideStringsForReverse(),
            Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("  ")
            )
        );
    }
}   @Test
    public void reverseStringUsingStack_NormalString_ReturnsReversedString() {
        // Input parameter
        String input = "stack";
        
        // Expected result
        String expected = "kcats";
        
        // Method call
        String result = ReverseString.reverseStringUsingStack(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    @Test
    public void reverseStringUsingStack_EmptyString_ReturnsEmptyString() {
        // Input parameter
        String input = "";
        
        // Expected result
        String expected = "";
        
        // Method call
        String result = ReverseString.reverseStringUsingStack(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reverseStringUsingStack_NullString_ThrowsIllegalArgumentException() {
        // Input parameter
        String input = null;
        
        // Method call - should throw exception
        ReverseString.reverseStringUsingStack(input);
    }

    // Edge case tests for all methods
    @Test
    public void reverse_SingleCharacterString_ReturnsSameString() {
        // Input parameter
        String input = "a";
        
        // Expected result
        String expected = "a";
        
        // Method call and assertion for all methods
        assertEquals(expected, ReverseString.reverse(input));
        assertEquals(expected, ReverseString.reverse2(input));
        assertEquals(expected, ReverseString.reverse3(input));
        assertEquals(expected, ReverseString.reverseStringUsingStack(input));
    }

    @Test
    public void reverse_PalindromeString_ReturnsSameString() {
        // Input parameter
        String input = "madam";
        
        // Expected result
        String expected = "madam";
        
        // Method call and assertion for all methods
        assertEquals(expected, ReverseString.reverse(input));
        assertEquals(expected, ReverseString.reverse2(input));
        assertEquals(expected, ReverseString.reverse3(input));
        assertEquals(expected, ReverseString.reverseStringUsingStack(input));
    }

    @Test
    public void reverse_StringWithSpaces_ReturnsReversedStringWithSpaces() {
        // Input parameter
        String input = "hello world";
        
        // Expected result
        String expected = "dlrow olleh";
        
        // Method call and assertion for all methods
        assertEquals(expected, ReverseString.reverse(input));
        assertEquals(expected, ReverseString.reverse2(input));
        assertEquals(expected, ReverseString.reverse3(input));
        assertEquals(expected, ReverseString.reverseStringUsingStack(input));
    }
}