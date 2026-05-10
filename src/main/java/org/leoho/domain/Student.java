package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.util.Constants;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class Student extends User {

    public Student(String name, Library library) {
        super(name, library);
    }

    @Override
    public boolean borrowItem(Item item) {
        if (!(item instanceof Book) || (borrowedItems.size() >= Constants.MAX_BOOKS_STUDENT)) {
            throw new IllegalArgumentException("Students can only borrow books.");
        } else if (item.getStatus() != Status.IN_STORE) {
            throw new IllegalArgumentException("The book is not available.");
        }

        item.setStatus(Status.BORROWED);

        return borrowedItems.add(item);
    }

    @Override
    public boolean returnItem(Item item) {
        if (!borrowedItems.contains(item)) {
            throw new IllegalArgumentException("You do not have this book in possession.");
        }

        item.setStatus(Status.IN_STORE);

        return borrowedItems.remove(item);
    }

    @Override
    public List<Item> searchRecursive(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchStream(String keyword) {
        return library.getItems().stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book ->
                        book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .distinct()
                .sorted()
                .map(book -> (Item) book)
                .toList();
    }

    /**
     * Books are compared by title names, if same first letter, sort by id.
     */
    private static class TitleComparator implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            int titleComparison =
                    b1.getTitle().compareTo(b2.getTitle());

            return (titleComparison != 0)
                    ? titleComparison
                    : b1.getId().compareTo(b2.getId());
        }
    }
    /**
     * Books are compared by author names, if same author, sort by id.
     */
    private static class AuthorComparator implements Comparator<Book> {

        @Override
        public int compare(Book b1, Book b2) {
            int authorComparison =
                    b1.getAuthor().compareTo(b2.getAuthor());

            return (authorComparison != 0)
                    ? authorComparison
                    : b1.getId().compareTo(b2.getId());
        }
    }
}
