package week5.labproblems;


import java.util.*;

final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String name, int year, String[] types, Map<String, Integer> limits) {
        if (name == null || name.isEmpty() || year <= 0 || types == null || types.length == 0 || limits == null)
            throw new IllegalArgumentException("Invalid config data");
        this.kingdomName = name;
        this.foundingYear = year;
        this.allowedStructureTypes = Arrays.copyOf(types, types.length);
        this.resourceLimits = new HashMap<>(limits);
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    public static KingdomConfig createDefaultKingdom() {
        return new KingdomConfig("Default Kingdom", 1000, new String[]{"WizardTower", "EnchantedCastle"}, Map.of("Gold", 1000));
    }

    public static KingdomConfig createFromTemplate(String type) {
        if ("War".equalsIgnoreCase(type)) {
            return new KingdomConfig("WarRealm", 1200, new String[]{"EnchantedCastle", "DragonLair"}, Map.of("Iron", 500, "Gold", 200));
        }
        return createDefaultKingdom();
    }

    @Override
    public String toString() {
        return "KingdomConfig{" + kingdomName + ", " + foundingYear + ", " + Arrays.toString(allowedStructureTypes) + ", " + resourceLimits + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KingdomConfig)) return false;
        KingdomConfig that = (KingdomConfig) o;
        return foundingYear == that.foundingYear && Objects.equals(kingdomName, that.kingdomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kingdomName, foundingYear);
    }
}

class MagicalStructure {
    private final String structureId;
    private final long constructionTimestamp;
    private final String structureName;
    private final String location;
    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    static final int MIN_MAGIC_POWER = 0, MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    public MagicalStructure(String name, String location) {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || location == null || power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER)
            throw new IllegalArgumentException("Invalid structure data");
        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = power;
        this.isActive = active;
    }

    public String getStructureId() { return structureId; }
    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }
    public int getMagicPower() { return magicPower; }
    public boolean isActive() { return isActive; }
    public String getCurrentMaintainer() { return currentMaintainer; }
    public void setMagicPower(int magicPower) { this.magicPower = Math.min(MAX_MAGIC_POWER, Math.max(MIN_MAGIC_POWER, magicPower)); }
    public void setActive(boolean active) { isActive = active; }
    public void setCurrentMaintainer(String maintainer) { this.currentMaintainer = maintainer; }

    @Override
    public String toString() {
        return "MagicalStructure{" + structureName + ", " + location + ", power=" + magicPower + ", active=" + isActive + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MagicalStructure)) return false;
        MagicalStructure that = (MagicalStructure) o;
        return Objects.equals(structureId, that.structureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(structureId);
    }
}

class WizardTower {
    private final int maxSpellCapacity;
    private List<String> knownSpells;
    private String currentWizard;
    private final MagicalStructure base;

    public WizardTower(String name, String location) {
        this(name, location, 100, true, 10, new ArrayList<>(), null);
    }

    public WizardTower(String name, String location, int cap, List<String> spells, String wizard) {
        this(name, location, 200, true, cap, new ArrayList<>(spells), wizard);
    }

    public WizardTower(String name, String location, int power, boolean active, int cap, List<String> spells, String wizard) {
        this.base = new MagicalStructure(name, location, power, active);
        this.maxSpellCapacity = cap;
        this.knownSpells = spells;
        this.currentWizard = wizard;
    }

    public int getMaxSpellCapacity() { return maxSpellCapacity; }
    public List<String> getKnownSpells() { return new ArrayList<>(knownSpells); }
    public String getCurrentWizard() { return currentWizard; }
}

class EnchantedCastle {
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;
    private final MagicalStructure base;

    public EnchantedCastle(String type, String location) {
        this(type, location, "Basic", 100, true);
    }

    public EnchantedCastle(String name, String location, String type, int defense, boolean drawbridge) {
        this.base = new MagicalStructure(name, location);
        this.castleType = type;
        this.defenseRating = defense;
        this.hasDrawbridge = drawbridge;
    }
}

class MysticLibrary {
    private final Map<String, String> bookCollection;
    private int knowledgeLevel;
    private final MagicalStructure base;

    public MysticLibrary(String name, String location) {
        this(name, location, new HashMap<>(), 50);
    }

    public MysticLibrary(String name, String location, Map<String, String> books, int knowledge) {
        this.base = new MagicalStructure(name, location);
        this.bookCollection = new HashMap<>(books);
        this.knowledgeLevel = knowledge;
    }
}

class DragonLair {
    private final String dragonType;
    private long treasureValue;
    private int territorialRadius;
    private final MagicalStructure base;

    public DragonLair(String name, String location, String type) {
        this(name, location, type, 1000, 50);
    }

    public DragonLair(String name, String location, String type, long treasure, int radius) {
        this.base = new MagicalStructure(name, location);
        this.dragonType = type;
        this.treasureValue = treasure;
        this.territorialRadius = radius;
    }
}

class KingdomManager {
    private final List<Object> structures;
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        this.config = config;
        this.structures = new ArrayList<>();
    }

    public void addStructure(Object s) { structures.add(s); }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) || (s1 instanceof DragonLair && s2 instanceof EnchantedCastle);
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker instanceof MagicalStructure && defender instanceof MagicalStructure) {
            MagicalStructure a = (MagicalStructure) attacker;
            MagicalStructure d = (MagicalStructure) defender;
            return a.getMagicPower() > d.getMagicPower() ? "Attacker wins" : "Defender wins";
        }
        return "No battle possible";
    }

    public static int calculateKingdomPower(Object[] structures) {
        int sum = 0;
        for (Object s : structures) {
            if (s instanceof MagicalStructure) {
                sum += ((MagicalStructure) s).getMagicPower();
            }
        }
        return sum;
    }

    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) return "Tower";
        if (structure instanceof EnchantedCastle) return "Castle";
        if (structure instanceof MysticLibrary) return "Library";
        if (structure instanceof DragonLair) return "Lair";
        return "Unknown";
    }
}

