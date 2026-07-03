public class Main {
    public static void main(String[] args) {
        SmartMeter meter = new SmartMeter("MTR001", 100);
        System.out.println("Initial balance: " + meter.getCreditBalance() + " | Valve open: " + meter.isValveOpen());

        boolean dispensed1 = meter.recordConsumption(1); // 1 * 50 = 50
        System.out.println("Consumed 1L -> dispensed: " + dispensed1 + " | Balance: " + meter.getCreditBalance() + " | Valve open: " + meter.isValveOpen());

        boolean dispensed2 = meter.recordConsumption(2); // 2 * 50 = 100 > 50 remaining -> exhausts and closes valve
        System.out.println("Consumed 2L -> dispensed: " + dispensed2 + " | Balance: " + meter.getCreditBalance() + " | Valve open: " + meter.isValveOpen());

        boolean dispensed3 = meter.recordConsumption(1); // valve closed, blocked
        System.out.println("Consumed 1L (blocked) -> dispensed: " + dispensed3 + " | Balance: " + meter.getCreditBalance() + " | Valve open: " + meter.isValveOpen());

        double newBalance = meter.loadToken(500);
        System.out.println("Loaded token of 500 -> new balance: " + newBalance + " | Valve open: " + meter.isValveOpen());
    }
}
