import com.librarymanagement.Book;
import com.librarymanagement.BookType;
import com.librarymanagement.LibraryManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        appCycle();
    }

    private static void appCycle() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to our library, what do you want to do?\n" +
                "Donate: 1\n" +
                "Give Back: 2\n" +
                "Rent: 3\n");

        var eventType = input.nextInt();

        switch (eventType) {1
            case 1: //Donate
                donate();
                break;

            case 2: //Give back
                giveBack();
                break;

            case 3: //Rent
                rent();
                break;

            default:
                error();
                return;
        }
    }

    // no declaration for TYPE!!!
    private static void donate() {
        Scanner input = new Scanner(System.in);

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
        var bookTitle = input.next();

        if (LibraryManagementSystem.rent(bookTitle)) {
            System.out.println(String.format("You've successfully rented the book: %s", bookTitle));
        }

        else {
            error();
            return;
        }
    }

    private static void giveBack() {
        Scanner input = new Scanner(System.in);
        var bookTitle = input.next();

        if (LibraryManagementSystem.giveBack(bookTitle)) {
            System.out.println(String.format("You've successfully rented the book: %s", bookTitle));
        }

        else {
            error();
            return;
        }
    }

    private static void error() {
        System.out.println("Oops! An error occurred, restarting program...\n");
        appCycle();
    }
}