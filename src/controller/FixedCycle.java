package controller;

import model.TrafficLight;

public class FixedCycle implements Strategy {

    @Override
    public void update(TrafficLight light, double dt) {
        light.getState().update(dt);
    }
}
