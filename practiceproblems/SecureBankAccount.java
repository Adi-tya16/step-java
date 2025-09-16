package week5.practiceproblems;

public class SecureBankAccount {
    // TODO: Create private fields that should NEVER be accessed directly:
    private String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    // TODO: Create private constants:
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // TODO: Create constructor that takes accountNumber and initial balance
    SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance >= MIN_BALANCE ? initialBalance : MIN_BALANCE;
        this.pin = 0;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    // TODO: Create PUBLIC methods for controlled access:
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot check balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // Security Methods
    public void setPin(int oldPin, int newPin) {
        if (this.pin == 0 || this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN updated successfully.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked.");
            return false;
        }
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            System.out.println("Invalid PIN.");
            return false;
        }
    }

    public void unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
            System.out.println("Account unlocked.");
        } else {
            System.out.println("Failed to unlock account.");
        }
    }

    // Transaction Methods
    public void deposit(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
            }
        }
    }

    public void withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                System.out.println("Withdrew: " + amount);
            } else {
                System.out.println("Insufficient funds.");
            }
        }
    }

    public void transfer(SecureBankAccount target, double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                target.balance += amount;
                System.out.println("Transferred: " + amount + " to " + target.getAccountNumber());
            } else {
                System.out.println("Transfer failed: insufficient funds.");
            }
        }
    }

    // TODO: Create private helper methods:
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed attempts.");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    public static void main(String[] args) {
        // TODO: Create two SecureBankAccount objects
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 5000);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 2000);

        // TODO: Try to access private fields directly (should fail)
        // acc1.balance = 10000;  // not allowed

        // TODO: Demonstrate proper usage through public methods:
        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);

        acc1.deposit(1000, 1234);
        acc1.withdraw(2000, 1234);

        // TODO: Show security features (account locking)
        acc1.validatePin(1111);
        acc1.validatePin(2222);
        acc1.validatePin(3333); // locks account
        acc1.deposit(500, 1234); // fails

        acc1.unlockAccount(1234);
        acc1.deposit(500, 1234);

        // TODO: Transfer money between accounts
        acc1.transfer(acc2, 1000, 1234);

        // TODO: Attempt security breaches:
        acc2.withdraw(5000, 5678); // more than balance
        acc2.validatePin(1111);
        acc2.validatePin(2222);
        acc2.validatePin(3333); // locks
        acc2.withdraw(100, 5678); // locked
    }
}
