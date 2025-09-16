package week3.practiceproblems;


public class Car {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }

    public void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }

    public void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }

    public void displayInfo() {
        System.out.println(year + " " + color + " " + brand + " " + model + " Running: " + isRunning);
    }

    public int getAge() {
        return 2025 - year;
    }

    public static void main(String[] args) {
        Car c1 = new Car("Toyota", "Camry", 2018, "Black");
        Car c2 = new Car("Tesla", "Model S", 2022, "White");
        Car c3 = new Car("Ford", "Mustang", 2015, "Red");
        c1.startEngine(); c1.displayInfo();
        c2.displayInfo();
        c3.stopEngine(); c3.displayInfo();
    }
}


