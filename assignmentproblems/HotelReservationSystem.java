package week3.assignmentproblems;

import java.util.Scanner;

public class HotelReservationSystem {

    static class Room {
        private String roomNumber;
        private String roomType;
        private double pricePerNight;
        private boolean isAvailable;
        private int maxOccupancy;

        public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.pricePerNight = pricePerNight;
            this.maxOccupancy = maxOccupancy;
            this.isAvailable = true;
        }

        public String getRoomNumber() { return roomNumber; }
        public String getRoomType() { return roomType; }
        public double getPricePerNight() { return pricePerNight; }
        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean available) { isAvailable = available; }

        public void displayRoom() {
            System.out.println(roomNumber + " | " + roomType + " | " + pricePerNight +
                    " | Max: " + maxOccupancy + " | Available: " + isAvailable);
        }
    }

    static class Guest {
        private static int counter = 0;
        private String guestId;
        private String guestName;
        private String phoneNumber;
        private String email;
        private String[] bookingHistory;
        private int bookingCount;

        public Guest(String name, String phone, String email) {
            this.guestId = generateId();
            this.guestName = name;
            this.phoneNumber = phone;
            this.email = email;
            this.bookingHistory = new String[10];
            this.bookingCount = 0;
        }

        private static String generateId() {
            counter++;
            return String.format("G%03d", counter);
        }

        public String getGuestId() { return guestId; }
        public String getGuestName() { return guestName; }

        public void addBookingHistory(String bookingId) {
            bookingHistory[bookingCount++] = bookingId;
        }

        public void displayGuest() {
            System.out.println(guestId + " | " + guestName + " | " + phoneNumber + " | " + email);
        }
    }

    static class Booking {
        private static int counter = 0;
        private static int totalBookings = 0;
        private static double hotelRevenue = 0;
        private static String hotelName = "DreamStay Hotel";

        private String bookingId;
        private Guest guest;
        private Room room;
        private String checkInDate;
        private String checkOutDate;
        private double totalAmount;

        public Booking(Guest guest, Room room, String checkIn, String checkOut, int nights) {
            this.bookingId = generateId();
            this.guest = guest;
            this.room = room;
            this.checkInDate = checkIn;
            this.checkOutDate = checkOut;
            this.totalAmount = nights * room.getPricePerNight();

            room.setAvailable(false);
            guest.addBookingHistory(bookingId);

            totalBookings++;
            hotelRevenue += totalAmount;
        }

        private static String generateId() {
            counter++;
            return String.format("B%03d", counter);
        }

        public String getBookingId() { return bookingId; }
        public Room getRoom() { return room; }
        public Guest getGuest() { return guest; }
        public double getTotalAmount() { return totalAmount; }

        public void cancelBooking() {
            room.setAvailable(true);
            totalBookings--;
            hotelRevenue -= totalAmount;
            System.out.println("Booking " + bookingId + " cancelled.");
        }

        public void displayBooking() {
            System.out.println(bookingId + " | Guest: " + guest.getGuestName() +
                    " | Room: " + room.getRoomNumber() +
                    " | " + checkInDate + " to " + checkOutDate +
                    " | Amount: " + totalAmount);
        }

        public static void displayStats(Room[] rooms) {
            System.out.println("Hotel: " + hotelName);
            System.out.println("Total Bookings: " + totalBookings);
            System.out.println("Total Revenue: " + hotelRevenue);

            int occupied = 0, total = 0;
            for (Room r : rooms) {
                if (r != null) {
                    total++;
                    if (!r.isAvailable()) occupied++;
                }
            }
            double rate = (total == 0) ? 0 : (occupied * 100.0 / total);
            System.out.println("Occupancy Rate: " + rate + "%");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Room[] rooms = new Room[6];
        rooms[0] = new Room("101", "Single", 2000, 1);
        rooms[1] = new Room("102", "Double", 3500, 2);
        rooms[2] = new Room("201", "Suite", 6000, 4);
        rooms[3] = new Room("202", "Double", 3500, 2);
        rooms[4] = new Room("301", "Deluxe", 8000, 5);
        rooms[5] = new Room("302", "Single", 2000, 1);

        Guest[] guests = new Guest[5];
        Booking[] bookings = new Booking[10];
        int guestCount = 0, bookingCount = 0;

        int choice;
        do {
            System.out.println("\n--- Hotel Reservation Menu ---");
            System.out.println("1. View Rooms");
            System.out.println("2. Register Guest");
            System.out.println("3. Make Reservation");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Hotel Report");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (Room r : rooms) r.displayRoom();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    guests[guestCount++] = new Guest(name, phone, email);
                    System.out.println("Guest registered successfully.");
                    break;
                case 3:
                    if (guestCount == 0) {
                        System.out.println("No guests registered yet.");
                        break;
                    }
                    System.out.print("Enter guest ID (G001, G002...): ");
                    String gid = sc.nextLine();
                    Guest guest = null;
                    for (Guest g : guests) {
                        if (g != null && g.getGuestId().equals(gid)) guest = g;
                    }
                    if (guest == null) {
                        System.out.println("Guest not found.");
                        break;
                    }
                    System.out.print("Enter room number: ");
                    String rnum = sc.nextLine();
                    Room room = null;
                    for (Room r : rooms) {
                        if (r.getRoomNumber().equals(rnum)) room = r;
                    }
                    if (room == null || !room.isAvailable()) {
                        System.out.println("Room not available.");
                        break;
                    }
                    System.out.print("Enter check-in date: ");
                    String in = sc.nextLine();
                    System.out.print("Enter check-out date: ");
                    String out = sc.nextLine();
                    System.out.print("Enter number of nights: ");
                    int nights = sc.nextInt();
                    sc.nextLine();
                    bookings[bookingCount++] = new Booking(guest, room, in, out, nights);
                    System.out.println("Reservation successful.");
                    break;
                case 4:
                    for (Booking b : bookings) if (b != null) b.displayBooking();
                    break;
                case 5:
                    System.out.print("Enter booking ID to cancel: ");
                    String bid = sc.nextLine();
                    for (int i = 0; i < bookingCount; i++) {
                        if (bookings[i] != null && bookings[i].getBookingId().equals(bid)) {
                            bookings[i].cancelBooking();
                            bookings[i] = null;
                        }
                    }
                    break;
                case 6:
                    Booking.displayStats(rooms);
                    break;
                case 7:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);

        sc.close();
    }
}

