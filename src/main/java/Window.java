import javax.swing.*;
import java.awt.*;

public class Window {

    public static JFrame frame;

    public Window(String title, int width, int height, GameRules gameRules) {

        JPanel gameField = new BombGrid(gameRules);

        frame = new JFrame();

        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: почистить срань

        JPanel redPanel = new JPanel();

        redPanel.setLocation(Game.LEFT/2, Game.TOP/2);
        redPanel.setSize(40, 40);

        JPanel bluePanel = new JPanel();
        bluePanel.setSize(Game.COLUMNS * Game.BUTTON_HEIGHT,Game.ROWS * Game.BUTTON_HEIGHT);
        bluePanel.setPreferredSize(new Dimension(
                Game.COLUMNS * Game.BUTTON_HEIGHT,
                Game.ROWS * Game.BUTTON_HEIGHT));
        bluePanel.setLocation(Game.LEFT, Game.TOP);
        bluePanel.setLayout(new GridBagLayout());
        bluePanel.setVisible(true);
        bluePanel.add(gameField);

        frame.add(bluePanel);

        frame.setLayout(null);

        frame.add(bluePanel);
        frame.add(redPanel);


        // Center window on screen
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(33,35,39));

        frame.pack();
        frame.setVisible(true);

        frame.setSize(width, height);

    }
}
