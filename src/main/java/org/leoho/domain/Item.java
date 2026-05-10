package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;

@EqualsAndHashCode
@Getter
@ToString
public abstract class Item {
    private static int nextId = 1;

    protected String id;
    protected String title;
    @Setter protected Status status;

    public Item(String title, Status status) {
        this.title = title;
        this.id = String.format("%04d", nextId++);
        this.status = status;
    }

    /**
     * Items are compared by title names, if same first letter, sort by id.
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
