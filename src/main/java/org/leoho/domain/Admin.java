package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.interfaces.Reportable;
import org.leoho.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                String[] elements = row.split(",");

                String id = elements[0];
                String name = elements[1];

                Student student = new Student(id, name, library);
                library.getUsers().add(student);
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
                String[] elements = row.split(",");

                String id = elements[0];
                String name = elements[1];

                Teacher teacher = new Teacher(id, name, library);
                library.getUsers().add(teacher);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load CSV fields to initialize books.
     */
    private void initBooks() {
        File file = new File(Constants.BOOKS_CSV_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] elements = row.split(",");

                String id = elements[0];
                String title = elements[1];
                Status status = Status.valueOf(elements[2]);
                String isbn = elements[3];
                String author = elements[4];
                String genre = elements[5];

                Book book = new Book(id, title, status, isbn, author, genre);
                library.getItems().add(book);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load CSV fields to initialize DVDs.
     */
    private void initDVDs() {
        File file = new File(Constants.DVDS_CSV_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] elements = row.split(",");

                String id = elements[0];
                String title = elements[1];
                Status status = Status.valueOf(elements[2]);
                String director = elements[3];
                int duration = Integer.parseInt(elements[4]);

                DVD dvd  = new DVD(id, title, status, director, duration);
                library.getItems().add(dvd);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Load CSV fields to initialize magazines.
     */
    private void initMagazines() {
        File file = new File(Constants.MAGAZINES_CSV_PATH);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String row = scanner.nextLine();
                String[] elements = row.split(",");

                String id = elements[0];
                String title = elements[1];
                Status status = Status.valueOf(elements[2]);
                int issueNumber = Integer.parseInt(elements[3]);
                String publisher = elements[4];

                Magazine magazine  = new Magazine(id, title, status, issueNumber, publisher);
                library.getItems().add(magazine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        initBooks();
        initDVDs();
        initMagazines();
    }

    /**
     * Backup the library data by writing current users and items into two CSV files
     */
    public void backupLibraryData() {
        File usersFile = new File(Constants.USERS_CSV_PATH);
        File itemsFile = new File(Constants.ITEMS_CSV_PATH);

        try (FileWriter fileWriter = new FileWriter(usersFile)) {
            for (User user : library.getUsers()) {
                if (user instanceof Student student) {
                    fileWriter.write(String.format("%s,%s\n", student.getId(), student.getName()));
                } else if (user instanceof Teacher teacher) {
                    fileWriter.write(String.format("%s,%s\n", teacher.getId(), teacher.getName()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fileWriter = new FileWriter(itemsFile)) {
            for (Item item : library.getItems()) {
                if (item instanceof Book book) {
                    fileWriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                            book.getId(),
                            book.getTitle(),
                            book.getStatus(),
                            book.getIsbn(),
                            book.getAuthor(),
                            book.getGenre())
                    );
                }

                if (item instanceof DVD dvd) {
                    fileWriter.write(String.format("%s,%s,%s,%s,%s\n",
                            dvd.getId(),
                            dvd.getTitle(),
                            dvd.getStatus(),
                            dvd.getDirector(),
                            dvd.getDuration())
                    );
                }

                if (item instanceof Magazine magazine) {
                    fileWriter.write(String.format("%s,%s,%s,%s,%s\n",
                            magazine.getId(),
                            magazine.getTitle(),
                            magazine.getStatus(),
                            magazine.getIssueNumber(),
                            magazine.getPublisher())
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
