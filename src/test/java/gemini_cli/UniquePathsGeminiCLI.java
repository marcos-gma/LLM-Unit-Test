
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UniquePathsGeminiCLI {

    @Test
    void uniquePaths_1x1_Returns1() {
        assertEquals(1, UniquePaths.uniquePaths(1, 1));
    }

    @Test
    void uniquePaths_2x2_Returns2() {
        assertEquals(2, UniquePaths.uniquePaths(2, 2));
    }

    @Test
    void uniquePaths_3x7_Returns28() {
        assertEquals(28, UniquePaths.uniquePaths(3, 7));
    }

    @Test
    void uniquePaths_7x3_Returns28() {
        assertEquals(28, UniquePaths.uniquePaths(7, 3));
    }

    @Test
    void uniquePaths2_1x1_Returns1() {
        assertEquals(1, UniquePaths.uniquePaths2(1, 1));
    }

    @Test
    void uniquePaths2_2x2_Returns2() {
        assertEquals(2, UniquePaths.uniquePaths2(2, 2));
    }

    @Test
    void uniquePaths2_3x7_Returns28() {
        assertEquals(28, UniquePaths.uniquePaths2(3, 7));
    }
}
