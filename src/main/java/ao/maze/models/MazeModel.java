package ao.maze.models;

public class MazeModel {

    private final MazeCellModel[][] matrix;
    private final MazeCellModel enter;
    private final MazeCellModel exit;

    public MazeModel(MazeCellModel[][] matrix, MazeCellModel enter, MazeCellModel exit) {
        this.matrix = matrix;
        this.enter = enter;
        this.exit = exit;
    }

    public MazeCellModel[][] getMatrix() {
        return matrix;
    }

    public MazeCellModel getEnter() {
        return enter;
    }

    public MazeCellModel getExit() {
        return exit;
    }

}
