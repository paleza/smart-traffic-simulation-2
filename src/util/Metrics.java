package util;

public class Metrics {

    private int carsServed = 0;
    private double totalWaitTime = 0;

    public void addWaitTime(double dt) {
        totalWaitTime += dt;
    }

    public void carServed() {
        carsServed++;
    }

    public double getAverageWaitTime() {
        if (carsServed == 0) return 0;
        return totalWaitTime / carsServed;
    }

    public int getCarsServed() {
        return carsServed;
    }
}
