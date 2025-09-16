package week2.assignmentproblems;

import java.util.*;

public class CSVAnalyzer {

    static String[][] parseCSV(String input) {
        List<String[]> rows = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        int i = 0, n = input.length();
        boolean inQuotes = false;
        int start = 0;

        while (i < n) {
            char c = input.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(input.substring(start, i));
                start = i + 1;
            } else if ((c == '\n' || c == '\r') && !inQuotes) {
                fields.add(input.substring(start, i));
                rows.add(fields.toArray(new String[0]));
                fields.clear();
                start = i + 1;
            }
            i++;
        }
        if (start < n) fields.add(input.substring(start));
        if (!fields.isEmpty()) rows.add(fields.toArray(new String[0]));

        return rows.toArray(new String[0][]);
    }

    static String[][] cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    data[i][j] = data[i][j].trim().replaceAll("^\"|\"$", "");
                }
            }
        }
        return data;
    }

    static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        int dotCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int a = c;
            if (a == 46) { // '.'
                dotCount++;
                if (dotCount > 1) return false;
            } else if (!(a >= 48 && a <= 57)) return false;
        }
        return true;
    }

    static void analyzeData(String[][] data) {
        int cols = data[0].length;
        for (int c = 0; c < cols; c++) {
            boolean numeric = true;
            for (int r = 1; r < data.length; r++) {
                if (!isNumeric(data[r][c])) {
                    numeric = false;
                    break;
                }
            }
            if (numeric) {
                double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;
                int count = 0;
                for (int r = 1; r < data.length; r++) {
                    String val = data[r][c];
                    if (isNumeric(val)) {
                        double num = Double.parseDouble(val);
                        min = Math.min(min, num);
                        max = Math.max(max, num);
                        sum += num;
                        count++;
                    }
                }
                double avg = sum / count;
                System.out.printf("Column '%s' → Min: %.2f, Max: %.2f, Avg: %.2f%n",
                        data[0][c], min, max, avg);
            } else {
                Set<String> unique = new HashSet<>();
                for (int r = 1; r < data.length; r++) {
                    String val = data[r][c];
                    if (val != null && !val.isEmpty()) unique.add(val);
                }
                System.out.printf("Column '%s' → Unique values: %d%n",
                        data[0][c], unique.size());
            }
        }
    }

    static void formatTable(String[][] data) {
        int cols = data[0].length;
        int[] widths = new int[cols];
        for (int c = 0; c < cols; c++) {
            int maxLen = 0;
            for (String[] row : data) {
                if (c < row.length && row[c] != null) {
                    maxLen = Math.max(maxLen, row[c].length());
                }
            }
            widths[c] = maxLen + 2;
        }

        StringBuilder sb = new StringBuilder();
        for (String[] row : data) {
            for (int c = 0; c < cols; c++) {
                String val = (c < row.length) ? row[c] : "";
                sb.append(String.format("%-" + widths[c] + "s", val));
            }
            sb.append("\n");
        }
        System.out.println("\nFormatted Table:");
        System.out.println(sb.toString());
    }

    static void summaryReport(String[][] data) {
        int records = data.length - 1;
        int totalFields = 0, missing = 0;
        for (int r = 1; r < data.length; r++) {
            for (int c = 0; c < data[r].length; c++) {
                totalFields++;
                if (data[r][c] == null || data[r][c].isEmpty()) missing++;
            }
        }
        double completeness = ((totalFields - missing) / (double) totalFields) * 100;
        System.out.println("\nSummary Report:");
        System.out.println("Total Records: " + records);
        System.out.println("Missing Fields: " + missing);
        System.out.printf("Data Completeness: %.2f%%%n", completeness);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end input with empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            input.append(line).append("\n");
        }

        String[][] parsed = parseCSV(input.toString());
        String[][] cleaned = cleanData(parsed);

        formatTable(cleaned);
        analyzeData(cleaned);
        summaryReport(cleaned);
    }
}

