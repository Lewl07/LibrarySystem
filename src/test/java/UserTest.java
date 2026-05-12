import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.leoho.domain.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTest {


    // searchItemRecursive()

    @Test
    @DisplayName("keyword: LEO -> [Java Code, Python Code]")
    void searchItemRecursiveTest1() {
        Set<Item> expected = new HashSet<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Python Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        expected.add(book1);
        expected.add(book3);

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        Set<Item> actual = student.searchItemRecursive("LEO");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("keyword: code -> [Java Code (Leo), Python Code (Leo)]")
    void searchItemRecursiveTest2() {
        Set<Item> expected = new HashSet<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Python Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        expected.add(book1);
        expected.add(book3);

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        Set<Item> actual = student.searchItemRecursive("code");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("keyword: JAVA -> [Java (Leo), Java (Robert)]")
    void searchItemRecursiveTest3() {
        Set<Item> expected = new HashSet<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Java", Status.IN_STORE, "1234567890123", "Robert", "Coding");

        expected.add(book1);
        expected.add(book3);

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        Set<Item> actual = student.searchItemRecursive("JAVA");

        Assertions.assertEquals(expected, actual);
    }

    // searchItemStream()

    @Test
    @DisplayName("keyword: LEO -> [Java Code, Python Code]")
    void searchItemStreamTest1() {
        List<Item> expected = new ArrayList<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Python Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        expected.add(book1);
        expected.add(book3);

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        List<Item> actual = student.searchItemStream("LEO");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("keyword: Robert -> []")
    void searchItemStreamTest2() {
        List<Item> expected = new ArrayList<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Python Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        List<Item> actual = student.searchItemStream("ROBERT");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("keyword: \"\" -> []")
    void searchItemStreamTest3() {
        List<Item> expected = new ArrayList<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Python Code", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        List<Item> actual = student.searchItemStream("\"\"");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("keyword: JAVA -> [Java]")
    void searchItemStreamTest4() {
        List<Item> expected = new ArrayList<>();

        Library library = new Library();
        Student student = new Student("Bob", library);

        Book book1 = new Book
                ("Java", Status.IN_STORE, "1234567890123", "Leo", "Coding");

        Book book2 = new Book
                ("Time machine", Status.IN_STORE, "1234567890123", "John", "Sci-Fi");

        Book book3 = new Book
                ("Java", Status.IN_STORE, "1234567890123", "Robert", "Coding");

        expected.add(book1);

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);

        List<Item> actual = student.searchItemStream("JAVA");

        Assertions.assertEquals(expected, actual);
    }
}
