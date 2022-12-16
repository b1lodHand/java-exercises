package com.librarymanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryManagementSystem {

    // Books which are rented by someone and not available at the moment.
    private static final List<RentInfo> rentedBooks = new ArrayList<>();

    // Books which are available.
    private static final List<Book> availableBooks = new ArrayList<>();

    // Getter For Rented Books
    public static List<RentInfo> getRentedBooks() {
        return rentedBooks;
    }

    // Getter For Available Books
    public static List<Book> getAvailableBooks() {
        return availableBooks;
    }

    // Finds all the books rented by target user.
    public static List<Book> getRentedBy(String nameOfUser) {
        return rentedBooks.stream().filter((ri) -> ri.getOwnerName().trim().equals(nameOfUser.trim())).map(RentInfo::getBookInfo).collect(Collectors.toList());
    }

    // Renting Process
    public static boolean rent(Scanner input, String nameOfUser, String bookTitle) {

        // Check if the input is invalid.
        if(bookTitle.trim().isBlank() || nameOfUser.trim().isBlank()) {
            System.out.println("Invalid input.");
            return false;
        }

        // Find all books with the target title.
        var all = availableBooks.stream().filter(b -> b.getTitle().trim().equals(bookTitle.trim())).toList();
        var found = all.stream().findFirst();

        // Check if there's multiple books with the same title.
        if(all.size() > 1) {
            // Get Author Name Input
            System.out.print("There are several books with this name, please enter author name of your book: ");
            var author = input.nextLine().trim(); // TRIM!!!

            // Filter the books by the author name and pick the correct one using the author input.
            found = all.stream().filter((b) -> b.getAuthor().trim().equals(author)).findFirst();
        }

        // Check if there's no book available after the whole process.
        if(!(found.isPresent())) {
            System.out.println("Oops! We don't have that book or somebody has rented it before you, sorry...");
            return false;
        }

        // Transfer the target book from available books to rented books.
        availableBooks.remove(found.get());
        rentedBooks.add(new RentInfo(nameOfUser, found.get()));

        System.out.printf("You've rented the book: %s%n\n", found.get().toString());
        return true;
    }

    // Donation Process
    public static boolean donate(Book book) {
        // Check if the target book is null.
        if(book == null) return false; //!!! no output in the console

        // Add target book to the available books list.
        availableBooks.add(book);

        System.out.printf("Thanks for donating the book: %s%n\n", book.toString());

        return true;
    }

    // Giving Back Process
    public static boolean giveBack(String bookTitle) {
        // Find all rented books with the target title.
        var book  = rentedBooks.stream().filter((b) -> b.getBookInfo().getTitle().trim().equals(bookTitle.trim())).findFirst();

        // Check if there's no book rented with that title.
        if(!book.isPresent()) return false; //!!! no output in the console

        // Transfer target book to the available books list.
        rentedBooks.remove(book.get());
        availableBooks.add(book.get().getBookInfo());

        System.out.printf("Thanks for giving back the book: %s\n%n", book.get().getBookInfo().toString());
        return true;
    }
}
