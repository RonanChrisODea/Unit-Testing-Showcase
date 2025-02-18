package ie.ronanodea.unitTesting;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ie.ronanodea.unitTesting.Account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;


/**
 * This test class provides methods to ascertain if the methods written within the 
 * account class are functioning as expected. 
 * 
 * <b> @BeforeAll, @BeforeEach, @AfterEach, @AfterAll</b> annotated methods are all 
 * demonstrated here. 
 * 
 * Parameterised testing is also employed where appropriate, such as in the method to test
 * edge cases for deposits.
 * 
 * @see Account
 * @see AccountTest
 * @see AccountManager
 * @see AccountManagerTest
 * @see BankingApp
 * @see BankingAppTest
 * @see BankingAppTestRunner 
 */



class AccountTest {
	private static int testCount;  // Static test counter - belongs to class
    private Account account;
    
    
    /**
     *  Runs once on initialisation to declare start of tests and initialise a 
     *  static count variable - used to track the number of tests run. 
     */
    @BeforeAll
    static void initAll() {
        // Runs once before the very first test
        System.out.println("=== Starting Account Tests ===");
        testCount = 0; // Initialise counter
    }
    
    /**
     * Runs before each test creating a fresh test account to ensure tests run
     * as expected.
     * 
     * Counter incremented before each test.
     */
    @BeforeEach
    void setUp() {
        // Create a new Account object before each test - not using this for constructors
        account = new Account("Testy McTest", 100); // an initial deposit of 100
        System.out.println("Starting Test #" + testCount + "...");
        testCount++;
    }
    
    /**
     * Run after each method - Print indicating which number test has finished. 
     */
    @AfterEach
    void tearDown() {
        // Runs after every test method
        System.out.println("Account Test #" + testCount + " finished.\n");
    }

    
    /**
     * Runs once after all tests are complete. Declares this and gives final count.
     */
    @AfterAll
    static void tearDownAll() {
        // Runs once after all tests have finished
        System.out.println("=== All Account Tests Completed ===");
        System.out.println("Total tests run: " + testCount);
    }
    
    
    /**
     * Tests for valid account creation. 
     * Creates a new account within the method.
     * Determines if getAccountHolder() method returns correct account holder. 
     * Determines if getBalance() returns correct balance.
     * Determines that initial loan is 0.
     */
    @Test
    void accountCreationValid() {
    	Account constructAcc = new Account("Testerina Testify", 500);
    	
    	 assertEquals("Testerina Testify", constructAcc.getAccountHolder());
         assertEquals(500, constructAcc.getBalance());
         assertEquals(0, constructAcc.getLoan()); 
    }
    
    /**
     * Triggers the IllegalArguementException for negative initial deposits
     * Determines if exception is thrown as expected. 
     */
    @Test
    void accountCreationNegativeInitialDeposit() {
        // This test focuses on triggering the exception, so the default account is not used
        assertThrows(IllegalArgumentException.class, () -> new Account("Neggy Test", -100));
    }
    
    
    /**
     * Triggers the NullPointerException for Account Holders.
     * Determines if exception is thrown as expected. 
     */
    @Test
    void accountCreationNullAccountHolder() {
        assertThrows(NullPointerException.class, () -> new Account(null, 100));
    }
    
   
    /**
     * Determines if deposit method functions as expected
     * Initial balance + deposit = 150
     */
    @Test
    void deposit() {
        // This test uses the default account created in setUp()
        account.deposit(50);
        assertEquals(150, account.getBalance());
    }
    
    

    /**
     * Triggers the IllegalArgumentException when a negative deposit amount is presented.
     * Determines if exception is thrown as expected. 
     */
    @Test
    void depositNegative() {
    	assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
    }
    
    
    /**
     * Example of Parameterized Testing. 
     * Used to test edge/border cases of the deposit function.
     *      * 
     * @param amount Deposit Amount
     * @param expectedResult Expected test result, determines conditional logic
     */
    @ParameterizedTest
    @CsvSource({
        "0, IllegalArgumentException",   // Edge case: Zero deposit
        "-50, IllegalArgumentException", // Negative deposit
        "10000, valid"                  // Note the second parameter doesn't trigger the assert throw
        
    })
    void depositEdgeCases(double amount, String expectedResult) {
        if ("IllegalArgumentException".equals(expectedResult)) {
            assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
        } else {
            account.deposit(amount);
            assertEquals(100 + amount, account.getBalance()); // Balance after deposit
        }
    }
    
    // Tests for withdraw()
    
    /**
     * Determines if balance is as expected if sufficient funds exist to perform withdrawal.
     */
    @Test
    void withdrawSufficientFunds() {
        assertTrue(account.withdraw(50));
        assertEquals(50, account.getBalance()); // Verify balance after withdrawal
    }

    /**
     * Determines if balance is unchanged if insufficient funds exist to perform withdrawal.
     */
    @Test
    void withdrawInsufficientFunds() {
        assertFalse(account.withdraw(150)); // Try to withdraw more than the balance
        assertEquals(100, account.getBalance()); // Verify balance unchanged
    }

    /**
     * Triggers IllegalArguementException by withdrawing negative amount.
     * Determines if balance is unchanged.
     */
    @Test
    void withdrawNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50));
        assertEquals(100, account.getBalance()); // Verify balance unchanged
    }

    /**
     * Triggers IllegalArguementException by withdrawing zero amount.
     * Determines if balance is unchanged.
     */
    @Test
    void withdrawZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertEquals(100, account.getBalance());
    }
    
    

    // Tests for approveLoan()
    
    /**
     * Determines if method to approve a loan functions as expected by
     * returning the correct amount when getLoan() is called before any repayments 
     */
    @Test
    void approveLoanValid() {
        account.approveLoan(200);
        assertEquals(200, account.getLoan());
    }
    
    
    /**
     * Triggers IllegalArguementException by approving negative amount.
     * Determines if loan balance is unchanged.
     */
    @Test
    void approveLoanNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.approveLoan(-50));
        assertEquals(0, account.getLoan()); // Verify loan unchanged
    }
    
    
    /**
     * Triggers IllegalArguementException by approving 0 amount.
     * Determines if loan balance is unchanged.
     */
    @Test
    void approveLoanZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.approveLoan(0));
        assertEquals(0, account.getLoan()); // Verify loan unchanged
    }

    
    
    // Tests for repayLoan()
    
    /**
     * Determines if method to repay a loan functions as expected by
     * returning the correct amount when getLoan() after repayments.
     */
    @Test
    void repayLoanValid() {
        account.approveLoan(200); // Approve a loan first
        assertTrue(account.repayLoan(100));
        assertEquals(100, account.getLoan());
    }
    
    
    /**
     * Determines if method to repay a loan functions as expected by
     * unchanging the loan balance if a payment exceeds the remainder.
     */
    @Test
    void repayLoanExceedsLoanAmount() {
        account.approveLoan(100);
        assertFalse(account.repayLoan(200));
        assertEquals(100, account.getLoan()); // Verify loan unchanged
    }
    
    /**
     * Triggers IllegalArguementException by repaying a negative amount.
     * Determines if loan balance is unchanged.
     */
    @Test
    void repayLoanNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.repayLoan(-50));
        assertEquals(0, account.getLoan()); // Verify loan unchanged (no loan approved yet in this test)
    }
    
    /**
     * Triggers IllegalArguementException by repaying a 0 amount.
     * Determines if loan balance is unchanged.
     */
    @Test
    void repayLoanZeroAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.repayLoan(0));
        assertEquals(0, account.getLoan());
    }
}
