package week1.assignmentproblems;

import java.util.Scanner;

public class CharFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        int[] freq = new int[256];  // frequency array for ASCII chars

        // Count frequency of each character
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        System.out.println("Character frequencies:");
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (freq[ch] > 0) {
                System.out.println(ch + " : " + freq[ch]);
                freq[ch] = 0;  // reset to avoid duplicate printing
            }
        }

        sc.close();
    }
}
