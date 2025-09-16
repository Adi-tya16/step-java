package week2.practiceproblems;

import java.util.*;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = sc.nextLine();

        for (char c : input.toCharArray()) {
            int ascii = (int) c;
            System.out.println(c + " -> " + ascii + " (" + classifyCharacter(c) + ")");
            if (Character.isLetter(c)) {
                char t = toggleCase(c);
                System.out.println("Toggle case: " + t + " (" + (int) t + ")");
            }
        }

        System.out.println("Caesar Cipher (shift 3): " + caesarCipher(input, 3));

        displayASCIITable(65, 90);

        int[] asciiArr = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArr));
        System.out.println("Back to String: " + asciiToString(asciiArr));

        sc.close();
    }

    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char)(ch + 32);
        if (Character.isLowerCase(ch)) return (char)(ch - 32);
        return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                sb.append((char)((c - base + shift) % 26 + base));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char)i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) arr[i] = text.charAt(i);
        return arr;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int v : asciiValues) sb.append((char)v);
        return sb.toString();
    }
}


