package ao.maze.models;

public class MazeCellModel {

    private final int x;
    private final int y;
    private boolean leftSideExists;
    private boolean topSideExists;
    private boolean rightSideExists;
    private boolean bottomSideExists;
    private long visitsCount;

    public MazeCellModel(int x, int y) {
        this.x = x;
        this.y = y;
        leftSideExists = true;
        topSideExists = true;
        rightSideExists = true;
        bottomSideExists = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLeftSideExists() {
        return leftSideExists;
    }

    public boolean isTopSideExists() {
        return topSideExists;
    }

    public boolean isRightSideExists() {
        return rightSideExists;
    }

    public boolean isBottomSideExists() {
        return bottomSideExists;
    }

    public void removeLeftSide() {
        leftSideExists = false;
    }

    public void removeTopSide() {
        topSideExists = false;
    }

    public void removeRightSide() {
        rightSideExists = false;
    }

    public void removeBottomSide() {
        bottomSideExists = false;
    }

    public boolean isVisited() {
        return visitsCount > 0;
    }

    public void visit() {
        visitsCount++;
    }

}
