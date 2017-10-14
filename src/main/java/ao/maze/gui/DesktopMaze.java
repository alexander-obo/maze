package ao.maze.gui;

import ao.maze.generators.MazeGenerator;
import ao.maze.generators.SimpleMazeGenerator;
import ao.maze.models.MazeModel;

public class DesktopMaze {

    private MazeGenerator mazeGenerator;
    private MazeModel mazeModel;
    private DesktopMazeCell[][] maze;
    private DesktopMazeCell enterCell;
    private DesktopMazeCell exitCell;

    DesktopMaze(int gameFieldX, int gameFieldY, int mazeWidth, int mazeHeight) {
        mazeGenerator = new SimpleMazeGenerator();
        mazeModel = mazeGenerator.generateMaze(mazeWidth, mazeHeight);

        maze = new DesktopMazeCell[mazeHeight][mazeWidth];
        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                int cellX = gameFieldX + DesktopMazeCell.CELL_SIDE_LENGTH * x;
                int cellY = gameFieldY + DesktopMazeCell.CELL_SIDE_LENGTH * y;
                maze[y][x] = new DesktopMazeCell(cellX, cellY, mazeModel.getMatrix()[y][x]);
            }
        }

        enterCell = maze[mazeModel.getEnter().getY()][mazeModel.getEnter().getX()];
        exitCell = maze[mazeModel.getExit().getY()][mazeModel.getExit().getX()];
    }

    DesktopMazeCell[][] getMatrix() {
        return maze;
    }

    DesktopMazeCell getEnterCell() {
        return enterCell;
    }

    DesktopMazeCell getExitCell() {
        return exitCell;
    }

}
