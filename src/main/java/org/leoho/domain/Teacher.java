package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.leoho.util.Constants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Teacher extends User {

    public Teacher(String name, Library library) {
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
        if (borrowedItems.size() >= Constants.MAX_ITEMS_TEACHER) {
            throw new IllegalArgumentException("Teachers can only borrow up to 10 items.");
        }

        if (item.getStatus() != Status.IN_STORE) {
            throw new IllegalArgumentException("The item is not available.");
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

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                '}';
    }
}
