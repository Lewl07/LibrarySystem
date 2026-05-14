package org.leoho;

import org.leoho.domain.*;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin("LEO", library);

        System.out.println("1. ADMIN INITIALIZE USERS AND ITEMS IN LIBRARY");
        admin.initUsers();
        admin.initItems();

        System.out.println();

        System.out.println("ITEMS:");
        for (Item item : library.getItems()) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("USERS:");
        for (User user : library.getUsers()) {
            System.out.println(user);
        }

        System.out.println();

        Teacher bestTeacher = new Teacher("GOAT", library);
        Book book1 = new Book("Java 101", Status.IN_STORE, "1234567890123", "Michael", "code");
        Book book2 = new Book("Java 101", Status.IN_STORE, "1234567890129", "Leo", "code");
        Book book3 = new Book("Java 101", Status.IN_STORE, "1234567890123", "Michael", "code");

        library.getItems().add(book1);
        library.getItems().add(book2);
        library.getItems().add(book3);
        library.getUsers().add(bestTeacher);

        List<Item> searchItem1 = bestTeacher.searchItemStream("Michael");
        Set<Item> searchItem2 = bestTeacher.searchItemRecursive("Java 101");

        System.out.println("2. SEARCH BY STREAM");
        for (Item item : searchItem1) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("3. SEARCH BY RECURSION");
        for (Item item : searchItem2) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("4. BORROW & RETURN ITEM BY USER");
        bestTeacher.borrowItem(book2);
        System.out.println(bestTeacher.getBorrowedItems());
        bestTeacher.returnItem(book2);
        System.out.println(bestTeacher.getBorrowedItems());

        System.out.println();

        System.out.println("5. GENERATE REPORT BY ADMIN");
        admin.generateReport();

        System.out.println();

        System.out.println("6. BACKUP DATA OF THE LIBRARY BY ADMIN (see users and items csv)");
        admin.backupLibraryData();
    }
}
