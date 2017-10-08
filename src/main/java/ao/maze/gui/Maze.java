package ao.maze.gui;

import java.util.Random;

public class Maze {

    private MazeCell[][] maze;
    private int playerCurrentCellX;
    private int playerCurrentCellY;
    private Player player;

    Maze(int gameFieldX, int gameFieldY, int mazeWidth, int mazeHeight, Player player) {
        maze = new MazeCell[mazeHeight][mazeWidth];
        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                int cellX = gameFieldX + MazeCell.CELL_SIDE_LENGTH * x;
                int cellY = gameFieldY + MazeCell.CELL_SIDE_LENGTH * y;
                maze[y][x] = new MazeCell(cellX, cellY);
            }
        }

        this.player = player;
        // TODO temporary solution for debug, should be actual maze enter. Change it after maze generation
        Random random = new Random();
        int entryPointRowIndex = random.nextInt(mazeHeight);
        playerCurrentCellX = 0;
        playerCurrentCellY = entryPointRowIndex;
        maze[entryPointRowIndex][0].removeLeftSide();
        maze[entryPointRowIndex][0].removeRightSide();
        maze[entryPointRowIndex][0].setPlayer(this.player);
    }

    MazeCell[][] getMatrix() {
        return maze;
    }

    int getPlayerCurrentCellX() {
        return playerCurrentCellX;
    }

    void setPlayerCurrentCellX(int playerCurrentCellX) {
        this.playerCurrentCellX = playerCurrentCellX;
    }

    int getPlayerCurrentCellY() {
        return playerCurrentCellY;
    }

    void setPlayerCurrentCellY(int playerCurrentCellY) {
        this.playerCurrentCellY = playerCurrentCellY;
    }

}
