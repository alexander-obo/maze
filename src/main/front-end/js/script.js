const ARROW_LEFT_KEY_CODE = 37;
const ARROW_UP_KEY_CODE = 38;
const ARROW_RIGHT_KEY_CODE = 39;
const ARROW_DOWN_KEY_CODE = 40;

const W_KEY_CODE = 87;
const A_KEY_CODE = 65;
const S_KEY_CODE = 83;
const D_KEY_CODE = 68;

function generateMazeModel(mazeWidth, mazeHeight) {
    const matrix = [];
    for (var y = 0; y < mazeHeight; y++) {
        matrix[y] = [];
        for (var x = 0; x < mazeWidth; x++) {
            matrix[y][x] = new MazeCellModel(x, y);
        }
    }
    const enter = selectEnterCell(matrix);
    enter.removeLeftSide();
    var currentCell = enter;
    currentCell.visit();
    const stack = [];
    while (isUnvisitedCellsExist(matrix)) {
        const unvisitedNeighbors = getUnvisitedNeighbors(matrix, currentCell);
        if (unvisitedNeighbors.length > 0) {
            stack.push(currentCell);
            const randomNeighborIndex = Math.floor((Math.random() * (unvisitedNeighbors.length)));
            const unvisitedNeighbor = unvisitedNeighbors[randomNeighborIndex];
            if (currentCell.x === unvisitedNeighbor.x + 1) {
                currentCell.removeLeftSide();
                unvisitedNeighbor.removeRightSide();
            } else if (currentCell.x === unvisitedNeighbor.x - 1) {
                currentCell.removeRightSide();
                unvisitedNeighbor.removeLeftSide();
            } else if (currentCell.y === unvisitedNeighbor.y + 1) {
                currentCell.removeTopSide();
                unvisitedNeighbor.removeBottomSide();
            } else {
                currentCell.removeBottomSide();
                unvisitedNeighbor.removeTopSide();
            }
            currentCell = unvisitedNeighbor;
            currentCell.visit();
        } else if (stack.length > 0) {
            currentCell = stack.pop();
        } else {
            // with proper algorithm implementation and proper input data this case should never be executed
            alert("Something went wrong. Please, check algorithm and input data");
        }
    }
    const exit = selectExitCell(matrix);
    exit.removeRightSide();
    return new MazeModel(matrix, enter, exit);
}

function generateMazeView(mazeModel) {
    var matrix = mazeModel.matrix;
    var tableBody = document.createElement("tbody");
    for (var i = 0; i < matrix.length; i++) {
        var row = document.createElement("tr");
        for (var j = 0; j < matrix[0].length; j++) {
            var cell = document.createElement("td");
            cell.classList.add("cell");

            if (!matrix[i][j].leftSideExists) {
                cell.style.borderLeft = "none";
            }
            if (!matrix[i][j].topSideExists) {
                cell.style.borderTop = "none";
            }
            if (!matrix[i][j].rightSideExists) {
                cell.style.borderRight = "none";
            }
            if (!matrix[i][j].bottomSideExists) {
                cell.style.borderBottom = "none";
            }

            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }

    var table = document.createElement("table");
    table.style.borderCollapse = "collapse";
    table.appendChild(tableBody);
    var gamePanel = document.getElementById("gamePanel");
    while (gamePanel.firstChild) {
        gamePanel.removeChild(gamePanel.firstChild);
    }
    gamePanel.appendChild(table);
    return table;
}

function movePlayer(x, y) {
    if (document.getElementById("player")) {
        document.getElementById("player").remove();
    }
    const circle = document.createElement("div");
    circle.id = "player";
    circle.classList.add("player");
    player.x = x;
    player.y = y;
    table.rows[player.y].cells[player.x].appendChild(circle);
}

function keyDownEventHandler(event) {
    const matrix = mazeModel.matrix;
    const x = player.x;
    const y = player.y;

    if (event.keyCode === ARROW_LEFT_KEY_CODE || event.keyCode === A_KEY_CODE) {
        if (x !== 0 && !matrix[y][x].leftSideExists && !matrix[y][x - 1].rightSideExists) {
            movePlayer(x - 1, y);
        }
    } else if (event.keyCode === ARROW_UP_KEY_CODE || event.keyCode === W_KEY_CODE) {
        if (y !== 0 && !matrix[y][x].topSideExists && !matrix[y - 1][x].bottomSideExists) {
            movePlayer(x, y - 1);
        }
    } else if (event.keyCode === ARROW_RIGHT_KEY_CODE || event.keyCode === D_KEY_CODE) {
        if (x !== (matrix[0].length - 1) && !matrix[y][x].rightSideExists && !matrix[y][x + 1].leftSideExists) {
            movePlayer(x + 1, y);
        }
    } else if (event.keyCode === ARROW_DOWN_KEY_CODE || event.keyCode === S_KEY_CODE) {
        if (y !== (matrix.length - 1) && !matrix[y][x].bottomSideExists && !matrix[y + 1][x].topSideExists) {
            movePlayer(x, y + 1);
        }
    }
}

document.onkeydown = keyDownEventHandler;

function keyUpEventHandler(event) {
    if (mazeModel.exit.x === player.x && mazeModel.exit.y === player.y) {
        alert("You win!");
        var width = document.getElementById('width').value;
        var height = document.getElementById('height').value;
        startGame(width, height);
    }
}

document.onkeyup = keyUpEventHandler;

function startGame(width, height) {
    mazeModel = generateMazeModel(width, height);
    player = new Player(mazeModel.enter.x, mazeModel.enter.y);
    table = generateMazeView(mazeModel);
    movePlayer(player.x, player.y);

}

var mazeModel;
var player;
var table;

var width = document.getElementById('width').value;
var height = document.getElementById('height').value;
startGame(width, height);
