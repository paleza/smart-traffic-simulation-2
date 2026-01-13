package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.*;

public class CarSpawner {

    private Road road;
    private double startPos;
    private Timeline timer;

    public CarSpawner(Road road, double startPos, double intervalSeconds) {
        this.road = road;
        this.startPos = startPos;

        timer = new Timeline(
            new KeyFrame(Duration.seconds(intervalSeconds), e -> spawn())
        );
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    private void spawn() {

        // evita criar carro se a entrada estiver ocupada
        if (!road.getVehicles().isEmpty()) {
            Vehicle last = road.getVehicles().get(road.getVehicles().size() - 1);

            double dist = road.isHorizontal()
                    ? last.getX() - startPos
                    : last.getY() - startPos;

            if (dist < 80) return;
        }

        road.addVehicle(new Vehicle(startPos));
    }

    public void start() {
        timer.play();
    }

    public void stop() {
        timer.stop();
    }
}
