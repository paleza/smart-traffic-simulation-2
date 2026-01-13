package model;

import java.util.ArrayList;
import java.util.List;

public class Road {

    private boolean horizontal;
    private double pos;
    private List<Vehicle> vehicles = new ArrayList<>();
    private Intersection intersection;
    private Road nextRoad;

    public Road(boolean horizontal, double pos) {
        this.horizontal = horizontal;
        this.pos = pos;
    }

    public boolean isHorizontal() { return horizontal; }
    public double getPos() { return pos; }

    public void setIntersection(Intersection i) {
        this.intersection = i;
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setNextRoad(Road r) {
        nextRoad = r;
    }

    public Road getNextRoad() {
        return nextRoad;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
        v.setRoad(this);
    }

    public void removeVehicle(Vehicle v) {
        vehicles.remove(v);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle getVehicleAhead(Vehicle v) {
        Vehicle best = null;
        for (Vehicle o : vehicles) {
            if (o == v) continue;

            if (horizontal && o.getX() > v.getX())
                if (best == null || o.getX() < best.getX()) best = o;

            if (!horizontal && o.getY() > v.getY())
                if (best == null || o.getY() < best.getY()) best = o;
        }
        return best;
    }
}
