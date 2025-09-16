package week3.labproblems;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int counter = 0;

    public BankAccount(String name, double initialDeposit) {
        this.accountHolderName = name;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }

    public double checkBalance() {
        return balance;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        counter++;
        return String.format("ACC%03d", counter);
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new BankAccount("Alice", 1000);
        accounts[1] = new BankAccount("Bob", 2000);
        accounts[2] = new BankAccount("Charlie", 500);

        accounts[0].deposit(500);
        accounts[1].withdraw(300);
        accounts[2].deposit(200);

        for (BankAccount acc : accounts) {
            acc.displayAccountInfo();
            System.out.println();
        }

        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
    }
}

