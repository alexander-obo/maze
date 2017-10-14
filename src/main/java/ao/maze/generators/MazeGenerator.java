package ao.maze.generators;

import ao.maze.models.MazeModel;

public interface MazeGenerator {

    MazeModel generateMaze(int mazeWidth, int mazeHeight);

}
