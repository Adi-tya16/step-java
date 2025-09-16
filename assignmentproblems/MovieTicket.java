package week4.assignmentproblems;

 public class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    MovieTicket() {
        this.movieName = "Unknown";
        this.theatreName = "NA";
        this.seatNumber = 0;
        this.price = 0;
    }

    MovieTicket(String movieName) {
        this.movieName = movieName;
        this.theatreName = "NA";
        this.seatNumber = 0;
        this.price = 200;
    }

    MovieTicket(String movieName, int seatNumber) {
        this.movieName = movieName;
        this.theatreName = "PVR";
        this.seatNumber = seatNumber;
        this.price = 200;
    }

    MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    void printTicket() {
        System.out.println("Movie: " + movieName + ", Theatre: " + theatreName +
                ", Seat: " + seatNumber + ", Price: " + price);
    }

    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Avatar");
        MovieTicket t3 = new MovieTicket("Batman", 12);
        MovieTicket t4 = new MovieTicket("Spiderman", "INOX", 25, 350);

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}

