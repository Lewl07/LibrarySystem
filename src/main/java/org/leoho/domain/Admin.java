package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.leoho.interfaces.Reportable;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class Admin extends User implements Reportable {

    public Admin(String name, Library library) {
        super(name, library);
    }

    /**
     * Admins do not borrow
     * @param item the item
     * @return nothing (exception handling)
     */
    @Override
    public boolean borrowItem(Item item) {
        throw new IllegalArgumentException("Admins cannot borrow items.");
    }

    /**
     * Admins do not borrow
     * @param item the item
     * @return nothing (exception handling)
     */
    @Override
    public boolean returnItem(Item item) {
       throw new IllegalArgumentException("Admins cannot return items.");
    }

    /**
     * The admin generates a report of each item in the library
     * giving the following information: ID, name, and status.
     */
    @Override
    public void generateReport() {
        for (Item item : library.getItems()) {
            System.out.printf("ID: %s, Name: %s, Status: %s\n", item.getId(), item.getTitle(), item.getStatus());
        }
    }
}
