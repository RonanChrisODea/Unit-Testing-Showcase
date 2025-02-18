package ie.ronanodea.unitTesting;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * This test class provides a runnable test suite for this application.  
 */
@Suite
@SelectClasses({AccountTest.class, AccountManagerTest.class, BankingAppTest.class})
public class BankingAppTestRunner {
   }
