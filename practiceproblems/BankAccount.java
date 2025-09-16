package week3.practiceproblems;


public class BankAccount {
    static String bankName;
    static int totalAccounts;
    static double interestRate;

    String accountNumber;
    String accountHolder;
    double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++;
    }

    public static void setBankName(String name) { bankName = name; }
    public static void setInterestRate(double rate) { interestRate = rate; }
    public static int getTotalAccounts() { return totalAccounts; }
    public static void displayBankInfo() {
        System.out.println("Bank: " + bankName + " Accounts: " + totalAccounts + " Interest: " + interestRate);
    }

    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { if (balance >= amount) balance -= amount; }
    public double calculateInterest() { return balance * interestRate / 100; }
    public void displayAccountInfo() {
        System.out.println(accountNumber + " " + accountHolder + " Balance: " + balance);
    }

    public static void main(String[] args) {
        setBankName("Global Bank");
        setInterestRate(5);
        BankAccount a1 = new BankAccount("A001", "Alice", 1000);
        BankAccount a2 = new BankAccount("A002", "Bob", 2000);
        a1.deposit(500);
        a2.withdraw(300);
        a1.displayAccountInfo();
        a2.displayAccountInfo();
        displayBankInfo();
    }
}

