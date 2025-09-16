package week3.assignmentproblems;

public class PersonalFinanceManager {

    static class PersonalAccount {
        private String accountHolderName;
        private String accountNumber;
        private double currentBalance;
        private double totalIncome;
        private double totalExpenses;
        private static int totalAccounts = 0;
        private static String bankName = "Default Bank";
        private static int counter = 0;

        public PersonalAccount(String name, double initialDeposit) {
            this.accountHolderName = name;
            this.accountNumber = generateAccountNumber();
            this.currentBalance = initialDeposit;
            this.totalIncome = initialDeposit;
            this.totalExpenses = 0;
            totalAccounts++;
        }

        private static String generateAccountNumber() {
            counter++;
            return String.format("AC%03d", counter);
        }

        public void addIncome(double amount, String description) {
            if (amount > 0) {
                currentBalance += amount;
                totalIncome += amount;
                System.out.println(accountHolderName + " received income: " + amount + " (" + description + ")");
            }
        }

        public void addExpense(double amount, String description) {
            if (amount > 0 && amount <= currentBalance) {
                currentBalance -= amount;
                totalExpenses += amount;
                System.out.println(accountHolderName + " spent: " + amount + " (" + description + ")");
            } else {
                System.out.println("Expense transaction failed for " + accountHolderName);
            }
        }

        public double calculateSavings() {
            return totalIncome - totalExpenses;
        }

        public void displayAccountSummary() {
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Bank: " + bankName);
            System.out.println("Current Balance: " + currentBalance);
            System.out.println("Total Income: " + totalIncome);
            System.out.println("Total Expenses: " + totalExpenses);
            System.out.println("Savings: " + calculateSavings());
            System.out.println();
        }

        public static void setBankName(String name) {
            bankName = name;
        }

        public static int getTotalAccounts() {
            return totalAccounts;
        }
    }

    public static void main(String[] args) {
        PersonalAccount.setBankName("Future Finance Bank");

        PersonalAccount a1 = new PersonalAccount("Alice", 1000);
        PersonalAccount a2 = new PersonalAccount("Bob", 2000);
        PersonalAccount a3 = new PersonalAccount("Charlie", 500);

        a1.addIncome(500, "Salary");
        a1.addExpense(200, "Groceries");

        a2.addIncome(1000, "Freelance");
        a2.addExpense(1500, "Rent");

        a3.addIncome(300, "Gift");
        a3.addExpense(100, "Snacks");

        a1.displayAccountSummary();
        a2.displayAccountSummary();
        a3.displayAccountSummary();

        System.out.println("Total Accounts: " + PersonalAccount.getTotalAccounts());
    }
}

