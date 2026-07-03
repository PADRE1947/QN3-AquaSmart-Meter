public class SmartMeter {

    private final String meterId;
    private double creditBalance;
    private boolean valveOpen;

    private static final double COST_PER_LITRE = 50.0; // UGX

    public SmartMeter(String meterId, double openingCreditBalance) {
        this.meterId = meterId;
        this.creditBalance = openingCreditBalance;
        this.valveOpen = true;
    }

    /**
     * Adds credit to the meter. Re-opens the valve if it had been closed.
     * Returns the new balance.
     */
    public double loadToken(double amount) {
        if (amount > 0) {
            creditBalance += amount;
        }
        if (!valveOpen) {
            valveOpen = true;
        }
        return creditBalance;
    }

    /**
     * Deducts the cost of the litres consumed (UGX 50 per litre).
     * If the balance is already exhausted, the request is blocked.
     * If this consumption drains the balance to zero or below,
     * the balance is clamped to 0 and the valve is closed.
     *
     * @return true if water was dispensed, false if blocked due to insufficient credit
     */
    public boolean recordConsumption(double litres) {
        if (!valveOpen || creditBalance <= 0) {
            return false; // meter disconnected / no credit — nothing dispensed
        }

        double cost = litres * COST_PER_LITRE;
        creditBalance -= cost;

        if (creditBalance <= 0) {
            creditBalance = 0;
            valveOpen = false;
        }
        return true;
    }

    public String getMeterId() {
        return meterId;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public boolean isValveOpen() {
        return valveOpen;
    }
}