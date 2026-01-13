package model;

public class Intersection {

    private double x, y;
    private TrafficLight light;

    public Intersection(double x, double y, TrafficLight light) {
        this.x = x;
        this.y = y;
        this.light = light;
    }
    
    public boolean canEnter(Road road) {
        if (light.isPedestrianGreen())
            return false;

        return light.canGo(road);
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // ✅ MÉTODO QUE FALTAVA
    public TrafficLight getLight() {
        return light;
    }
}
