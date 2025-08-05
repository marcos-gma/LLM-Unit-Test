import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe TravelingSalesman")
class TravelingSalesmanGemini0Test {

    // Matriz de distâncias simétrica para 4 cidades.
    // Menor caminho: 0 -> 1 -> 3 -> 2 -> 0, Custo: 10 + 25 + 30 + 15 = 80
    private static final int[][] FOUR_CITY_SYMMETRIC_MATRIX = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    // Matriz de distâncias assimétrica para 4 cidades.
    // Menor caminho: 0 -> 2 -> 3 -> 1 -> 0, Custo: 15 + 30 + 12 + 5 = 62
    private static final int[][] FOUR_CITY_ASYMMETRIC_MATRIX = {
        {0, 10, 15, 20},
        {5, 0, 9, 10},
        {6, 13, 0, 30},
        {8, 8, 12, 0}
    };


    @Nested
    @DisplayName("Testes para o método calculateDistance()")
    class CalculateDistanceTests {

        @Test
        @DisplayName("Deve calcular a distância de uma rota válida")
        void testCalculateValidRoute() {
            List<Integer> route = Arrays.asList(0, 2, 3, 1);
            // Distância: (0->2)15 + (2->3)30 + (3->1)25 + (1->0)10 = 80
            int expectedDistance = 80;
            assertEquals(expectedDistance, TravelingSalesman.calculateDistance(FOUR_CITY_SYMMETRIC_MATRIX, route));
        }

        @Test
        @DisplayName("Deve retornar MAX_VALUE se um trecho da rota for impossível")
        void testRouteWithImpossibleLeg() {
            int[][] matrixWithImpossibleLeg = {
                {0, 10, Integer.MAX_VALUE},
                {10, 0, 20},
                {5, 20, 0}
            };
            List<Integer> route = Arrays.asList(0, 2, 1);
            assertEquals(Integer.MAX_VALUE, TravelingSalesman.calculateDistance(matrixWithImpossibleLeg, route));
        }

        @Test
        @DisplayName("Deve retornar MAX_VALUE se o retorno à origem for impossível")
        void testRouteWithImpossibleReturn() {
            int[][] matrixWithImpossibleReturn = {
                {0, 10, 5},
                {10, 0, 20},
                {Integer.MAX_VALUE, 20, 0}
            };
            List<Integer> route = Arrays.asList(1, 2, 0);
            assertEquals(Integer.MAX_VALUE, TravelingSalesman.calculateDistance(matrixWithImpossibleReturn, route));
        }
        
        @Test
        @DisplayName("Deve calcular a distância para uma rota de cidade única (distância de volta para si mesma)")
        void testCalculateSingleCityRoute() {
            List<Integer> route = Collections.singletonList(0);
            int[][] matrix = {{5}}; // Distância para si mesmo é 5
            assertEquals(5, TravelingSalesman.calculateDistance(matrix, route));
        }

        @Test
        @DisplayName("Deve lançar IndexOutOfBoundsException para uma rota vazia")
        void testCalculateEmptyRoute() {
            assertThrows(IndexOutOfBoundsException.class,
                () -> TravelingSalesman.calculateDistance(FOUR_CITY_SYMMETRIC_MATRIX, new ArrayList<>()));
        }
    }

    @Nested
    @DisplayName("Testes para o método bruteForce()")
    class BruteForceTests {

        @Test
        @DisplayName("Deve encontrar o menor caminho em uma matriz simétrica")
        void testBruteForceSymmetric() {
            assertEquals(80, TravelingSalesman.bruteForce(FOUR_CITY_SYMMETRIC_MATRIX));
        }
        
        @Test
        @DisplayName("Deve encontrar o menor caminho em uma matriz assimétrica")
        void testBruteForceAsymmetric() {
             // Menor caminho: 0 -> 1 -> 3 -> 2 -> 0 Custo: 10 + 10 + 12 + 6 = 38
             int[][] matrix = {
                {0, 10, 15, 20},
                {5, 0, 9, 10},
                {6, 13, 0, 12},
                {8, 8, 9, 0}
             };
            assertEquals(35, TravelingSalesman.bruteForce(matrix)); // 0->1->2->3->0 = 10+9+9+8 = 36 | 0->2->3->1->0 = 15+12+8+5 = 40 | 0->1->3->2->0 = 10+10+9+6=35
        }

        @Test
        @DisplayName("Deve retornar 0 para uma única cidade")
        void testBruteForceSingleCity() {
            int[][] matrix = {{0}};
            assertEquals(0, TravelingSalesman.bruteForce(matrix));
        }

        @Test
        @DisplayName("Deve retornar 0 para uma matriz vazia")
        void testBruteForceEmptyMatrix() {
            int[][] matrix = {};
            assertEquals(0, TravelingSalesman.bruteForce(matrix));
        }

        @Test
        @DisplayName("Deve retornar a distância correta para duas cidades")
        void testBruteForceTwoCities() {
            int[][] matrix = {{0, 25}, {30, 0}};
            assertEquals(55, TravelingSalesman.bruteForce(matrix)); // 0->1->0 = 25 + 30
        }
    }
    
    @Nested
    @DisplayName("Testes para o método dynamicProgramming()")
    class DynamicProgrammingTests {
        
        @Test
        @DisplayName("Deve encontrar o menor caminho em uma matriz simétrica")
        void testDynamicProgrammingSymmetric() {
            assertEquals(80, TravelingSalesman.dynamicProgramming(FOUR_CITY_SYMMETRIC_MATRIX));
        }

        @Test
        @DisplayName("Deve encontrar o menor caminho em uma matriz assimétrica")
        void testDynamicProgrammingAsymmetric() {
            // Menor caminho: 0 -> 1 -> 3 -> 2 -> 0 Custo: 10 + 10 + 9 + 6 = 35
            int[][] matrix = {
                {0, 10, 15, 20},
                {5, 0, 9, 10},
                {6, 13, 0, 12},
                {8, 8, 9, 0}
            };
            assertEquals(35, TravelingSalesman.dynamicProgramming(matrix));
        }

        @Test
        @DisplayName("Deve retornar 0 para uma única cidade")
        void testDynamicProgrammingSingleCity() {
            int[][] matrix = {{0}};
            // O algoritmo de DP retorna 0 se não encontrar um caminho de volta completo, o que é o caso para 1 cidade.
            assertEquals(0, TravelingSalesman.dynamicProgramming(matrix));
        }

        @Test
        @DisplayName("Deve retornar 0 para uma matriz vazia")
        void testDynamicProgrammingEmptyMatrix() {
            int[][] matrix = {};
            assertEquals(0, TravelingSalesman.dynamicProgramming(matrix));
        }
        
        @Test
        @DisplayName("Deve retornar 0 se nenhum caminho for possível")
        void testDynamicProgrammingNoPathFound() {
             int[][] matrix = {
                {0, 1, Integer.MAX_VALUE},
                {1, 0, 1},
                {Integer.MAX_VALUE, 1, 0}
             };
            assertEquals(0, TravelingSalesman.dynamicProgramming(matrix));
        }

        @Test
        @DisplayName("Deve lançar IllegalArgumentException para matriz não quadrada")
        void testDynamicProgrammingNonSquareMatrix() {
            int[][] nonSquareMatrix = {
                {0, 1, 2},
                {3, 4, 5}
            };
            assertThrows(IllegalArgumentException.class, 
                () -> TravelingSalesman.dynamicProgramming(nonSquareMatrix));
        }
    }
}