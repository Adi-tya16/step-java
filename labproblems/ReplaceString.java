package week2.labproblems;

import java.util.Scanner;


import java.util.ArrayList;
import java.util.Scanner;

public class ReplaceString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();     // main text
        String find = sc.nextLine();     // substring to find
        String replace = sc.nextLine();  // replacement

        ArrayList<Integer> positions = new ArrayList<>();
        int index = text.indexOf(find);

        while (index != -1) {
            positions.add(index); // store where substring found
            index = text.indexOf(find, index + 1);
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }

        System.out.println("Occurrences: " + positions);
        System.out.println("Manual Replace: " + result.toString());
        System.out.println("Built-in Replace: " + text.replace(find, replace));
    }
}

