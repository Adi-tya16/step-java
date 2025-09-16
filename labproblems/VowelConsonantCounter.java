package week1.labproblems;

import java.util.Scanner;

public class VowelConsonantCounter {

    // a. Method to check if a character is a vowel or consonant
    public static String checkCharType(char ch) {
        // i. Convert uppercase to lowercase using ASCII
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32); // Convert to lowercase
        }

        // ii. Check if letter
        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        }
        return "Not a Letter";
    }

    // b. Method to find vowels and consonants count in a string
    public static int[] countVowelsAndConsonants(String str) {
        int vowelCount = 0, consonantCount = 0;

        try {
            int index = 0;
            while (true) {
                char ch = str.charAt(index);
                String type = checkCharType(ch);

                if (type.equals("Vowel")) {
                    vowelCount++;
                } else if (type.equals("Consonant")) {
                    consonantCount++;
                }

                index++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // End of string reached
        }

        return new int[]{vowelCount, consonantCount};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take user input
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Count vowels and consonants
        int[] counts = countVowelsAndConsonants(input);

        // Display result
        System.out.println("\nVowels: " + counts[0]);
        System.out.println("Consonants: " + counts[1]);

        sc.close();
    }
}
