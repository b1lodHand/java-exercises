package com.librarymanagement;

import java.util.Arrays;
import java.util.Scanner;

public class MainLibrary {
    public static void main(String[] args) {
        appCycle();
    }

    // General logic of the program.
    private static void appCycle() {
        Scanner input = new Scanner(System.in);

        // Print all available actions.
        System.out.println("""
                Welcome to our library, what do you want to do?
                Donate: 1
                Give Back: 2
                Rent: 3
                Show All Books As a List: 4
                """);

        // Get action input.
        var eventType = input.nextInt();

        // Calculate action based on the input from the user.
        switch (eventType) {
            case 1 -> donate(input);
            case 2 -> giveBack(input);
            case 3 -> rent(input);
            case 4 -> serializeAllBooks();
            default -> error();
        }
    }

    // Donation action
    private static void donate(Scanner input) {
        System.out.println("Please enter information of the book you're going to donate.");
        System.out.println();

        // Get Title Input.
        System.out.print("Title: ");
        input.nextLine(); // WTF ???
        var title = input.nextLine();
        System.out.println();

        // Get Author Input.
        System.out.print("Author: ");
        var author = input.nextLine();
        System.out.println();

        // Get BookType Input.
        int i = 0;
        for (BookType val : BookType.values()) {
            System.out.printf("%d : %s%n", i++, val.toString());
        }
        System.out.print("Select a book type from above that fits with your book and enter the number of it: ");
        var type = BookType.values()[input.nextInt()];

        // Get Description Input.
        System.out.print("Description: ");
        input.nextLine(); // WTF ???
        var description = input.nextLine();
        System.out.println();

        // Get Page Count Input.
        System.out.print("Pages: ");
        var pages = input.nextInt();
        System.out.println();

        // Try donating the book to the library.
        if(!LibraryManagementSystem.donate(new Book(title, author, type, description, pages))) {
            error();
            return;
        }

        System.out.println();
        appCycle();
    }

    // Renting action
    private static void rent(Scanner input) {
        //Get User Name Input.
        System.out.print("Please enter your name: ");
        var nameOfUser = input.next();

        //Get all books available at the library.
        var bookNames = LibraryManagementSystem.getAvailableBooks().stream().map(Book::getAdvertisementTitle).toArray();
        System.out.println("Available Books");

        // Check if there's no books available.
        if(bookNames.length == 0){
            System.out.println("No books available for you to rent.");
            appCycle();
            return;
        }

        // Display all books available.
        System.out.println(Arrays.deepToString(bookNames));
        System.out.print("Please enter the title of the book you want to rent: ");
        var bookTitle = input.next();

        // Try renting the book.
        if (!LibraryManagementSystem.rent(input, nameOfUser, bookTitle)) {
            error();
            return;
        }

        appCycle();
    }

    // Giving back action
    private static void giveBack(Scanner input) {
        // Get User Name Input.
        System.out.print("Please enter your name: ");
        var nameOfUser = input.next();

        // Try to find all books rented from this user.
        var rentedBooks = LibraryManagementSystem.getRentedBy(nameOfUser);

        // Check if there's no books found.
        if(rentedBooks.size() == 0) {
            System.out.println("You have never rented a book from this library before.");
            appCycle();
            return;
        }

        // Display the books found.
        System.out.println(Arrays.deepToString(rentedBooks.toArray()));

        // Get Title Input.
        System.out.print("These are the books you've rented from this library before, write the title of the book you want to give us back: ");
        var bookTitle = input.next();

        // Try to give the selected book back to the library.
        if (!LibraryManagementSystem.giveBack(bookTitle)) {
            error();
            return;
        }

        appCycle();
    }

    // Serializes and prints all books rented or available.
    private static void serializeAllBooks() {
        // Find and display all available books in the library.
        System.out.println("Available Books");
        System.out.println(Arrays.deepToString(LibraryManagementSystem.getAvailableBooks().toArray()));

        System.out.println();

        // Find and display all rented books in the library.
        System.out.println("Rented Books");
        System.out.println(Arrays.deepToString(LibraryManagementSystem.getRentedBooks().toArray()));

        appCycle();
    }

    private static void error() {
        System.out.println("Oops! An error occurred, restarting program...\n");
        appCycle();
    }
}