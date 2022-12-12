package com.librarymanagement;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LibraryManagementSystem {
    private static ArrayList<RentInfo> rentedBooks = new ArrayList<>();
    private static ArrayList<Book> availableBooks = new ArrayList<>();

    public static ArrayList<RentInfo> getRentedBooks() {
        return rentedBooks;
    }

    public static ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    public static boolean rent(String nameOfUser, String bookTitle) {
        if(bookTitle.trim().isBlank() || nameOfUser.trim().isBlank()) {
            System.out.println("Invalid input.");
            return false;
        }

        var found = availableBooks.stream().filter(b -> b.getTitle().trim().equals(bookTitle.trim())).findFirst();
        if(!(found.isPresent())) {
            System.out.println("Oops! We don't have that book or somebody has rented it before you, sorry...");
            return false;
        }

        availableBooks.remove(found.get());
        rentedBooks.add(new RentInfo(nameOfUser, found.get()));

        System.out.printf("You've rented the book: %s%n\n", found.get().getTitle());
        return true;
    }

    //!!! RECODE
    public static boolean donate(Book book) {
        if(book == null) return false; //!!! no output in the console

        availableBooks.add(book);

        System.out.printf("Thanks for donating the book: %s%n\n", book.getTitle());

        return true;
    }

    public static ArrayList<Book> getRentedBy(String nameOfUser) {
        return (ArrayList<Book>) rentedBooks.stream().filter((ri) -> ri.getOwnerName().trim().equals(nameOfUser.trim())).map(RentInfo::getBookInfo).collect(Collectors.toList());
    }

    public static boolean giveBack(String bookTitle) {
        var book  = rentedBooks.stream().filter((b) -> b.getBookInfo().getTitle().trim().equals(bookTitle.trim())).findFirst();
        if(!book.isPresent()) return false; //!!! no output in the console

        rentedBooks.remove(book.get());
        availableBooks.add(book.get().getBookInfo());
        System.out.printf("Thanks for giving back the book: %s\n%n", book.get().getBookInfo().getTitle());

        return true;
    }
}
