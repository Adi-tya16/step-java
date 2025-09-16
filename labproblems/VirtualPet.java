package week5.labproblems;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;
    private String petName;
    private int age;
    private int happiness;
    private int health;
    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Baby", "Teen", "Adult"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    public VirtualPet() {
        this("Pet" + System.currentTimeMillis(), new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 5000, "Forest"), 1, 50, 50);
    }

    public VirtualPet(String petName) {
        this(petName, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 5000, "Forest"), 1, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species) {
        this(petName, species, 1, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
        if (species == null) throw new IllegalArgumentException("Species cannot be null");
        this.petId = generatePetId();
        this.species = species;
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = petName;
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
    public String getPetName() { return petName; }
    public int getAge() { return age; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }

    public void setPetName(String petName) { this.petName = petName; }
    public void setAge(int age) { this.age = Math.max(0, age); }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness, MAX_HAPPINESS); }
    public void setHealth(int health) { this.health = validateStat(health, MAX_HEALTH); }

    public void feedPet(String foodType) {
        modifyHappiness(calculateFoodBonus(foodType));
        modifyHealth(5);
        checkEvolution();
    }

    public void playWithPet(String gameType) {
        modifyHappiness(calculateGameEffect(gameType));
        modifyHealth(-2);
        checkEvolution();
    }

    protected int calculateFoodBonus(String foodType) {
        return foodType.length() % 10;
    }

    protected int calculateGameEffect(String gameType) {
        return gameType.length() % 15;
    }

    private void modifyHappiness(int delta) {
        happiness = Math.min(MAX_HAPPINESS, Math.max(0, happiness + delta));
    }

    private void modifyHealth(int delta) {
        health = Math.min(MAX_HEALTH, Math.max(0, health + delta));
    }

    private void updateEvolutionStage() {}

    void getInternalState() {
        System.out.println("Debug: " + this.toString());
    }

    private int validateStat(int value, int max) {
        if (value < 0 || value > max) throw new IllegalArgumentException("Invalid stat");
        return value;
    }

    private String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private void checkEvolution() {}

    @Override
    public String toString() {
        return "VirtualPet{" +
                "petId='" + petId + '\'' +
                ", species=" + species +
                ", birthTimestamp=" + birthTimestamp +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet that = (VirtualPet) o;
        return Objects.equals(petId, that.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }
}

final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isEmpty() || evolutionStages == null || evolutionStages.length == 0 || maxLifespan <= 0 || habitat == null || habitat.isEmpty())
            throw new IllegalArgumentException("Invalid species data");
        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return Arrays.copyOf(evolutionStages, evolutionStages.length); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "speciesName='" + speciesName + '\'' +
                ", evolutionStages=" + Arrays.toString(evolutionStages) +
                ", maxLifespan=" + maxLifespan +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}

class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet basePet;

    public DragonPet(String dragonType, String breathWeapon, VirtualPet basePet) {
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.basePet = basePet;
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getBasePet() { return basePet; }
}

class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet basePet;

    public RobotPet(VirtualPet basePet) {
        this.basePet = basePet;
        this.needsCharging = false;
        this.batteryLevel = 100;
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = Math.max(0, Math.min(100, batteryLevel));
        this.needsCharging = this.batteryLevel < 20;
    }
    public VirtualPet getBasePet() { return basePet; }
}

