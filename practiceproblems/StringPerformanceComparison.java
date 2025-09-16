package week2.practiceproblems;

public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        long start = System.nanoTime();
        concatenateWithString(1000);
        long end = System.nanoTime();
        System.out.println("String time: " + (end - start) + " ns");

        start = System.nanoTime();
        concatenateWithStringBuilder(1000);
        end = System.nanoTime();
        System.out.println("StringBuilder time: " + (end - start) + " ns");

        start = System.nanoTime();
        concatenateWithStringBuffer(1000);
        end = System.nanoTime();
        System.out.println("StringBuffer time: " + (end - start) + " ns");

        demonstrateStringBuilderMethods();
        compareStringComparisonMethods();
    }

    public static String concatenateWithString(int n) {
        String s = "";
        for (int i = 0; i < n; i++) s += "Java " + i + " ";
        return s;
    }

    public static String concatenateWithStringBuilder(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("Java ").append(i).append(" ");
        return sb.toString();
    }

    public static String concatenateWithStringBuffer(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("Java ").append(i).append(" ");
        return sb.toString();
    }

    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        sb.append(" Java");
        sb.insert(5, " Amazing");
        sb.delete(6, 13);
        sb.deleteCharAt(0);
        sb.reverse();
        sb.replace(0, 3, "Hi");
        sb.setCharAt(0, 'X');
        System.out.println("Builder Result: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(50);
        sb.trimToSize();
    }

    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        System.out.println("== : " + (str1 == str2));
        System.out.println("== with new: " + (str1 == str3));
        System.out.println("equals: " + str1.equals(str3));
        System.out.println("equalsIgnoreCase: " + str1.equalsIgnoreCase("hello"));
        System.out.println("compareTo: " + str1.compareTo(str3));
        System.out.println("compareToIgnoreCase: " + str1.compareToIgnoreCase("hello"));
    }
}

