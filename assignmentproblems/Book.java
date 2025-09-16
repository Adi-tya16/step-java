package week4.assignmentproblems;

public class Book {
    String title;
    String author;
    String isbn;
    boolean isAvailable;

    Book() {
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.isAvailable = true;
    }

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isbn = "";
        this.isAvailable = true;
    }

    Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    void borrowBook() {
        isAvailable = false;
    }

    void returnBook() {
        isAvailable = true;
    }

    void displayBookInfo() {
        String status = isAvailable ? "Available" : "Not Available";
        System.out.println("Title: " + title + ", Author: " + author +
                ", ISBN: " + isbn + ", Status: " + status);
    }

    public static void main(String[] args) {
        Book bk1 = new Book();
        Book bk2 = new Book("1984", "Orwell");
        Book bk3 = new Book("Java", "James", "123", true);

        bk2.borrowBook();
        bk3.returnBook();

        bk1.displayBookInfo();
        bk2.displayBookInfo();
        bk3.displayBookInfo();
    }
}

