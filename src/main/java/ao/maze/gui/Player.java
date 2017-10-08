package ao.maze.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Player {

    private final int diameter;
    private final Color color;
    private Shape shape;

    Player(int radius, Color color) {
        diameter = radius * 2;
        this.color = color;
    }

    Shape getShape() {
        return shape;
    }

    Color getColor() {
        return color;
    }

    void moveTo(int x, int y) {
        shape = new Ellipse2D.Double(x, y, diameter, diameter);
    }

}
