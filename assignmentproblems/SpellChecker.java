package week2.assignmentproblems;

import java.util.Scanner;

public class SpellChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sentence = sc.nextLine();
        String[] dictionary = {"apple", "banana", "grape", "orange", "mango"};

        String[] words = extractWords(sentence);
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null && !words[i].equals("")) {
                String suggestion = findClosest(words[i], dictionary);
                int dist = stringDistance(words[i], suggestion);
                if (dist == 0) {
                    System.out.println(words[i] + " -> Correct");
                } else {
                    System.out.println(words[i] + " -> Suggestion: " + suggestion + " (distance " + dist + ")");
                }
            }
        }
    }

    public static String[] extractWords(String sentence) {
        String[] arr = new String[sentence.length()];
        int start = 0, index = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ' || i == sentence.length() - 1) {
                int end = (i == sentence.length() - 1) ? i + 1 : i;
                arr[index++] = sentence.substring(start, end);
                start = i + 1;
            }
        }
        return arr;
    }

    public static int stringDistance(String a, String b) {
        int m = a.length(), n = b.length();
        int diff = Math.abs(m - n);
        int min = Math.min(m, n);
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff;
    }

    public static String findClosest(String word, String[] dict) {
        String best = word;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < dict.length; i++) {
            int d = stringDistance(word, dict[i]);
            if (d < minDist) {
                minDist = d;
                best = dict[i];
            }
        }
        return best;
    }
}
