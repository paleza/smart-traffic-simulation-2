package model.state;

import model.TrafficLight;

/**
 * Classe base abstrata para todos os estados do semáforo.
 * Implementa o padrão State.
 */
public abstract class LightState {

    protected TrafficLight light;
    protected double timer = 0;

    /**
     * Construtor base de qualquer estado
     */
    public LightState(TrafficLight light) {
        this.light = light;
    }

    /**
     * Atualiza o estado com base no tempo decorrido (dt em segundos)
     */
    public abstract void update(double dt);

    /**
     * Indica se veículos podem avançar neste estado
     * (GreenState retorna true, os outros false)
     */
    public abstract boolean isGreen();
}
