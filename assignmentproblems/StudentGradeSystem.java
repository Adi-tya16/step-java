package week3.assignmentproblems;

import java.util.*;

public class StudentGradeSystem {

    static class Subject {
        private String subjectCode;
        private String subjectName;
        private int credits;
        private String instructor;

        public Subject(String code, String name, int credits, String instructor) {
            this.subjectCode = code;
            this.subjectName = name;
            this.credits = credits;
            this.instructor = instructor;
        }

        public String getSubjectCode() { return subjectCode; }
        public String getSubjectName() { return subjectName; }
        public int getCredits() { return credits; }
    }

    static class Student {
        private static int counter = 0;
        private static int totalStudents = 0;
        private static String schoolName = "Future Scholars School";
        private static String[] gradingScale = {"A:90-100","B:75-89","C:60-74","D:50-59","F:<50"};
        private static double passPercentage = 50.0;

        private String studentId;
        private String studentName;
        private String className;
        private String[] subjects;
        private double[][] marks;
        private double gpa;

        public Student(String name, String className, String[] subjects) {
            this.studentId = generateId();
            this.studentName = name;
            this.className = className;
            this.subjects = subjects;
            this.marks = new double[subjects.length][1];
            this.gpa = 0;
            totalStudents++;
        }

        private static String generateId() {
            counter++;
            return String.format("S%03d", counter);
        }

        public String getStudentName() { return studentName; }
        public String getClassName() { return className; }
        public double getGpa() { return gpa; }

        public void addMarks(String subject, double mark) {
            for (int i = 0; i < subjects.length; i++) {
                if (subjects[i].equals(subject)) {
                    marks[i][0] = mark;
                }
            }
        }

        public void calculateGPA() {
            double total = 0;
            for (int i = 0; i < subjects.length; i++) {
                total += marks[i][0];
            }
            double percentage = total / subjects.length;
            if (percentage >= 90) gpa = 4.0;
            else if (percentage >= 75) gpa = 3.5;
            else if (percentage >= 60) gpa = 3.0;
            else if (percentage >= 50) gpa = 2.0;
            else gpa = 0.0;
        }

        public void generateReportCard() {
            System.out.println("Report Card - " + studentName + " (" + studentId + ")");
            System.out.println("Class: " + className + " | School: " + schoolName);
            double total = 0;
            for (int i = 0; i < subjects.length; i++) {
                System.out.println(subjects[i] + ": " + marks[i][0]);
                total += marks[i][0];
            }
            double percentage = total / subjects.length;
            System.out.println("Percentage: " + percentage + "% | GPA: " + gpa + " | Grade: " + getGrade(percentage));
            System.out.println("Promotion Eligibility: " + (checkPromotionEligibility() ? "Yes" : "No"));
            System.out.println();
        }

        private String getGrade(double percentage) {
            if (percentage >= 90) return "A";
            else if (percentage >= 75) return "B";
            else if (percentage >= 60) return "C";
            else if (percentage >= 50) return "D";
            else return "F";
        }

        public boolean checkPromotionEligibility() {
            double total = 0;
            for (int i = 0; i < subjects.length; i++) {
                total += marks[i][0];
            }
            double percentage = total / subjects.length;
            return percentage >= passPercentage;
        }

        public static void setGradingScale(String[] scale) {
            gradingScale = scale;
        }

        public static void calculateClassAverage(Student[] students, String className) {
            double total = 0;
            int count = 0;
            for (Student s : students) {
                if (s != null && s.className.equals(className)) {
                    total += s.getGpa();
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("Class " + className + " Average GPA: " + (total / count));
            }
        }

        public static void getTopPerformers(Student[] students, int count) {
            Arrays.sort(students, (a, b) -> {
                if (a == null || b == null) return 0;
                return Double.compare(b.getGpa(), a.getGpa());
            });
            System.out.println("Top " + count + " Performers:");
            for (int i = 0; i < count && i < students.length; i++) {
                if (students[i] != null) {
                    System.out.println(students[i].studentName + " | GPA: " + students[i].getGpa());
                }
            }
        }

        public static void generateSchoolReport(Student[] students) {
            System.out.println("--- School Report for " + schoolName + " ---");
            System.out.println("Total Students: " + totalStudents);
            for (Student s : students) {
                if (s != null) s.generateReportCard();
            }
        }
    }

    public static void main(String[] args) {
        String[] subs = {"Math", "Science", "English"};
        Student[] students = new Student[5];

        students[0] = new Student("Alice", "10A", subs);
        students[1] = new Student("Bob", "10A", subs);
        students[2] = new Student("Charlie", "10B", subs);

        students[0].addMarks("Math", 95);
        students[0].addMarks("Science", 88);
        students[0].addMarks("English", 92);
        students[0].calculateGPA();

        students[1].addMarks("Math", 65);
        students[1].addMarks("Science", 70);
        students[1].addMarks("English", 68);
        students[1].calculateGPA();

        students[2].addMarks("Math", 45);
        students[2].addMarks("Science", 55);
        students[2].addMarks("English", 60);
        students[2].calculateGPA();

        Student.generateSchoolReport(students);
        Student.calculateClassAverage(students, "10A");
        Student.getTopPerformers(students, 2);
    }
}

