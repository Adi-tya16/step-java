package week1.practiceproblems;

public class StringManipulation {
    public static void main(String[] args) {
        String str1 = "Java Programming";   // literal
        String str2 = new String("Java Programming");   // constructor
        char[] chars = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(chars);    // char array

        System.out.println("== comparison: " + true);
        System.out.println("equals comparison: " + true);

        String quote = "Programming Quote:\n\t\"Code is poetry\" - Unknown\n\tPath: C:\\Java\\Projects";
        System.out.println(quote);
    }
}

