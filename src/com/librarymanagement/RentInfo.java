package com.librarymanagement;

public class RentInfo {
    private String ownerName;

    private Book book;

    public String getOwnerName() {
        return ownerName;
    }

    public Book getBookInfo() {
        return book;
    }

    public RentInfo(String ownerName, Book book) {
            this.ownerName = ownerName;
            this.book = book;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Rented By: %s\n", this.getBookInfo().getTitle(), this.getOwnerName());
    }
}
