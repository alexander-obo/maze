package ao.maze.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private static final int GAME_FIELD_X = 75;
    private static final int GAME_FIELD_Y = 10;

    private static final int PLAYER_SHAPE_RADIUS = 10;
    private static final Color PLAYER_COLOR = Color.RED;

    private Player player;
    private Maze maze;

    public GamePanel(int mazeWidth, int mazeHeight) {
        player = new Player(PLAYER_SHAPE_RADIUS, PLAYER_COLOR);
        maze = new Maze(GAME_FIELD_X, GAME_FIELD_Y, mazeWidth, mazeHeight, player);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int x = maze.getPlayerCurrentCellX();
                int y = maze.getPlayerCurrentCellY();
                MazeCell currentPlayerCell = maze.getMatrix()[y][x];
                if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
                    if (maze.getMatrix()[y][x - 1].getRightSide() == null) {
                        maze.setPlayerCurrentCellX(x - 1);
                        currentPlayerCell.setPlayer(null);
                        maze.getMatrix()[y][x - 1].setPlayer(player);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
                    if (maze.getMatrix()[y - 1][x].getBottomSide() == null) {
                        maze.setPlayerCurrentCellY(y - 1);
                        currentPlayerCell.setPlayer(null);
                        maze.getMatrix()[y - 1][x].setPlayer(player);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < mazeWidth - 1) {
                    if (maze.getMatrix()[y][x + 1].getLeftSide() == null) {
                        maze.setPlayerCurrentCellX(x + 1);
                        currentPlayerCell.setPlayer(null);
                        maze.getMatrix()[y][x + 1].setPlayer(player);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y < mazeHeight - 1) {
                    if (maze.getMatrix()[y + 1][x].getTopSide() == null) {
                        maze.setPlayerCurrentCellY(y + 1);
                        currentPlayerCell.setPlayer(null);
                        maze.getMatrix()[y + 1][x].setPlayer(player);
                    }
                }
                repaint();
                if (maze.getMatrix()[maze.getPlayerCurrentCellY()][maze.getPlayerCurrentCellX()] == maze.getExitCell()) {
                    JOptionPane.showMessageDialog(getParent(), "You win!");
                    // TODO check if this is correct way to shut down application
                    System.exit(0);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        MazeCell[][] mazeMatrix = maze.getMatrix();
        for (int y = 0; y < mazeMatrix.length; y++) {
            for (int x = 0; x < mazeMatrix[y].length; x++) {
                MazeCell cell = mazeMatrix[y][x];
                if (cell.getLeftSide() != null) {
                    graphics2D.draw(cell.getLeftSide());
                }
                if (cell.getTopSide() != null) {
                    graphics2D.draw(cell.getTopSide());
                }
                if (cell.getRightSide() != null) {
                    graphics2D.draw(cell.getRightSide());
                }
                if (cell.getBottomSide() != null) {
                    graphics2D.draw(cell.getBottomSide());
                }
                if (cell.isPlayerInCell()) {
                    int playerNewX = GAME_FIELD_X + MazeCell.CELL_SIDE_LENGTH * x + 5;
                    int playerNewY = GAME_FIELD_Y + MazeCell.CELL_SIDE_LENGTH * y + 5;
                    player.moveTo(playerNewX, playerNewY);

                    Color defaultColor = graphics2D.getColor();
                    graphics2D.setColor(player.getColor());
                    graphics2D.fill(player.getShape());
                    graphics2D.setColor(defaultColor);
                }
            }
        }
    }

}
