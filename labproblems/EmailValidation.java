package week2.labproblems;

import java.util.*;

public class EmailValidation{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();

        System.out.println("Enter emails (type 'end' to stop):");
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("end")) break;
            emails.add(input);
        }

        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s%n",
                "Email", "Username", "Domain", "Domain Name", "Extension", "Status");

        int validCount = 0, invalidCount = 0;
        int totalUsernameLength = 0;

        for (String email : emails) {
            boolean valid = validateEmail(email);
            String username = "";
            String domain = "";
            String domainName = "";
            String extension = "";

            if (valid) {
                validCount++;
                String[] parts = extractComponents(email);
                username = parts[0];
                domain = parts[1];
                domainName = parts[2];
                extension = parts[3];
                totalUsernameLength += username.length();
            } else {
                invalidCount++;
            }

            System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s%n",
                    email, username, domain, domainName, extension, valid ? "Valid" : "Invalid");
        }

        double avgUsernameLength = validCount > 0 ? (double) totalUsernameLength / validCount : 0;
        System.out.println("\nTotal Valid: " + validCount);
        System.out.println("Total Invalid: " + invalidCount);
        System.out.println("Average Username Length: " + avgUsernameLength);
    }

    public static boolean validateEmail(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at == -1 || at != lastAt) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    public static String[] extractComponents(String email) {
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        String domainName = email.substring(at + 1, dot);
        String extension = email.substring(dot + 1);
        return new String[]{username, domain, domainName, extension};
    }
}
