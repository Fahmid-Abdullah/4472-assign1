package atm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;

import atm.dispatcher.MessageDispatcher;
import atm.exceptions.InvalidCredentialsException;
import atm.ui.panels.MainPanel;
import atm.utils.CredentialsCheck;
import atm.utils.FormatChecker;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ATMTest {
	ATM atm;
	MessageDispatcher dispatcher;
	

	@BeforeEach
	public void setUp() throws Exception {
		dispatcher = Mockito.mock(MessageDispatcher.class);
		FormatChecker formatCheck = new FormatChecker();
		CredentialsCheck credentialsCheck = new CredentialsCheck(dispatcher);
		atm = new ATM(formatCheck, credentialsCheck, dispatcher);
		
		MainPanel mainPanel = Mockito.mock(MainPanel.class);
		atm.setMainPanel(mainPanel);
		atm.createSession();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void checkCorrectPINTest() {
		Mockito.when(dispatcher.checkCredentials(null, new char[] {'5','5','5','5'})).thenReturn(true);
		Assertions.assertDoesNotThrow(() -> atm.checkPin(new char[] {'5','5','5','5'}));
	}
	
	@Test
	public void checkIncorrectPINTest() {
		Mockito.when(dispatcher.checkCredentials(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(false);
		Assertions.assertThrows(InvalidCredentialsException.class, () -> atm.checkPin(new char[] {'5','5','5','4'}));
	}

	// More PIN Format Tests (Robust Worst Case BVA)
	@Test 
	public void testValidPin(){
		Assertions.assertDoesNotThrow(() -> atm.checkPin (new char[] {'1','2','3','4','5'}));
	}

	@Test
	public void testShorPin() {
		Assertions.assertThrows(Exception.class, () -> atm.checkPin(new char[] {'1','2','3','4'}));
	}

	@Test
	public void testLongPin() {
		Assertions.assertThrows(Exception.class, () -> atm.checkPin(new char[] {'1','2','3','4','5','6'}));
	}

	@Test
	public void testPinWithLetters() {
		Assertions.assertThrows(Exception.class, () -> atm.checkPin(new char[] {'1','2','a','4','5'}));
	}

	@Test
	public void testPinWithSymbols() {
		Assertions.assertThrows(Exception.class, () -> atm.checkPin(new char[] {'1','2','@','4','5'}));
	}

	@Test
	public void testEmptyPin() {
		Assertions.assertThrows(Exception.class, () -> atm.checkPin(new char[] {}));
	}


	// ===== Withdrawal Amount Tests (Robust Worst Case BVA) =====
	atm.setTransaction(TransactionType.Withdrawal);

	
	@Test
	public void testValidWithdrawal20() {
		Assertions.assertDoesNotThrow(() -> atm.setAmount(20));
	}

	@Test
	public void testValidWithdrawal50() {
		Assertions.assertDoesNotThrow(() -> atm.setAmount(50));
	}

	@Test
	public void testZeroWithdrawal() {
		Assertions.assertThrows(Exception.class, () -> atm.setAmount(0));
	}

	@Test
	public void testMaxValidWithdrawal() 
		Assertions.assertDoesNotThrow(() -> atm.setAmount(1000));
	}

	@Test
	public void testAboveDailyLimitWithdrawal() {
		Assertions.assertThrows(Exception.class, () -> atm.setAmount(1020));
	}

	@Test
	public void testInvalidWithdrawal25() {
		Assertions.assertThrows(Exception.class, () -> atm.setAmount(25));
	}

	@Test
	public void testNegativeWithdrawal() {
		Assertions.assertThrows(Exception.class, () -> atm.setAmount(-20));
	}

	@Test
	public void testExcessiveWithdrawal5000() {
		Assertions.assertThrows(Exception.class, () -> atm.setAmount(5000));
	}

	@Test
	public void testValidWithdrawal40() {
		Assertions.assertDoesNotThrow(() -> atm.withdraw(40));
	}

	@Test
	public void testInvalidWithdrawal75() {
		Assertions.assertThrows(Exception.class, () -> atm.withdraw(75));
	}
}



