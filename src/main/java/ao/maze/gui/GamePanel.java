package ao.maze.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private static final int GAME_FIELD_X = 75;
    private static final int GAME_FIELD_Y = 10;

    private static final int PLAYER_SHAPE_RADIUS = 10;
    private static final int PLAYER_START_POSITION_X = 50;
    private static final int PLAYER_START_POSITION_Y = 165;

    private Maze maze;
    private Player player;

    public GamePanel(int mazeWidth, int mazeHeight) {
        maze = new Maze(GAME_FIELD_X, GAME_FIELD_Y, mazeWidth, mazeHeight);
        player = new Player(PLAYER_SHAPE_RADIUS);
        player.moveTo(PLAYER_START_POSITION_X, PLAYER_START_POSITION_Y);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.stepLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.stepUp();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.stepRight();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.stepDown();
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        for (MazeCell[] row : maze.getMatrix()) {
            for (MazeCell cell : row) {
                graphics2D.draw(cell.getLeftSide());
                graphics2D.draw(cell.getTopSide());
                graphics2D.draw(cell.getRightSide());
                graphics2D.draw(cell.getBottomSide());
            }
        }

        graphics2D.setColor(Color.RED);
        graphics2D.fill(player.getShape());
    }

}
