package week1.labproblems;

import java.util.Scanner;

public class ShortestLongestWord {

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

        // Count spaces to determine number of words
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
        words[wordIndex] = str.substring(start, length);

        return words;
    }

    // d. Method to create 2D array with word and length
    public static String[][] wordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i]; // word
            result[i][1] = String.valueOf(findLength(words[i])); // length as String
        }
        return result;
    }

    // e. Method to find shortest and longest words
    // Return format: {shortestIndex, longestIndex}
    public static int[] findShortestAndLongest(String[][] wordsWithLen) {
        int shortestIndex = 0, longestIndex = 0;

        for (int i = 1; i < wordsWithLen.length; i++) {
            int len = Integer.parseInt(wordsWithLen[i][1]);
            if (len < Integer.parseInt(wordsWithLen[shortestIndex][1])) {
                shortestIndex = i;
            }
            if (len > Integer.parseInt(wordsWithLen[longestIndex][1])) {
                longestIndex = i;
            }
        }

        return new int[]{shortestIndex, longestIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take input
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        // b. Manual split
        String[] words = manualSplit(input);

        // d. Create 2D array with lengths
        String[][] wordsWithLen = wordsWithLengths(words);

        // e. Find shortest and longest
        int[] indexes = findShortestAndLongest(wordsWithLen);

        // Display all words with lengths
        System.out.println("\nWord\tLength");
        System.out.println("-----------------");
        for (String[] row : wordsWithLen) {
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
        }

        // Display shortest and longest
        System.out.println("\nShortest word: " + wordsWithLen[indexes[0]][0] +
                " (Length: " + Integer.parseInt(wordsWithLen[indexes[0]][1]) + ")");
        System.out.println("Longest word: " + wordsWithLen[indexes[1]][0] +
                " (Length: " + Integer.parseInt(wordsWithLen[indexes[1]][1]) + ")");

        sc.close();
    }
}

