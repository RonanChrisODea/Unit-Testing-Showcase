package ie.ronanodea.unitTesting;

/**
 * This program simulates a simple banking application that manages user accounts.
 * <p>
 * It provides functionalities to:
 * <ul>
 *  <li>Add new accounts with an initial deposit.</li>
 *  <li>Deposit and withdraw money from accounts.</li>
 *  <li>Approve and repay loans for account holders.</li>
 *  <li>Track the total deposits available in the bank.</li>
 * </ul>
 * <p>
 * The application uses a list of {@link Account} objects to manage account data.
 * <p>
 * <b>Note:</b> There is currently a known mismatch between the total account
 * balances and the total deposits. This is because loan approvals are not
 * deposited into the account holder's account. Further investigation into
 * client requirements or industry regulations would be needed to address this.
 * <p>
 * <b>Refactoring:</b> This code has been refactored to simplify the testing
 * process. The original code has been split into three classes for improved
 * readability and maintainability: BankingApp, Account and AccountManager.
 *
 * @see Account
 * @see AccountManager
 */
public class BankingApp {

    /**
     * Constructor for BankingApp.
     */
    public BankingApp() {
        // No initialization needed.
    }

    /**
     * Banking Apps main method
     * @param args none provided
     */
    public static void main(String[] args) {
        // Create a new banking application instance
        AccountManager manageAcc = new AccountManager();

        // Add accounts
        manageAcc.addAccount("Alice", 1000);
        manageAcc.addAccount("Bob", 500);

        // Test deposits
        System.out.println("Depositing 200 to Alice: " + manageAcc.deposit("Alice", 200)); // Should return true
        System.out.println("Alice's balance: " + manageAcc.getBalance("Alice")); // Should be 1200

        // Test withdrawals
        System.out.println("Withdrawing 300 from Bob: " + manageAcc.withdraw("Bob", 300)); // Should return true
        System.out.println("Bob's balance: " + manageAcc.getBalance("Bob")); // Should be 200

        // Test loan approval
        System.out.println("Approving a loan of 400 for Alice: " + manageAcc.approveLoan("Alice", 400)); // Should return true
        System.out.println("Alice's loan: " + manageAcc.getLoan("Alice")); // Should be 400

        // Test loan repayment
        System.out.println("Repaying 200 of Alice's loan: " + manageAcc.repayLoan("Alice", 200)); // Should return true
        System.out.println("Alice's remaining loan: " + manageAcc.getLoan("Alice")); // Should be 200

        System.out.println("Total account balances: " + (manageAcc.getBalance("Alice") + manageAcc.getBalance("Bob")));

        // Check total deposits in the manageAcc instance
        System.out.println("Total deposits in the bank: " + manageAcc.getTotalDeposits());
    }
}
