package week1.assignmentproblems;

import java.util.Scanner;

public class UniqueCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean unique = true;

            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == ch) {
                    unique = false;
                    break;
                }
            }

            if (unique) result += ch;
        }

        System.out.println("Unique characters: " + result);
        sc.close();
    }
}
