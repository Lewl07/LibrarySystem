import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.leoho.domain.*;

public class TeacherTest {

    // borrowItem()

    @Test
    @DisplayName("Borrowing an item with an empty list of borrowedItems -> true")
    void borrowItemTest1() {
        Teacher teacher = new Teacher("Leo", new Library());
        Magazine magazine = new Magazine("Yoga", Status.IN_STORE, 1234, "Mike");

        boolean expected = true;
        boolean actual = teacher.borrowItem((magazine));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Borrowing a lost item -> IllegalArgumentException")
    void borrowItemTest2() {
        Teacher teacher = new Teacher("Leo", new Library());

        DVD dvd = new DVD("The Life of Michael", Status.LOST, "Mike", 120);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> teacher.borrowItem(dvd)
        );
    }

    @Test
    @DisplayName("Borrowing a borrowed item -> IllegalArgumentException")
    void borrowItemTest3() {
        Student student = new Student("Leo", new Library());

        DVD dvd = new DVD("The Life of Michael", Status.BORROWED, "Mike", 120);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> student.borrowItem(dvd)
        );
    }

    @Test
    @DisplayName("Borrowing more than 10 items -> IllegalArgumentException")
    void borrowItemTest4() {
        Teacher teacher = new Teacher("Leo", new Library());

        Book book1 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book2 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book3 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book4 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book5 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Book book6 = new Book("Java", Status.IN_STORE, "1234567890123", "Leo", "Thriller");
        Magazine magazine1 = new Magazine("Yoga", Status.IN_STORE, 1234, "Mike");
        Magazine magazine2 = new Magazine("Yoga", Status.IN_STORE, 1234, "Mike");
        Magazine magazine3 = new Magazine("Yoga", Status.IN_STORE, 1234, "Mike");
        DVD dvd1 = new DVD("The Life of Michael", Status.IN_STORE, "Mike", 120);
        DVD dvd2 = new DVD("A purposeless life", Status.IN_STORE, "John", 200);


        teacher.borrowItem(book1);
        teacher.borrowItem(book2);
        teacher.borrowItem(book3);
        teacher.borrowItem(book4);
        teacher.borrowItem(book5);
        teacher.borrowItem(book6);
        teacher.borrowItem(magazine1);
        teacher.borrowItem(magazine2);
        teacher.borrowItem(magazine3);
        teacher.borrowItem(dvd1);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> teacher.borrowItem(dvd2)
        );
    }

    @Test
    @DisplayName("Borrowing an item twice in a row -> IllegalArgumentException")
    void borrowItemTest5() {
        Teacher teacher = new Teacher("Leo", new Library());

        Magazine magazine = new Magazine("Java", Status.IN_STORE, 123,"Mike");

        teacher.borrowItem(magazine);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> teacher.borrowItem(magazine)
        );
    }
}
