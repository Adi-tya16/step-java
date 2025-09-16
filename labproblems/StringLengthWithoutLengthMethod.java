package week1.labproblems;

import java.util.Scanner;

public class StringLengthWithoutLengthMethod {

    // Method to find string length without using length()
    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count); // Will throw exception when index is out of bounds
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            // Exception means we reached the end of the string
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take user input
        System.out.print("Enter a string: ");
        String input = sc.next(); // Reads a single word (no spaces)

        // b. Call user-defined method
        int manualLength = findLength(input);

        // c. Display both results
        System.out.println("Length without using length(): " + manualLength);
        System.out.println("Length using length(): " + input.length());

        sc.close();
    }
}