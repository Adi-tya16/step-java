package week2.assignmentproblems;

import java.util.*;

public class PasswordStrength {

    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (char ch : password.toCharArray()) {
            int ascii = (int) ch;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digit++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    public static int calculateScore(String password, int[] counts) {
        int score = 0;
        if (password.length() > 8) score += (password.length() - 8) * 2;
        if (counts[0] > 0) score += 10;
        if (counts[1] > 0) score += 10;
        if (counts[2] > 0) score += 10;
        if (counts[3] > 0) score += 10;
        String lowerPass = password.toLowerCase();
        String[] weakPatterns = {"123", "abc", "qwerty", "password", "111"};
        for (String pattern : weakPatterns) if (lowerPass.contains(pattern)) score -= 10;
        return Math.max(score, 0);
    }

    public static String getStrengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    public static String generatePassword(int length) {
        if (length < 4) length = 8;
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specials = "!@#$%^&*()-_=+<>?";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(specials.charAt(rand.nextInt(specials.length())));
        String allChars = upper + lower + digits + specials;
        for (int i = 4; i < length; i++) sb.append(allChars.charAt(rand.nextInt(allChars.length())));
        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder finalPass = new StringBuilder();
        for (char c : chars) finalPass.append(c);
        return finalPass.toString();
    }

    public static void displayResults(List<String> passwords) {
        System.out.printf("%-15s %-6s %-8s %-10s %-7s %-13s %-6s %-10s%n",
                "Password", "Len", "Upper", "Lower", "Digits", "SpecialChars", "Score", "Strength");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (String pass : passwords) {
            int[] counts = analyzePassword(pass);
            int score = calculateScore(pass, counts);
            String strength = getStrengthLevel(score);
            System.out.printf("%-15s %-6d %-8d %-10d %-7d %-13d %-6d %-10s%n",
                    pass, pass.length(), counts[0], counts[1], counts[2], counts[3], score, strength);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of passwords to analyze: ");
        int n = sc.nextInt();
        sc.nextLine();
        List<String> passwords = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            passwords.add(sc.nextLine());
        }
        displayResults(passwords);
        System.out.print("\nEnter desired length for new strong password: ");
        int length = sc.nextInt();
        String strongPassword = generatePassword(length);
        System.out.println("Generated Strong Password: " + strongPassword);
    }
}

