import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sector extends JButton {

    // Type of button (Empty/Flag/Bomb/Number)
    private int type;
    private boolean pressable;
    private int position;

    private boolean flagged;

    // Before press of the button it should be inactive
    private boolean activated = false;

    private GameRules gameRules;

    public Sector(ImageIcon icon, int type, boolean pressable, boolean flagged, int position, GameRules gameRules) {
        super(icon);
        this.type = type;
        this.pressable = pressable;
        this.position = position;
        this.flagged = flagged;
        this.gameRules = gameRules;

        this.setPreferredSize(new Dimension(Game.BUTTON_HEIGHT, Game.BUTTON_HEIGHT));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.setIconTextGap(0);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            // Add mouse events only for not activated sectors
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isActivated() & !isFlagged()) {
                    super.mouseEntered(e);
                    setIcon(new ImageIcon(new PathImagesForButtons(9, false).getIconPath()));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // If cell is not discovered, it will be highlighted on hover
                if (!isActivated() & !isFlagged()) {
                    super.mouseExited(e);
                    setIcon(new ImageIcon(new PathImagesForButtons(0, false).getIconPath()));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    rightClick();
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    leftClick();
                }

            }
        });


    }


    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isPressable() {
        return pressable;
    }

    public void setPressable(boolean pressable) {
        this.pressable = pressable;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void rightClick() {
        gameRules.rightClick(this);
    }

    public void leftClick() {
        gameRules.leftClick(this);
    }

}
