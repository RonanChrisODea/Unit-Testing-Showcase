package ie.ronanodea.unitTesting;

/**
 * Represents a single bank account with an account holder name, balance, and loan amount.
 * <p>
 * This class provides methods for basic account operations such as depositing, withdrawing,
 * approving loans, and repaying loans.
 * <p>
 * <b>Refactoring Notes:</b> This class was extracted from a larger banking application
 * to improve code organisation, readability, and testability. Error handling has been
 * implemented using {@link IllegalArgumentException} to ensure valid input values. 
 * A {@link NullPointerException} was also implemented to demonstrate prohibiting null values
 * for certain fields. 
 * 
 * @see Account
 * @see AccountTest
 * @see AccountManager
 * @see AccountManagerTest
 * @see BankingApp
 * @see BankingAppTest
 * @see BankingAppTestRunner 
 */


public class Account {

	    // Represents a single bank account with account holder name, balance, and loan amount
        private String accountHolder; // Name of the account holder
        private double balance;       // Current account balance
        private double loan;          // Outstanding loan amount

        
       /**
        * Initial constructor, creates an individual account.
        * 
        * 
        * @param accountHolder Account holder's name (can't be null).
        * @param initDeposit Initial Deposit (can't be negative).
        * @throws IllegalArgumentException For negative deposit.
        * @throws NullPointerException For Null account Holder name.
        */
        public Account(String accountHolder, double initDeposit) throws IllegalArgumentException, NullPointerException {
            // New accounts are not automatically in a negative balance(error check)
        	if(initDeposit < 0) {
        		throw new IllegalArgumentException("Initial deposit can't be negative.");
        	}
        	if(accountHolder == null) {
        		throw new NullPointerException("Name cannot be null");
        	}
        	   
        	   
        	this.accountHolder = accountHolder;
            this.balance = initDeposit;
            this.loan = 0; // new accounts start with no loan balance. 
        }

        /**
         * Getter for the account holder's name
         * @return String accountHolder
         */
        public String getAccountHolder() {
            return accountHolder;
        }

        /**
         *  Getter for the account balance
         * @return double balance
         */
        public double getBalance() {
            return balance;
        }

        /**
         * Getter for the loan amount
         * @return double loan
         */
        public double getLoan() {
            return loan;
        }

       /**
        * Method to deposit money to account. Deposit can't be 0 or less. 
        * 
        * @param amount Deposit amount
        * @throws IllegalArgumentException Can't be 0 or less. 
        */
        public void deposit(double amount) throws IllegalArgumentException {
        	if (amount <=0) {
        		throw new IllegalArgumentException("Deposit amount must be positive");
        	}
            balance += amount;
        }

     
        /** Method to withdraw money from account. 
         * 
         * @param amount Withdrawal amount
         * @return Returns a boolean, allows manager class to perform withdrawal
         * @throws IllegalArgumentException
         */
        public boolean withdraw(double amount) throws IllegalArgumentException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive");
            }
            if (amount > balance) {
                return false; // Insufficient funds
            }
            balance -= amount;
            return true;
        }

        
        
       /** 
        * Method to approve a loan. Loan Amount must be positive.
        * 
        * @param amount Loan amount
        * @throws IllegalArgumentException Loan amount must be positive
        */
        public void approveLoan(double amount) throws IllegalArgumentException {
        	if( amount <=0) {
        		throw new IllegalArgumentException("Loan amount must be positive");
        	}
            loan += amount;
        }

     
        /** 
         * Method to facilitate loan repayments. Repayments must be positive.
         * 
         * @param amount Re-payment amount
         * @return Boolean to facilitate manager class repayments method. 
         * @throws IllegalArgumentException Repayments must be positive
         */
        public boolean repayLoan(double amount) throws IllegalArgumentException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Repayment must be positive");
            }
            if (amount > loan) {
                return false;  // Repayment exceeds loan amount
            }
            loan -= amount;
            return true;
        }
    }

