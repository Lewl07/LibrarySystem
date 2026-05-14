package org.leoho;

import org.leoho.domain.*;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin("LEO", library);

        admin.initUsers();
        admin.initItems();

        Book book1 = new Book("Java 101", Status.IN_STORE, "1234567890123", "Michael", "code");
        Book book2 = new Book("Java 101", Status.IN_STORE, "1234567890129", "Leo", "code");
        Book book3 = new Book("Java 101", Status.IN_STORE, "1234567890123", "Michael", "code");
        Teacher bestTeacher = new Teacher("GOAT", library);

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);
        library.getUsers().add(bestTeacher);

        List<Item> searchItem1 = bestTeacher.searchItemStream("Robert");
        Set<Item> searchItem2 = bestTeacher.searchItemRecursive("Java 101");

        for (Item item : searchItem1) {
            System.out.println(item);
        }

        for (Item item : searchItem2) {
            System.out.println(item);
        }

        bestTeacher.borrowItem(book2);
        bestTeacher.returnItem(book2);

        admin.generateReport();
        admin.backupLibraryData();
    }
}
