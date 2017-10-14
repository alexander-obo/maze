package ao.maze.generators;

import ao.maze.models.MazeCellModel;

import java.util.*;

public final class Util {

    private static final Random RANDOM = new Random();

    private Util() {
    }

    static MazeCellModel selectEnterCell(MazeCellModel[][] matrix) {
        final int x = 0;
        final int y = RANDOM.nextInt(matrix.length);
        return matrix[y][x];
    }

    static MazeCellModel selectExitCell(MazeCellModel[][] matrix) {
        final int x = matrix[0].length - 1;
        final int y = RANDOM.nextInt(matrix.length);
        return matrix[y][x];
    }

    static boolean isUnvisitedCellsExist(MazeCellModel[][] matrix) {
        for (MazeCellModel[] row : matrix) {
            for (MazeCellModel cell : row) {
                if (!cell.isVisited()) {
                    return true;
                }
            }
        }
        return false;
    }

    static List<MazeCellModel> getUnvisitedNeighbors(MazeCellModel[][] matrix, MazeCellModel cell) {
        final List<MazeCellModel> unvisitedNeighbors = new ArrayList<>();
        final int x = cell.getX();
        final int y = cell.getY();

        if (x != 0 && x != matrix[y].length - 1) {
            if (!matrix[y][x - 1].isVisited()) {
                unvisitedNeighbors.add(matrix[y][x - 1]);
            }
            if (!matrix[y][x + 1].isVisited()) {
                unvisitedNeighbors.add(matrix[y][x + 1]);
            }
        } else if (x == 0) {
            if (!matrix[y][x + 1].isVisited()) {
                unvisitedNeighbors.add(matrix[y][x + 1]);
            }
        } else {
            if (!matrix[y][x - 1].isVisited()) {
                unvisitedNeighbors.add(matrix[y][x - 1]);
            }
        }

        if (y != 0 && y != matrix.length - 1) {
            if (!matrix[y - 1][x].isVisited()) {
                unvisitedNeighbors.add(matrix[y - 1][x]);
            }
            if (!matrix[y + 1][x].isVisited()) {
                unvisitedNeighbors.add(matrix[y + 1][x]);
            }
        } else if (y == 0) {
            if (!matrix[y + 1][x].isVisited()) {
                unvisitedNeighbors.add(matrix[y + 1][x]);
            }
        } else {
            if (!matrix[y - 1][x].isVisited()) {
                unvisitedNeighbors.add(matrix[y - 1][x]);
            }
        }

        return unvisitedNeighbors;
    }

}
