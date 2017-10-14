package ao.maze.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class GamePanel extends JPanel {

    private static final int GAME_FIELD_X = 75;
    private static final int GAME_FIELD_Y = 10;

    private static final int PLAYER_SHAPE_RADIUS = 10;
    private static final Color PLAYER_COLOR = Color.RED;

    private Player player;
    private DesktopMaze maze;

    public GamePanel(int mazeWidth, int mazeHeight) {
        maze = new DesktopMaze(GAME_FIELD_X, GAME_FIELD_Y, mazeWidth, mazeHeight);
        player = new Player(PLAYER_SHAPE_RADIUS, PLAYER_COLOR);
        player.moveTo(maze.getEnterCell().getX(), maze.getEnterCell().getY());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int x = player.getX();
                int y = player.getY();
                if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
                    if (maze.getMatrix()[y][x - 1].getRightSide() == null) {
                        player.moveTo(x - 1, y);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
                    if (maze.getMatrix()[y - 1][x].getBottomSide() == null) {
                        player.moveTo(x, y - 1);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < mazeWidth - 1) {
                    if (maze.getMatrix()[y][x + 1].getLeftSide() == null) {
                        player.moveTo(x + 1, y);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y < mazeHeight - 1) {
                    if (maze.getMatrix()[y + 1][x].getTopSide() == null) {
                        player.moveTo(x, y + 1);
                    }
                }
                repaint();
                if (x == maze.getExitCell().getX() && y == maze.getExitCell().getY()) {
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

        DesktopMazeCell[][] mazeMatrix = maze.getMatrix();
        for (int y = 0; y < mazeMatrix.length; y++) {
            for (int x = 0; x < mazeMatrix[y].length; x++) {
                DesktopMazeCell cell = mazeMatrix[y][x];
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
                if (player.getY() == y && player.getX() == x) {
                    final int playerShapeLeftTopX = GAME_FIELD_X + DesktopMazeCell.CELL_SIDE_LENGTH * x + 5;
                    final int playerShapeLeftTopY = GAME_FIELD_Y + DesktopMazeCell.CELL_SIDE_LENGTH * y + 5;
                    Color defaultColor = graphics2D.getColor();
                    graphics2D.setColor(player.getColor());
                    graphics2D.fill(new Ellipse2D.Double(
                            playerShapeLeftTopX,
                            playerShapeLeftTopY,
                            PLAYER_SHAPE_RADIUS * 2,
                            PLAYER_SHAPE_RADIUS * 2)
                    );
                    graphics2D.setColor(defaultColor);
                }
            }
        }
    }

}
