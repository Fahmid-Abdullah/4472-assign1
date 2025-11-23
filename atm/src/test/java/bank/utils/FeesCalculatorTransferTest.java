package bank.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FeesCalculatorTransferTest {

    FeesCalculator calculator = new FeesCalculator();

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    // ----- Student Paths -----
    @Test
    public void testStudentPath1() {
        double fee = calculator.calculateTransferFee(50, 500, 500, true);
        assertEquals(50 * 0.01, fee);
    }

    @Test
    public void testStudentPath2() {
        double fee = calculator.calculateTransferFee(50, 500, 1500, true);
        assertEquals(50 * 0.005, fee);
    }

    @Test
    public void testStudentPath3() {
        double fee = calculator.calculateTransferFee(50, 1500, 500, true);
        assertEquals(50 * 0.05, fee);
    }

    @Test
    public void testStudentPath4() {
        double fee = calculator.calculateTransferFee(50, 1500, 1500, true);
        assertEquals(50 * 0.025, fee);
    }

    @Test
    public void testStudentPath5() {
        double fee = calculator.calculateTransferFee(150, 500, 500, true);
        assertEquals(150 * 0.005, fee);
    }

    @Test
    public void testStudentPath6() {
        double fee = calculator.calculateTransferFee(150, 500, 1500, true);
        assertEquals(150 * 0.0025, fee);
    }

    @Test
    public void testStudentPath7() {
        double fee = calculator.calculateTransferFee(150, 1500, 500, true);
        assertEquals(150 * 0.025, fee);
    }

    @Test
    public void testStudentPath8() {
        double fee = calculator.calculateTransferFee(150, 1500, 1500, true);
        assertEquals(150 * 0.0125, fee);
    }

    // ----- Non-Student Paths -----
    @Test
    public void testNonStudentPath1() {
        double fee = calculator.calculateTransferFee(50, 500, 500, false);
        assertEquals(50 * 0.02, fee);
    }

    @Test
    public void testNonStudentPath2() {
        double fee = calculator.calculateTransferFee(50, 500, 1500, false);
        assertEquals(50 * 0.01, fee);
    }

    @Test
    public void testNonStudentPath3() {
        double fee = calculator.calculateTransferFee(50, 1500, 500, false);
        assertEquals(50 * 0.1, fee);
    }

    @Test
    public void testNonStudentPath4() {
        double fee = calculator.calculateTransferFee(50, 1500, 1500, false);
        assertEquals(50 * 0.05, fee);
    }

    @Test
    public void testNonStudentPath5() {
        double fee = calculator.calculateTransferFee(150, 500, 500, false);
        assertEquals(150 * 0.01, fee);
    }

    @Test
    public void testNonStudentPath6() {
        double fee = calculator.calculateTransferFee(150, 500, 1500, false);
        assertEquals(150 * 0.005, fee);
    }

    @Test
    public void testNonStudentPath7() {
        double fee = calculator.calculateTransferFee(150, 1500, 500, false);
        assertEquals(150 * 0.05, fee);
    }

    @Test
    public void testNonStudentPath8() {
        double fee = calculator.calculateTransferFee(150, 1500, 1500, false);
        assertEquals(150 * 0.055, fee);
    }
}
