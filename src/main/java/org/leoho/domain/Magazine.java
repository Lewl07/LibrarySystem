package org.leoho.domain;

public class Magazine extends Item {
    private int issueNumber;
    private String publisher;

    public Magazine(String title, Status status, int issueNumber, String publisher) {
        super(title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }
}
