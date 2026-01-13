package controller;

import javafx.animation.AnimationTimer;
import model.*;
import view.CanvasView;
import view.StatsPanel;

public class Simulation extends AnimationTimer {
	private StatsPanel statsPanel;
	private Sensor hSensor;
	private Sensor vSensor;

    private Road h, v;
    private TrafficLight light;
    private CanvasView view;

    private long last = 0;
    private boolean running = true;
    private double speedFactor = 1.0;

    public Simulation(Road h, Road v, TrafficLight l, CanvasView view, Sensor hSensor, Sensor vSensor,  StatsPanel statsPanel ) {
        this.h = h;
        this.v = v;
        this.light = l;
        this.view = view;
        this.hSensor = hSensor;
        this.vSensor = vSensor;
        this.statsPanel = statsPanel;
    }

    public void setRunning(boolean r) {
        running = r;
    }

    public void setSpeedFactor(double f) {
        speedFactor = f;
    }

    public void fullReset() {
        running = true;
        last = 0;

        h.getVehicles().clear();
        v.getVehicles().clear();

        view.draw();
    }


    @Override
    public void handle(long now) {
        if (!running) return;

        if (last == 0) {
            last = now;
            return;
        }

        double rawDt = (now - last) / 1e9;
        last = now;

        final double dt = rawDt * speedFactor;


        light.update(dt);
        h.getVehicles().forEach(c -> c.update(dt));
        v.getVehicles().forEach(c -> c.update(dt));
        hSensor.update();
        vSensor.update();
        statsPanel.update(hSensor, vSensor);


        
        
        
        view.draw();
    }
}
