package view;

import controller.AdaptiveCycle;
import controller.CarSpawner;
import controller.Simulation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Intersection;
import model.Road;
import model.Sensor;
import model.TrafficLight;
import model.Vehicle;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        TrafficLight light = new TrafficLight();

        Road h = new Road(true, 260);
        Road v = new Road(false, 420);

        CarSpawner spawnH = new CarSpawner(h, -120, 1.8);
        CarSpawner spawnV = new CarSpawner(v, -140, 2.4);

        spawnH.start();
        spawnV.start();
        
        Intersection i = new Intersection(420, 260, light);
        h.setIntersection(i);
        v.setIntersection(i);

        h.setNextRoad(v);
        v.setNextRoad(h);

        for (int k = 0; k < 10; k++)
            h.addVehicle(new Vehicle(-k * 70));

        for (int k = 0; k < 8; k++)
            v.addVehicle(new Vehicle(-k * 90));

        CanvasView canvas = new CanvasView(h, v, i);
        canvas.draw(); 
        Sensor hSensor = new Sensor(h);
        Sensor vSensor = new Sensor(v);
        AdaptiveCycle adaptive = new AdaptiveCycle(hSensor, vSensor);
        light.setStrategy(adaptive);
        StatsPanel stats = new StatsPanel(hSensor, vSensor);
       
        
        Simulation sim = new Simulation(h, v, light, canvas, hSensor, vSensor, stats);
        sim.start();

        
        ControlPanel controls = new ControlPanel(sim, spawnH, spawnV);


        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(controls);
        root.setRight(stats);


        stage.setScene(new Scene(root, 900, 650));
        stage.setTitle("Smart Traffic Simulation");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
