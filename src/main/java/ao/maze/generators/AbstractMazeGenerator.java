package ao.maze.generators;

import ao.maze.models.MazeModel;

public abstract class AbstractMazeGenerator {

    public abstract MazeModel generateMaze(int mazeWidth, int mazeHeight);

}
