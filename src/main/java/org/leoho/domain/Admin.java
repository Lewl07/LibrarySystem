package org.leoho.domain;

import org.leoho.interfaces.Reportable;

import java.util.List;

public class Admin extends User implements Reportable {

    public Admin(String name) {
        super(name);
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
    public List<Item> searchTitleRecursive(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchAuthorStream(String keyword) {
        return List.of();
    }

    @Override
    public void generateReport() {

    }
}
