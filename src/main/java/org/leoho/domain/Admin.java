package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.interfaces.Reportable;
import org.leoho.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class Admin extends User implements Reportable {

    public Admin(String name, Library library) {
        super(name, library);
    }

    /**
     * Admins do not borrow
     * @param item the item
     * @return nothing (exception handling)
     */
    @Override
    public boolean borrowItem(Item item) {
        throw new IllegalArgumentException("Admins cannot borrow items.");
    }

    /**
     * Admins do not borrow
     * @param item the item
     * @return nothing (exception handling)
     */
    @Override
    public boolean returnItem(Item item) {
       throw new IllegalArgumentException("Admins cannot return items.");
    }

    /**
     * The admin generates a report of each item in the library
     * giving the following information: ID, name, and status.
     */
    @Override
    public void generateReport() {
        for (Item item : library.getItems()) {
            System.out.printf("ID: %s, Name: %s, Status: %s\n", item.getId(), item.getTitle(), item.getStatus());
        }
    }

    /**
     * Load CSV fields to initialize students.
     */
    private void initStudents() {
        File file = new File(Constants.STUDENTS_CSV_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] elements = row.split(" ");

                id = elements[0];
                name = elements[1];

                Student student = new Student(id, name);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load CSV fields to initialize teachers.
     */
    private void initTeachers() {
        File file = new File(Constants.TEACHERS_CSV_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] elements = row.split(" ");

                id = elements[0];
                name = elements[1];

                Teacher teacher = new Teacher(id, name);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load CSV fields to initialize books.
     */
    private void initBooks() {

    }

    /**
     * Load CSV fields to initialize DVDs.
     */
    private void initDVDs() {

    }

    /**
     * Load CSV fields to initialize magazines.
     */
    private void initMagazines() {

    }

    /**
     * Call initStudents() and initTeachers() to load users.
     */
    public void initUsers() {
        initStudents();
        initTeachers();
    }

    /**
     * Call initBooks(), initDVDs(), and initMagazines() to load items.
     */
    public void initItems() {

    }

    /**
     * Backup the library data by writing current users and items into two CSV files
     */
    public void backupLibraryData() {

    }
}
