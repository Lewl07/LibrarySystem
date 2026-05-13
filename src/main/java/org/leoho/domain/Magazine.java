package org.leoho.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class Magazine extends Item {
    private int issueNumber;
    private String publisher;

    public Magazine(String title, Status status, int issueNumber, String publisher) {
        super(title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public Magazine(String id, String title, Status status, int issueNumber, String publisher) {
        super(id, title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }
}
