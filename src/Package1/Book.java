package Package1;

import java.util.Scanner;

public class Book {
    public String title, author, publisher, genre;
    public int noOfPages;
    // public boolean checkedOut = false;
    public Member checkedOut = null;

    public Book(String title, String author, String publisher, String genre, int noOfPages) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.noOfPages = noOfPages;
    }

    public static Book createBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the details of the Book");

        System.out.println("Enter the title of the book");
        String title = sc.nextLine();
        System.out.println("Enter the author of the book");
        String author = sc.nextLine();
        System.out.println("Enter the publisher of the book");
        String publisher = sc.nextLine();
        System.out.println("Enter the genre of the book");
        String genre = sc.nextLine();
        System.out.println("Enter the no of pages in the book");
        int noOfPages = sc.nextInt();
        sc.nextLine();

        Book newBook = new Book(title, author, publisher, genre, noOfPages);
        return newBook;

    }

}