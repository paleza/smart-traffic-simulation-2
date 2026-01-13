package controller;

import model.TrafficLight;

public interface Strategy {
    void update(TrafficLight light, double dt);
}
