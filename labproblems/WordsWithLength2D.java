package week1.labproblems;

import java.util.Scanner;

public class WordsWithLength2D {

    // c. Method to find string length without using length()
    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // End of string
        }
        return count;
    }

    // b. Method to split string manually into words (without split())
    public static String[] manualSplit(String str) {
        int length = findLength(str);

        // Count spaces to know number of words
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        String[] words = new String[spaceCount + 1];
        int start = 0, wordIndex = 0;

        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                words[wordIndex++] = str.substring(start, i);
                start = i + 1;
            }
        }
        // Add last word
        words[wordIndex] = str.substring(start, length);

        return words;
    }

    // d. Method to create a 2D array with word and its length
    public static String[][] wordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i]; // Word
            result[i][1] = String.valueOf(findLength(words[i])); // Length as String
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take user input
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        // b. Manual split
        String[] words = manualSplit(input);

        // d. Create 2D array
        String[][] wordsWithLen = wordsWithLengths(words);

        // e. Display in tabular format (length converted back to int)
        System.out.println("\nWord\tLength");
        System.out.println("-----------------");
        for (String[] row : wordsWithLen) {
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
        }

        sc.close();
    }
}