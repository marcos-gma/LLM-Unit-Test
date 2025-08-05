import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
// import static org.junit.Assert.*; // Commented out to avoid JUnit 4/JUnit 5 conflict
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TravelingSalesmanDeepSeekTest {

    // Test cases for bruteForce method
    @Test
    public void bruteForce_EmptyMatrix_ReturnsZero() {
        int[][] distanceMatrix = new int[0][0];
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    public void bruteForce_SingleCity_ReturnsZero() {
        int[][] distanceMatrix = {{0}};
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    public void bruteForce_TwoCities_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {
            {0, 10},
            {10, 0}
        };
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(20, result);
    }

    @Test
    public void bruteForce_ThreeCities_ReturnsShortestRoute() {
        int[][] distanceMatrix = {
            {0, 10, 15},
            {10, 0, 20},
            {15, 20, 0}
        };
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(45, result);
    }

    @Test
    public void bruteForce_UnreachableCity_ReturnsMaxValue() {
        int[][] distanceMatrix = {
            {0, 10, Integer.MAX_VALUE},
            {10, 0, 20},
            {Integer.MAX_VALUE, 20, 0}
        };
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(Integer.MAX_VALUE, result);
    }

    // Test cases for calculateDistance method
    @Test
    public void calculateDistance_ValidRoute_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {
            {0, 10, 15},
            {10, 0, 20},
            {15, 20, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(45, result);
    }

    @Test
    public void calculateDistance_UnreachableIntermediateCity_ReturnsMaxValue() {
        int[][] distanceMatrix = {
            {0, 10, Integer.MAX_VALUE},
            {10, 0, 20},
            {15, 20, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void calculateDistance_UnreachableReturnCity_ReturnsMaxValue() {
        int[][] distanceMatrix = {
            {0, 10, 15},
            {10, 0, 20},
            {Integer.MAX_VALUE, 20, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    public void calculateDistance_SingleCityRoute_ReturnsZero() {
        int[][] distanceMatrix = {{0}};
        List<Integer> route = Arrays.asList(0);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(0, result);
    }

    // Test cases for dynamicProgramming method
    @Test
    public void dynamicProgramming_EmptyMatrix_ReturnsZero() {
        int[][] distanceMatrix = new int[0][0];
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    public void dynamicProgramming_SingleCity_ReturnsZero() {
        int[][] distanceMatrix = {{0}};
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    public void dynamicProgramming_TwoCities_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {
            {0, 10},
            {10, 0}
        };
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(20, result);
    }

    @Test
    public void dynamicProgramming_ThreeCities_ReturnsShortestRoute() {
        int[][] distanceMatrix = {
            {0, 10, 15},
            {10, 0, 20},
            {15, 20, 0}
        };
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(45, result);
    }

    @Test
    public void dynamicProgramming_UnreachableCity_ReturnsZero() {
        int[][] distanceMatrix = {
            {0, 10, Integer.MAX_VALUE},
            {10, 0, 20},
            {Integer.MAX_VALUE, 20, 0}
        };
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    public void dynamicProgramming_NonSquareMatrix_ThrowsException() {
        int[][] distanceMatrix = {
            {0, 10, 15},
            {10, 0, 20}
        };
        assertThrows(IllegalArgumentException.class, () -> {
            TravelingSalesman.dynamicProgramming(distanceMatrix);
        });
    }

    // Helper method to test generatePermutations (via bruteForce)
    @Test
    public void bruteForce_VerifyPermutationsGeneration() {
        int[][] distanceMatrix = {
            {0, 1, 2},
            {1, 0, 3},
            {2, 3, 0}
        };
        // For 3 cities (0 is fixed start), there should be 2! = 2 permutations
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertTrue(result > 0); // Just verify it runs, actual distance depends on values
    }
}