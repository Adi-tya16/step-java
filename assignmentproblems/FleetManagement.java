package week3.assignmentproblems;

import java.util.*;

class Vehicle {
    protected String vehicleId, brand, model, fuelType, currentStatus;
    protected int year;
    protected double mileage, value;

    static int totalVehicles = 0;
    static double fleetValue = 0;
    static String companyName = "Transpo Corp";
    static double totalFuelConsumption = 0;

    Vehicle(String brand, String model, int year, double mileage, String fuelType, double value) {
        this.vehicleId = generateId();
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.value = value;
        this.currentStatus = "Available";
        totalVehicles++;
        fleetValue += value;
    }

    private static int counter = 0;
    private static String generateId() {
        counter++;
        return "V" + String.format("%03d", counter);
    }

    public void assignDriver(Driver d) {
        if (currentStatus.equals("Available")) {
            currentStatus = "Assigned to " + d.driverName;
            d.assignedVehicle = this;
            System.out.println(d.driverName + " assigned to " + brand + " " + model);
        }
    }

    public void scheduleMaintenance() {
        currentStatus = "Maintenance";
        System.out.println(vehicleId + " scheduled for maintenance");
    }

    public void updateMileage(double km, double fuelUsed) {
        mileage += km;
        totalFuelConsumption += fuelUsed;
    }

    public boolean checkServiceDue() {
        return mileage % 10000 < 500; // due every 10,000 km
    }

    public double calculateRunningCost(double fuelPricePerLiter, double fuelUsed) {
        return fuelPricePerLiter * fuelUsed + (checkServiceDue() ? 500 : 0);
    }

    public void displayInfo() {
        System.out.println(vehicleId + " | " + brand + " " + model + " | " + year + " | Mileage: " + mileage + " | Status: " + currentStatus);
    }
}

class Car extends Vehicle {
    int seatingCapacity;

    Car(String brand, String model, int year, double mileage, String fuelType, double value, int seats) {
        super(brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seats;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Car | Seats: " + seatingCapacity);
    }
}

class Bus extends Vehicle {
    int seatingCapacity;

    Bus(String brand, String model, int year, double mileage, String fuelType, double value, int seats) {
        super(brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seats;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Bus | Seats: " + seatingCapacity);
    }
}

class Truck extends Vehicle {
    double loadCapacity;

    Truck(String brand, String model, int year, double mileage, String fuelType, double value, double loadCapacity) {
        super(brand, model, year, mileage, fuelType, value);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Truck | Load Capacity: " + loadCapacity + " tons");
    }
}

class Driver {
    String driverId, driverName, licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    private static int counter = 0;
    Driver(String name, String licenseType) {
        this.driverId = generateId();
        this.driverName = name;
        this.licenseType = licenseType;
        this.totalTrips = 0;
    }

    private static String generateId() {
        counter++;
        return "D" + String.format("%03d", counter);
    }

    public void completeTrip(double km, double fuelUsed) {
        if (assignedVehicle != null) {
            assignedVehicle.updateMileage(km, fuelUsed);
            totalTrips++;
            System.out.println(driverName + " completed a trip of " + km + " km with " + assignedVehicle.brand);
        }
    }
}

public class FleetManagement {
    public static void getFleetUtilization(Vehicle[] vehicles) {
        int assigned = 0;
        for (Vehicle v : vehicles) if (!v.currentStatus.equals("Available")) assigned++;
        System.out.println("Fleet Utilization: " + (assigned * 100.0 / vehicles.length) + "%");
    }

    public static void calculateTotalMaintenanceCost(Vehicle[] vehicles) {
        double total = 0;
        for (Vehicle v : vehicles) if (v.checkServiceDue()) total += 500;
        System.out.println("Total Maintenance Cost: " + total);
    }

    public static void getVehiclesByType(Vehicle[] vehicles, String type) {
        System.out.println("Vehicles of type " + type + ":");
        for (Vehicle v : vehicles) {
            if ((type.equals("Car") && v instanceof Car) ||
                    (type.equals("Bus") && v instanceof Bus) ||
                    (type.equals("Truck") && v instanceof Truck)) {
                v.displayInfo();
            }
        }
    }

    public static void main(String[] args) {
        Vehicle v1 = new Car("Toyota", "Corolla", 2020, 15000, "Petrol", 12000, 5);
        Vehicle v2 = new Bus("Volvo", "9400", 2019, 50000, "Diesel", 40000, 50);
        Vehicle v3 = new Truck("Tata", "LPT", 2021, 30000, "Diesel", 30000, 20);

        Driver d1 = new Driver("Alice", "Car");
        Driver d2 = new Driver("Bob", "Heavy");

        v1.assignDriver(d1);
        v2.assignDriver(d2);

        d1.completeTrip(200, 20);
        d2.completeTrip(500, 80);

        v1.displayInfo();
        v2.displayInfo();
        v3.displayInfo();

        Vehicle[] fleet = {v1, v2, v3};

        getFleetUtilization(fleet);
        calculateTotalMaintenanceCost(fleet);
        getVehiclesByType(fleet, "Bus");

        System.out.println("Total Vehicles: " + Vehicle.totalVehicles);
        System.out.println("Fleet Value: " + Vehicle.fleetValue);
        System.out.println("Total Fuel Consumption: " + Vehicle.totalFuelConsumption);
    }
}
