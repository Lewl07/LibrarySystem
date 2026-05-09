import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.leoho.domain.Book;
import org.leoho.domain.Status;
import org.leoho.util.Validation;

public class ValidationTest {

    @Test
    @DisplayName("1234567890123 (13 digits)-> true")
    void isISBNValidTest1() {
        boolean expected = true;
        boolean actual = Validation.isValidISBN("1234567890123");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("12345678901234 (14 digits)-> IllegalArgumentException")
    void isISBNValidTest2() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Book("fake", Status.IN_STORE, "12345678901234", "?", "?"));
    }

    @Test
    @DisplayName("123456789012 (12 digits) -> IllegalArgumentException")
    void isISBNValidTest3() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Book("fake", Status.IN_STORE, "123456789012", "?", "?"));
    }

    @Test
    @DisplayName("\"\" -> IllegalArgumentException")
    void isISBNValidTest4() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Book("fake", Status.IN_STORE, "", "?", "?"));
    }

    @Test
    @DisplayName("null -> IllegalArgumentException")
    void isISBNValidTest5() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Book("fake", Status.IN_STORE, null, "?", "?"));
    }
}
