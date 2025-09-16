package week3.labproblems;

public class PayrollSystem {

    static class Employee {
        private String empId;
        private String empName;
        private String department;
        private double baseSalary;
        private String empType;
        private static int totalEmployees = 0;
        private static int counter = 0;

        public Employee(String name, String dept, double baseSalary, double bonus) {
            this.empId = generateId();
            this.empName = name;
            this.department = dept;
            this.baseSalary = baseSalary + bonus;
            this.empType = "Full-Time";
            totalEmployees++;
        }

        public Employee(String name, String dept, double hourlyRate, int hours) {
            this.empId = generateId();
            this.empName = name;
            this.department = dept;
            this.baseSalary = hourlyRate * hours;
            this.empType = "Part-Time";
            totalEmployees++;
        }

        public Employee(String name, String dept, double contractAmount) {
            this.empId = generateId();
            this.empName = name;
            this.department = dept;
            this.baseSalary = contractAmount;
            this.empType = "Contract";
            totalEmployees++;
        }

        private static String generateId() {
            counter++;
            return String.format("E%03d", counter);
        }

        public double calculateSalary() {
            return baseSalary;
        }

        public double calculateTax() {
            if (empType.equals("Full-Time")) return baseSalary * 0.2;
            else if (empType.equals("Part-Time")) return baseSalary * 0.1;
            else return baseSalary * 0.05;
        }

        public void generatePaySlip() {
            System.out.println("PaySlip for " + empName);
            System.out.println("ID: " + empId + ", Dept: " + department + ", Type: " + empType);
            System.out.println("Salary: " + calculateSalary());
            System.out.println("Tax: " + calculateTax());
            System.out.println("Net Pay: " + (calculateSalary() - calculateTax()));
            System.out.println();
        }

        public void displayEmployeeInfo() {
            System.out.println("ID: " + empId + ", Name: " + empName + ", Dept: " + department + ", Type: " + empType);
        }

        public static int getTotalEmployees() {
            return totalEmployees;
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Alice", "IT", 50000, 5000);
        Employee e2 = new Employee("Bob", "HR", 200, 80);
        Employee e3 = new Employee("Charlie", "Finance", 30000);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        e1.displayEmployeeInfo();
        e2.displayEmployeeInfo();
        e3.displayEmployeeInfo();

        System.out.println("\nTotal Employees: " + Employee.getTotalEmployees());
    }
}

