import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseStringDeepSeekTest {

    // Testes para reverse(String str)
    @Test
    void reverse_NormalString_ReturnsReversedString() {
        String input = "hello";
        String expected = "olleh";
        String actual = ReverseString.reverse(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse_EmptyString_ReturnsEmptyString() {
        String input = "";
        String expected = "";
        String actual = ReverseString.reverse(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse_SingleCharacter_ReturnsSameCharacter() {
        String input = "a";
        String expected = "a";
        String actual = ReverseString.reverse(input);
        assertEquals(expected, actual);
    }

    // Testes para reverse2(String str)
    @Test
    void reverse2_NormalString_ReturnsReversedString() {
        String input = "world";
        String expected = "dlrow";
        String actual = ReverseString.reverse2(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse2_EmptyString_ReturnsEmptyString() {
        String input = "";
        String expected = "";
        String actual = ReverseString.reverse2(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse2_NullString_ReturnsNull() {
        String input = null;
        String expected = null;
        String actual = ReverseString.reverse2(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse2_Palindrome_ReturnsSameString() {
        String input = "madam";
        String expected = "madam";
        String actual = ReverseString.reverse2(input);
        assertEquals(expected, actual);
    }

    // Testes para reverse3(String string)
    @Test
    void reverse3_NormalString_ReturnsReversedString() {
        String input = "java";
        String expected = "avaj";
        String actual = ReverseString.reverse3(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse3_EmptyString_ReturnsEmptyString() {
        String input = "";
        String expected = "";
        String actual = ReverseString.reverse3(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverse3_StringWithSpaces_ReturnsReversedString() {
        String input = "hello world";
        String expected = "dlrow olleh";
        String actual = ReverseString.reverse3(input);
        assertEquals(expected, actual);
    }

    // Testes para reverseStringUsingStack(String str)
    @Test
    void reverseStringUsingStack_NormalString_ReturnsReversedString() {
        String input = "stack";
        String expected = "kcats";
        String actual = ReverseString.reverseStringUsingStack(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverseStringUsingStack_EmptyString_ReturnsEmptyString() {
        String input = "";
        String expected = "";
        String actual = ReverseString.reverseStringUsingStack(input);
        assertEquals(expected, actual);
    }

    @Test
    void reverseStringUsingStack_NullString_ThrowsIllegalArgumentException() {
        String input = null;
        assertThrows(IllegalArgumentException.class, () -> ReverseString.reverseStringUsingStack(input));
    }

    @Test
    void reverseStringUsingStack_StringWithSpecialChars_ReturnsReversedString() {
        String input = "a!b@c#";
        String expected = "#c@b!a";
        String actual = ReverseString.reverseStringUsingStack(input);
        assertEquals(expected, actual);
    }

    // Testes adicionais para casos extremos
    @Test
    void allMethods_SameInput_ConsistentResults() {
        String input = "consistent";
        
        String result1 = ReverseString.reverse(input);
        String result2 = ReverseString.reverse2(input);
        String result3 = ReverseString.reverse3(input);
        String result4 = ReverseString.reverseStringUsingStack(input);
        
        String expected = "tnetsisnoc";
        
        assertEquals(expected, result1);
        assertEquals(expected, result2);
        assertEquals(expected, result3);
        assertEquals(expected, result4);
    }

    @Test
    void allMethods_UnicodeCharacters_ReturnsReversedString() {
        String input = "こんにちは"; // "Hello" in Japanese
        String expected = "はちにんこ";
        
        assertEquals(expected, ReverseString.reverse(input));
        assertEquals(expected, ReverseString.reverse2(input));
        assertEquals(expected, ReverseString.reverse3(input));
        assertEquals(expected, ReverseString.reverseStringUsingStack(input));
    }
}