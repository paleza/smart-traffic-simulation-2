package model;

import java.util.ArrayList;
import java.util.List;

public class Lane {
	private Direction dir;
    private double offset; // deslocamento visual
    private List<Vehicle> vehicles = new ArrayList<>();

    public Lane(Direction dir, double offset) {
        this.dir = dir;
        this.offset = offset;
    }

    public Direction getDirection() { return dir; }
    public double getOffset() { return offset; }
    public List<Vehicle> getVehicles() { return vehicles; }

}
