import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.leoho.util.Validation;

public class ValidationTest {

    @Test
    @DisplayName("1234567890123 -> true")
    void isISBNValidTest1() {
        boolean expected = true;
        boolean actual = Validation.isValidISBN("1234567890123");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("12345678901234 -> false")
    void isISBNValidTest2() {
        boolean expected = false;
        boolean actual = Validation.isValidISBN("12345678901234");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("123456789012 -> false")
    void isISBNValidTest3() {
        boolean expected = false;
        boolean actual = Validation.isValidISBN("123456789012");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("\"\" -> false")
    void isISBNValidTest4() {
        boolean expected = false;
        boolean actual = Validation.isValidISBN("");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("null -> false")
    void isISBNValidTest5() {
        boolean expected = false;
        boolean actual = Validation.isValidISBN(null);

        Assertions.assertEquals(expected, actual);
    }
}
