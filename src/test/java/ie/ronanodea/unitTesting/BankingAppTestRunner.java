package test.java.ie.ronanodea.unitTesting;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * This test class provides a runnable test suite for this application.  
 * 
 * @see Account
 * @see AccountTest
 * @see AccountManager
 * @see AccountManagerTest
 * @see BankingApp
 * @see BankingAppTest
 * @see BankingAppTestRunner 
 */
@Suite
@SelectClasses({AccountTest.class, AccountManagerTest.class, BankingAppTest.class})
public class BankingAppTestRunner {
   }