// File: Dog.java
public class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel; // 1-10 scale
    private String favoriteActivity;

    // Constructor 1: Basic Dog
    public Dog() {
        super("Dog", "Domestic", 13, false, "Varies", 60);
        this.breed = "Mixed";
        this.isDomesticated = true;
        this.loyaltyLevel = 7;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Constructor 2: Detailed Dog
    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod,
               String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Constructor 3: Copy Constructor
    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod, other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }
    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }
    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    public void bark() {
        System.out.println("Woof! Woof!");
    }
    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }
    public void showLoyalty() {
        System.out.println("Loyalty level: " + loyaltyLevel);
    }

    public void demonstrateInheritance() {
        System.out.println(super.getAnimalInfo());
        eat();
        move();
        sleep();
        nurse();
        regulateTemperature();
        bark();
        fetch();
        showLoyalty();
    }

    public static void main(String[] args) {
        // Test 1: Basic Dog
        Dog d1 = new Dog();
        d1.demonstrateInheritance();

        // Test 2: Detailed Dog
        Dog d2 = new Dog("Dog", "Police Service", 12, false, "Black", 63, "German Shepherd", true, 10, "Guarding");
        d2.demonstrateInheritance();

        // Test 3: Copy Constructor
        Dog d3 = new Dog(d2);
        d3.demonstrateInheritance();
    }
}
