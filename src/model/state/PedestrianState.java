package model.state;

import model.TrafficLight;

public class PedestrianState extends LightState {

    public PedestrianState(TrafficLight light) {
        super(light);
    }

    @Override
    public void update(double dt) {
        timer += dt;

        if (timer >= 4) {          // tempo de travessia
            light.setState(new GreenState(light));
        }
    }

    @Override
    public boolean isGreen() {
        return false;              // carros nunca passam
    }
}
