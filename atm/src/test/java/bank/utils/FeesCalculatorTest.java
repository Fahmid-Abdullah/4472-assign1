package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    // For all the below test cases, the total fee is calculated by doing amount * fee.
    // STUDENT TEST CASES

    @Test
    void TC1_student_smallLowLow() {
        assertEquals(150 * 0.001, calculator.calculateTransferFee(150, 1500, 800, true), 1e-6);
    }

    @Test
    void TC2_student_smallLowHigh() {
        assertEquals(150 * 0.0005, calculator.calculateTransferFee(150, 1500, 1200, true), 1e-6);
    }

    @Test
    void TC3_student_smallHighLow() {
        assertEquals(150 * 0.0005, calculator.calculateTransferFee(150, 2500, 800, true), 1e-6);
    }

    @Test
    void TC4_student_smallHighHigh() {
        assertEquals(150 * 0.00025, calculator.calculateTransferFee(150, 2500, 1200, true), 1e-6);
    }

    @Test
    void TC5_student_largeLowLow() {
        assertEquals(300 * 0.0005, calculator.calculateTransferFee(300, 1500, 800, true), 1e-6);
    }

    @Test
    void TC6_student_largeLowHigh() {
        assertEquals(300 * 0.00025, calculator.calculateTransferFee(300, 1500, 1200, true), 1e-6);
    }

    @Test
    void TC7_student_largeHighLow() {
        assertEquals(300 * 0.0025, calculator.calculateTransferFee(300, 2500, 800, true), 1e-6);
    }

    @Test
    void TC8_student_largeHighHigh() {
        assertEquals(300 * 0.00125, calculator.calculateTransferFee(300, 2500, 1200, true), 1e-6);
    }

    // NON-STUDENT TEST CASES

    @Test
    void TC9_nonStudent_smallLowLow() {
        assertEquals(80 * 0.002, calculator.calculateTransferFee(80, 3000, 1500, false), 1e-6);
    }

    @Test
    void TC10_nonStudent_smallLowHigh() {
        assertEquals(80 * 0.001, calculator.calculateTransferFee(80, 3000, 2500, false), 1e-6);
    }

    @Test
    void TC11_nonStudent_smallHighLow() {
        assertEquals(80 * 0.01, calculator.calculateTransferFee(80, 5000, 1500, false), 1e-6);
    }

    @Test
    void TC12_nonStudent_smallHighHigh() {
        assertEquals(80 * 0.005, calculator.calculateTransferFee(80, 5000, 2500, false), 1e-6);
    }

    @Test
    void TC13_nonStudent_largeLowLow() {
        assertEquals(150 * 0.002, calculator.calculateTransferFee(150, 3000, 1500, false), 1e-6);
    }

    @Test
    void TC14_nonStudent_largeLowHigh() {
        assertEquals(150 * 0.001, calculator.calculateTransferFee(150, 3000, 2500, false), 1e-6);
    }

    @Test
    void TC15_nonStudent_largeHighLow() {
        assertEquals(150 * 0.005, calculator.calculateTransferFee(150, 5000, 1500, false), 1e-6);
    }

    @Test
    void TC16_nonStudent_largeHighHigh() {
        assertEquals(150 * 0.0025, calculator.calculateTransferFee(150, 5000, 2500, false), 1e-6);
    }
}
