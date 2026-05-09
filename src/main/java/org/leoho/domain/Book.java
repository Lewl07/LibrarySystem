package org.leoho.domain;

import org.leoho.util.Validation;

public class Book extends Item {
    private String isbn;
    private String author;
    private String genre;

    public Book(String title, Status status, String isbn, String author, String genre) {
        super(title, status);

        if (Validation.isValidISBN(isbn)) {
            this.isbn = isbn;
        }

        this.author = author;
        this.genre = genre;
    }
}
