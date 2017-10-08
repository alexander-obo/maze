package ao.maze.gui;

import java.awt.*;
import java.awt.geom.Line2D;

public class MazeCell {

    static final int CELL_SIDE_LENGTH = 30;

    private final int x;
    private final int y;
    private boolean visited;
    private Shape leftSide;
    private Shape topSide;
    private Shape rightSide;
    private Shape bottomSide;
    private Player player;

    MazeCell(int topLeftXCoordinate, int topLeftYCoordinate, int x, int y) {
        leftSide = new Line2D.Double(topLeftXCoordinate, topLeftYCoordinate,
                topLeftXCoordinate, topLeftYCoordinate + CELL_SIDE_LENGTH);
        topSide = new Line2D.Double(topLeftXCoordinate, topLeftYCoordinate,
                topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate);
        rightSide = new Line2D.Double(topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate,
                topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate + CELL_SIDE_LENGTH);
        bottomSide = new Line2D.Double(topLeftXCoordinate, topLeftYCoordinate + CELL_SIDE_LENGTH,
                topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate + CELL_SIDE_LENGTH);
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    public Shape getLeftSide() {
        return leftSide;
    }

    public Shape getTopSide() {
        return topSide;
    }

    public Shape getRightSide() {
        return rightSide;
    }

    public Shape getBottomSide() {
        return bottomSide;
    }

    public Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }

    boolean isPlayerInCell() {
        return player != null;
    }

    void removeLeftSide() {
        leftSide = null;
    }

    void removeTopSide() {
        topSide = null;
    }

    void removeRightSide() {
        rightSide = null;
    }

    void removeBottomSide() {
        bottomSide = null;
    }

}
