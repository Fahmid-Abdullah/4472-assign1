package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepositFeesTest {
    FeesCalculator calculator;

    @BeforeEach
    void setUp() throws Exception {
        calculator = new FeesCalculator();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    // Valid Inputs
    @Test
    void testStudentAmountOver50BalanceOver500() {
        // Student, amount=100 (>50), balance=600 (>500) → 0.5% of 100 = 0.50
        assertEquals(0.50, calculator.calculateDepositInterest(100, 600, true));
    }

    @Test
    void testStudentAmountOver50BalanceUnder500() {
        // Student, amount=100 (>50), balance=500 (≤500) → 0.25% of 100 = 0.25
        assertEquals(0.25, calculator.calculateDepositInterest(100, 500, true));
    }

    @Test
    void testStudentAmountUnder50BalanceOver5000() {
        // Student, amount=50 (≤50), balance=5100 (>5000) → 0.5% of 50 = 0.25
        assertEquals(0.25, calculator.calculateDepositInterest(50, 5100, true));
    }

    @Test
    void testStudentAmountUnder50BalanceUnder5000() {
        // Student, amount=50 (≤50), balance=5000 (≤5000) → 0% of 50 = 0.00
        assertEquals(0.00, calculator.calculateDepositInterest(50, 5000, true));
    }

    @Test
    void testNonStudentAmountOver250BalanceOver2500() {
        // Non-student, amount=300 (>250), balance=3000 (>2500) → 0.8% of 300 = 2.40
        assertEquals(2.40, calculator.calculateDepositInterest(300, 3000, false));
    }

    @Test
    void testNonStudentAmountOver250BalanceUnder2500() {
        // Non-student, amount=300 (>250), balance=2500 (≤2500) → 0.4% of 300 = 1.20
        assertEquals(1.20, calculator.calculateDepositInterest(300, 2500, false));
    }

    @Test
    void testNonStudentAmountUnder250BalanceOver10000() {
        // Non-student, amount=250 (≤250), balance=10100 (>10000) → 0% of 250 = 0.00
        assertEquals(0.00, calculator.calculateDepositInterest(250, 10100, false));
    }

    @Test
    void testNonStudentAmountUnder250BalanceUnder10000() {
        // Non-student, amount=250 (≤250), balance=10000 (≤10000) → 0.1% of 250 = 0.25
        assertEquals(0.25, calculator.calculateDepositInterest(250, 10000, false));
    }

    // Invalid Inputs

    @Test
    void testInvalidStudentAmountNegative() {
        // Student, invalid amount (negative) - should return 0 or handle gracefully
        double result = calculator.calculateDepositInterest(-10, 600, true);
        assertEquals(0.0, result);
    }

    @Test
    void testInvalidStudentAmountZero() {
        // Student, invalid amount (zero) - should return 0 or handle gracefully
        double result = calculator.calculateDepositInterest(0, 600, true);
        assertEquals(0.0, result);
    }

    @Test
    void testInvalidNonStudentAmountNegative() {
        // Non-student, invalid amount (negative) - should return 0 or handle gracefully
        double result = calculator.calculateDepositInterest(-10, 3000, false);
        assertEquals(0.0, result);
    }

    @Test
    void testInvalidNonStudentAmountZero() {
        // Non-student, invalid amount (zero) - should return 0 or handle gracefully
        double result = calculator.calculateDepositInterest(0, 3000, false);
        assertEquals(0.0, result);
    }
}