package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public abstract class User {
    private static int nextId = 1;

    protected String id;
    protected String name;
    protected List<Item> borrowedItems;
    protected Library library;

    public User(String name, Library library) {
        this.id = String.format("%04d", nextId++);
        this.name = name;
        this.borrowedItems = new ArrayList<>();
        this.library = library;
    }

    /**
     * A user can borrow items, with a borrowing limit of 5 books (BOOKS ONLY) for students, and
     * a limit of 10 items for teachers.
     * @param item the item
     * @return whether the borrowing was a success or not
     */
    public abstract boolean borrowItem(Item item);

    /**
     * A user can return items that was previously borrowed
     * @param item the item
     * @return whether the item was successfully returned
     */
    public abstract boolean returnItem(Item item);

    /**
     * Search items in a library recursively, the user can either search by title or author of items.
     * The search result is alphabetically yielded, if same letter, sort by ID.
     * The sorting is ascending by default.
     * @param keyword the keyword
     * @return the list of items in accordance to the keyword
     */
    public abstract List<Item> searchTitleRecursive(String keyword);

    /**
     * Search items in a library recursively, the user can either search by title or author of items.
     * The search result is alphabetically yielded, if same letter, sort by ID.
     * The sorting is ascending by default.
     * @param keyword the keyword
     * @return the list of items in accordance to the keyword
     */
    public abstract List<Item> searchStream(String keyword);
}
