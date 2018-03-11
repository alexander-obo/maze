function selectEnterCell(matrix) {
    const x = 0;
    const y = Math.floor((Math.random() * (matrix.length)));
    return matrix[y][x];
}

function selectExitCell(matrix) {
    const x = matrix[0].length - 1;
    const y = Math.floor((Math.random() * (matrix.length)));
    return matrix[y][x];
}

function isUnvisitedCellsExist(matrix) {
    for (var rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
        const row = matrix[rowIndex];
        for (var columnIndex = 0; columnIndex < row.length; columnIndex++) {
            const cell = row[columnIndex];
            if (!cell.isVisited()) {
                return true;
            }
        }
    }
    return false;
}

function getUnvisitedNeighbors(matrix, cell) {
    const unvisitedNeighbors = [];
    const x = cell.x;
    const y = cell.y;

    if (x !== 0 && x !== matrix[y].length - 1) {
        if (!matrix[y][x - 1].isVisited()) {
            unvisitedNeighbors.push(matrix[y][x - 1]);
        }
        if (!matrix[y][x + 1].isVisited()) {
            unvisitedNeighbors.push(matrix[y][x + 1]);
        }
    } else if (x === 0) {
        if (!matrix[y][x + 1].isVisited()) {
            unvisitedNeighbors.push(matrix[y][x + 1]);
        }
    } else {
        if (!matrix[y][x - 1].isVisited()) {
            unvisitedNeighbors.push(matrix[y][x - 1]);
        }
    }

    if (y !== 0 && y !== matrix.length - 1) {
        if (!matrix[y - 1][x].isVisited()) {
            unvisitedNeighbors.push(matrix[y - 1][x]);
        }
        if (!matrix[y + 1][x].isVisited()) {
            unvisitedNeighbors.push(matrix[y + 1][x]);
        }
    } else if (y === 0) {
        if (!matrix[y + 1][x].isVisited()) {
            unvisitedNeighbors.push(matrix[y + 1][x]);
        }
    } else {
        if (!matrix[y - 1][x].isVisited()) {
            unvisitedNeighbors.push(matrix[y - 1][x]);
        }
    }

    return unvisitedNeighbors;
}
