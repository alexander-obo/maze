package ao.maze.gui;

import java.util.*;

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
                maze[y][x] = new MazeCell(cellX, cellY, x, y);
            }
        }

        this.player = player;

        // Maze generation algorithm has been took from here: https://habrahabr.ru/post/262345/
        Random random = new Random();
        playerCurrentCellX = 0;
        playerCurrentCellY = random.nextInt(mazeHeight);
        maze[playerCurrentCellY][playerCurrentCellX].removeLeftSide();
        maze[playerCurrentCellY][playerCurrentCellX].setPlayer(this.player);
        maze[playerCurrentCellY][playerCurrentCellX].visit();
        MazeCell currentCell = maze[playerCurrentCellY][playerCurrentCellX];

        Deque<MazeCell> stack = new ArrayDeque<>();
        while (isUnvisitedCellsExist()) {
            List<MazeCell> unvisitedNeighbors = getUnvisitedNeighbors(maze, currentCell);
            if (!unvisitedNeighbors.isEmpty()) {
                stack.push(currentCell);
                MazeCell randomUnvisitedNeighborCell = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));
                if (currentCell.getX() == randomUnvisitedNeighborCell.getX() + 1) {
                    currentCell.removeLeftSide();
                    randomUnvisitedNeighborCell.removeRightSide();
                } else if (currentCell.getX() == randomUnvisitedNeighborCell.getX() - 1) {
                    currentCell.removeRightSide();
                    randomUnvisitedNeighborCell.removeLeftSide();
                } else if (currentCell.getY() == randomUnvisitedNeighborCell.getY() + 1) {
                    currentCell.removeTopSide();
                    randomUnvisitedNeighborCell.removeBottomSide();
                } else {
                    currentCell.removeBottomSide();
                    randomUnvisitedNeighborCell.removeTopSide();
                }
                currentCell = randomUnvisitedNeighborCell;
                currentCell.visit();
            } else if (!stack.isEmpty()) {
                currentCell = stack.pop();
            } else {
                // with proper algorithm implementation and proper input data this case should never be executed
                throw new RuntimeException("Something went wrong. Please, check algorithm and input data");
            }
        }
        int exitX = mazeWidth - 1;
        int exitY = random.nextInt(mazeHeight);
        maze[exitY][exitX].removeRightSide();
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

    private boolean isUnvisitedCellsExist() {
        for (MazeCell[] row : maze) {
            for (MazeCell cell : row) {
                if (!cell.isVisited()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<MazeCell> getUnvisitedNeighbors(MazeCell[][] maze, MazeCell cell) {
        List<MazeCell> unvisitedNeighbors = new ArrayList<>();
        int xIndex = cell.getX();
        int yIndex = cell.getY();

        if (xIndex != 0 && xIndex != maze[yIndex].length - 1) {
            if (!maze[yIndex][xIndex - 1].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex][xIndex - 1]);
            }
            if (!maze[yIndex][xIndex + 1].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex][xIndex + 1]);
            }
        } else if (xIndex == 0) {
            if (!maze[yIndex][xIndex + 1].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex][xIndex + 1]);
            }
        } else {
            if (!maze[yIndex][xIndex - 1].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex][xIndex - 1]);
            }
        }

        if (yIndex != 0 && yIndex != maze.length - 1) {
            if (!maze[yIndex - 1][xIndex].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex - 1][xIndex]);
            }
            if (!maze[yIndex + 1][xIndex].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex + 1][xIndex]);
            }
        } else if (yIndex == 0) {
            if (!maze[yIndex + 1][xIndex].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex + 1][xIndex]);
            }
        } else {
            if (!maze[yIndex - 1][xIndex].isVisited()) {
                unvisitedNeighbors.add(maze[yIndex - 1][xIndex]);
            }
        }

        return unvisitedNeighbors;
    }

}
