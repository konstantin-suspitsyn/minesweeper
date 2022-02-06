import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestartGame extends JPanel {

    public RestartGame() {

        JButton buttonRestart = new JButton(new ImageIcon("src/main/resources/restart_button.png"));

        setSize( 2 * Game.BUTTON_HEIGHT, 2 * Game.BUTTON_HEIGHT);
        setLocation(Game.LEFT + 1 + (Game.COLUMNS - 2) * Game.BUTTON_HEIGHT, Game.BUTTON_HEIGHT);
        setBackground(new Color(33,35,39));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, (int) Game.BUTTON_HEIGHT / 2));

        buttonRestart.setPreferredSize(new Dimension(Game.BUTTON_HEIGHT, Game.BUTTON_HEIGHT));
        buttonRestart.setBorder(new EmptyBorder(0,0,0,0));
        buttonRestart.setFocusPainted(false);
        buttonRestart.setBorderPainted(false);
        buttonRestart.setContentAreaFilled(false);
        buttonRestart.setVerticalTextPosition(0);
        buttonRestart.setHorizontalTextPosition(0);
        buttonRestart.setIconTextGap(0);
        buttonRestart.setMargin(new Insets(0, 0, 0, 0));
        buttonRestart.setContentAreaFilled(false);

        buttonRestart.addMouseListener(new MouseAdapter() {
            // Add mouse events only for not activated sectors
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonRestart.setIcon(new ImageIcon("src/main/resources/restart_button_bright.png"));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // If cell is not discovered, it will be highlighted on hover
                super.mouseEntered(e);
                buttonRestart.setIcon(new ImageIcon("src/main/resources/restart_button.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Window.stop();
                BombGrid.allCells.clear();
                BombGrid.clearBombsPositions();
                Game run = new Game();
            }
        });

        add(buttonRestart);

    }
}
