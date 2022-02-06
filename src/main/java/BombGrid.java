import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class BombGrid extends JPanel {

    // Bomb positions stored here
    private static ArrayList<Integer> bombsPositions = new ArrayList<>();
    // All type positions stored here
    public static ArrayList<Sector> allCells = new ArrayList<>();

    private static void addMines() {
        // Creating position of mines
        while (bombsPositions.size() < Game.NUMBER_OF_MINES) {
            int minePosition = (int) (Math.random() * Game.ROWS * Game.COLUMNS);
            if (!bombsPositions.contains(minePosition)) {
                bombsPositions.add(minePosition);
            }
        }
    }

    public static ArrayList<Integer> listToCheckForBombs (int i) {
        // Generates list of all indexes to Check
        ArrayList<Integer> checkPositions = new ArrayList<>();

        int minIndex = 0;
        int maxIndex = Game.COLUMNS * Game.ROWS - 1;

        if (i % Game.ROWS == 0) {
            // If index of a cell is left
            // * * *
            // i * *
            // * * *
            checkPositions.add(i - Game.ROWS);
            checkPositions.add(i - Game.ROWS + 1);
            checkPositions.add(i + 1);
            checkPositions.add(i + Game.ROWS + 1);
            checkPositions.add(i + Game.ROWS);
        } else if ((i + 1) % Game.ROWS == 0) {
            // If index of a cell is right
            //  * * *
            //  * * i
            //  * * *
            checkPositions.add(i - Game.ROWS);
            checkPositions.add(i - Game.ROWS - 1);
            checkPositions.add(i - 1);
            checkPositions.add(i + Game.ROWS - 1);
            checkPositions.add(i + Game.ROWS);
        } else {
            // If index of a cell in center
            // * * *
            // * i *
            // * * *
            checkPositions.add(i - Game.ROWS);
            checkPositions.add(i - Game.ROWS - 1);
            checkPositions.add(i - 1);
            checkPositions.add(i + Game.ROWS - 1);
            checkPositions.add(i + Game.ROWS);
            checkPositions.add(i - Game.ROWS + 1);
            checkPositions.add(i + 1);
            checkPositions.add(i + Game.ROWS + 1);
        }

        // Cut indexes out of boundaries
        checkPositions = checkPositions.stream()
                .filter(val -> val >= minIndex)
                .filter(val -> val <= maxIndex)
                .collect(Collectors.toCollection(ArrayList::new));

        return checkPositions;
    }

    private static void addAllFields(GameRules gameRules) {

        boolean pressable = true;

        // Iterate through all fields and change type

        for (int i = 0; i < Game.ROWS * Game.COLUMNS; i++) {
            int type = 0;

            if (bombsPositions.contains(i)) {
                // -2 is mine
                type = -2;
            } else {
                ArrayList<Integer> checkIndexes = listToCheckForBombs(i);
                for (int c :
                        checkIndexes) {
                    if (bombsPositions.contains(c)) {
                        type = type + 1;
                    }
                }
            }
            allCells.add(new Sector(
                    new ImageIcon(new PathImagesForButtons(type, false).getIconPath()),
                    type,
                    pressable,
                    false, i, gameRules));
        }
    }

    private void addGridPositions() {
        // Adding data to JPanel
        for (Sector allCell : allCells) {
            this.add(allCell);
        }
    }

    public BombGrid(GameRules gameRules) {
        addMines();
        addAllFields(gameRules);
        addGridPositions();
        // Adding dimensions of Grid
        this.setPreferredSize(new Dimension(
                Game.COLUMNS * Game.BUTTON_HEIGHT,
                Game.ROWS * Game.BUTTON_HEIGHT));
        this.setLocation(Game.TOP, Game.LEFT);
        this.setLayout(new GridLayout(Game.COLUMNS, Game.ROWS, 0, 0));

    }


}
