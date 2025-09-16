package week2.labproblems;

import java.util.Scanner;

public class ASCIICoversion {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = input.nextLine();

        char[] c = str.toCharArray();
        int[] ascii = new int[c.length];

        for (int i = 0; i < c.length; i++) {
            ascii[i] = (int) c[i];
            System.out.println(c[i] + " -> " + ascii[i]);
        }

        System.out.println("Uppercase: " + toUpperCase(c));
        System.out.println("Lowercase: " + toLowerCase(c));
        System.out.println("Titlecase: " + toTitleCase(c));

        input.close();
    }

    public static String toUpperCase(char[] c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 97 && c[i] <= 122) sb.append((char)(c[i] - 32));
            else sb.append(c[i]);
        }
        return sb.toString();
    }

    public static String toLowerCase(char[] c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 65 && c[i] <= 90) sb.append((char)(c[i] + 32));
            else sb.append(c[i]);
        }
        return sb.toString();
    }

    public static String toTitleCase(char[] c) {
        StringBuilder sb = new StringBuilder();
        boolean makeUpper = true;
        for (int i = 0; i < c.length; i++) {
            char ch = c[i];
            if (ch >= 65 && ch <= 90) ch = (char)(ch + 32);
            if (makeUpper && ch >= 97 && ch <= 122) {
                sb.append((char)(ch - 32));
                makeUpper = false;
            } else {
                sb.append(ch);
                if (ch == 32) makeUpper = true;
            }
        }
        return sb.toString();
    }
}
