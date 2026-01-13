package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

public class CanvasView extends Canvas {

    private Road h;
    private Road v;
    private Intersection intersection;

    public CanvasView(Road h, Road v, Intersection intersection) {
        super(900, 600);
        this.h = h;
        this.v = v;
        this.intersection = intersection;
    }

    public void draw() {
        GraphicsContext g = getGraphicsContext2D();

        /* ================= FUNDO ================= */
        g.setFill(Color.web("#dcdcdc"));
        g.fillRect(0, 0, getWidth(), getHeight());

        /* ================= ESTRADAS ================= */
        g.setFill(Color.DARKGRAY);
        g.fillRect(0, h.getPos(), getWidth(), 80);      // horizontal
        g.fillRect(v.getPos(), 0, 80, getHeight());     // vertical

        /* ================= CRUZAMENTO ================= */
        g.setFill(Color.GRAY);
        g.fillRect(
            intersection.getX() - 40,
            intersection.getY() - 40,
            80, 80
        );

        /* ================= SEMÁFORO DE VEÍCULOS ================= */
        TrafficLight light = intersection.getLight();

        g.setFill(Color.BLACK);
        g.fillRect(
            intersection.getX() - 12,
            intersection.getY() - 85,
            24, 65
        );

        if (light.getState().getClass().getSimpleName().contains("Red"))
            g.setFill(Color.RED);
        else if (light.getState().getClass().getSimpleName().contains("Yellow"))
            g.setFill(Color.YELLOW);
        else
            g.setFill(Color.GREEN);

        g.fillOval(
            intersection.getX() - 9,
            intersection.getY() - 80,
            18, 18
        );

        /* ================= CARROS ================= */
        g.setFill(Color.DODGERBLUE);
        for (Vehicle c : h.getVehicles()) {
            g.fillRect(c.getX(), c.getY(), 30, 15);
        }

        for (Vehicle c : v.getVehicles()) {
            g.fillRect(c.getX(), c.getY(), 15, 30);
        }

        /* ================= FAIXA DE PEDESTRES ================= */
        g.setFill(Color.WHITE);
        for (int offset = -40; offset <= 40; offset += 15) {
            g.fillRect(
                intersection.getX() - 10,
                intersection.getY() + offset,
                20,
                8
            );
        }

        /* ================= SEMÁFORO DE PEDESTRES ================= */
        g.setFill(Color.BLACK);
        g.fillRect(
            intersection.getX() - 45,
            intersection.getY() - 20,
            15, 40
        );

        g.setFill(
            intersection.getLight().isPedestrianGreen()
                ? Color.GREEN
                : Color.RED
        );

        g.fillOval(
            intersection.getX() - 43,
            intersection.getY() - 5,
            10, 10
        );
    }
}
