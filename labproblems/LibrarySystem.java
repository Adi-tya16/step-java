package week3.labproblems;

public class LibrarySystem {

    static class Book {
        private String bookId;
        private String title;
        private String author;
        private boolean isAvailable;
        private static int totalBooks = 0;
        private static int availableBooks = 0;
        private static int counter = 0;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true;
            this.bookId = generateBookId();
            totalBooks++;
            availableBooks++;
        }

        private static String generateBookId() {
            counter++;
            return String.format("B%03d", counter);
        }

        public void issueBook() {
            if (isAvailable) {
                isAvailable = false;
                availableBooks--;
            }
        }

        public void returnBook() {
            if (!isAvailable) {
                isAvailable = true;
                availableBooks++;
            }
        }

        public void displayBookInfo() {
            System.out.println("BookID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable);
        }

        public String getBookId() {
            return bookId;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public static int getTotalBooks() {
            return totalBooks;
        }

        public static int getAvailableBooks() {
            return availableBooks;
        }
    }

    static class Member {
        private String memberId;
        private String memberName;
        private String[] booksIssued;
        private int bookCount;
        private static int counter = 0;

        public Member(String memberName, int maxBooks) {
            this.memberName = memberName;
            this.memberId = generateMemberId();
            this.booksIssued = new String[maxBooks];
            this.bookCount = 0;
        }

        private static String generateMemberId() {
            counter++;
            return String.format("M%03d", counter);
        }

        public void borrowBook(Book book) {
            if (book.isAvailable() && bookCount < booksIssued.length) {
                book.issueBook();
                booksIssued[bookCount++] = book.getBookId();
                System.out.println(memberName + " borrowed " + book.getBookId());
            } else {
                System.out.println("Cannot borrow book: " + book.getBookId());
            }
        }

        public void returnBook(String bookId, Book[] books) {
            for (int i = 0; i < bookCount; i++) {
                if (booksIssued[i].equals(bookId)) {
                    for (Book b : books) {
                        if (b.getBookId().equals(bookId)) {
                            b.returnBook();
                            System.out.println(memberName + " returned " + bookId);
                            break;
                        }
                    }
                    booksIssued[i] = booksIssued[bookCount - 1];
                    booksIssued[bookCount - 1] = null;
                    bookCount--;
                    return;
                }
            }
            System.out.println("Book not found in member's issued list.");
        }

        public void displayMemberInfo() {
            System.out.print("MemberID: " + memberId + ", Name: " + memberName + ", Books Issued: ");
            for (int i = 0; i < bookCount; i++) {
                System.out.print(booksIssued[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("The Alchemist", "Paulo Coelho");
        books[1] = new Book("1984", "George Orwell");
        books[2] = new Book("Clean Code", "Robert Martin");

        Member[] members = new Member[2];
        members[0] = new Member("Alice", 2);
        members[1] = new Member("Bob", 2);

        members[0].borrowBook(books[0]);
        members[0].borrowBook(books[1]);
        members[1].borrowBook(books[0]);
        members[0].returnBook("B001", books);
        members[1].borrowBook(books[0]);

        System.out.println("\nBooks Info:");
        for (Book b : books) b.displayBookInfo();

        System.out.println("\nMembers Info:");
        for (Member m : members) m.displayMemberInfo();

        System.out.println("\nTotal Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}

