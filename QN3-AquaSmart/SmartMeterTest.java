public class SmartMeterTest {

    public static void main(String[] args) {
        loadingTokenReopensClosedValve();
        consumptionBeyondCreditClosesValve();
        System.out.println("All tests passed!");
    }

    public static void loadingTokenReopensClosedValve() {
        SmartMeter meter = new SmartMeter("MTR001", 50);
        meter.recordConsumption(2); // 2 * 50 = 100 cost > 50 balance -> valve closes
        assertFalse(meter.isValveOpen(), "Valve should be closed after credit is exhausted");

        meter.loadToken(500);

        assertTrue(meter.isValveOpen(), "Valve should reopen after loading a token");
        assertEquals(500, meter.getCreditBalance(), "Balance should equal the loaded token amount");

        System.out.println("loadingTokenReopensClosedValve: PASSED");
    }

    public static void consumptionBeyondCreditClosesValve() {
        SmartMeter meter = new SmartMeter("MTR002", 100);

        boolean dispensed = meter.recordConsumption(5); // 5 * 50 = 250 > 100 balance

        assertTrue(dispensed, "This request should still dispense water");
        assertFalse(meter.isValveOpen(), "Valve should close once credit is exhausted");
        assertEquals(0, meter.getCreditBalance(), "Balance should be clamped to 0");

        System.out.println("consumptionBeyondCreditClosesValve: PASSED");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertEquals(double expected, double actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " (expected " + expected + " but was " + actual + ")");
        }
    }
}
