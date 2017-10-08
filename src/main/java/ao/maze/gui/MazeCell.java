package ao.maze.gui;

import java.awt.*;
import java.awt.geom.Line2D;

public class MazeCell {

    static final int CELL_SIDE_LENGTH = 30;

    private Shape leftSide;
    private Shape topSide;
    private Shape rightSide;
    private Shape bottomSide;
    private Player player;

    MazeCell(int x, int y) {
        leftSide = new Line2D.Double(x, y, x, y + CELL_SIDE_LENGTH);
        topSide = new Line2D.Double(x, y, x + CELL_SIDE_LENGTH, y);
        rightSide = new Line2D.Double(x + CELL_SIDE_LENGTH, y, x + CELL_SIDE_LENGTH, y + CELL_SIDE_LENGTH);
        bottomSide = new Line2D.Double(x, y + CELL_SIDE_LENGTH, x + CELL_SIDE_LENGTH, y + CELL_SIDE_LENGTH);
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
