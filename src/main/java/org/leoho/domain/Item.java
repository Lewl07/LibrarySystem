package org.leoho.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;
import java.util.Objects;

@Getter
@ToString
public abstract class Item {
    private static int nextId = 1;

    protected String id;
    protected String title;
    @Setter protected Status status;

    public Item(String title, Status status) {
        this.id = String.format("%04d", nextId++);
        this.title = title;
        this.status = status;
    }

    public Item(String id, String title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;

        int numericId = Integer.parseInt(id);
        if (numericId >= nextId) {
            nextId = numericId + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(title, item.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    /**
     * Items are compared by title names, if same letter, sort by id.
     */
    public static class TitleComparator implements Comparator<Item> {

        @Override
        public int compare(Item b1, Item b2) {
            int titleComparison =
                    b1.getTitle().compareTo(b2.getTitle());

            return (titleComparison != 0)
                    ? titleComparison
                    : b1.getId().compareTo(b2.getId());
        }
    }
}
