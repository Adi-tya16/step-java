package week2.labproblems;

import java.util.*;

public class TextBuilder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();
        System.out.println("Enter line width:");
        int width = sc.nextInt();

        String[] words = splitWords(text);
        System.out.println("\nOriginal Text:\n" + text);

        System.out.println("\nLeft-Justified Text:");
        long start1 = System.nanoTime();
        List<String> justified = justifyText(words, width);
        long end1 = System.nanoTime();
        display(justified);

        System.out.println("\nCenter-Aligned Text:");
        List<String> centered = centerAlign(words, width);
        display(centered);

        long start2 = System.nanoTime();
        concatJustify(words, width);
        long end2 = System.nanoTime();

        System.out.println("\nPerformance Analysis:");
        System.out.println("Using StringBuilder: " + (end1 - start1) + " ns");
        System.out.println("Using Concatenation: " + (end2 - start2) + " ns");
    }

    public static String[] splitWords(String text) {
        List<String> list = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) list.add(text.substring(start, i));
                start = i + 1;
            }
        }
        if (start < text.length()) list.add(text.substring(start));
        return list.toArray(new String[0]);
    }

    public static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int count = 0, start = 0;
        for (int i = 0; i < words.length; i++) {
            if (count + words[i].length() + (i - start) <= width) {
                count += words[i].length();
            } else {
                int gaps = i - start - 1;
                StringBuilder sb = new StringBuilder();
                sb.append(words[start]);
                if (gaps > 0) {
                    int spaces = (width - count) / gaps;
                    int extra = (width - count) % gaps;
                    for (int j = start + 1; j < i; j++) {
                        for (int s = 0; s < spaces; s++) sb.append(" ");
                        if (extra-- > 0) sb.append(" ");
                        sb.append(words[j]);
                    }
                }
                while (sb.length() < width) sb.append(" ");
                lines.add(sb.toString());
                start = i;
                count = words[i].length();
            }
        }
        StringBuilder last = new StringBuilder(words[start]);
        for (int i = start + 1; i < words.length; i++) last.append(" ").append(words[i]);
        while (last.length() < width) last.append(" ");
        lines.add(last.toString());
        return lines;
    }

    public static List<String> centerAlign(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (sb.length() + word.length() + 1 > width) {
                int padding = (width - sb.length()) / 2;
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < padding; i++) line.append(" ");
                line.append(sb);
                lines.add(line.toString());
                sb = new StringBuilder();
            }
            if (sb.length() > 0) sb.append(" ");
            sb.append(word);
        }
        int padding = (width - sb.length()) / 2;
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < padding; i++) line.append(" ");
        line.append(sb);
        lines.add(line.toString());
        return lines;
    }

    public static void concatJustify(String[] words, int width) {
        String line = "";
        int count = 0, start = 0;
        for (int i = 0; i < words.length; i++) {
            if (count + words[i].length() + (i - start) <= width) {
                count += words[i].length();
            } else {
                String result = words[start];
                for (int j = start + 1; j < i; j++) result += " " + words[j];
                while (result.length() < width) result += " ";
                start = i;
                count = words[i].length();
            }
        }
    }

    public static void display(List<String> lines) {
        int lineNo = 1;
        for (String line : lines) {
            System.out.printf("%2d | %-20s | %d%n", lineNo++, line, line.length());
        }
    }
}
