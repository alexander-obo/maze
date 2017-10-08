package ao.maze.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Player {

    private final int diameter;
    private Shape shape;

    Player(int radius) {
        diameter = radius * 2;
    }

    Shape getShape() {
        return shape;
    }

    void stepLeft() {
        int currentX = shape.getBounds().x;
        int newX = currentX - MazeCell.CELL_SIDE_LENGTH;
        int currentY = shape.getBounds().y;
        moveTo(newX, currentY);
    }

    void stepUp() {
        int currentX = shape.getBounds().x;
        int currentY = shape.getBounds().y;
        int newY = currentY - MazeCell.CELL_SIDE_LENGTH;
        moveTo(currentX, newY);
    }

    void stepRight() {
        int currentX = shape.getBounds().x;
        int newX = currentX + MazeCell.CELL_SIDE_LENGTH;
        int currentY = shape.getBounds().y;
        moveTo(newX, currentY);
    }

    void stepDown() {
        int currentX = shape.getBounds().x;
        int currentY = shape.getBounds().y;
        int newY = currentY + MazeCell.CELL_SIDE_LENGTH;
        moveTo(currentX, newY);
    }

    void moveTo(int x, int y) {
        shape = new Ellipse2D.Double(x, y, diameter, diameter);
    }


}
