package week2.assignmentproblems;

import java.util.*;

public class TextCompression {

    public static Object[] countFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int size = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = -1;
            for (int j = 0; j < size; j++) {
                if (chars[j] == c) {
                    index = j;
                    break;
                }
            }
            if (index == -1) {
                chars[size] = c;
                freq[size] = 1;
                size++;
            } else {
                freq[index]++;
            }
        }
        char[] finalChars = Arrays.copyOf(chars, size);
        int[] finalFreq = Arrays.copyOf(freq, size);
        return new Object[]{finalChars, finalFreq};
    }

    public static String[][] createCodes(char[] chars, int[] freq) {
        int n = chars.length;
        String[][] mapping = new String[n][2];

        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        Arrays.sort(indices, (a, b) -> freq[b] - freq[a]);

        String[] codes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "@", "#", "$", "%", "&", "!", "*", "+", "-", "="};
        int codeIndex = 0;

        for (int i = 0; i < n; i++) {
            int idx = indices[i];
            mapping[idx][0] = String.valueOf(chars[idx]);
            if (codeIndex < codes.length) {
                mapping[idx][1] = codes[codeIndex++];
            } else {
                mapping[idx][1] = "X" + i;
            }
        }
        return mapping;
    }

    public static String compressText(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (String[] map : mapping) {
                if (map[0].charAt(0) == c) {
                    sb.append(map[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static String decompressText(String compressed, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < compressed.length()) {
            boolean matched = false;
            for (String[] map : mapping) {
                String code = map[1];
                if (compressed.startsWith(code, i)) {
                    sb.append(map[0]);
                    i += code.length();
                    matched = true;
                    break;
                }
            }
            if (!matched) i++;
        }
        return sb.toString();
    }

    public static void displayAnalysis(String text, String compressed, String decompressed,
                                       char[] chars, int[] freq, String[][] mapping) {
        System.out.println("\nCharacter Frequency:");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " : " + freq[i]);
        }

        System.out.println("\nCompression Mapping:");
        for (String[] map : mapping) {
            System.out.println("'" + map[0] + "' -> " + map[1]);
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);

        int originalSize = text.length();
        int compressedSize = compressed.length();
        double efficiency = (1 - (compressedSize / (double) originalSize)) * 100;
        System.out.printf("Compression Efficiency: %.2f%%\n", efficiency);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        Object[] result = countFrequency(text);
        char[] chars = (char[]) result[0];
        int[] freq = (int[]) result[1];

        String[][] mapping = createCodes(chars, freq);
        String compressed = compressText(text, mapping);
        String decompressed = decompressText(compressed, mapping);

        displayAnalysis(text, compressed, decompressed, chars, freq, mapping);
    }
}
