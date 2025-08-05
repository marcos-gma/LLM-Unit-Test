import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelingSalesmanGPTTest {

    // ========== Testes para bruteForce ==========

    @Test
    @DisplayName("bruteForce_MatrizCom1Cidade_RetornaZero")
    void bruteForce_MatrizCom1Cidade_RetornaZero() {
        int[][] distanceMatrix = new int[1][1];
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("bruteForce_MatrizCom3Cidades_RetornaMenorDistancia")
    void bruteForce_MatrizCom3Cidades_RetornaMenorDistancia() {
        int[][] distanceMatrix = {
                {0, 10, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(45, result); // 0 → 1 → 2 → 0
    }

    @Test
    @DisplayName("bruteForce_MatrizComCaminhoInvalido_RetornaIntegerMaxValue")
    void bruteForce_MatrizComCaminhoInvalido_RetornaIntegerMaxValue() {
        int[][] distanceMatrix = {
                {0, Integer.MAX_VALUE, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        int result = TravelingSalesman.bruteForce(distanceMatrix);
        assertEquals(Integer.MAX_VALUE, result);
    }

    // ========== Testes para calculateDistance ==========

    @Test
    @DisplayName("calculateDistance_RotaValida_RetornaDistanciaCorreta")
    void calculateDistance_RotaValida_RetornaDistanciaCorreta() {
        int[][] distanceMatrix = {
                {0, 10, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(45, result); // 0→1→2→0
    }

    @Test
    @DisplayName("calculateDistance_CaminhoComIntegerMaxValue_RetornaIntegerMaxValue")
    void calculateDistance_CaminhoComIntegerMaxValue_RetornaIntegerMaxValue() {
        int[][] distanceMatrix = {
                {0, Integer.MAX_VALUE, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    @DisplayName("calculateDistance_RetornoParaOrigemInvalido_RetornaIntegerMaxValue")
    void calculateDistance_RetornoParaOrigemInvalido_RetornaIntegerMaxValue() {
        int[][] distanceMatrix = {
                {0, 10, 15},
                {10, 0, 20},
                {Integer.MAX_VALUE, 20, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);
        int result = TravelingSalesman.calculateDistance(distanceMatrix, route);
        assertEquals(Integer.MAX_VALUE, result);
    }

    // ========== Testes para dynamicProgramming ==========

    @Test
    @DisplayName("dynamicProgramming_MatrizVazia_RetornaZero")
    void dynamicProgramming_MatrizVazia_RetornaZero() {
        int[][] distanceMatrix = {};
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("dynamicProgramming_MatrizNaoQuadrada_LancaExcecao")
    void dynamicProgramming_MatrizNaoQuadrada_LancaExcecao() {
        int[][] distanceMatrix = {
                {0, 10},
                {10}
        };
        assertThrows(IllegalArgumentException.class, () -> TravelingSalesman.dynamicProgramming(distanceMatrix));
    }

    @Test
    @DisplayName("dynamicProgramming_MatrizCom3Cidades_RetornaMenorDistancia")
    void dynamicProgramming_MatrizCom3Cidades_RetornaMenorDistancia() {
        int[][] distanceMatrix = {
                {0, 10, 15},
                {10, 0, 20},
                {15, 20, 0}
        };
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(45, result); // 0→1→2→0
    }

    @Test
    @DisplayName("dynamicProgramming_CaminhosInvalidos_RetornaZero")
    void dynamicProgramming_CaminhosInvalidos_RetornaZero() {
        int[][] distanceMatrix = {
                {0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        assertEquals(0, result); // Nenhum caminho completo possível
    }

}
