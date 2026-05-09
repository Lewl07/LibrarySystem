package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}
