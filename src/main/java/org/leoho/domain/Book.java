package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.util.Validation;

@EqualsAndHashCode(callSuper = true)
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

    public Book(String id, String title, Status status, String isbn, String author, String genre) {
        super(id, title, status);
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }
}
