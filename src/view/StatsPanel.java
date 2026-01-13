package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Sensor;
import util.MetricsHolder;

public class StatsPanel extends VBox {
	
	private Label avgWait = new Label();
    private Label hQueue = new Label();
    private Label vQueue = new Label();
    private Label served = new Label();

    public StatsPanel(Sensor hSensor, Sensor vSensor) {

        getChildren().addAll(
                hQueue,
                vQueue,
                served,
                avgWait
        );

        setSpacing(5);
        update(hSensor, vSensor);
    }

    public void update(Sensor hSensor, Sensor vSensor) {
        hQueue.setText("Fila horizontal: " + hSensor.getStoppedCount());
        vQueue.setText("Fila vertical: " + vSensor.getStoppedCount());
        served.setText("Carros atendidos: " +
        MetricsHolder.metrics.getCarsServed());
        avgWait.setText(
                String.format("Tempo médio de espera: %.2f s",
                    MetricsHolder.metrics.getAverageWaitTime())
            );  
    }
}
