import javax.swing.*;
import java.awt.*;

public class FlaggedBombsCount extends JPanel {

    private static JLabel leftFields;

    public FlaggedBombsCount() {

        JLabel flagsLabel = new JLabel(new ImageIcon("design/bomb_count_icon.png"));
        leftFields = new JLabel(new ImageIcon("design/left_unopened_count_icon.png"));

        this.leftCountFields = Game.ROWS * Game.COLUMNS;

        setSize( 6 * Game.BUTTON_HEIGHT, 2 * Game.BUTTON_HEIGHT);
        setLocation(Game.LEFT + 1, Game.BUTTON_HEIGHT);
        setBackground(new Color(51, 54, 60));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, (int) Game.BUTTON_HEIGHT / 2));
        flagsLabel.setText(": " + String.valueOf(Game.NUMBER_OF_MINES));
        flagsLabel.setForeground(Color.WHITE);
        flagsLabel.setFont(new Font("Arial", Font.BOLD, 15));

        leftFields.setText(": " + String.valueOf(leftCountFields));
        leftFields.setForeground(Color.WHITE);
        leftFields.setFont(new Font("Arial", Font.BOLD, 15));

        add(flagsLabel);
        add(leftFields);
        revalidate();
        repaint();
    }

    public int getFlaggedCount() {
        return leftCountFields;
    }

    public void setFlaggedCount(int flaggedCount) {
        this.leftCountFields = flaggedCount;
    }

    public static void changeScore(int flaggedCount) {
        leftFields.setText(": " + String.valueOf(flaggedCount));
    }

    private int leftCountFields;

}
