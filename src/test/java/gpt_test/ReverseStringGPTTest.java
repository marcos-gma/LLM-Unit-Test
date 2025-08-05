import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReverseStringGPTTest {

    // ----------------------------
    // reverse(String)
    // ----------------------------

    @Test
    void reverse_StringNormal_DeveRetornarReverso() {
        // Input parameter
        String input = "algorithms";

        // Expected result
        String expected = "smhtirogla";

        // Assert
        assertThat(ReverseString.reverse(input)).isEqualTo(expected);
    }

    @Test
    void reverse_StringVazia_DeveRetornarVazio() {
        String input = "";
        String expected = "";

        assertThat(ReverseString.reverse(input)).isEqualTo(expected);
    }

    @Test
    void reverse_StringUmCaractere_DeveRetornarMesma() {
        String input = "A";
        assertThat(ReverseString.reverse(input)).isEqualTo("A");
    }

    @Test
    void reverse_StringComEspacos_DeveInverterTudo() {
        String input = "a b c";
        String expected = "c b a";

        assertThat(ReverseString.reverse(input)).isEqualTo(expected);
    }

    // ----------------------------
    // reverse2(String)
    // ----------------------------

    @Test
    void reverse2_StringNormal_DeveRetornarReverso() {
        String input = "abcdef";
        String expected = "fedcba";

        assertThat(ReverseString.reverse2(input)).isEqualTo(expected);
    }

    @Test
    void reverse2_StringVazia_DeveRetornarVazio() {
        String input = "";
        String expected = "";

        assertThat(ReverseString.reverse2(input)).isEqualTo(expected);
    }

    @Test
    void reverse2_StringNula_DeveRetornarNull() {
        String input = null;

        assertThat(ReverseString.reverse2(input)).isNull();
    }

    @Test
    void reverse2_StringPalindromo_DeveRetornarIgual() {
        String input = "arara";

        assertThat(ReverseString.reverse2(input)).isEqualTo("arara");
    }

    // ----------------------------
    // reverse3(String)
    // ----------------------------

    @Test
    void reverse3_StringNormal_DeveRetornarReverso() {
        String input = "hello";
        String expected = "olleh";

        assertThat(ReverseString.reverse3(input)).isEqualTo(expected);
    }

    @Test
    void reverse3_StringVazia_DeveRetornarVazio() {
        String input = "";

        assertThat(ReverseString.reverse3(input)).isEqualTo("");
    }

    // ----------------------------
    // reverseStringUsingStack(String)
    // ----------------------------

    @Test
    void reverseStringUsingStack_StringNormal_DeveRetornarReverso() {
        String input = "stack";
        String expected = "kcats";

        assertThat(ReverseString.reverseStringUsingStack(input)).isEqualTo(expected);
    }

    @Test
    void reverseStringUsingStack_StringVazia_DeveRetornarVazio() {
        String input = "";

        assertThat(ReverseString.reverseStringUsingStack(input)).isEqualTo("");
    }

    @Test
    void reverseStringUsingStack_StringNula_DeveLancarExcecao() {
        String input = null;

        // Expected result
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ReverseString.reverseStringUsingStack(input);
        });

        assertThat(exception.getMessage()).isEqualTo("Input string cannot be null");
    }

    @Test
    void reverseStringUsingStack_StringUmCaractere_DeveRetornarMesma() {
        String input = "x";

        assertThat(ReverseString.reverseStringUsingStack(input)).isEqualTo("x");
    }
}
