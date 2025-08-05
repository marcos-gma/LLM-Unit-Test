
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ReverseStringGeminiCLI {

    @Test
    void reverse_EmptyString_ReturnsEmptyString() {
        assertEquals("", ReverseString.reverse(""));
    }

    @Test
    void reverse_SingleCharacter_ReturnsSameCharacter() {
        assertEquals("a", ReverseString.reverse("a"));
    }

    @Test
    void reverse_SimpleString_ReturnsReversedString() {
        assertEquals("olleh", ReverseString.reverse("hello"));
    }

    @Test
    void reverse2_NullString_ReturnsNull() {
        assertEquals(null, ReverseString.reverse2(null));
    }

    @Test
    void reverse2_EmptyString_ReturnsEmptyString() {
        assertEquals("", ReverseString.reverse2(""));
    }

    @Test
    void reverse2_SingleCharacter_ReturnsSameCharacter() {
        assertEquals("a", ReverseString.reverse2("a"));
    }

    @Test
    void reverse2_SimpleString_ReturnsReversedString() {
        assertEquals("olleh", ReverseString.reverse2("hello"));
    }

    @Test
    void reverse3_EmptyString_ReturnsEmptyString() {
        assertEquals("", ReverseString.reverse3(""));
    }

    @Test
    void reverse3_SingleCharacter_ReturnsSameCharacter() {
        assertEquals("a", ReverseString.reverse3("a"));
    }

    @Test
    void reverse3_SimpleString_ReturnsReversedString() {
        assertEquals("olleh", ReverseString.reverse3("hello"));
    }

    @Test
    void reverseStringUsingStack_NullString_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ReverseString.reverseStringUsingStack(null);
        });
    }

    @Test
    void reverseStringUsingStack_EmptyString_ReturnsEmptyString() {
        assertEquals("", ReverseString.reverseStringUsingStack(""));
    }

    @Test
    void reverseStringUsingStack_SimpleString_ReturnsReversedString() {
        assertEquals("olleh", ReverseString.reverseStringUsingStack("hello"));
    }
}
