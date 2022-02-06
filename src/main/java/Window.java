import javax.swing.*;
import java.awt.*;

public class Window {

    public static JFrame frame;

    public Window(String title, int width, int height, GameRules gameRules) {

        JPanel gameField = new BombGrid(gameRules);

        frame = new JFrame();

        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel scorePanel = new FlaggedBombsCount();
        JPanel restartPanel = new RestartGame();

        JPanel gameFieldWrapper = new JPanel();
        gameFieldWrapper.setSize(Game.COLUMNS * Game.BUTTON_HEIGHT,Game.ROWS * Game.BUTTON_HEIGHT);
        gameFieldWrapper.setPreferredSize(new Dimension(
                Game.COLUMNS * Game.BUTTON_HEIGHT,
                Game.ROWS * Game.BUTTON_HEIGHT));
        gameFieldWrapper.setLocation(Game.LEFT, Game.TOP);
        gameFieldWrapper.setLayout(new GridBagLayout());
        gameFieldWrapper.setVisible(true);
        gameFieldWrapper.add(gameField);

        frame.add(gameFieldWrapper);

        frame.setLayout(null);

        frame.add(gameFieldWrapper);
        frame.add(scorePanel);
        frame.add(restartPanel);


        // Center window on screen
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(33,35,39));

        frame.setResizable(false);

        frame.pack();

        frame.setVisible(true);

        frame.setSize(new Dimension(width, height));

    }

    public static void changeTitle(String message) {
        frame.setTitle(message);
    }

    public static void stop() {
        frame.dispose();
    }

}
