
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class TravelingSalesmanGeminiCLI {

    @Test
    void bruteForce_SingleCity_ReturnsZero() {
        int[][] distanceMatrix = {{0}};
        assertEquals(0, TravelingSalesman.bruteForce(distanceMatrix));
    }

    @Test
    void bruteForce_TwoCities_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {{0, 10}, {10, 0}};
        assertEquals(20, TravelingSalesman.bruteForce(distanceMatrix));
    }

    @Test
    void bruteForce_ThreeCities_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {{0, 10, 15}, {10, 0, 20}, {15, 20, 0}};
        assertEquals(45, TravelingSalesman.bruteForce(distanceMatrix));
    }

    @Test
    void calculateDistance_EmptyRoute_ReturnsZero() {
        int[][] distanceMatrix = {{0, 10}, {10, 0}};
        List<Integer> route = new ArrayList<>();
        assertEquals(0, TravelingSalesman.calculateDistance(distanceMatrix, route));
    }

    @Test
    void calculateDistance_SingleCityRoute_ReturnsZero() {
        int[][] distanceMatrix = {{0, 10}, {10, 0}};
        List<Integer> route = Collections.singletonList(0);
        assertEquals(0, TravelingSalesman.calculateDistance(distanceMatrix, route));
    }

    @Test
    void calculateDistance_ValidRoute_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {{0, 10, 15}, {10, 0, 20}, {15, 20, 0}};
        List<Integer> route = Arrays.asList(0, 1, 2);
        assertEquals(45, TravelingSalesman.calculateDistance(distanceMatrix, route));
    }

    @Test
    void dynamicProgramming_EmptyMatrix_ReturnsZero() {
        int[][] distanceMatrix = {{}};
        assertEquals(0, TravelingSalesman.dynamicProgramming(distanceMatrix));
    }

    @Test
    void dynamicProgramming_SingleCity_ReturnsZero() {
        int[][] distanceMatrix = {{0}};
        assertEquals(0, TravelingSalesman.dynamicProgramming(distanceMatrix));
    }

    @Test
    void dynamicProgramming_TwoCities_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {{0, 10}, {10, 0}};
        assertEquals(20, TravelingSalesman.dynamicProgramming(distanceMatrix));
    }

    @Test
    void dynamicProgramming_ThreeCities_ReturnsCorrectDistance() {
        int[][] distanceMatrix = {{0, 10, 15}, {10, 0, 20}, {15, 20, 0}};
        assertEquals(45, TravelingSalesman.dynamicProgramming(distanceMatrix));
    }

    @Test
    void dynamicProgramming_NonSquareMatrix_ThrowsIllegalArgumentException() {
        int[][] distanceMatrix = {{0, 10}, {10, 0, 20}};
        assertThrows(IllegalArgumentException.class, () -> {
            TravelingSalesman.dynamicProgramming(distanceMatrix);
        });
    }
}
