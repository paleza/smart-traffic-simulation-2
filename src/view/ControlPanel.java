package view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import controller.Simulation;
import controller.CarSpawner;
import util.CsvExporter;

public class ControlPanel extends HBox {

    public ControlPanel(
            Simulation sim,
            CarSpawner spawnH,
            CarSpawner spawnV) {
    	
    	Button export = new Button("Exportar CSV");
    	export.setOnAction(e ->
    	    CsvExporter.export("resultados.csv")
    	);

        Button start = new Button("Iniciar");
        Button stop = new Button("Parar");
        Button reset = new Button("Reiniciar");

        Slider speed = new Slider(0.5, 2.0, 1.0);
        speed.setShowTickLabels(true);
        speed.setShowTickMarks(true);

        start.setOnAction(e -> sim.setRunning(true));
        stop.setOnAction(e -> sim.setRunning(false));

        reset.setOnAction(e -> {
            sim.fullReset();
            spawnH.stop();
            spawnV.stop();
            spawnH.start();
            spawnV.start();
            sim.setRunning(true);
        });

        speed.valueProperty().addListener(
            (obs, o, n) -> sim.setSpeedFactor(n.doubleValue())
        );

        setSpacing(10);
        getChildren().addAll(start, stop, reset,
                new Label("Velocidade"), speed);
        getChildren().add(export);

    }
}
