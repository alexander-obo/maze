package ao.maze.gui;

import ao.maze.models.MazeCellModel;

import java.awt.*;
import java.awt.geom.Line2D;

public class DesktopMazeCell {

    static final int CELL_SIDE_LENGTH = 30;

    private final MazeCellModel cellModel;
    private final int x;
    private final int y;
    private final Shape leftSide;
    private final Shape topSide;
    private final Shape rightSide;
    private final Shape bottomSide;

    DesktopMazeCell(int topLeftXCoordinate, int topLeftYCoordinate, MazeCellModel cellModel) {
        this.cellModel = cellModel;
        this.x = this.cellModel.getX();
        this.y = this.cellModel.getY();

        if (this.cellModel.isLeftSideExists()) {
            leftSide = new Line2D.Double(topLeftXCoordinate, topLeftYCoordinate,
                    topLeftXCoordinate, topLeftYCoordinate + CELL_SIDE_LENGTH);
        } else {
            leftSide = null;
        }

        if (this.cellModel.isTopSideExists()) {
            topSide = new Line2D.Double(topLeftXCoordinate, topLeftYCoordinate,
                    topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate);
        } else {
            topSide = null;
        }

        if (this.cellModel.isRightSideExists()) {
            rightSide = new Line2D.Double(topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate,
                    topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate + CELL_SIDE_LENGTH);
        } else {
            rightSide = null;
        }

        if (this.cellModel.isBottomSideExists()) {
            bottomSide = new Line2D.Double(topLeftXCoordinate, topLeftYCoordinate + CELL_SIDE_LENGTH,
                    topLeftXCoordinate + CELL_SIDE_LENGTH, topLeftYCoordinate + CELL_SIDE_LENGTH);
        } else {
            bottomSide = null;
        }
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Shape getLeftSide() {
        return leftSide;
    }

    Shape getTopSide() {
        return topSide;
    }

    Shape getRightSide() {
        return rightSide;
    }

    Shape getBottomSide() {
        return bottomSide;
    }

}
