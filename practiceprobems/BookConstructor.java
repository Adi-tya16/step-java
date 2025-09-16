package week4.practiceprobems;

public class BookConstructor {
    String title;
    String author;
    double price;

    public  BookConstructor() {
        System.out.println("Welcome to Books Shop");

    }
    public  BookConstructor(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    public void display(){
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Book Price: " + price);
    }

    public static void main(String[] args) {
        BookConstructor book = new BookConstructor("Book Title", "Book Author", 100.00);
        BookConstructor book1 = new BookConstructor();
        book.display();
    }
}
