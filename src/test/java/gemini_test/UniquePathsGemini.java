import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes unitários para a classe UniquePaths.
 *
 * Esta classe visa testar todos os métodos públicos da classe UniquePaths,
 * garantindo que todos os cenários e branches de código sejam cobertos.
 */
class UniquePathsGeminiTest {

    //region Testes para o método uniquePaths(int, int)

    /**
     * Case name: [uniquePaths_GridPadrao_DeveRetornarNumeroCorretoDeCaminhos]
     *
     * Input parameter(s):
     * (1) Initialize m = 3
     * (2) Initialize n = 7
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 28 deve ser retornado]
     */
    @Test
    @DisplayName("uniquePaths: Deve retornar o número correto de caminhos para um grid padrão (3x7)")
    void uniquePaths_GridPadrao_DeveRetornarNumeroCorretoDeCaminhos() {
        // Arrange
        int m = 3;
        int n = 7;
        int expectedPaths = 28;

        // Act
        int result = UniquePaths.uniquePaths(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths_DimensoesTrocadas_DeveRetornarMesmoResultado]
     *
     * Input parameter(s):
     * (1) Initialize m = 7
     * (2) Initialize n = 3
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Hint specification:
     * (1) Este teste cobre o branch `if (m > n)`, que faz uma chamada recursiva.
     *
     * Expected result: [O valor 28 deve ser retornado, o mesmo que para 3x7]
     */
    @Test
    @DisplayName("uniquePaths: Deve retornar o mesmo resultado quando as dimensões m e n são trocadas (branch m > n)")
    void uniquePaths_DimensoesTrocadas_DeveRetornarMesmoResultado() {
        // Arrange
        int m = 7;
        int n = 3;
        int expectedPaths = 28;

        // Act
        int result = UniquePaths.uniquePaths(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths_Grid1x1_DeveRetornar1]
     *
     * Input parameter(s):
     * (1) Initialize m = 1
     * (2) Initialize n = 1
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 1 deve ser retornado]
     */
    @Test
    @DisplayName("uniquePaths: Deve retornar 1 para um grid de 1x1")
    void uniquePaths_Grid1x1_DeveRetornar1() {
        // Arrange
        int m = 1;
        int n = 1;
        int expectedPaths = 1;

        // Act
        int result = UniquePaths.uniquePaths(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths_GridComUmaLinha_DeveRetornar1]
     *
     * Input parameter(s):
     * (1) Initialize m = 1
     * (2) Initialize n = 10
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 1 deve ser retornado, pois só há um caminho reto]
     */
    @Test
    @DisplayName("uniquePaths: Deve retornar 1 para um grid com apenas uma linha")
    void uniquePaths_GridComUmaLinha_DeveRetornar1() {
        // Arrange
        int m = 1;
        int n = 10;
        int expectedPaths = 1;

        // Act
        int result = UniquePaths.uniquePaths(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths_GridComUmaColuna_DeveRetornar1]
     *
     * Input parameter(s):
     * (1) Initialize m = 10
     * (2) Initialize n = 1
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 1 deve ser retornado, pois só há um caminho reto]
     */
    @Test
    @DisplayName("uniquePaths: Deve retornar 1 para um grid com apenas uma coluna")
    void uniquePaths_GridComUmaColuna_DeveRetornar1() {
        // Arrange
        int m = 10;
        int n = 1;
        int expectedPaths = 1;

        // Act
        int result = UniquePaths.uniquePaths(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }
    
    /**
     * Case name: [uniquePaths_GridGrande_DeveLancarArithmeticException]
     *
     * Input parameter(s):
     * (1) Initialize m = 30
     * (2) Initialize n = 30
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Hint specification:
     * (1) Este teste verifica o comportamento de Math.addExact em caso de overflow.
     *
     * Expected result: [Uma exceção ArithmeticException deve ser lançada]
     */
    @Test
    @DisplayName("uniquePaths: Deve lançar ArithmeticException para um grid grande que causa overflow")
    void uniquePaths_GridGrande_DeveLancarArithmeticException() {
        // Arrange
        int m = 30;
        int n = 30;

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> {
            UniquePaths.uniquePaths(m, n);
        });
    }

    //endregion

    //region Testes para o método uniquePaths2(int, int)

    /**
     * Case name: [uniquePaths2_GridPadrao_DeveRetornarNumeroCorretoDeCaminhos]
     *
     * Input parameter(s):
     * (1) Initialize m = 3
     * (2) Initialize n = 7
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 28 deve ser retornado]
     */
    @Test
    @DisplayName("uniquePaths2: Deve retornar o número correto de caminhos para um grid padrão (3x7)")
    void uniquePaths2_GridPadrao_DeveRetornarNumeroCorretoDeCaminhos() {
        // Arrange
        int m = 3;
        int n = 7;
        int expectedPaths = 28;

        // Act
        int result = UniquePaths.uniquePaths2(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }
    
    /**
     * Case name: [uniquePaths2_DimensoesTrocadas_DeveRetornarMesmoResultado]
     *
     * Input parameter(s):
     * (1) Initialize m = 7
     * (2) Initialize n = 3
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 28 deve ser retornado, o mesmo que para 3x7]
     */
    @Test
    @DisplayName("uniquePaths2: Deve retornar o mesmo resultado quando as dimensões m e n são trocadas")
    void uniquePaths2_DimensoesTrocadas_DeveRetornarMesmoResultado() {
        // Arrange
        int m = 7;
        int n = 3;
        int expectedPaths = 28;

        // Act
        int result = UniquePaths.uniquePaths2(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths2_Grid1x1_DeveRetornar1]
     *
     * Input parameter(s):
     * (1) Initialize m = 1
     * (2) Initialize n = 1
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 1 deve ser retornado]
     */
    @Test
    @DisplayName("uniquePaths2: Deve retornar 1 para um grid de 1x1")
    void uniquePaths2_Grid1x1_DeveRetornar1() {
        // Arrange
        int m = 1;
        int n = 1;
        int expectedPaths = 1;

        // Act
        int result = UniquePaths.uniquePaths2(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths2_GridComUmaLinha_DeveRetornar1]
     *
     * Input parameter(s):
     * (1) Initialize m = 1
     * (2) Initialize n = 10
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 1 deve ser retornado]
     */
    @Test
    @DisplayName("uniquePaths2: Deve retornar 1 para um grid com apenas uma linha")
    void uniquePaths2_GridComUmaLinha_DeveRetornar1() {
        // Arrange
        int m = 1;
        int n = 10;
        int expectedPaths = 1;

        // Act
        int result = UniquePaths.uniquePaths2(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }

    /**
     * Case name: [uniquePaths2_GridComUmaColuna_DeveRetornar1]
     *
     * Input parameter(s):
     * (1) Initialize m = 10
     * (2) Initialize n = 1
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor 1 deve ser retornado]
     */
    @Test
    @DisplayName("uniquePaths2: Deve retornar 1 para um grid com apenas uma coluna")
    void uniquePaths2_GridComUmaColuna_DeveRetornar1() {
        // Arrange
        int m = 10;
        int n = 1;
        int expectedPaths = 1;

        // Act
        int result = UniquePaths.uniquePaths2(m, n);

        // Assert
        assertEquals(expectedPaths, result);
    }
    
    /**
     * Case name: [uniquePaths2_GridGrande_DeveLancarArithmeticException]
     *
     * Input parameter(s):
     * (1) Initialize m = 30
     * (2) Initialize n = 30
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Hint specification:
     * (1) Este teste verifica o comportamento de Math.addExact em caso de overflow.
     *
     * Expected result: [Uma exceção ArithmeticException deve ser lançada]
     */
    @Test
    @DisplayName("uniquePaths2: Deve lançar ArithmeticException para um grid grande que causa overflow")
    void uniquePaths2_GridGrande_DeveLancarArithmeticException() {
        // Arrange
        int m = 30;
        int n = 30;

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> {
            UniquePaths.uniquePaths2(m, n);
        });
    }

    //endregion
}
