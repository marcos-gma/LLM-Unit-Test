import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReverseStringTest {

    // Test cases for reverse() method
    @Test
    public void reverse_NormalString_ReturnsReversedString() {
        // Input parameter
        String input = "hello";
        
        // Expected result
        String expected = "olleh";
        
        // Method call
        String result = ReverseString.reverse(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    @Test
    public void reverse_EmptyString_ReturnsEmptyString() {
        // Input parameter
        String input = "";
        
        // Expected result
        String expected = "";
        
        // Method call
        String result = ReverseString.reverse(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    // Test cases for reverse2() method
    @Test
    public void reverse2_NormalString_ReturnsReversedString() {
        // Input parameter
        String input = "world";
        
        // Expected result
        String expected = "dlrow";
        
        // Method call
        String result = ReverseString.reverse2(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    @Test
    public void reverse2_EmptyString_ReturnsEmptyString() {
        // Input parameter
        String input = "";
        
        // Expected result
        String expected = "";
        
        // Method call
        String result = ReverseString.reverse2(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    @Test
    public void reverse2_NullString_ReturnsNull() {
        // Input parameter
        String input = null;
        
        // Expected result
        String expected = null;
        
        // Method call
        String result = ReverseString.reverse2(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    // Test cases for reverse3() method
    @Test
    public void reverse3_NormalString_ReturnsReversedString() {
        // Input parameter
        String input = "java";
        
        // Expected result
        String expected = "avaj";
        
        // Method call
        String result = ReverseString.reverse3(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    @Test
    public void reverse3_EmptyString_ReturnsEmptyString() {
        // Input parameter
        String input = "";
        
        // Expected result
        String expected = "";
        
        // Method call
        String result = ReverseString.reverse3(input);
        
        // Assertion
        assertEquals(expected, result);
    }

    // Test cases for reverseStringUsingStack() method
    @Test
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