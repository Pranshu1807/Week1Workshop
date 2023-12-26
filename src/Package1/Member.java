package Package1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Member {
    public String name, address, phoneNumber, email;
    public ArrayList<Loan> memberLoans = new ArrayList<Loan>();

    public Member(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static Member createMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the details of the Member");

        System.out.println("Enter the name of the Member");
        String name = sc.nextLine();
        System.out.println("Enter the address of the Member");
        String address = sc.nextLine();
        System.out.println("Enter the phoneNumber of the Member");
        String phoneNUmber = sc.nextLine();
        System.out.println("Enter the email of the Member");
        String email = sc.nextLine();

        Member newMember = new Member(name, address, phoneNUmber, email);
        return newMember;

    }
}