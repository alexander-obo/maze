function MazeCellModel(x, y) {
    this.x = x;
    this.y = y;
    this.leftSideExists = true;
    this.topSideExists = true;
    this.rightSideExists = true;
    this.bottomSideExists = true;
    this.visitsCount = 0;
    this.removeLeftSide = function () {
        this.leftSideExists = false;
    };
    this.removeTopSide = function () {
        this.topSideExists = false;
    };
    this.removeRightSide = function () {
        this.rightSideExists = false;
    };
    this.removeBottomSide = function () {
        this.bottomSideExists = false;
    };
    this.isVisited = function () {
        return this.visitsCount > 0;
    };
    this.visit = function () {
        this.visitsCount++;
    }
}

function MazeModel(matrix, enter, exit) {
    this.matrix = matrix;
    this.enter = enter;
    this.exit = exit;
}

function Player(x, y) {
    this.x = x;
    this.y = y;
}
