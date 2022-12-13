package com.librarymanagement;

public class Book {
    // Fields.
    private BookType type;
    private String title;
    private String author;
    private String description;
    private int pages;

    // Properties.
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public String getAdvertisementTitle() {
        return String.format("%s by %s", this.title, this.author);
    }

    // Constructors.
    public Book() {
        new Book("no_title", "no_author", BookType.Unknown, "no_description", 0);
    }
    public Book(String title, String author, BookType type, String description, int pages) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.description = description;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return String.format("Title: %s (%s pages), Author: %s, Type: %s, Description: %s\n",
                this.title, this.pages, this.author, this.type.toString(), this.description);
    }
}
