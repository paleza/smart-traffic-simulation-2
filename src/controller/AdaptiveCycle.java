package controller;

import model.*;

public class AdaptiveCycle implements Strategy {

    private Sensor hSensor;
    private Sensor vSensor;

    private double timer = 0;
    private double minGreen = 4;
    private double maxGreen = 10;
    private double pedestrianInterval = 15; // 👈 a cada 15s

    public AdaptiveCycle(Sensor h, Sensor v) {
        this.hSensor = h;
        this.vSensor = v;
    }

    @Override
    public void update(TrafficLight light, double dt) {

        timer += dt;

        hSensor.update();
        vSensor.update();

        int hQueue = hSensor.getStoppedCount();
        int vQueue = vSensor.getStoppedCount();

        /* ===== PEDESTRES ===== */
        if (timer >= pedestrianInterval) {
            light.allowPedestrians();
            timer = 0;
           
        }

        /* ===== VEÍCULOS ===== */
        if (timer < minGreen)
            return;

        if (light.isHorizontalGreen() && vQueue > hQueue) {
            light.switchDirection();
            timer = 0;
        }
        else if (!light.isHorizontalGreen() && hQueue > vQueue) {
            light.switchDirection();
            timer = 0;
        }

        if (timer >= maxGreen) {
            light.switchDirection();
            timer = 0;
        }
    }
}
