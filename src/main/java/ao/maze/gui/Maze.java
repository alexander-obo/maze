package ao.maze.gui;

public class Maze {

    private MazeCell[][] maze;

    Maze(int gameFieldX, int gameFieldY, int mazeWidth, int mazeHeight) {
        maze = new MazeCell[mazeHeight][mazeWidth];
        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                int cellX = gameFieldX + MazeCell.CELL_SIDE_LENGTH * x;
                int cellY = gameFieldY + MazeCell.CELL_SIDE_LENGTH * y;
                maze[y][x] = new MazeCell(cellX, cellY);
            }
        }
    }

    MazeCell[][] getMatrix() {
        return maze;
    }

}
