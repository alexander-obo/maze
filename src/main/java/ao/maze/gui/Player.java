package ao.maze.gui;

import java.awt.*;

public class Player {

    private int x;
    private int y;
    private final int diameter;
    private final Color color;

    Player(int radius, Color color) {
        diameter = radius * 2;
        this.color = color;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getDiameter() {
        return diameter;
    }

    Color getColor() {
        return color;
    }

    void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
