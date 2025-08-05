import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsGPTTest {

    // ========== Testes para uniquePaths (1D DP) ==========

    @Test
    @DisplayName("uniquePaths_MenorQueMaior_InverteParametros")
    void uniquePaths_MenorQueMaior_InverteParametros() {
        // Input parameter(s):
        int m = 5;
        int n = 3;

        // Expected result:
        int expected = UniquePaths.uniquePaths(3, 5); // chamada equivalente

        // Chamada do método:
        int result = UniquePaths.uniquePaths(m, n);

        // Verificação:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("uniquePaths_Grid1x1_Retorna1")
    void uniquePaths_Grid1x1_Retorna1() {
        int result = UniquePaths.uniquePaths(1, 1);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("uniquePaths_Grid3x2_Retorna3")
    void uniquePaths_Grid3x2_Retorna3() {
        int result = UniquePaths.uniquePaths(3, 2);
        assertEquals(3, result); // caminhos: DDR, DRD, RDD
    }

    @Test
    @DisplayName("uniquePaths_Grid3x3_Retorna6")
    void uniquePaths_Grid3x3_Retorna6() {
        int result = UniquePaths.uniquePaths(3, 3);
        assertEquals(6, result); // combinação de 2 movimentos para direita e 2 para baixo
    }

    @Test
    @DisplayName("uniquePaths_GridGrande_RetornaValorEsperado")
    void uniquePaths_GridGrande_RetornaValorEsperado() {
        int result = UniquePaths.uniquePaths(10, 10);
        assertEquals(48620, result);
    }

    @Test
    @DisplayName("uniquePaths_OverflowCausaArithmeticException")
    void uniquePaths_OverflowCausaArithmeticException() {
        // Parâmetros grandes o suficiente para causar overflow em addExact
        assertThrows(ArithmeticException.class, () -> UniquePaths.uniquePaths(1000, 1000));
    }

    // ========== Testes para uniquePaths2 (2D DP) ==========

    @Test
    @DisplayName("uniquePaths2_Grid1x1_Retorna1")
    void uniquePaths2_Grid1x1_Retorna1() {
        int result = UniquePaths.uniquePaths2(1, 1);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("uniquePaths2_Grid2x3_Retorna3")
    void uniquePaths2_Grid2x3_Retorna3() {
        int result = UniquePaths.uniquePaths2(2, 3);
        assertEquals(3, result); // caminhos: RRD, RDR, DRR
    }

    @Test
    @DisplayName("uniquePaths2_Grid3x3_Retorna6")
    void uniquePaths2_Grid3x3_Retorna6() {
        int result = UniquePaths.uniquePaths2(3, 3);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("uniquePaths2_GridGrande_RetornaValorEsperado")
    void uniquePaths2_GridGrande_RetornaValorEsperado() {
        int result = UniquePaths.uniquePaths2(10, 10);
        assertEquals(48620, result);
    }

    @Test
    @DisplayName("uniquePaths2_OverflowCausaArithmeticException")
    void uniquePaths2_OverflowCausaArithmeticException() {
        assertThrows(ArithmeticException.class, () -> UniquePaths.uniquePaths2(1000, 1000));
    }
}
