package org.leoho.domain;

import java.util.List;

public class Student extends User {

    public Student(String name) {
        super(name);
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
    public List<Item> searchTitleRecursive(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchAuthorStream(String keyword) {
        return List.of();
    }
}
