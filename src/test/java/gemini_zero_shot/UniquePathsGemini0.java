import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes para a classe UniquePaths")
class UniquePathsGemini0Test {

    @Nested
    @DisplayName("Testes para uniquePaths() - Otimização com Array 1D")
    class UniquePaths1DTests {

        @Test
        @DisplayName("Deve calcular o número de caminhos para uma grade padrão (3x7)")
        void testWithStandardGrid() {
            // C(8,2) = (8*7)/(2*1) = 28
            assertEquals(28, UniquePaths.uniquePaths(3, 7));
        }

        @Test
        @DisplayName("Deve calcular o número de caminhos para uma grade quadrada (3x3)")
        void testWithSquareGrid() {
            // C(4,2) = (4*3)/(2*1) = 6
            assertEquals(6, UniquePaths.uniquePaths(3, 3));
        }

        @Test
        @DisplayName("Deve retornar 1 para uma grade com uma única linha (1x10)")
        void testWithOneRow() {
            assertEquals(1, UniquePaths.uniquePaths(1, 10));
        }

        @Test
        @DisplayName("Deve retornar 1 para uma grade com uma única coluna (10x1)")
        void testWithOneColumn() {
            assertEquals(1, UniquePaths.uniquePaths(10, 1));
        }

        @Test
        @DisplayName("Deve retornar 1 para uma grade mínima (1x1)")
        void testWithOneByOneGrid() {
            assertEquals(1, UniquePaths.uniquePaths(1, 1));
        }

        @Test
        @DisplayName("Deve retornar 2 para uma grade 2x2")
        void testWithTwoByTwoGrid() {
            assertEquals(2, UniquePaths.uniquePaths(2, 2));
        }

        @Test
        @DisplayName("Deve retornar o mesmo resultado para dimensões trocadas (3x7 e 7x3)")
        void testWithSwappedDimensions() {
            assertEquals(UniquePaths.uniquePaths(3, 7), UniquePaths.uniquePaths(7, 3));
        }

        @Test
        @DisplayName("Deve lançar ArithmeticException para entradas grandes que causam estouro de inteiro")
        void testForIntegerOverflow() {
            // O resultado para (18, 18) é 2.333.606.220, que é maior que Integer.MAX_VALUE
            assertThrows(ArithmeticException.class, () -> UniquePaths.uniquePaths(18, 18));
        }
    }

    @Nested
    @DisplayName("Testes para uniquePaths2() - Abordagem com Array 2D")
    class UniquePaths2DTests {

        @Test
        @DisplayName("Deve calcular o número de caminhos para uma grade padrão (3x7)")
        void testWithStandardGrid() {
            assertEquals(28, UniquePaths.uniquePaths2(3, 7));
        }

        @Test
        @DisplayName("Deve calcular o número de caminhos para uma grade quadrada (3x3)")
        void testWithSquareGrid() {
            assertEquals(6, UniquePaths.uniquePaths2(3, 3));
        }

        @Test
        @DisplayName("Deve retornar 1 para uma grade com uma única linha (1x10)")
        void testWithOneRow() {
            assertEquals(1, UniquePaths.uniquePaths2(1, 10));
        }

        @Test
        @DisplayName("Deve retornar 1 para uma grade com uma única coluna (10x1)")
        void testWithOneColumn() {
            assertEquals(1, UniquePaths.uniquePaths2(10, 1));
        }

        @Test
        @DisplayName("Deve retornar 1 para uma grade mínima (1x1)")
        void testWithOneByOneGrid() {
            assertEquals(1, UniquePaths.uniquePaths2(1, 1));
        }

        @Test
        @DisplayName("Deve retornar 2 para uma grade 2x2")
        void testWithTwoByTwoGrid() {
            assertEquals(2, UniquePaths.uniquePaths2(2, 2));
        }

        @Test
        @DisplayName("Deve retornar o mesmo resultado para dimensões trocadas (3x7 e 7x3)")
        void testWithSwappedDimensions() {
            assertEquals(UniquePaths.uniquePaths2(3, 7), UniquePaths.uniquePaths2(7, 3));
        }

        @Test
        @DisplayName("Deve lançar ArithmeticException para entradas grandes que causam estouro de inteiro")
        void testForIntegerOverflow() {
            // O resultado para (18, 18) é 2.333.606.220, que é maior que Integer.MAX_VALUE
            assertThrows(ArithmeticException.class, () -> UniquePaths.uniquePaths2(18, 18));
        }
    }
}