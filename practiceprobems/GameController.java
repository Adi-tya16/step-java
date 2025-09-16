package week4.practiceprobems;

public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public GameController(String controllerBrand, String connectionType, 
                         boolean hasVibration, int batteryLevel, 
                         double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        this.batteryLevel = Math.min(100, Math.max(0, batteryLevel));
        this.sensitivity = Math.min(3.0, Math.max(0.1, sensitivity));
    }

    public GameController(String brand, String connectionType) {
        this(brand, connectionType, true, 100, 1.0);
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + hasVibration);
        System.out.println("Battery: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");
        
        GameController default1 = new GameController();
        GameController custom = new GameController("ProGamer", "Bluetooth", true, 80, 2.0);
        GameController basic = new GameController("BasicPad", "Wired");

        System.out.println("\nDefault Controller:");
        default1.displayConfiguration();
        default1.calibrateController();
        default1.testVibration();

        System.out.println("\nCustom Controller:");
        custom.displayConfiguration();
        custom.calibrateController();
        custom.testVibration();

        System.out.println("\nBasic Controller:");
        basic.displayConfiguration();
        basic.calibrateController();
        basic.testVibration();
    }
}