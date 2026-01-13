package model.state;

import model.TrafficLight;

public class GreenState extends LightState {

    public GreenState(TrafficLight light) {
        super(light);
    }

    @Override
    public void update(double dt) {
        timer += dt;

        if (timer >= 6) {          // tempo de verde
            light.setState(new YellowState(light));
        }
    }

    @Override
    public boolean isGreen() {
        return true;
    }
}
