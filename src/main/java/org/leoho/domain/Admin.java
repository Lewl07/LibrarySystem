package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.interfaces.Reportable;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class Admin extends User implements Reportable {

    public Admin(String name, Library library) {
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
    public List<Item> searchRecursive(String keyword) {
        return List.of();
    }

    @Override
    public List<Item> searchStream(String keyword) {
        return List.of();
    }

    @Override
    public void generateReport() {

    }
}
