package model;

import model.state.*;
import controller.Strategy;

public class TrafficLight {

    private LightState state;
    private boolean horizontalGreen = true;

    // 🔹 STRATEGY
    private Strategy strategy;

    public TrafficLight() {
        state = new GreenState(this);        // começa verde
        strategy = null;                     // por defeito usa State puro
    }

    // 🔹 chamado pela Simulation
    public void update(double dt) {
    	// 1️⃣ o estado SEMPRE atualiza (cores, timers)
        state.update(dt);

        // 2️⃣ a estratégia decide se força mudanças
        if (strategy != null)
            strategy.update(this, dt);
    }

    // 🔹 STATE
    public void setState(LightState s) {
        state = s;
    }

    public LightState getState() {
        return state;
    }

    // 🔹 STRATEGY
    public void setStrategy(Strategy s) {
        this.strategy = s;
    }

    // 🔹 CONTROLO DE DIREÇÃO
    public boolean canGo(Road road) {
    	if (!state.isGreen()) 
    		return false;
        return road.isHorizontal() == horizontalGreen;
    }

    public boolean isHorizontalGreen() {
        return horizontalGreen;
    }

    public void switchDirection() {
        horizontalGreen = !horizontalGreen;
    }

    // 🔹 usados pelo AdaptiveCycle
    public void forceHorizontalGreen() {
        setState(new GreenState(this));
        horizontalGreen = true;
    }

    public void forceVerticalGreen() {
        setState(new GreenState(this));
        horizontalGreen = false;
    }
    
    public boolean isPedestrianGreen() {
        return state instanceof PedestrianState;
    }

    public void allowPedestrians() {
        setState(new PedestrianState(this));
    }

    
}
