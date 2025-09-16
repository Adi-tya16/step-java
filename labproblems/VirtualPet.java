package week4.labproblems;

import java.util.*;

public class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private int stageIndex;
    private boolean isGhost;
    private static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static int totalPetsCreated;

    public VirtualPet() {
        this("Unknown", "Random", 0, 50, 50, 0);
    }

    public VirtualPet(String petName) {
        this(petName, "Random", 0, 70, 70, 1);
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 0, 80, 80, 2);
    }

    public VirtualPet(String petName, String species, int age, int happiness, int health, int stageIndex) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stageIndex = stageIndex;
        this.isGhost = false;
        totalPetsCreated++;
    }

    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    public void evolvePet() {
        if (!isGhost && stageIndex < EVOLUTION_STAGES.length - 1 && age > 2 && happiness > 40 && health > 40) {
            stageIndex++;
            System.out.println(petName + " evolved into " + EVOLUTION_STAGES[stageIndex]);
        }
    }

    public void feedPet() {
        if (!isGhost) {
            happiness += 10;
            health += 5;
        }
    }

    public void playWithPet() {
        if (!isGhost) {
            happiness += 15;
            health -= 5;
        }
    }

    public void healPet() {
        if (!isGhost) health += 20;
    }

    public void simulateDay() {
        if (!isGhost) {
            age++;
            happiness -= new Random().nextInt(10);
            health -= new Random().nextInt(10);
            if (health <= 0) {
                isGhost = true;
                species = "Ghost";
                System.out.println(petName + " has died and become a Ghost.");
            }
            evolvePet();
        }
    }

    public String getPetStatus() {
        return isGhost ? "Ghost" : EVOLUTION_STAGES[stageIndex];
    }

    public void displayInfo() {
        System.out.println("\nPet ID: " + petId);
        System.out.println("Name: " + petName);
        System.out.println("Species: " + species);
        System.out.println("Age: " + age);
        System.out.println("Happiness: " + happiness);
        System.out.println("Health: " + health);
        System.out.println("Stage: " + getPetStatus());
    }

    public static void main(String[] args) {
        VirtualPet pet1 = new VirtualPet("Luna", "Cat");
        VirtualPet pet2 = new VirtualPet("Spike");
        VirtualPet pet3 = new VirtualPet();

        for (int day = 1; day <= 5; day++) {
            System.out.println("\n--- Day " + day + " ---");
            pet1.feedPet();
            pet1.simulateDay();
            pet2.playWithPet();
            pet2.simulateDay();
            pet3.healPet();
            pet3.simulateDay();

            pet1.displayInfo();
            pet2.displayInfo();
            pet3.displayInfo();
        }
    }
}

