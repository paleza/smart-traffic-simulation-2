package model.state;

import model.TrafficLight;

public class YellowState extends LightState {

    public YellowState(TrafficLight light) {
        super(light);
    }

    @Override
    public void update(double dt) {
        timer += dt;

        if (timer >= 1.5) {        // tempo de amarelo
            light.switchDirection();
            light.setState(new RedState(light));
        }
    }

    @Override
    public boolean isGreen() {
        return false;
    }
}
