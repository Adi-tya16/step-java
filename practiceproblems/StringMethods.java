package week1.practiceproblems;

import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full name: ");
        String fullName = sc.nextLine();

        System.out.print("Favorite programming language: ");
        String lang = sc.nextLine();

        System.out.print("Sentence about your programming experience: ");
        String sentence = sc.nextLine();

        String[] nameParts = fullName.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        int charCount = sentence.replace(" ", "").length();

        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Language (UPPERCASE): " + lang.toUpperCase());
        System.out.println("Sentence characters (no spaces): " + charCount);

        sc.close();
    }
}
