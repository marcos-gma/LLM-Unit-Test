import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes unitários para a classe TravelingSalesman.
 *
 * Esta classe visa testar todos os métodos públicos da classe TravelingSalesman,
 * garantindo que todos os cenários e branches de código sejam cobertos.
 */
class TravelingSalesmanGeminiTest {

    // Matriz de distância padrão para 4 cidades, usada em múltiplos testes.
    // O caminho mais curto é 0 -> 1 -> 3 -> 2 -> 0 com distância 80.
    private final int[][] standardMatrix = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    //region Testes para o método bruteForce(int[][])

    /**
     * Case name: [bruteForce_MatrizValida_DeveRetornarDistanciaMinima]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = Matriz 4x4 padrão
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 80, que é a distância mínima do tour, deve ser retornado]
     */
    @Test
    @DisplayName("bruteForce: Deve retornar a distância mínima para uma matriz de distância válida")
    void bruteForce_MatrizValida_DeveRetornarDistanciaMinima() {
        // Arrange
        int[][] distanceMatrix = standardMatrix;
        int expectedDistance = 80;

        // Act
        int result = TravelingSalesman.bruteForce(distanceMatrix);

        // Assert
        assertEquals(expectedDistance, result);
    }

    /**
     * Case name: [bruteForce_MatrizComUmaCidade_DeveRetornarZero]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = {{0}}
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 0 deve ser retornado]
     */
    @Test
    @DisplayName("bruteForce: Deve retornar 0 para uma matriz com apenas uma cidade")
    void bruteForce_MatrizComUmaCidade_DeveRetornarZero() {
        // Arrange
        int[][] distanceMatrix = {{0}};
        int expectedDistance = 0;

        // Act
        int result = TravelingSalesman.bruteForce(distanceMatrix);

        // Assert
        assertEquals(expectedDistance, result);
    }
    
    /**
     * Case name: [bruteForce_MatrizVazia_DeveRetornarZero]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = {}
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 0 deve ser retornado]
     */
    @Test
    @DisplayName("bruteForce: Deve retornar 0 para uma matriz vazia")
    void bruteForce_MatrizVazia_DeveRetornarZero() {
        // Arrange
        int[][] distanceMatrix = {};
        int expectedDistance = 0;

        // Act
        int result = TravelingSalesman.bruteForce(distanceMatrix);

        // Assert
        assertEquals(expectedDistance, result);
    }

    //endregion

    //region Testes para o método calculateDistance(int[][], List<Integer>)

    /**
     * Case name: [calculateDistance_RotaValida_DeveRetornarDistanciaTotal]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = Matriz 4x4 padrão
     * (2) Initialize route = [0, 2, 3, 1]
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 80, que é a soma das distâncias (15 + 30 + 25 + 10), deve ser retornado]
     */
    @Test
    @DisplayName("calculateDistance: Deve retornar a distância correta para uma rota válida")
    void calculateDistance_RotaValida_DeveRetornarDistanciaTotal() {
        // Arrange
        List<Integer> route = Arrays.asList(0, 2, 3, 1);
        int expectedDistance = 15 + 30 + 25 + 10; // 0->2 + 2->3 + 3->1 + 1->0

        // Act
        int result = TravelingSalesman.calculateDistance(standardMatrix, route);

        // Assert
        assertEquals(expectedDistance, result);
    }

    /**
     * Case name: [calculateDistance_RotaComConexaoInvalida_DeveRetornarMaxInt]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix com uma conexão impossível
     * (2) Initialize route = [0, 1, 2]
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor Integer.MAX_VALUE deve ser retornado]
     */
    @Test
    @DisplayName("calculateDistance: Deve retornar MAX_VALUE se a rota contiver uma conexão impossível")
    void calculateDistance_RotaComConexaoInvalida_DeveRetornarMaxInt() {
        // Arrange
        int[][] matrixWithInvalidPath = {
            {0, Integer.MAX_VALUE, 20},
            {10, 0, 30},
            {20, 30, 0}
        };
        List<Integer> route = Arrays.asList(0, 1, 2);

        // Act
        int result = TravelingSalesman.calculateDistance(matrixWithInvalidPath, route);

        // Assert
        assertEquals(Integer.MAX_VALUE, result);
    }

    /**
     * Case name: [calculateDistance_RotaComRetornoInvalido_DeveRetornarMaxInt]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix com uma conexão de retorno impossível
     * (2) Initialize route = [0, 1, 2]
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor Integer.MAX_VALUE deve ser retornado]
     */
    @Test
    @DisplayName("calculateDistance: Deve retornar MAX_VALUE se o retorno ao início for impossível")
    void calculateDistance_RotaComRetornoInvalido_DeveRetornarMaxInt() {
        // Arrange
        int[][] matrixWithInvalidReturn = {
            {0, 10, Integer.MAX_VALUE},
            {10, 0, 30},
            {20, 30, 0}
        };
        List<Integer> route = Arrays.asList(0, 2, 1);

        // Act
        int result = TravelingSalesman.calculateDistance(matrixWithInvalidReturn, route);

        // Assert
        assertEquals(Integer.MAX_VALUE, result);
    }
    
    /**
     * Case name: [calculateDistance_RotaComUmaCidade_DeveRetornarZero]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = {{0}}
     * (2) Initialize route = [0]
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 0 deve ser retornado]
     */
    @Test
    @DisplayName("calculateDistance: Deve retornar 0 para uma rota com apenas uma cidade")
    void calculateDistance_RotaComUmaCidade_DeveRetornarZero() {
        // Arrange
        int[][] matrix = {{0}};
        List<Integer> route = Collections.singletonList(0);
        
        // Act
        int result = TravelingSalesman.calculateDistance(matrix, route);

        // Assert
        assertEquals(0, result);
    }

    //endregion

    //region Testes para o método dynamicProgramming(int[][])

    /**
     * Case name: [dynamicProgramming_MatrizValida_DeveRetornarDistanciaMinima]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = Matriz 4x4 padrão
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 80, que é a distância mínima do tour, deve ser retornado]
     */
    @Test
    @DisplayName("dynamicProgramming: Deve retornar a distância mínima para uma matriz válida")
    void dynamicProgramming_MatrizValida_DeveRetornarDistanciaMinima() {
        // Arrange
        int[][] distanceMatrix = standardMatrix;
        int expectedDistance = 80;

        // Act
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);

        // Assert
        assertEquals(expectedDistance, result);
    }
    
    /**
     * Case name: [dynamicProgramming_MatrizVazia_DeveRetornarZero]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = {}
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 0 deve ser retornado]
     */
    @Test
    @DisplayName("dynamicProgramming: Deve retornar 0 para uma matriz vazia")
    void dynamicProgramming_MatrizVazia_DeveRetornarZero() {
        // Arrange
        int[][] distanceMatrix = {};
        int expectedDistance = 0;

        // Act
        int result = TravelingSalesman.dynamicProgramming(distanceMatrix);
        
        // Assert
        assertEquals(expectedDistance, result);
    }

    /**
     * Case name: [dynamicProgramming_MatrizNaoQuadrada_DeveLancarIllegalArgumentException]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = {{1, 2}, {3, 4}, {5, 6}}
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma exceção IllegalArgumentException com a mensagem "Matrix must be square" deve ser lançada]
     */
    @Test
    @DisplayName("dynamicProgramming: Deve lançar exceção para matriz não quadrada")
    void dynamicProgramming_MatrizNaoQuadrada_DeveLancarIllegalArgumentException() {
        // Arrange
        int[][] nonSquareMatrix = {{1, 2}, {3, 4}, {5, 6}};

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            TravelingSalesman.dynamicProgramming(nonSquareMatrix);
        });
        assertEquals("Matrix must be square", exception.getMessage());
    }

    /**
     * Case name: [dynamicProgramming_SemCaminhoDeVoltaPossivel_DeveRetornarZero]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix onde o retorno à cidade 0 é impossível
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 0 deve ser retornado, pois não há tour completo]
     */
    @Test
    @DisplayName("dynamicProgramming: Deve retornar 0 quando não houver caminho de volta possível")
    void dynamicProgramming_SemCaminhoDeVoltaPossivel_DeveRetornarZero() {
        // Arrange
        int[][] matrixNoReturnPath = {
            {0, 10, 20},
            {10, 0, 30},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        int expected = 0; // O método retorna 0 se minDistance continua como MAX_VALUE

        // Act
        int result = TravelingSalesman.dynamicProgramming(matrixNoReturnPath);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [dynamicProgramming_MatrizComUmaCidade_DeveRetornarZero]
     *
     * Input parameter(s):
     * (1) Initialize distanceMatrix = {{0}}
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 0 deve ser retornado]
     */
    @Test
    @DisplayName("dynamicProgramming: Deve retornar 0 para uma matriz com uma única cidade")
    void dynamicProgramming_MatrizComUmaCidade_DeveRetornarZero() {
        // Arrange
        int[][] singleCityMatrix = {{0}};
        int expectedDistance = 0;

        // Act
        int result = TravelingSalesman.dynamicProgramming(singleCityMatrix);
        
        // Assert
        assertEquals(expectedDistance, result);
    }

    //endregion
}