package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.util.Validation;

import java.util.Comparator;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString(callSuper = true)
public class Book extends Item {
    private String isbn;
    private String author;
    private String genre;

    public Book(String title, Status status, String isbn, String author, String genre) {
        super(title, status);

        if (!Validation.isValidISBN(isbn)) {
            throw new IllegalArgumentException("Invalid ISBN");
        }

        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    /**
     * Books are compared by title names, if same first letter, sort by id.
     */
    public static class TitleComparator implements Comparator<Book> {

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
    public static class AuthorComparator implements Comparator<Book> {

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
