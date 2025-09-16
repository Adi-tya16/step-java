package week4.labproblems;

abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public MagicalStructure(String structureName, int magicPower, String location) {
        this(structureName, magicPower, location, true);
    }

    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown", true);
    }

    public abstract void castMagicSpell();
}

class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    public WizardTower(String name, int magicPower, String location, int spellCapacity, String[] knownSpells) {
        super(name, magicPower, location);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " casts powerful wizard magic!");
    }
}

class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle(String name, int magicPower, String location, int defenseRating, boolean hasDrawbridge) {
        super(name, magicPower, location);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " radiates protective magic!");
    }
}

class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary(String name, int magicPower, String location, int bookCount, String ancientLanguage) {
        super(name, magicPower, location);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " reveals ancient knowledge!");
    }
}

class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair(String name, int magicPower, String location, String dragonType, int treasureValue) {
        super(name, magicPower, location);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    public void castMagicSpell() {
        System.out.println(structureName + " unleashes dragon fury!");
    }
}

class KingdomManager {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
                (s1 instanceof EnchantedCastle && s2 instanceof DragonLair);
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        return attacker.magicPower > defender.magicPower ? attacker.structureName + " wins!" : defender.structureName + " wins!";
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) total += s.magicPower;
        return total;
    }
}

public class KingdomGame {
    public static void main(String[] args) {
        WizardTower tower = new WizardTower("Mage Tower", 80, "North", 5, new String[]{"Fireball", "Shield"});
        EnchantedCastle castle = new EnchantedCastle("Royal Castle", 60, "Center", 100, true);
        MysticLibrary library = new MysticLibrary("Arcane Library", 70, "East", 500, "Elder Tongue");
        DragonLair lair = new DragonLair("Dragon's Den", 90, "Mountain", "Fire Dragon", 1000);

        tower.castMagicSpell();
        castle.castMagicSpell();
        library.castMagicSpell();
        lair.castMagicSpell();

        MagicalStructure[] kingdom = {tower, castle, library, lair};
        System.out.println("Total Kingdom Power: " + KingdomManager.calculateKingdomMagicPower(kingdom));

        System.out.println("Interaction: " + KingdomManager.canStructuresInteract(tower, library));
        System.out.println("Battle: " + KingdomManager.performMagicBattle(tower, lair));
    }
}
