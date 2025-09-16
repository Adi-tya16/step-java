package week3.labproblems;

public class VehicleRentalSystem {

    static class Vehicle {
        private String vehicleId;
        private String brand;
        private String model;
        private double rentPerDay;
        private boolean isAvailable;
        private int totalRentedDays;
        private static int totalVehicles = 0;
        private static double totalRevenue = 0;
        private static int totalRentalDays = 0;
        private static String companyName = "Default Rentals";
        private static int counter = 0;

        public Vehicle(String brand, String model, double rentPerDay) {
            this.vehicleId = generateId();
            this.brand = brand;
            this.model = model;
            this.rentPerDay = rentPerDay;
            this.isAvailable = true;
            this.totalRentedDays = 0;
            totalVehicles++;
        }

        private static String generateId() {
            counter++;
            return String.format("V%03d", counter);
        }

        public double calculateRent(int days) {
            double amount = rentPerDay * days;
            totalRevenue += amount;
            totalRentalDays += days;
            totalRentedDays += days;
            return amount;
        }

        public void rentVehicle(int days) {
            if (isAvailable) {
                double amount = calculateRent(days);
                isAvailable = false;
                System.out.println(vehicleId + " rented for " + days + " days. Rent: " + amount);
            } else {
                System.out.println(vehicleId + " is not available.");
            }
        }

        public void returnVehicle() {
            if (!isAvailable) {
                isAvailable = true;
                System.out.println(vehicleId + " has been returned.");
            }
        }

        public void displayVehicleInfo() {
            System.out.println("ID: " + vehicleId + ", Brand: " + brand + ", Model: " + model +
                    ", Rent/Day: " + rentPerDay + ", Available: " + isAvailable +
                    ", Total Rented Days: " + totalRentedDays);
        }

        public static void setCompanyName(String name) {
            companyName = name;
        }

        public static double getTotalRevenue() {
            return totalRevenue;
        }

        public static double getAverageRentPerDay() {
            if (totalRentalDays == 0) return 0;
            return totalRevenue / totalRentalDays;
        }

        public static void displayCompanyStats() {
            System.out.println("Company: " + companyName);
            System.out.println("Total Vehicles: " + totalVehicles);
            System.out.println("Total Revenue: " + totalRevenue);
            System.out.println("Average Rent Per Day: " + getAverageRentPerDay());
        }
    }

    public static void main(String[] args) {
        Vehicle.setCompanyName("CityRide Rentals");

        Vehicle v1 = new Vehicle("Toyota", "Corolla", 1000);
        Vehicle v2 = new Vehicle("Honda", "Civic", 1200);
        Vehicle v3 = new Vehicle("Tesla", "Model 3", 2000);

        v1.rentVehicle(3);
        v2.rentVehicle(2);
        v1.returnVehicle();
        v1.rentVehicle(5);

        System.out.println("\nVehicle Info:");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        System.out.println("\nCompany Stats:");
        Vehicle.displayCompanyStats();
    }
}

