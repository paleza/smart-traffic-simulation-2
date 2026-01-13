package model.state;

import model.TrafficLight;

public class RedState extends LightState {

    public RedState(TrafficLight light) {
        super(light);
    }

    @Override
    public void update(double dt) {
        timer += dt;

        if (timer >= 3) {          // tempo de vermelho
            light.setState(new GreenState(light));
        }
    }

    @Override
    public boolean isGreen() {
        return false;
    }
}
