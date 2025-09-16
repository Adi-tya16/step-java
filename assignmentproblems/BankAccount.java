package week4.assignmentproblems;

 public class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    BankAccount() {
        this.accountHolder = "NA";
        this.accountNumber = 0;
        this.balance = 0;
    }

    BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = (int)(Math.random() * 10000);
        this.balance = 0;
    }

    BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = (int)(Math.random() * 10000);
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    void displayAccount() {
        System.out.println("Holder: " + accountHolder + ", Account No: " + accountNumber +
                ", Balance: " + balance);
    }

    public static void main(String[] args) {
        BankAccount b1 = new BankAccount();
        BankAccount b2 = new BankAccount("Alice");
        BankAccount b3 = new BankAccount("Bob", 5000);

        b2.deposit(2000);
        b3.withdraw(1000);

        b1.displayAccount();
        b2.displayAccount();
        b3.displayAccount();
    }
}

