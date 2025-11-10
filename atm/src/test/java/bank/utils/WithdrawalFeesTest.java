package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Use of Parameterized helps in this case, since multiple runs of same test are required
class WithdrawalFeesTest {
	FeesCalculator calculator = new FeesCalculator();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void withdrawalTest() {
		assertEquals(0.2, calculator.calculateWithdrawalFee(200, 1000, false, 0));		//pass
		//assertEquals(0.01, calculator.calculateWithdrawalFee(200, 1000, false, 0));	//fail
	}

	// Withdrawal Amount Tests, STUDENT (Robust Worst Case BVA)
	@Test
	void withdrawal_StudentWeekday(){
		//Testing if client is a student AND performed during the weekday, otherwise the fee is 0.1%
		assertEquals(0.0, calculator.calculateWithdrawalFee(500, 2000,true,0));
	}

	@Test
	void withdrawal_StudentWeekend(){
		// Student withdrawing on a weekend → 0.1% fee
		double expectedFee = 500 * 0.001;
		assertEquals(expectedFee, calculator.calculateWithdrawalFee(500, 2000,true,1));
	}

	// Withdrawal Amount Tests, NON-STUDENT (Robust Worst Case BVA)
	@Test
	void withdrawal_Below1000Balance(){
		// Balance just below $1,000, a fee of 0.3% is applied
		double expectedFee = 200 * 0.003;
		assertEquals(expectedFee, calculator.calculateWithdrawalFee(200, 999.99,false,0));

	}

	@Test
	void withdrawal_At1000Balance(){
		// Balance at $1,000, a fee of 0.1% is applied
		double expectedFee = 200 * 0.001;
		assertEquals(expectedFee, calculator.calculateWithdrawalFee(200, 1000,false,0));
	}

	@Test
	void withdrawal_Below5000Balance(){
		// Balance just below $5,000, a fee of 0.1% is applied
		double expectedFee = 200 * 0.001;
		assertEquals(expectedFee, calculator.calculateWithdrawalFee(200, 4999.99 ,false,0));
	}

	@Test
	void withdrawal_At5000Balance(){
		// Balance exactly $5,000 , no fee
		assertEquals(500, calculator.calculateWithdrawalFee(500, 5000,false,0));
	}

	@Test
	void withdrawal_Above5000Balance(){
		// Balance just above $5,000 , no fee
		assertEquals(500, calculator.calculateWithdrawalFee(500, 5000.01,false,0));
	}

	//Edge Cases
	@Test
	void withdrawal_NegativeAmount() {
		// Negative amount should ideally trigger a fault or return 0
		assertEquals(0.0, calculator.calculateWithdrawalFee(-100, 2000, false, 0));
	}

	@Test
	void withdrawal_ZeroAmount() {
		// Zero amount withdrawal, no fee
		assertEquals(0.0, calculator.calculateWithdrawalFee(0, 2000, false, 0));
	}

	@Test
	void withdrawalFee_NegativeBalance() {
		// Negative balance edge case, we're gonna store the result and see how it handles it.
		double result = calculator.calculateWithdrawalFee(200, -50, false, 0);
		assertTrue(result >= 0); // Should not crash or produce negative fee
	}

}
