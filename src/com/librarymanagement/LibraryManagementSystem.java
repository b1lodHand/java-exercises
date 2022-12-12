package com.librarymanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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

    public static ArrayList<Book> getRentedBy(String nameOfUser) {
        return (ArrayList<Book>) rentedBooks.stream().filter((ri) -> ri.getOwnerName().trim().equals(nameOfUser.trim())).map(RentInfo::getBookInfo).collect(Collectors.toList());
    }

    public static boolean rent(String nameOfUser, String bookTitle) {
        if(bookTitle.trim().isBlank() || nameOfUser.trim().isBlank()) {
            System.out.println("Invalid input.");
            return false;
        }

        var all = availableBooks.stream().filter(b -> b.getTitle().trim().equals(bookTitle.trim())).toList();
        var found = all.stream().findFirst();
        if(all.size() > 1) {
            System.out.print("There are several books with this name, please enter author name of your book: ");
            Scanner input = new Scanner(System.in);
            var author = input.nextLine().trim(); // TRIM!!!
            found = all.stream().filter((b) -> b.getAuthor().trim().equals(author)).findFirst();
        }

        if(!(found.isPresent())) {
            System.out.println("Oops! We don't have that book or somebody has rented it before you, sorry...");
            return false;
        }

        availableBooks.remove(found.get());
        rentedBooks.add(new RentInfo(nameOfUser, found.get()));

        System.out.printf("You've rented the book: %s%n\n", found.get().getAdvertisementTitle());
        return true;
    }

    public static boolean donate(Book book) {
        if(book == null) return false; //!!! no output in the console

        availableBooks.add(book);

        System.out.printf("Thanks for donating the book: %s%n\n", book.getAdvertisementTitle());

        return true;
    }

    public static boolean giveBack(String bookTitle) {
        var book  = rentedBooks.stream().filter((b) -> b.getBookInfo().getTitle().trim().equals(bookTitle.trim())).findFirst();
        if(!book.isPresent()) return false; //!!! no output in the console

        rentedBooks.remove(book.get());
        availableBooks.add(book.get().getBookInfo());
        System.out.printf("Thanks for giving back the book: %s\n%n", book.get().getBookInfo().getAdvertisementTitle());

        return true;
    }
}
