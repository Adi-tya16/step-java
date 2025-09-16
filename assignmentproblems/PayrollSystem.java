package week3.assignmentproblems;

import java.util.*;

public class PayrollSystem {

    static class Employee {
        private static int counter = 0;
        private static int totalEmployees = 0;
        private static String companyName = "TechCorp Pvt Ltd";
        private static double totalSalaryExpense = 0;
        private static int workingDaysPerMonth = 30;

        private String empId;
        private String empName;
        private String department;
        private String designation;
        private double baseSalary;
        private String joinDate;
        private boolean[] attendanceRecord;
        private String empType;

        public Employee(String name, String dept, String designation, double salary, String joinDate, String empType) {
            this.empId = generateId();
            this.empName = name;
            this.department = dept;
            this.designation = designation;
            this.baseSalary = salary;
            this.joinDate = joinDate;
            this.attendanceRecord = new boolean[workingDaysPerMonth];
            this.empType = empType;
            totalEmployees++;
        }

        private static String generateId() {
            counter++;
            return String.format("E%03d", counter);
        }

        public String getEmpName() { return empName; }
        public String getDepartment() { return department; }
        public double getBaseSalary() { return baseSalary; }
        public String getEmpType() { return empType; }

        public void markAttendance(int day, boolean present) {
            if (day >= 1 && day <= workingDaysPerMonth) {
                attendanceRecord[day - 1] = present;
            }
        }

        public int getPresentDays() {
            int count = 0;
            for (boolean b : attendanceRecord) if (b) count++;
            return count;
        }

        public double calculateSalary() {
            int presentDays = getPresentDays();
            double salary = 0;
            switch (empType) {
                case "FullTime":
                    salary = (baseSalary / workingDaysPerMonth) * presentDays;
                    break;
                case "PartTime":
                    salary = (baseSalary * presentDays); // baseSalary = per day wage
                    break;
                case "Contract":
                    salary = baseSalary; // fixed amount
                    break;
            }
            return salary + calculateBonus();
        }

        public double calculateBonus() {
            int presentDays = getPresentDays();
            double attendanceRate = (presentDays * 100.0) / workingDaysPerMonth;
            if (attendanceRate >= 90) return 0.1 * baseSalary;
            else if (attendanceRate >= 75) return 0.05 * baseSalary;
            else return 0;
        }

        public void requestLeave(int day) {
            if (day >= 1 && day <= workingDaysPerMonth && attendanceRecord[day - 1]) {
                attendanceRecord[day - 1] = false;
                System.out.println(empName + " requested leave on day " + day);
            }
        }

        public void generatePaySlip() {
            double salary = calculateSalary();
            totalSalaryExpense += salary;
            System.out.println("\n--- Pay Slip ---");
            System.out.println("Company: " + companyName);
            System.out.println("Employee: " + empName + " (" + empId + ")");
            System.out.println("Department: " + department + " | Designation: " + designation);
            System.out.println("Base Salary: " + baseSalary);
            System.out.println("Present Days: " + getPresentDays());
            System.out.println("Bonus: " + calculateBonus());
            System.out.println("Total Salary: " + salary);
        }

        public static void calculateCompanyPayroll(Employee[] employees) {
            double total = 0;
            for (Employee e : employees) {
                if (e != null) total += e.calculateSalary();
            }
            System.out.println("\nTotal Company Payroll Expense: " + total);
        }

        public static void getAttendanceReport(Employee[] employees) {
            System.out.println("\nAttendance Report:");
            for (Employee e : employees) {
                if (e != null) {
                    System.out.println(e.empName + " | Present Days: " + e.getPresentDays());
                }
            }
        }
    }

    static class Department {
        private static int counter = 0;

        private String deptId;
        private String deptName;
        private Employee manager;
        private Employee[] employees;
        private double budget;

        public Department(String name, Employee manager, double budget, int size) {
            this.deptId = generateId();
            this.deptName = name;
            this.manager = manager;
            this.budget = budget;
            this.employees = new Employee[size];
        }

        private static String generateId() {
            counter++;
            return String.format("D%02d", counter);
        }

        public void addEmployee(Employee emp, int index) {
            if (index < employees.length) employees[index] = emp;
        }

        public static void getDepartmentWiseExpenses(Department[] depts) {
            System.out.println("\nDepartment Wise Expenses:");
            for (Department d : depts) {
                if (d != null) {
                    double total = 0;
                    for (Employee e : d.employees) {
                        if (e != null) total += e.calculateSalary();
                    }
                    System.out.println(d.deptName + " | Total Expense: " + total);
                }
            }
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", "IT", "Developer", 60000, "01-01-2024", "FullTime");
        Employee e2 = new Employee("Bob", "IT", "Tester", 2000, "01-02-2024", "PartTime"); // per day wage
        Employee e3 = new Employee("Charlie", "HR", "Consultant", 40000, "01-03-2024", "Contract");

        for (int i = 1; i <= 28; i++) e1.markAttendance(i, true);
        for (int i = 1; i <= 20; i++) e2.markAttendance(i, true);
        for (int i = 1; i <= 30; i++) e3.markAttendance(i, true);

        Department d1 = new Department("IT", e1, 200000, 2);
        d1.addEmployee(e1, 0);
        d1.addEmployee(e2, 1);

        Department d2 = new Department("HR", e3, 100000, 1);
        d2.addEmployee(e3, 0);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        Employee.calculateCompanyPayroll(new Employee[]{e1, e2, e3});
        Employee.getAttendanceReport(new Employee[]{e1, e2, e3});
        Department.getDepartmentWiseExpenses(new Department[]{d1, d2});
    }
}
