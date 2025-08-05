import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe ReverseString")
class ReverseStringTestGemini0 {

    private static final String NORMAL_STRING = "Hello World";
    private static final String REVERSED_NORMAL_STRING = "dlroW olleH";
    private static final String EMPTY_STRING = "";
    private static final String SINGLE_CHAR_STRING = "a";
    private static final String PALINDROME = "madam";
    private static final String SPECIAL_CHARS_STRING = "!@#123";
    private static final String REVERSED_SPECIAL_CHARS_STRING = "321#@!";

    @Nested
    @DisplayName("Testes para o método reverse()")
    class ReverseTests {
        @Test
        @DisplayName("Deve inverter uma string normal")
        void testReverseNormalString() {
            assertEquals(REVERSED_NORMAL_STRING, ReverseString.reverse(NORMAL_STRING));
        }

        @Test
        @DisplayName("Deve retornar uma string vazia para uma entrada vazia")
        void testReverseEmptyString() {
            assertEquals(EMPTY_STRING, ReverseString.reverse(EMPTY_STRING));
        }

        @Test
        @DisplayName("Deve lançar NullPointerException para entrada nula")
        void testReverseNullString() {
            assertThrows(NullPointerException.class, () -> ReverseString.reverse(null));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um único caractere")
        void testReverseSingleCharString() {
            assertEquals(SINGLE_CHAR_STRING, ReverseString.reverse(SINGLE_CHAR_STRING));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um palíndromo")
        void testReversePalindrome() {
            assertEquals(PALINDROME, ReverseString.reverse(PALINDROME));
        }
        
        @Test
        @DisplayName("Deve inverter corretamente uma string com caracteres especiais")
        void testReverseWithSpecialChars() {
            assertEquals(REVERSED_SPECIAL_CHARS_STRING, ReverseString.reverse(SPECIAL_CHARS_STRING));
        }
    }

    @Nested
    @DisplayName("Testes para o método reverse2()")
    class Reverse2Tests {
        @Test
        @DisplayName("Deve inverter uma string normal")
        void testReverse2NormalString() {
            assertEquals(REVERSED_NORMAL_STRING, ReverseString.reverse2(NORMAL_STRING));
        }

        @Test
        @DisplayName("Deve retornar uma string vazia para uma entrada vazia")
        void testReverse2EmptyString() {
            assertEquals(EMPTY_STRING, ReverseString.reverse2(EMPTY_STRING));
        }

        @Test
        @DisplayName("Deve retornar nulo para entrada nula")
        void testReverse2NullString() {
            assertNull(ReverseString.reverse2(null));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um único caractere")
        void testReverse2SingleCharString() {
            assertEquals(SINGLE_CHAR_STRING, ReverseString.reverse2(SINGLE_CHAR_STRING));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um palíndromo")
        void testReverse2Palindrome() {
            assertEquals(PALINDROME, ReverseString.reverse2(PALINDROME));
        }

        @Test
        @DisplayName("Deve inverter corretamente uma string com caracteres especiais")
        void testReverse2WithSpecialChars() {
            assertEquals(REVERSED_SPECIAL_CHARS_STRING, ReverseString.reverse2(SPECIAL_CHARS_STRING));
        }
    }

    @Nested
    @DisplayName("Testes para o método reverse3()")
    class Reverse3Tests {
        @Test
        @DisplayName("Deve inverter uma string normal")
        void testReverse3NormalString() {
            assertEquals(REVERSED_NORMAL_STRING, ReverseString.reverse3(NORMAL_STRING));
        }

        @Test
        @DisplayName("Deve retornar uma string vazia para uma entrada vazia")
        void testReverse3EmptyString() {
            assertEquals(EMPTY_STRING, ReverseString.reverse3(EMPTY_STRING));
        }

        @Test
        @DisplayName("Deve lançar NullPointerException para entrada nula")
        void testReverse3NullString() {
            assertThrows(NullPointerException.class, () -> ReverseString.reverse3(null));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um único caractere")
        void testReverse3SingleCharString() {
            assertEquals(SINGLE_CHAR_STRING, ReverseString.reverse3(SINGLE_CHAR_STRING));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um palíndromo")
        void testReverse3Palindrome() {
            assertEquals(PALINDROME, ReverseString.reverse3(PALINDROME));
        }

        @Test
        @DisplayName("Deve inverter corretamente uma string com caracteres especiais")
        void testReverse3WithSpecialChars() {
            assertEquals(REVERSED_SPECIAL_CHARS_STRING, ReverseString.reverse3(SPECIAL_CHARS_STRING));
        }
    }

    @Nested
    @DisplayName("Testes para o método reverseStringUsingStack()")
    class ReverseStringUsingStackTests {
        @Test
        @DisplayName("Deve inverter uma string normal")
        void testReverseStackNormalString() {
            assertEquals(REVERSED_NORMAL_STRING, ReverseString.reverseStringUsingStack(NORMAL_STRING));
        }

        @Test
        @DisplayName("Deve retornar uma string vazia para uma entrada vazia")
        void testReverseStackEmptyString() {
            assertEquals(EMPTY_STRING, ReverseString.reverseStringUsingStack(EMPTY_STRING));
        }

        @Test
        @DisplayName("Deve lançar IllegalArgumentException para entrada nula")
        void testReverseStackNullString() {
            assertThrows(IllegalArgumentException.class, () -> ReverseString.reverseStringUsingStack(null));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um único caractere")
        void testReverseStackSingleCharString() {
            assertEquals(SINGLE_CHAR_STRING, ReverseString.reverseStringUsingStack(SINGLE_CHAR_STRING));
        }

        @Test
        @DisplayName("Deve retornar a mesma string para um palíndromo")
        void testReverseStackPalindrome() {
            assertEquals(PALINDROME, ReverseString.reverseStringUsingStack(PALINDROME));
        }
        
        @Test
        @DisplayName("Deve inverter corretamente uma string com caracteres especiais")
        void testReverseStackWithSpecialChars() {
            assertEquals(REVERSED_SPECIAL_CHARS_STRING, ReverseString.reverseStringUsingStack(SPECIAL_CHARS_STRING));
        }
    }
}