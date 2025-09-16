package week3.practiceproblems;

class Vehicle {
    protected String make, model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make; this.model = model; this.year = year; this.fuelLevel = fuelLevel;
    }

    public void startVehicle() { System.out.println(make + " " + model + " started."); }
    public void stopVehicle() { System.out.println(make + " " + model + " stopped."); }
    public void refuel(double amount) { fuelLevel += amount; }
    public void displayVehicleInfo() {
        System.out.println(year + " " + make + " " + model + " Fuel: " + fuelLevel);
    }
}

class CarV extends Vehicle {
    public CarV(String m, String mo, int y, double f) { super(m, mo, y, f); }
}
class Truck extends Vehicle {
    public Truck(String m, String mo, int y, double f) { super(m, mo, y, f); }
}
class Motorcycle extends Vehicle {
    public Motorcycle(String m, String mo, int y, double f) { super(m, mo, y, f); }
}

public class TestVehicle {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
                new CarV("Toyota","Corolla",2020,50),
                new Truck("Ford","F-150",2019,70),
                new Motorcycle("Yamaha","R15",2022,15)
        };
        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.displayVehicleInfo();
            v.stopVehicle();
        }
    }
}


