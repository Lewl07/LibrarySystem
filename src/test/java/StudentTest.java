import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.leoho.domain.*;

import java.util.*;

public class StudentTest {

    // borrowItem()

    @Test
    @DisplayName("Borrowing a book with an empty list of borrowedBooks -> true")
    void borrowItemTest1() {
        Student student = new Student("Leo", new Library());
        Book book = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");

        boolean expected = true;
        boolean actual = student.borrowItem(book);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Borrowing a lost book -> IllegalArgumentException")
    void borrowItemTest2() {
        Student student = new Student("Leo", new Library());

        Book book = new Book("Java", Status.LOST, "1234567890123", "Leo", "Thriller");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.borrowItem(book)
        );
    }

    @Test
    @DisplayName("Borrowing a borrowed book -> IllegalArgumentException")
    void borrowItemTest3() {
        Student student = new Student("Leo", new Library());

        Book book = new Book("Java", Status.BORROWED, "1234567890123", "Leo", "Thriller");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.borrowItem(book)
        );
    }

    @Test
    @DisplayName("Borrowing more than 5 books -> IllegalArgumentException")
    void borrowItemTest4() {
        Student student = new Student("Leo", new Library());

        Book book1 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book2 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book3 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book4 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book5 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book6 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");

        student.borrowItem(book1);
        student.borrowItem(book2);
        student.borrowItem(book3);
        student.borrowItem(book4);
        student.borrowItem(book5);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.borrowItem(book6)
        );
    }

    @Test
    @DisplayName("Borrowing an item that is not a book (magazine) -> IllegalArgumentException")
    void borrowItemTest5() {
        Student student = new Student("Leo", new Library());

        Magazine magazine = new Magazine("Java", Status.IN_STORE, 123,"Mike");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.borrowItem(magazine)
        );
    }

    @Test
    @DisplayName("Borrowing an item that is not a book (dvd) -> IllegalArgumentException")
    void borrowItemTest6() {
        Student student = new Student("Leo", new Library());

        DVD dvd = new DVD("Java", Status.IN_STORE,"Mike", 120);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.borrowItem(dvd)
        );
    }

    @Test
    @DisplayName("Borrowing a book with an already existing list containing other books -> true")
    void borrowItemTest7() {
        Student student = new Student("Leo", new Library());

        Book book1 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book2 = new Book("Python", Status.IN_STORE, "1234567890123", "Leo", "Thriller");

        student.borrowItem(book1);

        boolean expected = true;
        boolean actual = student.borrowItem(book2);

        Assertions.assertEquals(expected, actual);
    }

    // returnItem()

    @Test
    @DisplayName("Returning a book that was borrowed -> true")
    void returnItemTest1() {
        Student student = new Student("Leo", new Library());

        Book book1 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book2 = new Book("Python", Status.IN_STORE, "1234567890123", "Leo", "Thriller");

        student.borrowItem(book1);
        student.borrowItem(book2);

        boolean expected = true;
        boolean actual = student.returnItem(book1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Returning a book that wasn't borrowed -> IllegalArgumentException")
    void returnItemTest2() {
        Student student = new Student("Leo", new Library());

        Book book1 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book2 = new Book("Python", Status.IN_STORE, "1234567890123", "Leo", "Thriller");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.returnItem(book1)
        );
    }
}
