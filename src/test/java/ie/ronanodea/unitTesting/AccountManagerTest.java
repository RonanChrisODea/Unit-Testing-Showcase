package test.java.ie.ronanodea.unitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.java.ie.ronanodea.unitTesting.AccountManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * This test class provides methods to ascertain if the methods written within the 
 * account manager class are functioning as expected. 
 * 
 * <b> @BeforeAll, @BeforeEach, @AfterEach, @AfterAll</b> annotated methods are all 
 * demonstrated here. 
 * 
 * Parameterised testing is also employed where appropriate, such as in the method to test
 * various cases for deposits. 
 * 
 * @see Account
 * @see AccountTest
 * @see AccountManager
 * @see AccountManagerTest
 * @see BankingApp
 * @see BankingAppTest
 * @see BankingAppTestRunner 
 */



class AccountManagerTest {
	 private AccountManager accountManager; 
	 private static int testCount;

	 /**
	  * Initialises counter and indicates start of AcountManagerTests once.
	  */
	 @BeforeAll
	 static void initAll() {
		 System.out.println("=== Starting AccountManager Tests ===");
		 testCount = 0;
	 }

	 /**
	  * New instance of manager class before each test
	  * Increments counter before each test.
	  */
	 @BeforeEach
	 void setUp() {
		 accountManager = new AccountManager(); // Create a new instance before each test
		 testCount++;
		 System.out.println("Starting Test #" + testCount + "...");
	 }

	 
	 /**
	  * Prints which test has completed after each test.
	  */
	 @AfterEach
	 void tearDown() {
		 System.out.println("Account Manager Test #" + testCount + " finished.\n");
	 }

	 /**
	  * Indicates test finalisation and total count once after all tests have run.
	  */
	 @AfterAll
	 static void tearDownAll() {
		 System.out.println("=== All AccountManager Tests Completed ===");
		 System.out.println("Total tests run: " + testCount);
	 }

	/**
	 *  Verify if account creation method behaves as expected. 
	 *  Determine if Initial deposit not null and method
	 *  return's provided initial deposit when account holder provided.
	 */
    @Test
    void addAccountValid() {
        accountManager.addAccount("Alice", 1000);
        assertNotNull(accountManager.getBalance("Alice"));
        assertEquals(1000, accountManager.getBalance("Alice"));
    }

    /**
     * Determines if correct exception is thrown when provided with 0 initial deposit. 
     */
    @Test
    void addAccountZeroInitialDeposit() {
        assertThrows(IllegalArgumentException.class, () -> accountManager.addAccount("Bob", 0));
    }
    
    /**
     * Determines if correct exception is thrown when provided with negative initial deposit.
     */
    @Test
    void addAccountNegativeInitialDeposit() {
        assertThrows(IllegalArgumentException.class, () -> accountManager.addAccount("Alice", -100));
    }

    /**
     * Parameterised test for various cases.
     * Determines if exception is thrown for 0 or negative amounts, 
     * Determine if correct balance is returned for valid deposit amounts.
     * 
     * 
     * @param amount // deposit amounts
     * @param expectedResult // the expected outcome of tests, determines conditional below
     */
    @ParameterizedTest
    @CsvSource({
            "0, IllegalArgumentException", // zero deposit
            "-50, IllegalArgumentException", //negative deposit
            "500, valid", // valid deposit
            "1000, valid" // valid large deposit
    })
    void depositEdgeCases(double amount, String expectedResult) {
        accountManager.addAccount("Alice", 1000); // Ensure account exists
        if ("IllegalArgumentException".equals(expectedResult)) {
            assertThrows(IllegalArgumentException.class, () -> accountManager.deposit("Alice", amount));
        } else {
            assertTrue(accountManager.deposit("Alice", amount));
            assertEquals(1000 + amount, accountManager.getBalance("Alice"));
            assertEquals(1000 + amount, accountManager.getTotalDeposits());
        }
    }

    /**
     * Determines if nonexistent accounts trigger expected behaviour for deposits.
     */
    @Test
    void depositAccountNotFound() {
        assertFalse(accountManager.deposit("Bob", 500));
        assertEquals(0, accountManager.getTotalDeposits());
    }

    /**
     * Determines if valid withdrawals return the expected account and total deposit
     * balances
     */
    @Test
    void withdrawValid() {
        accountManager.addAccount("Alice", 1000);
        assertTrue(accountManager.withdraw("Alice", 200));
        assertEquals(800, accountManager.getBalance("Alice"));
        assertEquals(800, accountManager.getTotalDeposits());
    }
    
    /**
     * Determines if behaviour is as expected if withdrawal greater than the account balance.
     * Balances remain unchanged and false returned for withdrawal method. 
     */
    @Test
    void withdrawInsufficientFunds() {
        accountManager.addAccount("Alice", 1000);
        assertFalse(accountManager.withdraw("Alice", 1500)); 
        assertEquals(1000, accountManager.getBalance("Alice"));
        assertEquals(1000, accountManager.getTotalDeposits());
    }

    /**
     * Determines if behaviour is as expected if  negative withdrawal initiated.
     * Balances remain unchanged and exception thrown for withdrawal method. 
     */
    @Test
    void withdrawNegativeAmount() {
        accountManager.addAccount("Alice", 1000);
        assertThrows(IllegalArgumentException.class, () -> accountManager.withdraw("Alice", -200));
        assertEquals(1000, accountManager.getBalance("Alice"));
        assertEquals(1000, accountManager.getTotalDeposits());
    }

    /**
     * Determines if nonexistent accounts trigger expected behaviour for withdrawals.
     */
    @Test
    void withdrawAccountNotFound() {
        assertFalse(accountManager.withdraw("Bob", 200));
        assertEquals(0, accountManager.getTotalDeposits());
    }

    /**
     * Determine if approving loan results in expected behaviour. Total deposits currently decrease
     * and loan is not directly deposited to account currently.
     */
    @Test
    void approveLoanValid() {
        accountManager.addAccount("Alice", 1000);
        assertTrue(accountManager.approveLoan("Alice", 200));
        assertEquals(200, accountManager.getLoan("Alice"));
        assertEquals(800, accountManager.getTotalDeposits()); // Loan approved, so total deposits decrease
    }

    /**
     * Determines if nonexistent accounts trigger expected behaviour for loans.
     */
    @Test
    void approveLoanAccountNotFound() {
        assertFalse(accountManager.approveLoan("Bob", 200));
        assertEquals(0, accountManager.getTotalDeposits());
    }
    
    /**
     * Determines if current logic is implemented correctly 
     * When a loan amount exceeds available deposits it is not approved and no loan is issued
     * Total Deposits don't change.
     */
    @Test
    void approveLoanInsufficientTotalDeposits() {
        accountManager.addAccount("Alice", 1000);
        assertFalse(accountManager.approveLoan("Alice", 1200)); // Total deposits are only 1000
        assertEquals(0, accountManager.getLoan("Alice")); 
        assertEquals(1000, accountManager.getTotalDeposits());
    }

    /**
     * Determines if behaviour is as expected if  negative loan initiated.
     * Balances remain unchanged and exception thrown for approve loan method. 
     */
    @Test
    void approveLoanNegativeAmount() {
        accountManager.addAccount("Alice", 1000);
        assertThrows(IllegalArgumentException.class, () -> accountManager.approveLoan("Alice", -200));
        assertEquals(0, accountManager.getLoan("Alice")); 
        assertEquals(1000, accountManager.getTotalDeposits());
    }
    
    /**
     * Determines if loan repayments method works as expected. 
     * Loan amount reduces by payment amount.
     * Total deposits available increase by loan amount. 
     */
    @Test
    void repayLoanValid() {
        accountManager.addAccount("Alice", 1000);
        accountManager.approveLoan("Alice", 500);
        assertTrue(accountManager.repayLoan("Alice", 200));
        assertEquals(300, accountManager.getLoan("Alice"));
        assertEquals(700, accountManager.getTotalDeposits()); // Loan repaid, so total deposits increase
    }
    
    /**
     * Determines if nonexistent accounts trigger expected behaviour for loans.
     */
    @Test
    void repayLoanAccountNotFound() {
        assertFalse(accountManager.repayLoan("Bob", 200));
        assertEquals(0, accountManager.getTotalDeposits());
    }
    
    
    /**
     * DEtermines that repaying a loan with an amount exceeding the current loan value is unsuccessful.
     * The loan balance should remain unchanged, and the total deposits should not be affected.
     */
   
    @Test
    void repayLoanExceedsLoanAmount() {
        accountManager.addAccount("Alice", 1000);
        accountManager.approveLoan("Alice", 200);
        assertFalse(accountManager.repayLoan("Alice", 300)); // Trying to repay more than the loan amount
        assertEquals(200, accountManager.getLoan("Alice"));
        assertEquals(800, accountManager.getTotalDeposits());
    }
    
    
    /**
     * Determines that attempting to repay a loan with a negative amount throws an IllegalArgumentException.
     * The loan balance and total deposits should remain unchanged when an invalid repayments attempted.
     */
    @Test
    void repayLoanNegativeAmount() {
        accountManager.addAccount("Alice", 1000);
        assertThrows(IllegalArgumentException.class, () -> accountManager.repayLoan("Alice", -100));
        assertEquals(0, accountManager.getLoan("Alice"));
        assertEquals(1000, accountManager.getTotalDeposits());
    }

    /**
     * Determines if default accountManager state is correct; 0 in total deposits
     */
    @Test
    void getTotalDepositsNoAccounts() {
    	System.out.println(accountManager.getTotalDeposits());
        assertEquals(0, accountManager.getTotalDeposits());
    }

    /**
     * Determines if populated accountManager state is correct; correct amount in total deposits
     */
    @Test
    void getTotalDepositsWithAccounts() {
        accountManager.addAccount("Alice", 1000);
        accountManager.addAccount("Bob", 500);
        assertEquals(1500, accountManager.getTotalDeposits());
    }

    /**
     * Determines if correct balance returned for account when get balance method called
     */
    @Test
    void getBalanceValid() {
        accountManager.addAccount("Alice", 1000);
        assertEquals(1000, accountManager.getBalance("Alice"));
    }
    
    /**
     * Determines if nonexistent accounts trigger expected behaviour for get balance method.
     */
    @Test
    void getBalanceAccountNotFound() {
        assertNull(accountManager.getBalance("Bob"));
    }
    
    /**
     * Determines that getLoan returns the expected amount.
     */
    @Test
    void getLoanValid() {
        accountManager.addAccount("Alice", 1000);
        accountManager.approveLoan("Alice", 200);
        assertEquals(200, accountManager.getLoan("Alice"));
    }

    /**
     * Determines if nonexistent accounts trigger expected behaviour for get loan method.
     */
    @Test
    void getLoanAccountNotFound() {
        assertNull(accountManager.getLoan("Bob"));
    }
}