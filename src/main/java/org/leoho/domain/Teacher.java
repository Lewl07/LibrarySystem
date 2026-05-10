package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
public class Teacher extends User {

    public Teacher(String name, Library library) {
        super(name, library);
    }

    @Override
    public boolean borrowItem(Item item) {
        return false;
    }

    @Override
    public boolean returnItem(Item item) {
        return false;
    }

    @Override
    public Set<Item> searchItemRecursive(String keyword) {
        return new HashSet<>();
    }

    @Override
    public List<Item> searchItemStream(String keyword) {
        return List.of();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                super.toString() +
                '}';
    }
}
