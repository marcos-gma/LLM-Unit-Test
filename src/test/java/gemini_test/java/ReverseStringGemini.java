import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes unitários para a classe ReverseString.
 *
 * Esta classe visa testar todos os métodos públicos da classe ReverseString,
 * garantindo que todos os cenários e branches de código sejam cobertos.
 */
class ReverseStringTestGemini {

    //region Testes para o método reverse(String)

    /**
     * Case name: [reverse_StringNormal_DeveRetornarStringInvertida]
     *
     * Input parameter(s):
     * (1) Initialize str = "hello"
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [A string "olleh" deve ser retornada]
     */
    @Test
    @DisplayName("reverse: Deve retornar a string invertida para uma entrada padrão")
    void reverse_StringNormal_DeveRetornarStringInvertida() {
        // Arrange
        String input = "hello";
        String expected = "olleh";

        // Act
        String result = ReverseString.reverse(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverse_StringVazia_DeveRetornarStringVazia]
     *
     * Input parameter(s):
     * (1) Initialize str = ""
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma string vazia "" deve ser retornada]
     */
    @Test
    @DisplayName("reverse: Deve retornar uma string vazia se a entrada for vazia")
    void reverse_StringVazia_DeveRetornarStringVazia() {
        // Arrange
        String input = "";
        String expected = "";

        // Act
        String result = ReverseString.reverse(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverse_StringNula_DeveLancarNullPointerException]
     *
     * Input parameter(s):
     * (1) Initialize str = null
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma exceção NullPointerException deve ser lançada]
     */
    @Test
    @DisplayName("reverse: Deve lançar NullPointerException para entrada nula")
    void reverse_StringNula_DeveLancarNullPointerException() {
        // Arrange
        String input = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ReverseString.reverse(input);
        });
    }

    //endregion

    //region Testes para o método reverse2(String)

    /**
     * Case name: [reverse2_StringNormal_DeveRetornarStringInvertida]
     *
     * Input parameter(s):
     * (1) Initialize str = "Java"
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [A string "avaJ" deve ser retornada]
     */
    @Test
    @DisplayName("reverse2: Deve retornar a string invertida para uma entrada padrão")
    void reverse2_StringNormal_DeveRetornarStringInvertida() {
        // Arrange
        String input = "Java";
        String expected = "avaJ";

        // Act
        String result = ReverseString.reverse2(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverse2_StringNula_DeveRetornarNulo]
     *
     * Input parameter(s):
     * (1) Initialize str = null
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [O valor null deve ser retornado]
     */
    @Test
    @DisplayName("reverse2: Deve retornar nulo se a entrada for nula (branch if str == null)")
    void reverse2_StringNula_DeveRetornarNulo() {
        // Arrange
        String input = null;

        // Act
        String result = ReverseString.reverse2(input);

        // Assert
        assertNull(result);
    }

    /**
     * Case name: [reverse2_StringVazia_DeveRetornarStringVazia]
     *
     * Input parameter(s):
     * (1) Initialize str = ""
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma string vazia "" deve ser retornada]
     */
    @Test
    @DisplayName("reverse2: Deve retornar uma string vazia se a entrada for vazia (branch str.isEmpty())")
    void reverse2_StringVazia_DeveRetornarStringVazia() {
        // Arrange
        String input = "";
        String expected = "";

        // Act
        String result = ReverseString.reverse2(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverse2_StringDeUmCaractere_DeveRetornarMesmaString]
     *
     * Input parameter(s):
     * (1) Initialize str = "A"
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [A string "A" deve ser retornada]
     */
    @Test
    @DisplayName("reverse2: Deve retornar a mesma string para uma entrada de um único caractere")
    void reverse2_StringDeUmCaractere_DeveRetornarMesmaString() {
        // Arrange
        String input = "A";
        String expected = "A";

        // Act
        String result = ReverseString.reverse2(input);

        // Assert
        assertEquals(expected, result);
    }

    //endregion

    //region Testes para o método reverse3(String)

    /**
     * Case name: [reverse3_StringNormal_DeveRetornarStringInvertida]
     *
     * Input parameter(s):
     * (1) Initialize string = "testing"
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [A string "gnitset" deve ser retornada]
     */
    @Test
    @DisplayName("reverse3: Deve retornar a string invertida para uma entrada padrão")
    void reverse3_StringNormal_DeveRetornarStringInvertida() {
        // Arrange
        String input = "testing";
        String expected = "gnitset";

        // Act
        String result = ReverseString.reverse3(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverse3_StringVazia_DeveRetornarStringVazia]
     *
     * Input parameter(s):
     * (1) Initialize string = ""
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma string vazia "" deve ser retornada]
     */
    @Test
    @DisplayName("reverse3: Deve retornar uma string vazia se a entrada for vazia (branch string.isEmpty())")
    void reverse3_StringVazia_DeveRetornarStringVazia() {
        // Arrange
        String input = "";
        String expected = "";

        // Act
        String result = ReverseString.reverse3(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverse3_StringNula_DeveLancarNullPointerException]
     *
     * Input parameter(s):
     * (1) Initialize string = null
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma exceção NullPointerException deve ser lançada]
     */
    @Test
    @DisplayName("reverse3: Deve lançar NullPointerException para entrada nula")
    void reverse3_StringNula_DeveLancarNullPointerException() {
        // Arrange
        String input = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            ReverseString.reverse3(input);
        });
    }

    //endregion

    //region Testes para o método reverseStringUsingStack(String)

    /**
     * Case name: [reverseStringUsingStack_StringNormal_DeveRetornarStringInvertida]
     *
     * Input parameter(s):
     * (1) Initialize str = "Stack"
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [A string "kcatS" deve ser retornada]
     */
    @Test
    @DisplayName("reverseStringUsingStack: Deve retornar a string invertida para uma entrada padrão")
    void reverseStringUsingStack_StringNormal_DeveRetornarStringInvertida() {
        // Arrange
        String input = "Stack";
        String expected = "kcatS";

        // Act
        String result = ReverseString.reverseStringUsingStack(input);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Case name: [reverseStringUsingStack_StringNula_DeveLancarIllegalArgumentException]
     *
     * Input parameter(s):
     * (1) Initialize str = null
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma exceção IllegalArgumentException deve ser lançada]
     */
    @Test
    @DisplayName("reverseStringUsingStack: Deve lançar IllegalArgumentException para entrada nula (branch str == null)")
    void reverseStringUsingStack_StringNula_DeveLancarIllegalArgumentException() {
        // Arrange
        String input = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ReverseString.reverseStringUsingStack(input);
        });
        assertEquals("Input string cannot be null", exception.getMessage());
    }

    /**
     * Case name: [reverseStringUsingStack_StringVazia_DeveRetornarStringVazia]
     *
     * Input parameter(s):
     * (1) Initialize str = ""
     *
     * Mock condition(s):
     * (1) Nenhuma dependência externa para mockar.
     *
     * Expected result: [Uma string vazia "" deve ser retornada]
     */
    @Test
    @DisplayName("reverseStringUsingStack: Deve retornar uma string vazia se a entrada for vazia (branch str.isEmpty())")
    void reverseStringUsingStack_StringVazia_DeveRetornarStringVazia() {
        // Arrange
        String input = "";
        String expected = "";

        // Act
        String result = ReverseString.reverseStringUsingStack(input);

        // Assert
        assertEquals(expected, result);
    }
    
    //endregion
}