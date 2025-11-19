package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;


// Use of Parameterized helps in this case, since multiple runs of same test are required
class FeesCalculatorTest {
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

	// ============================
	// WITHDRAWAL TESTS (SLICING)
	// ============================

	// ---- STUDENT CASES ----

	@Test
	void studentWeekendWithdrawal() {
		double fee = calculator.calculateWithdrawalFee(
				1000,            // amount
				5000,           // balance
				true,           // student
				Calendar.SATURDAY
		);
		assertEquals(0.001, fee);
	}

	@Test
	void studentWeekendWithdrawal2() {
		double fee = calculator.calculateWithdrawalFee(
				1000,            // amount
				5000,           // balance
				true,           // student
				Calendar.SUNDAY
		);
		assertEquals(0.001, fee);
	}

	@Test
	void studentWeekdayWithdrawal() {
		double fee = calculator.calculateWithdrawalFee(
				1000,
				5000,
				true,
				Calendar.WEDNESDAY
		);
		assertEquals(0.0, fee); // 1000 * 0.001
	}

	// ---- NON-STUDENT CASES ----

	@Test
	void nonStudent_BalanceUnder1000() {
		double fee = calculator.calculateWithdrawalFee(
				1000,
				900,           // balance < 1000
				false,
				Calendar.SATURDAY
		);
		assertEquals(0.003, fee); // 1000 * 0.003
	}

	@Test
	void nonStudent_BalanceUnder10000() {
		double fee = calculator.calculateWithdrawalFee(
				1000,
				4000,          // 1000 < bal < 5000
				false,
				Calendar.SATURDAY
		);
		assertEquals(0.001, fee); // 1000 * 0.001
	}

	@Test
	void nonStudent_BalanceOver10000() {
		double fee = calculator.calculateWithdrawalFee(
				1000,
				10000,         // > 5000
				false,
				Calendar.SATURDAY
		);
		assertEquals(0.0, fee);
	}

}
