package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.util.Constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Item> searchTitleRecursive(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchAuthorStream(String keyword) {
        return library.getItems().stream()
                .filter(item -> item instanceof Book)
                .map(item -> (Book) item)
                .filter(book -> book.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                .distinct()
                .sorted((b1, b2) -> {

                    int titleComparison = b1.getTitle().compareTo(b2.getTitle());

                    if (titleComparison != 0) {
                        return titleComparison;
                    }

                    return b1.getId().compareTo(b2.getId());
                })
                .map(book -> (Item) book)
                .toList();
    }
}
