package bank.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferFeesTest {
    FeesCalculator calculator = new FeesCalculator();

    @BeforeEach
    void setUp() throws Exception {}

    @AfterEach
    void tearDown() throws Exception {}

    // 🎓 Student Cases

    @Test
    void T1_Student_Amount100_From1000_To500() {
        double fee = calculator.calculateTransferFee(100, 1000, 500, true);
        assertEquals(100 * 0.001, fee, 1e-6);
    }

    @Test
    void T2_Student_Amount100_From1000_To1500() {
        double fee = calculator.calculateTransferFee(100, 1000, 1500, true);
        assertEquals(100 * 0.0005, fee, 1e-6);
    }

    @Test
    void T3_Student_Amount100_From2500_To500() {
        double fee = calculator.calculateTransferFee(100, 2500, 500, true);
        assertEquals(100 * 0.0005, fee, 1e-6);
    }

    @Test
    void T4_Student_Amount100_From2500_To1500() {
        double fee = calculator.calculateTransferFee(100, 2500, 1500, true);
        assertEquals(100 * 0.00025, fee, 1e-6);
    }

    @Test
    void T5_Student_Amount300_From1000_To500() {
        double fee = calculator.calculateTransferFee(300, 1000, 500, true);
        assertEquals(300 * 0.0005, fee, 1e-6);
    }

    @Test
    void T6_Student_Amount300_From1000_To1500() {
        double fee = calculator.calculateTransferFee(300, 1000, 1500, true);
        assertEquals(300 * 0.00025, fee, 1e-6);
    }

    @Test
    void T7_Student_Amount300_From3000_To500() {
        double fee = calculator.calculateTransferFee(300, 3000, 500, true);
        assertEquals(300 * 0.0025, fee, 1e-6);
    }

    @Test
    void T8_Student_Amount300_From3000_To1500() {
        double fee = calculator.calculateTransferFee(300, 3000, 1500, true);
        assertEquals(300 * 0.00125, fee, 1e-6);
    }

    // 👨‍💼 Non-student Cases

    @Test
    void T9_NonStudent_Amount50_From1000_To1000() {
        double fee = calculator.calculateTransferFee(50, 1000, 1000, false);
        assertEquals(50 * 0.002, fee, 1e-6);
    }

    @Test
    void T10_NonStudent_Amount50_From1000_To3000() {
        double fee = calculator.calculateTransferFee(50, 1000, 3000, false);
        assertEquals(50 * 0.001, fee, 1e-6);
    }

    @Test
    void T11_NonStudent_Amount50_From5000_To1000() {
        double fee = calculator.calculateTransferFee(50, 5000, 1000, false);
        assertEquals(50 * 0.01, fee, 1e-6);
    }

    @Test
    void T12_NonStudent_Amount50_From5000_To3000() {
        double fee = calculator.calculateTransferFee(50, 5000, 3000, false);
        assertEquals(50 * 0.005, fee, 1e-6);
    }

    @Test
    void T13_NonStudent_Amount200_From1000_To500() {
        double fee = calculator.calculateTransferFee(200, 1000, 500, false);
        assertEquals(200 * 0.002, fee, 1e-6);
    }

    @Test
    void T14_NonStudent_Amount200_From1000_To1500() {
        double fee = calculator.calculateTransferFee(200, 1000, 1500, false);
        assertEquals(200 * 0.001, fee, 1e-6);
    }

    @Test
    void T15_NonStudent_Amount200_From3000_To500() {
        double fee = calculator.calculateTransferFee(200, 3000, 500, false);
        assertEquals(200 * 0.005, fee, 1e-6);
    }

    @Test
    void T16_NonStudent_Amount200_From3000_To1500() {
        double fee = calculator.calculateTransferFee(200, 3000, 1500, false);
        assertEquals(200 * 0.0025, fee, 1e-6);
    }
}
