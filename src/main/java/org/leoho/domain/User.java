package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;

@EqualsAndHashCode
public abstract class User {
    private static int nextId = 1;

    @Getter protected String id;
    @Getter protected String name;
    @Getter protected List<Item> borrowedItems;
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
     * @return the Set of items in accordance to the keyword
     */
    public Set<Item> searchItemRecursive(String keyword) {
        return helperSearchItemRecursive(keyword.toLowerCase(), 0, new LinkedHashSet<>());
    }

    /**
     * The helper method for searchItemRecursive
     * @param keyword the keyword
     * @param idx the index
     * @param results the results
     * @return the method itself
     */
    private Set<Item> helperSearchItemRecursive(String keyword, int idx, Set<Item> results) {
        if (idx >= library.getItems().size()) {
            return results;
        }

        Item item = library.getItems().get(idx);

        boolean match = item.getTitle().toLowerCase().contains(keyword)

                        || (item instanceof Book
                                &&
                                ((Book) item).getAuthor()
                                        .toLowerCase()
                                        .contains(keyword))

                        || (item instanceof Magazine
                                &&
                                ((Magazine) item).getPublisher()
                                        .toLowerCase()
                                        .contains(keyword))

                        || (item instanceof DVD
                                &&
                                ((DVD) item).getDirector()
                                        .toLowerCase()
                                        .contains(keyword)
        );

        if (match) {
            results.add(item);
        }

        return helperSearchItemRecursive(keyword, idx + 1, results);
    }

    /**
     * Search items in a library by stream, the user can either search by title or author of items.
     * The search result is alphabetically yielded, if same letter, sort by ID.
     * The sorting is ascending by default.
     * @param keyword the keyword
     * @return the list of items in accordance to the keyword
     */
    public List<Item> searchItemStream(String keyword) {
        Set<String> uniqueTitle = new LinkedHashSet<>();
        String lowerKeyword = keyword.toLowerCase();

        return new ArrayList<>(
                library.getItems().stream()
                        .filter(item -> item.getTitle().toLowerCase().contains(lowerKeyword)

                                || (item instanceof Book
                                && ((Book) item).getAuthor().toLowerCase().contains(lowerKeyword))

                                || (item instanceof Magazine
                                && ((Magazine) item).getPublisher().toLowerCase().contains(lowerKeyword))

                                || (item instanceof DVD
                                && ((DVD) item).getDirector().toLowerCase().contains(lowerKeyword)))

                        .filter(item -> uniqueTitle.add(item.getTitle().toLowerCase()))
                        .toList()
        );
    }

    @Override
    public String toString() {
        return "Id='" + id + '\'' +
                ", Name='" + name + '\'';
    }

    /**
     * Users are compared by names, if same letter, sort by id.
     */
    public static class NameComparator implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            int titleComparison =
                    o1.getName().compareTo(o2.getName());

            return (titleComparison != 0)
                    ? titleComparison
                    : o1.getId().compareTo(o2.getId());
        }
    }
}
