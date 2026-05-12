package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.leoho.util.Constants;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Student extends User {

    public Student(String name, Library library) {
        super(name, library);
    }

    /**
     * A user can borrow items, with a borrowing limit of 5 books (BOOKS ONLY) for students, and
     * a limit of 10 items for teachers.
     * @param item the item
     * @return whether the borrowing was a success or not
     */
    @Override
    public boolean borrowItem(Item item) {
        if (!(item instanceof Book)) {
            throw new IllegalArgumentException("Students can only borrow books.");
        }

        if (borrowedItems.size() >= Constants.MAX_BOOKS_STUDENT) {
            throw new IllegalArgumentException("Students can only borrow up to 5 books.");
        }

        if (item.getStatus() != Status.IN_STORE) {
            throw new IllegalArgumentException("The book is not available.");
        }

        item.setStatus(Status.BORROWED);

        return borrowedItems.add(item);
    }

    /**
     * A user can return items that was previously borrowed
     * @param item the item
     * @return whether the item was successfully returned
     */
    @Override
    public boolean returnItem(Item item) {
        if (!borrowedItems.contains(item)) {
            throw new IllegalArgumentException("You do not have this book in possession.");
        }

        item.setStatus(Status.IN_STORE);

        return borrowedItems.remove(item);
    }

    /**
     * Search items in a library recursively, the user can either search by title or author of items.
     * @param keyword the keyword
     * @return the Set of items in accordance to the keyword
     */
    public Set<Item> searchItemRecursive(String keyword) {
        return helperSearchItemRecursive(keyword.toLowerCase(), 0, new HashSet<>());
    }

    /**
     * Helper method for searchItemRecursive()
     * @param keyword the keyword
     * @param idx the idx
     * @return the method itself, recursion
     */
    private Set<Item> helperSearchItemRecursive(String keyword, int idx, Set<Item> results) {
        if (idx >= library.getItems().size()) {
            return results;
        }

        Item item = library.getItems().get(idx);

        boolean match = item.getTitle().toLowerCase().contains(keyword) ||
                        (item instanceof Book && ((Book) item).getAuthor().toLowerCase().contains(keyword));

        if (match) {
            results.add(item);
        }

        return helperSearchItemRecursive(keyword, idx + 1, results);
    }

    /**
     * Search items in a library by stream, the user can either search by title or author of items.
     * Sorted ascendingly by default.
     * @param keyword the keyword
     * @return the list of items in accordance to the keyword
     */
    @Override
    public List<Item> searchItemStream(String keyword) {
        Set<String> uniqueTitle = new LinkedHashSet<>();

        return new ArrayList<>(
                library.getItems().stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book ->
                        book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .filter(item -> uniqueTitle.add(item.getTitle()))
                .map(book -> (Item) book)
                .toList()
        );
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                '}';
    }
}
