package com.librarymanagement;

import com.librarymanagement.Book;
import com.librarymanagement.BookType;
import com.librarymanagement.LibraryManagementSystem;
import com.librarymanagement.RentInfo;

import java.util.Arrays;
import java.util.Scanner;

public class MainLibrary {
    public static void main(String[] args) {
        appCycle();
    }

    private static void appCycle() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nWelcome to our library, what do you want to do?\n" +
                "Donate: 1\n" +
                "Give Back: 2\n" +
                "Rent: 3\n" +
                "Show All Books As a List: 4\n");

        var eventType = input.nextInt();

        switch (eventType) {
            case 1: //Donate
                donate();
                break;

            case 2: //Give back
                giveBack();
                break;

            case 3: //Rent
                rent();
                break;

            case 4:
                serializeAllBooks();
                break;

            default:
                error();
                return;
        }
    }

    // no declaration for TYPE!!!
    private static void donate() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter information of the book you're going to donate.");
        System.out.println();

        System.out.print("Title: ");
        var title = input.next();
        System.out.println();

        System.out.print("Author: ");
        var author = input.next();
        System.out.println();

        System.out.print("Description: ");
        var description = input.next();
        System.out.println();

        System.out.print("Pages: ");
        var pages = input.nextInt();
        System.out.println();

        if(!LibraryManagementSystem.donate(new Book(title, author, BookType.Unknown, description, pages))) {
            error();
            return;
        }

        System.out.println();
        appCycle();
    }

    private static void rent() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        var nameOfUser = input.next();

        var bookNames = LibraryManagementSystem.getAvailableBooks().stream().map(Book::getAdvertisementTitle).toArray();
        System.out.println("Available Books");

        if(bookNames.length == 0){
            System.out.println("No books available for you to rent.");
            appCycle();
            return;
        }

        System.out.println(Arrays.deepToString(bookNames));
        System.out.print("Please enter the title of the book you want to rent: ");
        var bookTitle = input.next();

        if (!LibraryManagementSystem.rent(nameOfUser, bookTitle)) {
            error();
            return;
        }

        //System.out.println(String.format("You've successfully rented the book: %s", bookTitle));
        appCycle();
    }

    private static void giveBack() {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        var nameOfUser = input.next();

        var rentedBooks = LibraryManagementSystem.getRentedBy(nameOfUser);

        if(rentedBooks.size() == 0) {
            System.out.println("You have never rented a book from this library before.");
            appCycle();
            return;
        }

        System.out.println(Arrays.deepToString(rentedBooks.toArray()));

        System.out.print("These are the books you've rented from this library before, write the title of the book you want to give us back: ");
        var bookTitle = input.next();

        if (!LibraryManagementSystem.giveBack(bookTitle)) {
            error();
            return;
        }

        appCycle();
    }

    private static void serializeAllBooks() {
        System.out.println("Available Books");
        System.out.println(Arrays.deepToString(LibraryManagementSystem.getAvailableBooks().toArray()));

        System.out.println();

        System.out.println("Rented Books");
        System.out.println(Arrays.deepToString(LibraryManagementSystem.getRentedBooks().toArray()));

        appCycle();
    }

    private static void error() {
        System.out.println("Oops! An error occurred, restarting program...\n");
        appCycle();
    }
}