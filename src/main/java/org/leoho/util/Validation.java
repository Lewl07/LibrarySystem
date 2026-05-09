package org.leoho.util;

public class Validation {

    /**
     * Checks whether the ISBN is valid or not
     * @param isbn the isbn
     * @return whether the ISBN is valid or not
     */
    public static boolean isValidISBN(String isbn) {
        return isbn.matches("\\d{13}");
    }
}
