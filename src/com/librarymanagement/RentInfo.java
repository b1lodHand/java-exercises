package com.librarymanagement;

public class RentInfo {
    // Fields
    private String ownerName;
    private Book book;

    // Properties.
    public String getOwnerName() {
        return ownerName;
    }
    public Book getBookInfo() {
        return book;
    }

    // Constructors.
    public RentInfo(String ownerName, Book book) {
            this.ownerName = ownerName;
            this.book = book;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Rented By: %s\n", this.getBookInfo().getAdvertisementTitle(), this.getOwnerName());
    }
}
