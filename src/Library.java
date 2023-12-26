import java.util.ArrayList;
import java.util.Scanner;

import Package1.*;

public class Library {

    public static int fine = 50;

    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<Member> members = new ArrayList<Member>();
    public static ArrayList<Loan> loans = new ArrayList<Loan>();

    public static void addBook() {
        Book newBook = Package1.Book.createBook();
        books.add(newBook);
    }

    public static void displayBooks() {
        if (books.size() == 0)
            System.out.println("There are no Books currently in the library");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("\nThe Details of Book " + (i + 1) + " are");
            System.out.println("Title: " + books.get(i).title);
            System.out.println("Author: " + books.get(i).author);
            System.out.println("Publisher " + books.get(i).publisher);
            System.out.println("Genre " + books.get(i).genre);
            System.out.println("No of Pages " + books.get(i).noOfPages);
        }
    }

    public static void addMember() {
        Member newMember = Package1.Member.createMember();
        members.add(newMember);
    }

    public static void displayMembers() {
        if (members.size() == 0)
            System.out.println("There are no Members currently in the library");
        for (int i = 0; i < members.size(); i++) {
            System.out.println("\nThe Details of Member " + (i + 1) + " are");
            System.out.println("Name: " + members.get(i).name);
            System.out.println("Address: " + members.get(i).address);
            System.out.println("Phone Number: " + members.get(i).phoneNumber);
            System.out.println("Email " + members.get(i).email);
        }
    }

    public static void displayBooksChecked() {
        System.out.println("\nShowing the Names of the books that are checked out");
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).checkedOut != null) {
                count++;
                System.out.println(books.get(i).title);
            }
        }
        if (count == 0) {
            System.out.println("There are currently no books that is checked out");
        }
    }

    public static Member findMember(String name) {
        Member member = null;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).name.equals(name)) {
                member = members.get(i);
            }
        }
        return member;
    }

    public static Book findBook(String title) {
        Book book = null;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).title.equals(title)) {
                book = books.get(i);
            }
        }
        return book;
    }

    public static void checkOutBook(String name, String title, String date) {
        Book book = findBook(title);
        Member member = findMember(name);

        if (member == null) {
            System.out.println("\nThere is no Member with this name");
            return;
        }

        if (book == null) {
            System.out.println("\nThere is no Book with this title");
            return;
        }
        if (book.checkedOut != null) {
            System.out.println("\nThe Book is already Checked out");
            return;
        }
        Loan newLoan = new Loan(book, member, date);
        loans.add(newLoan);
        member.memberLoans.add(newLoan);

        book.checkedOut = member;
    }

    public static void checkIn(String title, String name) {
        Book book = findBook(title);
        Member member = findMember(name);
        if (book == null) {
            System.out.println("\nThere is no book in the library with this title");
            return;
        }
        if (member == null) {
            System.out.println("\n Member not found");
            return;
        }
        for (int i = 0; i < loans.size(); i++) {
            if (loans.get(i).book.title.equals(title) && loans.get(i).member.name.equals(name)) {
                loans.remove(i);
                member.memberLoans.remove(loans.get(i));
            }
        }
        book.checkedOut = null;
    }

    public static void showLoans() {
        if (loans.size() == 0) {
            System.out.println("There are currently no loans");
            return;
        }
        System.out.println("\nShowing the loans");
        for (int i = 0; i < loans.size(); i++) {
            System.out.println(
                    loans.get(i).book.title + " " + loans.get(i).member.name + " " + loans.get(i).dueDate);
        }
    }

    public static void overdueFines(String name, String currDate) {
        Member member = findMember(name);
        int count = 0;
        int currMonth = Integer.parseInt(currDate.substring(3, 5));
        int currDay = Integer.parseInt(currDate.substring(0, 2));
        System.out.println("\nShowing overdueFines for " + name + "\n");
        for (int i = 0; i < member.memberLoans.size(); i++) {
            String dueDate = loans.get(i).dueDate;

            int dueMonth = Integer.parseInt(dueDate.substring(3, 5));
            int dueDay = Integer.parseInt(dueDate.substring(0, 2));
            if (currMonth > dueMonth) {
                int days = 30 - dueDay;
                days += (currMonth - dueMonth - 1) * 10;
                days += currDay;
                System.out.println("Overdue fine for Book " + loans.get(i).book.title + " is " + days * 10);
                count++;
            } else {
                if (currMonth == (dueMonth)) {
                    if (currDay > (dueDay)) {
                        System.out.println(
                                "Overdue fine for Book " + loans.get(i).book.title + " is " + (currDay - dueDay) * 10);
                        count++;
                    }
                }

            }
        }
        if (count == 0)
            System.out.println("\nThere is no overdue fines for the member");
    }

    public static void overdueBooks(String currDate) {
        int count = 0;
        String currMonth = currDate.substring(3, 5);
        String currDay = currDate.substring(0, 2);
        System.out.println("Overdue Books:");
        for (int i = 0; i < loans.size(); i++) {
            String dueDate = loans.get(i).dueDate;

            String dueMonth = dueDate.substring(3, 5);
            String dueDay = dueDate.substring(0, 2);
            if (currMonth.compareTo(dueMonth) > 0) {

                System.out.println(loans.get(i).book.title + " " + loans.get(i).member.name);
                count++;
            } else {
                if (currMonth.compareTo(dueMonth) == 0) {
                    if (currDay.compareTo(dueDay) > 0) {
                        System.out.println(loans.get(i).book.title + " " + loans.get(i).member.name);
                        count++;
                    }
                }

            }
        }
        if (count == 0)
            System.out.println("\n There is no overdue book currently");
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Book book1 = new Book("ab1", "a", "b", "c", 12);
        Book book2 = new Book("ab2", "a", "b", "c", 12);
        books.add(book1);
        books.add(book2);
        // displayBooks();

        Member member1 = new Member("raj", "a", "a", "a");
        Member member2 = new Member("rahul", "a", "a", "a");
        members.add(member1);
        members.add(member2);

        // displayMembers();
        checkOutBook("raj", "ab1", "14-06-2022");
        checkOutBook("raj", "ab2", "18-06-2022");
        checkOutBook("rahul", "ab2", "18-06-2022");
        // checkIn("ab1");

        displayBooksChecked();
        showLoans();
        overdueFines("raj", "25-06-2022");
        // overdueBooks("25-06-2022");
        checkIn("ab1", "raj");
        displayBooksChecked();
        showLoans();
        overdueFines("raj", "25-06-2022");

        boolean end = false;

        while (true) {
            System.out.println("\nWhat do you want to do");
            System.out.println("Enter 0 to exit");
            System.out.println("Enter 1 to add a Book");
            System.out.println("Enter 2 to add a Member");
            System.out.println("Enter 3 to checkout a Book ");
            System.out.println("Enter 4 to check in a Book");
            System.out.println("Enter 5 to calculate the overdue fines for a member");
            System.out.println("Enter 6 to display all Books");
            System.out.println("Enter 7 to display all Members");
            System.out.println("Enter 8 to display all Loans");
            System.out.println("Enter 9 to display all checked Books");
            System.out.println("Enter 10 to display all overdue Books");
            int n = sc.nextInt();
            sc.nextLine();
            switch (n) {
                case 0: {
                    end = true;
                    break;
                }
                case 1: {
                    addBook();
                    break;
                }
                case 2: {
                    addMember();
                    break;
                }
                case 3: {
                    System.out.println("Enter title of the Book to be checked out");
                    String title = sc.nextLine();
                    System.out.println("Enter name of the Membet ");
                    String name = sc.nextLine();
                    System.out.println("Enter the due date");
                    String due = sc.nextLine();
                    checkOutBook(name, title, due);
                    break;
                }
                case 4: {
                    System.out.println("Enter title of the Book to be checked in");
                    String title = sc.nextLine();
                    System.out.println("Enter name of the Member ");
                    String name = sc.nextLine();
                    checkIn(title, name);
                    break;
                }
                case 5: {
                    System.out.println("Enter the name of the member");
                    String name = sc.nextLine();
                    System.out.println("Enter the current date");
                    String currDate = sc.nextLine();
                    overdueFines(name, currDate);
                    break;
                }
                case 6: {
                    displayBooks();
                    break;
                }
                case 7: {
                    displayMembers();
                    break;
                }
                case 8: {
                    showLoans();
                    break;
                }
                case 9: {
                    displayBooksChecked();
                    break;
                }

                case 10: {
                    System.out.println("Enter the current Date");
                    String currentDate = sc.nextLine();
                    overdueBooks(currentDate);
                    break;
                }

                default:
                    break;
            }
            if (end) {
                break;
            }
        }
    }
}
