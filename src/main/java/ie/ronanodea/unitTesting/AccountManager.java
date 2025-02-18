package ie.ronanodea.unitTesting;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents core functionality and methods of banking application. 
 * <p>
 * This class had methods documented in the original application before refactoring;
 * This is a new section. The original documentation is unchanged. 
 * 
 * <p>
 * <b>Refactoring Notes:</b> This class was extracted from a larger banking application
 * to improve code organisation, readability, and testability. Error handling has been
 * implemented using {@link IllegalArgumentException} to ensure valid input values. 
 *  
 * @see Account
 * @see AccountTest
 * @see AccountManager
 * @see AccountManagerTest
 * @see BankingApp
 * @see BankingAppTest
 * @see BankingAppTestRunner 
 */



public class AccountManager {
	
	 // List to store all accounts in the banking application
    private List<Account> accounts;
    private double totalDeposits; // Tracks total deposits in the bank

    // Constructor to initialize the banking application
    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.totalDeposits = 0;
    }

    /**
     * Helper method to find an account by account holder's name.
     * @param accountHolder The name of the account holder.
     * @return The Account object if found, otherwise null.
     */
    private Account findAccount(String accountHolder) {
        for (Account account : accounts) {
            if (account.getAccountHolder().equals(accountHolder)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Adds a new account with an initial deposit.
     * @param accountHolder The name of the new account holder.
     * @param initialDeposit The initial deposit amount.
     * Initial Deposit must be positive:
     * @throws IllegalArgumentException if amount is negative
     */
    public void addAccount(String accountHolder, double initDeposit) throws IllegalArgumentException {
    	if (initDeposit <= 0) {
            throw new IllegalArgumentException("Initial deposit must be positive.");
        }
    	
    	accounts.add(new Account(accountHolder, initDeposit));
        totalDeposits += initDeposit;
    }

    /**
     * Deposits money into an account.
     * @param accountHolder The name of the account holder.
     * @param amount The deposit amount.
     * @return True if the deposit is successful, otherwise false.
     * @throws IllegalArgumentException if amount is negative
     */
    public boolean deposit(String accountHolder, double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        Account account = findAccount(accountHolder);
        if (account == null) return false;
        account.deposit(amount);
        totalDeposits += amount;
        return true;
    }

    /**
     * Withdraws money from an account.
     * @param accountHolder The name of the account holder.
     * @param amount The withdrawal amount.
     * @return True if the withdrawal is successful, otherwise false.
     * @throws IllegalArgumentException if amount is negative
     */
    public boolean withdraw(String accountHolder, double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative");
        }
        Account account = findAccount(accountHolder);
        if (account == null) return false;
        if (account.withdraw(amount)) {
            totalDeposits -= amount;
            return true;
        }
        return false;
    }

    /**
     * Approves a loan for an account holder.
     * @param accountHolder The name of the account holder.
     * @param loanAmount The loan amount.
     * @return True if the loan is approved, otherwise false.
     * @throws IllegalArgumentException if loan amount is negative
     */
    public boolean approveLoan(String accountHolder, double loanAmount) throws IllegalArgumentException {
        if (loanAmount < 0) {
            throw new IllegalArgumentException("Loan amount cannot be negative");
        }
        Account account = findAccount(accountHolder);
        if (account == null || loanAmount > totalDeposits) return false;
        account.approveLoan(loanAmount);
        totalDeposits -= loanAmount;
        return true;
    }

    /**
     * Repays a part of the loan for an account holder.
     * @param accountHolder The name of the account holder.
     * @param amount The repayment amount.
     * @return True if the repayment is successful, otherwise false.
     * @throws IllegalArgumentException if amount is negative
     */
    public boolean repayLoan(String accountHolder, double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Repayment amount cannot be negative");
        }
        Account account = findAccount(accountHolder);
        if (account == null) return false;
        if (account.repayLoan(amount)) {
            totalDeposits += amount;
            return true;
        }
        return false;
    }

    /**
     * Gets the total deposits available in the bank.
     * @return The total deposits.
     */
    public double getTotalDeposits() {
        return totalDeposits;
    }


    /**
     * Gets the balance of a specific account holder.
     * @param accountHolder The name of the account holder.
     * @return The balance if the account exists, otherwise null.
     */
    public Double getBalance(String accountHolder) {
        Account account = findAccount(accountHolder);
        return account != null ? account.getBalance() : null;
    }

    /**
     * Gets the loan amount of a specific account holder.
     * @param accountHolder The name of the account holder.
     * @return The loan amount if the account exists, otherwise null.
     */
    public Double getLoan(String accountHolder) {
        Account account = findAccount(accountHolder);
        return account != null ? account.getLoan() : null;
    }

}
