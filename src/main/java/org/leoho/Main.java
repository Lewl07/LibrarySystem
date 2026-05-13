package org.leoho;

import org.leoho.domain.*;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin("leoAdministrator01", library);

        admin.initUsers();
        admin.initItems();

//        library.getItems().add(new Book
//                ("deletedBook", org.leoho.domain.Status.IN_STORE,
//                "1234567890123", "Leo", "inexistant")
//        );

        admin.generateReport();

        System.out.println(library.getUsers().getFirst().searchItemRecursive("fake"));
    }
}
