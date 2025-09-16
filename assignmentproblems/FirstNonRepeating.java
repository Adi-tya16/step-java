package week1.assignmentproblems;


import java.util.Scanner;

public class FirstNonRepeating {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        int[] freq = new int[256];  // frequency array for ASCII chars

        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        // Find first non-repeating character
        for (int i = 0; i < text.length(); i++) {
            if (freq[text.charAt(i)] == 1) {
                System.out.println("First non-repeating: " + text.charAt(i));
                sc.close();
                return;
            }
        }

        System.out.println("No unique character found.");
        sc.close();
    }
}

