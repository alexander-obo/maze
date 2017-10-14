package ao.maze.generators;

import ao.maze.models.MazeModel;
import ao.maze.models.MazeCellModel;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import static ao.maze.generators.Util.*;

public class SimpleMazeGenerator implements MazeGenerator {

    /**
     * MazeModel generation algorithm has been took from here: https://habrahabr.ru/post/262345/
     */
    @Override
    public MazeModel generateMaze(int mazeWidth, int mazeHeight) {
        final MazeCellModel[][] matrix = new MazeCellModel[mazeHeight][mazeWidth];
        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                matrix[y][x] = new MazeCellModel(x, y);
            }
        }
        final MazeCellModel enter = selectEnterCell(matrix);
        enter.removeLeftSide();
        MazeCellModel currentCell = enter;
        currentCell.visit();
        final Random random = new Random();
        final Deque<MazeCellModel> stack = new ArrayDeque<>();
        while (isUnvisitedCellsExist(matrix)) {
            final List<MazeCellModel> unvisitedNeighbors = getUnvisitedNeighbors(matrix, currentCell);
            if (!unvisitedNeighbors.isEmpty()) {
                stack.push(currentCell);
                final int randomNeighborIndex = random.nextInt(unvisitedNeighbors.size());
                final MazeCellModel unvisitedNeighbor = unvisitedNeighbors.get(randomNeighborIndex);
                if (currentCell.getX() == unvisitedNeighbor.getX() + 1) {
                    currentCell.removeLeftSide();
                    unvisitedNeighbor.removeRightSide();
                } else if (currentCell.getX() == unvisitedNeighbor.getX() - 1) {
                    currentCell.removeRightSide();
                    unvisitedNeighbor.removeLeftSide();
                } else if (currentCell.getY() == unvisitedNeighbor.getY() + 1) {
                    currentCell.removeTopSide();
                    unvisitedNeighbor.removeBottomSide();
                } else {
                    currentCell.removeBottomSide();
                    unvisitedNeighbor.removeTopSide();
                }
                currentCell = unvisitedNeighbor;
                currentCell.visit();
            } else if (!stack.isEmpty()) {
                currentCell = stack.pop();
            } else {
                // with proper algorithm implementation and proper input data this case should never be executed
                throw new RuntimeException("Something went wrong. Please, check algorithm and input data");
            }
        }
        final MazeCellModel exit = selectExitCell(matrix);
        exit.removeRightSide();
        return new MazeModel(matrix, enter, exit);
    }

}
