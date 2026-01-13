package util;

import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {

    public static void export(String file) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("cars_served,avg_wait_time\n");
            fw.write(
                MetricsHolder.metrics.getCarsServed() + "," +
                MetricsHolder.metrics.getAverageWaitTime() + "\n"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
