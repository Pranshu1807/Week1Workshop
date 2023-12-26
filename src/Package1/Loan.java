package Package1;

public class Loan {
    public Book book;
    public Member member;
    public String dueDate;

    public Loan(Book book, Member member, String dueDate) {
        this.book = book;
        this.member = member;
        this.dueDate = dueDate;
    }
}