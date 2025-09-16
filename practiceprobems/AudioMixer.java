package week4.practiceprobems;

public class AudioMixer {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;
public AudioMixer() {
    this("StandardMix-8", 8, false);
}

public AudioMixer(String mixerModel, int numberOfChannels) {
    this(mixerModel, numberOfChannels, false);
}

public AudioMixer(String mixerModel, int numberOfChannels,
                  boolean hasBluetoothConnectivity) {
    this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0);
}

public AudioMixer(String mixerModel, int numberOfChannels,
                  boolean hasBluetoothConnectivity, double
                          maxVolumeDecibels) {
    this.mixerModel = mixerModel;
    this.numberOfChannels = numberOfChannels;
    this.hasBluetoothConnectivity = hasBluetoothConnectivity;
    this.maxVolumeDecibels = maxVolumeDecibels;
    this.connectedDevices = new String[numberOfChannels];
    this.deviceCount = 0;
    System.out.println("Created mixer: " + mixerModel);
}
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }
    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity
                ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
                System.out.println("Connected Devices: " + deviceCount + "/"
                        + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " +
                    connectedDevices[i]);
        }

        

    }

    public static void main(String[] args) {
        AudioMixer mixer1 = new AudioMixer();
        AudioMixer mixer2 = new AudioMixer("ProMix-16", 16);
        AudioMixer mixer3 = new AudioMixer("BluetoothMix-4", 4, true);
        AudioMixer mixer4 = new AudioMixer("StudioMix-24", 24, true, 140.0);

        mixer1.connectDevice("Microphone");
        mixer2.connectDevice("Keyboard");
        mixer3.connectDevice("Guitar");
        mixer4.connectDevice("Drums");

        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();

        // Constructor chaining flows from no-args -> three-args -> four-args constructor
    }
}
