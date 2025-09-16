// File: Vehicle.java

public class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;

    private String registrationNumber;
    private boolean isRunning;

    public Vehicle() {
        this.brand = "Generic";
        this.model = "Model";
        this.year = 2000;
        this.engineType = "Petrol";
        this.registrationNumber = "REG" + (int)(Math.random() * 10000);
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }

    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = "REG" + (int)(Math.random() * 10000);
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }

    public void start() {
        isRunning = true;
        System.out.println("Vehicle started");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Vehicle stopped");
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String reg) {
        this.registrationNumber = reg;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getVehicleInfo() {
        return "Brand: " + brand + ", Model: " + model + ", Year: " + year
            + ", Engine: " + engineType + ", Reg#: " + registrationNumber
            + ", Running: " + isRunning;
    }

    public void displaySpecs() {
        System.out.println("Specs: " + getVehicleInfo());
    }
}
