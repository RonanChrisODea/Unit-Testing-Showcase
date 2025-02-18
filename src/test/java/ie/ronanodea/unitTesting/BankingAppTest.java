package test.java.ie.ronanodea.unitTesting;

import org.junit.jupiter.api.Test;

import main.java.ie.ronanodea.unitTesting.BankingApp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


/**
 * This test class provides a placeholder test to provide coverage for the default banking application 
 * runner. This isn't really required as testing isn't usually applied to main runner methods. 
 * 
 * @see Account
 * @see AccountTest
 * @see AccountManager
 * @see AccountManagerTest
 * @see BankingApp
 * @see BankingAppTest
 * @see BankingAppTestRunner 
 */

class BankingAppTest {
    
    @Test
    void testMainMethodExecution() {
        assertDoesNotThrow(() -> {
            BankingApp.main(new String[]{});
        });
    }
}
