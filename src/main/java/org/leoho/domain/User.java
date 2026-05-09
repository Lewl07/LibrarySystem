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

    public User(String name) {
        this.id = String.format("%04d", nextId++);
        this.name = name;
        this.borrowedItems = new ArrayList<>();
    }

    public abstract boolean borrowItem(Item item);

    public abstract boolean returnItem(Item item);

    public abstract List<Item> searchTitleRecursive(String keyword);

    public abstract List<Item> searchAuthorStream(String keyword);
}
