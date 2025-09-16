// File: Car.java

public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    public Car() {
        super();
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Car specific startup sequence");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Car specs: Doors: " + numberOfDoors +
                ", Fuel: " + fuelType + ", Transmission: " + transmissionType);
    }

    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    // Test method inside Car
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.displaySpecs();
        c1.start();
        c1.openTrunk();
        c1.playRadio();

        Car c2 = new Car("Honda", "Civic", 2020, "Diesel", 4, "Diesel", "Automatic");
        c2.displaySpecs();
        c2.start();
        c2.stop();
    }
}
