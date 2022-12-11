package com.librarymanagement;

import java.util.ArrayList;

public class LibraryManagementSystem {
    private static ArrayList<Book> rentedBooks = new ArrayList<>();
    private static ArrayList<Book> availableBooks = new ArrayList<>();

    public static ArrayList<Book> getRentedBooks() {
        return rentedBooks;
    }

    public static ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    public static boolean rent(String bookTitle) {
        var found = availableBooks.stream().filter(b -> b.getTitle().trim() == bookTitle.trim()).findFirst();
        if(!found.isPresent()) {
            System.out.println("Oops! We don't have that book or somebody has rented it before you, sorry...");
            return false;
        }

        availableBooks.remove(found.get());
        rentedBooks.add(found.get());

        System.out.println(String.format("You've rented the book: %s", found.get().getTitle()));
        return true;
    }

    //!!! RECODE
    public static boolean donate(Book book) {
        if(book == null) return false; //!!! no output in the console

        availableBooks.add(book);

        System.out.println(String.format("Thanks for donating the book: %s", book.getTitle()));

        return true;
    }

    public static boolean giveBack(String bookTitle) {
        var book  = rentedBooks.stream().filter((b) -> b.getTitle().trim() == bookTitle.trim()).findFirst();
        if(!book.isPresent()) return false; //!!! no output in the console

        rentedBooks.remove(book.get());
        availableBooks.add(book.get());
        System.out.println(String.format("Thanks for giving back the book: %s", book.get().getTitle()));

        return true;
    }
}
