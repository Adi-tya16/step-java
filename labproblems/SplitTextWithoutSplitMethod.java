package week1.labproblems;

import java.util.Scanner;

public class SplitTextWithoutSplitMethod {

    // a. Method to find string length without length()
    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // reached end of string
        }
        return count;
    }

    // c. Method to split text into words without split()
    public static String[] manualSplit(String str) {
        int length = findLength(str);

        // Step 1: Count words and store space positions
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        int[] spaceIndexes = new int[spaceCount];
        int idx = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                spaceIndexes[idx++] = i;
            }
        }

        // Step 2: Create words array
        String[] words = new String[spaceCount + 1];
        int start = 0;
        int wordIndex = 0;

        for (int i = 0; i < spaceCount; i++) {
            int end = spaceIndexes[i];
            words[wordIndex++] = str.substring(start, end);
            start = end + 1;
        }

        // Last word
        words[wordIndex] = str.substring(start, length);

        return words;
    }

    // d. Method to compare two string arrays
    public static boolean compareArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take user input
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        // b. Find length without length()
        int manualLength = findLength(input);

        // c. Manual split
        String[] manualWords = manualSplit(input);

        // Built-in split
        String[] builtInWords = input.split(" ");

        // d. Compare arrays
        boolean isSame = compareArrays(manualWords, builtInWords);

        // Output
        System.out.println("\nManual length: " + manualLength);
        System.out.println("Manual split result:");
        for (String word : manualWords) {
            System.out.println(word);
        }

        System.out.println("\nBuilt-in split result:");
        for (String word : builtInWords) {
            System.out.println(word);
        }

        System.out.println("\nDo both match? " + isSame);

        sc.close();
    }
}
