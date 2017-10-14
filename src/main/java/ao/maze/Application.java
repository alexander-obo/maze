package ao.maze;

import ao.maze.gui.GamePanel;

import javax.swing.*;

public class Application {

    private static final int FRAME_WIDTH = 620;
    private static final int FRAME_HEIGHT = 420;

    private static final int MAZE_WIDTH = 15;
    private static final int MAZE_HEIGHT = 11;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("MazeModel");
            frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

            /* Menu initializing */
            JMenuBar menuBar = new JMenuBar();
            JMenu optionsMenu = new JMenu("Options");
            JMenuItem fogOfWarMenuItem = new JCheckBoxMenuItem("Fog of war");
            optionsMenu.add(fogOfWarMenuItem);
            menuBar.add(optionsMenu);
            frame.setJMenuBar(menuBar);

            GamePanel gamePanel = new GamePanel(MAZE_WIDTH, MAZE_HEIGHT);
            frame.add(gamePanel);

            frame.addKeyListener(gamePanel.getKeyListeners()[0]);

            frame.setVisible(true);
        });
    }

}
