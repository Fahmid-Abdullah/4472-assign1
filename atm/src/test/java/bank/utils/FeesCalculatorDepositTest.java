package bank.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeesCalculatorDepositTest {
    FeesCalculator calculator;

    @BeforeEach
    void setUp() throws Exception {
        calculator = new FeesCalculator();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    // DU1: student && amount > 100 && accountBalance > 1000 -> interestPercentage = 0.01
    @Test
    void testDU1() {
        assertEquals(200.00 * 0.01, calculator.calculateDepositInterest(200.00, 2000.00, true));
    }

    // DU2: student && amount > 100 && accountBalance <= 1000 → interestPercentage = 0.005
    @Test
    void testDU2() {
        assertEquals(200.00 * 0.005, calculator.calculateDepositInterest(200.00, 800.00, true));
    }

    // DU3: student && amount <= 100 && balance > 5000 → interestPercentage = 0.005
    @Test
    void testDU3() {
        assertEquals(80.00 * 0.005, calculator.calculateDepositInterest(80.00, 7000.00, true));
    }

    // DU4: student && amount <= 100 && balance <= 5000 → interestPercentage = 0.002
    @Test
    void testDU4() {
        assertEquals(80.00 * 0.002, calculator.calculateDepositInterest(80.00, 3000.00, true));
    }

    // DU5: Non-student && amount > 500 && balance > 5000 → interestPercentage = 0.01
    @Test
    void testDU5() {
        assertEquals(600.00 * 0.01, calculator.calculateDepositInterest(600.00, 8000.00, false));
    }

    // DU6: Non-student && amount > 500 && balance <= 5000 → interestPercentage = 0.005
    @Test
    void testDU6() {
        assertEquals(600.00 * 0.005, calculator.calculateDepositInterest(600.00, 3000.00, false));
    }

    // DU7: Non-student && amount <= 500 && balance > 10000 → interestPercentage = 0.005
    @Test
    void testDU7() {
        assertEquals(300.00 * 0.005, calculator.calculateDepositInterest(300.00, 15000.00, false));
    }

    // DU8: Non-student && amount <= 500 && balance <= 10000 → interestPercentage =  0.0
    @Test
    void testDU8() {
        assertEquals(300.00 * 0.0, calculator.calculateDepositInterest(300.00, 8000.00, false));
    }


    // Boundary Test Cases

    // Boundary: student && amount = 100 && balance > 1000 → interestPercentage = 0.002
    @Test
    void testDU4_boundary() {
        assertEquals(100.00 * 0.002, calculator.calculateDepositInterest(100.00, 1500.00, true));
    }

    // Boundary: student && amount = 100.01 && balance = 1000 → interestPercentage = 0.005
    @Test
    void testDU2_boundary() {
        assertEquals(100.01 * 0.005, calculator.calculateDepositInterest(100.01, 1000.00, true));
    }

    // Boundary: student && amount = 100.01 && balance = 1000.01 → interestPercentage = 0.01
    @Test
    void testDU1_boundary() {
        assertEquals(100.01 * 0.01, calculator.calculateDepositInterest(100.01, 1000.01, true));
    }

    // Boundary: Non-student && amount = 500 && balance > 500 → interestPercentage = 0.0
    @Test
    void testDU8_boundary() {
        assertEquals(500.00 * 0.0, calculator.calculateDepositInterest(500.00, 6000.00, false));
    }

    // Boundary: Non-student && amount = 500.01 && balance = 5000 → interestPercentage = 0.005
    @Test
    void testDU6_boundary() {
        assertEquals(500.01 * 0.005, calculator.calculateDepositInterest(500.01, 5000.00, false));
    }

    // Boundary: Non-student && amount = 500.01 && balance = 5000.01 → interestPercentage = 0.01
    @Test
    void testDU5_boundary() {
        assertEquals(500.01 * 0.01, calculator.calculateDepositInterest(500.01, 5000.01, false));
    }
}