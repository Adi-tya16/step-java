package week2.assignmentproblems;

import java.util.*;

public class FileOrganize {

    static class FileInfo {
        String original;
        String name;
        String ext;
        String category;
        String newName;
        String subCategory;
        boolean valid;
    }

    static FileInfo extractFile(String filename) {
        FileInfo f = new FileInfo();
        f.original = filename;
        int dot = filename.lastIndexOf('.');
        if (dot == -1 || dot == 0 || dot == filename.length() - 1) {
            f.valid = false;
            return f;
        }
        f.name = filename.substring(0, dot);
        f.ext = filename.substring(dot).toLowerCase();
        f.valid = true;
        return f;
    }

    static void categorize(FileInfo f) {
        if (!f.valid) {
            f.category = "Invalid";
            return;
        }
        if (f.ext.equals(".txt") || f.ext.equals(".doc")) f.category = "Document";
        else if (f.ext.equals(".jpg") || f.ext.equals(".png")) f.category = "Image";
        else if (f.ext.equals(".mp3")) f.category = "Audio";
        else if (f.ext.equals(".mp4")) f.category = "Video";
        else if (f.ext.equals(".java") || f.ext.equals(".py")) f.category = "Code";
        else f.category = "Unknown";
    }

    static void generateNewName(FileInfo f, Map<String, Integer> counters) {
        if (!f.valid) {
            f.newName = "INVALID";
            return;
        }
        String base = f.category + "_" + f.name;
        int count = counters.getOrDefault(base, 0) + 1;
        counters.put(base, count);
        f.newName = base + (count > 1 ? "_" + count : "") + f.ext;
    }

    static void contentAnalysis(FileInfo f) {
        if (f.category.equals("Document")) {
            if (f.name.toLowerCase().contains("resume")) f.subCategory = "Resume";
            else if (f.name.toLowerCase().contains("report")) f.subCategory = "Report";
            else f.subCategory = "GeneralDoc";
        } else if (f.category.equals("Code")) {
            if (f.name.toLowerCase().contains("test")) f.subCategory = "TestCode";
            else f.subCategory = "SourceCode";
        } else {
            f.subCategory = "N/A";
        }
    }

    static void report(List<FileInfo> files) {
        System.out.println("\nFile Organization Report:");
        System.out.printf("%-20s %-12s %-20s %-15s\n", "Original", "Category", "New Name", "SubCategory");
        for (FileInfo f : files) {
            System.out.printf("%-20s %-12s %-20s %-15s\n", f.original, f.category, f.newName, f.subCategory);
        }

        Map<String, Integer> counts = new HashMap<>();
        for (FileInfo f : files) {
            counts.put(f.category, counts.getOrDefault(f.category, 0) + 1);
        }
        System.out.println("\nCategory Counts:");
        for (String c : counts.keySet()) {
            System.out.println(c + ": " + counts.get(c));
        }

        System.out.println("\nFiles Needing Attention:");
        for (FileInfo f : files) {
            if (!f.valid || f.category.equals("Unknown")) {
                System.out.println(f.original);
            }
        }
    }

    static void batchCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo f : files) {
            if (f.valid && !f.newName.equals("INVALID")) {
                System.out.println("rename " + f.original + " -> " + f.newName);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();
        System.out.println("Enter file names (empty line to stop):");
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            FileInfo f = extractFile(line);
            categorize(f);
            contentAnalysis(f);
            files.add(f);
        }
        Map<String, Integer> counters = new HashMap<>();
        for (FileInfo f : files) generateNewName(f, counters);
        report(files);
        batchCommands(files);
    }
}

