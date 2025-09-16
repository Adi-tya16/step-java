package week1.assignmentproblems;

import java.lang.invoke.StringConcatFactory;
import java.util.Scanner;

public class frequencyOfCharactersNestedLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str=sc.nextLine();
        int[] freq =  new int[256];

        for(int i=0;i<str.length();i++){
            freq[str.charAt(i)]++;
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {   // only print if count > 0
                System.out.println((char)i + " -> " + freq[i]);
            }
        }

        sc.close();
    }
}
