package model;

import util.MetricsHolder;

public class Vehicle {

    private double x, y;
    private double speed = 80;
    private Road road;

    private boolean stopped = false;
    private boolean counted = false;

    // viragem
    private boolean decided = false;
    private boolean willTurn;

    public Vehicle(double start) {
        x = start;
        y = start;

        // 30% dos carros vão virar
        willTurn = Math.random() < 0.3;
    }

    public void setRoad(Road r) {
        road = r;
        if (r.isHorizontal())
            y = r.getPos() + 30;
        else
            x = r.getPos() + 30;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public boolean isStopped() {
        return stopped;
    }

    public void update(double dt) {

        boolean stop = false;

        /* 1️⃣ Evitar colisão */
        Vehicle ahead = road.getVehicleAhead(this);
        if (ahead != null) {
            double dist = road.isHorizontal()
                    ? ahead.getX() - x
                    : ahead.getY() - y;

            if (dist < 35)
                stop = true;
        }

        /* 2️⃣ Respeitar semáforo */
        Intersection i = road.getIntersection();
        if (i != null) {
            double dist = road.isHorizontal()
                    ? i.getX() - x
                    : i.getY() - y;

            if (dist > 0 && dist < 60 && !i.canEnter(road))
                stop = true;
        }

        /* 3️⃣ Paragem */
        if (stop) {
            stopped = true;
            MetricsHolder.metrics.addWaitTime(dt); 
            return;
        }

        /* 4️⃣ Movimento */
        stopped = false;

        if (road.isHorizontal())
            x += speed * dt;
        else
            y += speed * dt;

        /* 5️⃣ Decisão de virar (NO CENTRO DA INTERSEÇÃO) */
        /* 5️⃣ Decisão de virar (NO CENTRO DA INTERSEÇÃO) */
        if (!decided && i != null) {

            double centerPos = road.isHorizontal()
                    ? i.getX()
                    : i.getY();

            double pos = road.isHorizontal() ? x : y;

            if (pos >= centerPos) {

                decided = true;

                if (willTurn && road.getNextRoad() != null) {

                    // remover da estrada atual
                    road.getVehicles().remove(this);

                    // mudar para a nova estrada
                    road = road.getNextRoad();
                    road.getVehicles().add(this);

                    // ⚠️ posicionar EXATAMENTE no cruzamento
                    if (road.isHorizontal()) {
                        x = i.getX();
                        y = road.getPos() + 30;
                    } else {
                        y = i.getY();
                        x = road.getPos() + 30;
                    }
                }
            }
        }


        /* 6️⃣ Estatísticas (M4) */
        if (!counted && i != null) {

            double center = road.isHorizontal()
                    ? i.getX()
                    : i.getY();

            double pos = road.isHorizontal() ? x : y;

            if (pos > center) {
                MetricsHolder.metrics.carServed();
                counted = true;
            }
        }
    }
}
