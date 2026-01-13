package model;

public class Sensor {

    private Road road;
    private int stoppedCount = 0;

    public Sensor(Road road) {
        this.road = road;
    }

    public void update() {
        stoppedCount = 0;
        for (Vehicle v : road.getVehicles()) {
            if (v.isStopped())
                stoppedCount++;
        }
    }

    public int getStoppedCount() {
        return stoppedCount;
    }
}


