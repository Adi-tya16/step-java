package week3.assignmentproblems;

import java.util.*;

public class LibrarySystem {

    static class Book {
        private static int counter = 0;
        private static int totalBooks = 0;

        private String bookId;
        private String title;
        private String author;
        private String isbn;
        private String category;
        private boolean isIssued;
        private String issueDate;
        private String dueDate;
        private int issuedCount; // track popularity

        public Book(String title, String author, String isbn, String category) {
            this.bookId = generateId();
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.category = category;
            this.isIssued = false;
            this.issueDate = null;
            this.dueDate = null;
            this.issuedCount = 0;
            totalBooks++;
        }

        private static String generateId() {
            counter++;
            return String.format("B%03d", counter);
        }

        public String getBookId() { return bookId; }
        public String getTitle() { return title; }
        public boolean isIssued() { return isIssued; }
        public int getIssuedCount() { return issuedCount; }

        public void issue(String issueDate, String dueDate) {
            isIssued = true;
            this.issueDate = issueDate;
            this.dueDate = dueDate;
            issuedCount++;
        }

        public void returnBook() {
            isIssued = false;
            issueDate = null;
            dueDate = null;
        }

        public void renew(String newDueDate) {
            this.dueDate = newDueDate;
        }

        public void displayInfo() {
            System.out.println(bookId + " | " + title + " | " + author + " | " + category + " | Issued: " + isIssued);
        }
    }

    static class Member {
        private static int counter = 0;
        private static int totalMembers = 0;
        private static String libraryName = "City Central Library";
        private static double finePerDay = 2.0;
        private static int maxBooksAllowed = 3;

        private String memberId;
        private String memberName;
        private String memberType;
        private Book[] booksIssued;
        private int bookCount;
        private double totalFines;
        private String membershipDate;

        public Member(String name, String type, String membershipDate) {
            this.memberId = generateId();
            this.memberName = name;
            this.memberType = type;
            this.membershipDate = membershipDate;
            this.booksIssued = new Book[maxBooksAllowed];
            this.bookCount = 0;
            this.totalFines = 0;
            totalMembers++;
        }

        private static String generateId() {
            counter++;
            return String.format("M%03d", counter);
        }

        public String getMemberName() { return memberName; }
        public double getTotalFines() { return totalFines; }

        public void issueBook(Book book, String issueDate, String dueDate) {
            if (bookCount >= maxBooksAllowed) {
                System.out.println(memberName + " has reached max book limit.");
                return;
            }
            if (!book.isIssued()) {
                book.issue(issueDate, dueDate);
                booksIssued[bookCount++] = book;
                System.out.println(memberName + " issued " + book.getTitle());
            } else {
                System.out.println("Book already issued: " + book.getTitle());
            }
        }

        public void returnBook(String bookId, String returnDate) {
            for (int i = 0; i < bookCount; i++) {
                if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                    double fine = calculateFine(returnDate, booksIssued[i].dueDate);
                    totalFines += fine;
                    booksIssued[i].returnBook();
                    System.out.println(memberName + " returned " + booksIssued[i].getTitle() + " | Fine: " + fine);
                    booksIssued[i] = null;
                }
            }
        }

        private double calculateFine(String returnDate, String dueDate) {
            int returnDay = Integer.parseInt(returnDate.substring(0, 2));
            int dueDay = Integer.parseInt(dueDate.substring(0, 2));
            int diff = returnDay - dueDay;
            return diff > 0 ? diff * finePerDay : 0;
        }

        public void renewBook(String bookId, String newDueDate) {
            for (int i = 0; i < bookCount; i++) {
                if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                    booksIssued[i].renew(newDueDate);
                    System.out.println("Book " + bookId + " renewed till " + newDueDate);
                }
            }
        }

        public static void generateLibraryReport(Book[] books, Member[] members) {
            System.out.println("\n--- Library Report ---");
            System.out.println("Library: " + libraryName);
            System.out.println("Total Books: " + Book.totalBooks);
            System.out.println("Total Members: " + totalMembers);
            double totalFines = 0;
            for (Member m : members) {
                if (m != null) totalFines += m.totalFines;
            }
            System.out.println("Total Fines Collected: " + totalFines);
        }

        public static void getOverdueBooks(Book[] books, String todayDate) {
            System.out.println("\nOverdue Books:");
            int today = Integer.parseInt(todayDate.substring(0, 2));
            for (Book b : books) {
                if (b != null && b.isIssued) {
                    int due = Integer.parseInt(b.dueDate.substring(0, 2));
                    if (today > due) {
                        System.out.println(b.getTitle() + " | Due: " + b.dueDate);
                    }
                }
            }
        }

        public static void getMostPopularBooks(Book[] books) {
            System.out.println("\nMost Popular Books:");
            Arrays.sort(books, (a, b) -> b.getIssuedCount() - a.getIssuedCount());
            for (int i = 0; i < books.length && i < 3; i++) {
                if (books[i] != null) {
                    System.out.println(books[i].getTitle() + " | Issued " + books[i].getIssuedCount() + " times");
                }
            }
        }
    }

    public static void main(String[] args) {
        Book[] books = {
                new Book("Java Basics", "Author A", "111", "Programming"),
                new Book("Python Guide", "Author B", "222", "Programming"),
                new Book("Database Systems", "Author C", "333", "Database"),
                new Book("Operating Systems", "Author D", "444", "Systems")
        };

        Member[] members = {
                new Member("Alice", "Student", "01-01-2025"),
                new Member("Bob", "Faculty", "02-01-2025")
        };

        members[0].issueBook(books[0], "01-01-2025", "05-01-2025");
        members[0].issueBook(books[1], "01-01-2025", "05-01-2025");
        members[1].issueBook(books[2], "01-01-2025", "06-01-2025");

        members[0].returnBook("B001", "08-01-2025"); // late return
        members[1].renewBook("B003", "10-01-2025");

        for (Book b : books) if (b != null) b.displayInfo();

        Member.generateLibraryReport(books, members);
        Member.getOverdueBooks(books, "09-01-2025");
        Member.getMostPopularBooks(books);
    }
}
