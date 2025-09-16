package week1.practiceproblems;

import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        return input.substring(0,1).toUpperCase() + input.substring(1);
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        System.out.println("Word count: " + words.length);
        System.out.println("Character count: " + text.replace(" ", "").length());

        String longest = "";
        for (String w : words)
            if (w.length() > longest.length()) longest = w;
        System.out.println("Longest word: " + longest);
    }

    public static String[] getWordsSorted(String text) {
        String[] words = text.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph: ");
        String paragraph = sc.nextLine();

        paragraph = cleanInput(paragraph);
        analyzeText(paragraph);

        System.out.println("\nSorted words:");
        for (String w : getWordsSorted(paragraph))
            System.out.println(w);

        System.out.print("\nSearch word: ");
        String search = sc.nextLine();
        System.out.println(paragraph.toLowerCase().contains(search.toLowerCase())
                ? "Found!" : "Not found!");

        sc.close();
    }
}
