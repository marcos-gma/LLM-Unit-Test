import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UniquePathsDeepSeekTest {

    // Test cases for uniquePaths method
    @Test
    public void uniquePaths_SmallGrid_ReturnsCorrectCount() {
        // Input parameter(s)
        int m = 3; // rows
        int n = 2; // columns
        
        // Expected result
        int expected = 3;
        
        // Actual result
        int actual = UniquePaths.uniquePaths(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 3 unique paths for 3x2 grid");
    }

    @Test
    public void uniquePaths_SquareGrid_ReturnsCorrectCount() {
        // Input parameter(s)
        int m = 3; // rows
        int n = 3; // columns
        
        // Expected result
        int expected = 6;
        
        // Actual result
        int actual = UniquePaths.uniquePaths(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 6 unique paths for 3x3 grid");
    }

    @Test
    public void uniquePaths_SingleRow_ReturnsOne() {
        // Input parameter(s)
        int m = 1; // rows
        int n = 5; // columns
        
        // Expected result
        int expected = 1;
        
        // Actual result
        int actual = UniquePaths.uniquePaths(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 1 unique path for single row grid");
    }

    @Test
    public void uniquePaths_SingleColumn_ReturnsOne() {
        // Input parameter(s)
        int m = 5; // rows
        int n = 1; // columns
        
        // Expected result
        int expected = 1;
        
        // Actual result
        int actual = UniquePaths.uniquePaths(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 1 unique path for single column grid");
    }

    @Test
    public void uniquePaths_LargeGrid_ReturnsCorrectCount() {
        // Input parameter(s)
        int m = 7; // rows
        int n = 3; // columns
        
        // Expected result
        int expected = 28;
        
        // Actual result
        int actual = UniquePaths.uniquePaths(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 28 unique paths for 7x3 grid");
    }

    @Test
    public void uniquePaths_WhenColumnsGreaterThanRows_ReturnsSameAsSwapped() {
        // Input parameter(s)
        int m = 3; // rows
        int n = 7; // columns
        
        // Expected result (should be same as n=7, m=3)
        int expected = 28;
        
        // Actual result
        int actual = UniquePaths.uniquePaths(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return same result as swapped parameters");
    }

    // Test cases for uniquePaths2 method
    @Test
    public void uniquePaths2_SmallGrid_ReturnsCorrectCount() {
        // Arquivo renomeado para UniquePathsDeepSeekTest.java
        int m = 3; // rows
        int n = 2; // columns
        
        // Expected result
        int expected = 3;
        
        // Actual result
        int actual = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 3 unique paths for 3x2 grid");
    }

    @Test
    public void uniquePaths2_SquareGrid_ReturnsCorrectCount() {
        // Input parameter(s)
        int m = 3; // rows
        int n = 3; // columns
        
        // Expected result
        int expected = 6;
        
        // Actual result
        int actual = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 6 unique paths for 3x3 grid");
    }

    @Test
    public void uniquePaths2_SingleRow_ReturnsOne() {
        // Input parameter(s)
        int m = 1; // rows
        int n = 5; // columns
        
        // Expected result
        int expected = 1;
        
        // Actual result
        int actual = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 1 unique path for single row grid");
    }

    @Test
    public void uniquePaths2_SingleColumn_ReturnsOne() {
        // Input parameter(s)
        int m = 5; // rows
        int n = 1; // columns
        
        // Expected result
        int expected = 1;
        
        // Actual result
        int actual = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 1 unique path for single column grid");
    }

    @Test
    public void uniquePaths2_LargeGrid_ReturnsCorrectCount() {
        // Input parameter(s)
        int m = 7; // rows
        int n = 3; // columns
        
        // Expected result
        int expected = 28;
        
        // Actual result
        int actual = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return 28 unique paths for 7x3 grid");
    }

    @Test
    public void uniquePaths2_WhenColumnsGreaterThanRows_ReturnsCorrectCount() {
        // Input parameter(s)
        int m = 3; // rows
        int n = 7; // columns
        
        // Expected result
        int expected = 28;
        
        // Actual result
        int actual = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(expected, actual, "Should return correct count regardless of parameter order");
    }

    // Edge case tests for both methods
    @Test
    public void bothMethods_SingleCellGrid_ReturnsOne() {
        // Input parameter(s)
        int m = 1; // rows
        int n = 1; // columns
        
        // Expected result
        int expected = 1;
        
        // Actual results
        int actual1 = UniquePaths.uniquePaths(m, n);
        int actual2 = UniquePaths.uniquePaths2(m, n);
        
        // Assertions
        assertEquals(expected, actual1, "uniquePaths should return 1 for single cell grid");
        assertEquals(expected, actual2, "uniquePaths2 should return 1 for single cell grid");
    }

    @Test
    public void bothMethods_ShouldReturnSameResultForSameInput() {
        // Input parameter(s)
        int m = 4; // rows
        int n = 5; // columns
        
        // Actual results
        int result1 = UniquePaths.uniquePaths(m, n);
        int result2 = UniquePaths.uniquePaths2(m, n);
        
        // Assertion
        assertEquals(result1, result2, "Both methods should return same result for same input");
    }
}