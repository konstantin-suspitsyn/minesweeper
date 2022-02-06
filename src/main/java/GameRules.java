import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameRules {

    private ArrayList<Sector> checked = new ArrayList<>();

    private int unOpenedCells = Game.ROWS * Game.COLUMNS;

    private ArrayList<Integer> toClick(int position) {
        // Returns list of indexes for Game to open
        ArrayList<Integer> checked = new ArrayList<>();
        Queue<Integer> toCheck = new LinkedList<>();

        toCheck.add(position);

        while (!toCheck.isEmpty()) {

            position = toCheck.poll();

            if ((!BombGrid.allCells.get(position).isActivated())) {
                if (!checked.contains(position)) {
                    checked.add(position);
                }

                if (BombGrid.allCells.get(position).getType() == 0) {
                    // Blank field
                    ArrayList<Integer> listOfSectors = BombGrid.listToCheckForBombs(position);
                    for (Integer indexPosition :
                            listOfSectors) {
                        if ((!BombGrid.allCells.get(indexPosition).isFlagged())
                                & (!checked.contains(indexPosition))
                                & (!BombGrid.allCells.get(indexPosition).isActivated())) {
                            checked.add(indexPosition);
                            if (BombGrid.allCells.get(indexPosition).getType() == 0) {
                                // Blank
                                toCheck.add(indexPosition);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(checked);
        return checked;
    }

    public void leftClick(Sector sector) {
        // What happens when we left-click

        // Position of minefield we clicked
        int currentPosition = sector.getPosition();

        if ((sector.isFlagged()) || (!sector.isPressable()) || (sector.isActivated())) {
            // You can not left-click on flagged field or opened field
            return;
        }


        if (sector.getType() == -2) {
            // Bomb
            for (Sector sectorField:
                 BombGrid.allCells) {
                sectorField.setActivated(true);
                if (sectorField.getPosition() == currentPosition) {
                    sectorField.setIcon(new ImageIcon(
                            new PathImagesForButtons(10).getIconPath()));
                } else if ((sectorField.isFlagged()) & (sectorField.getType() != -2)) {
                    sectorField.setIcon(new ImageIcon(
                            new PathImagesForButtons(11).getIconPath()));
                } else {
                    sectorField.setIcon(new ImageIcon(
                            new PathImagesForButtons(sectorField.getType(), sectorField.isActivated()).getIconPath()));
                }
            }
            // TODO: Say that gamer lost game
        } else if (sector.getType() == 0) {
            // Empty field
            for (Integer i:
                toClick(currentPosition)) {
                unOpenedCells--;
                Sector currentSector = BombGrid.allCells.get(i);
                currentSector.setActivated(true);
                currentSector.setIcon(new ImageIcon(new PathImagesForButtons(currentSector.getType(), true).getIconPath()));
            }
        } else if ((sector.getType() > 0) && ((sector.getType() <= 8))) {
            // Number cell
            unOpenedCells--;
            Sector currentSector = BombGrid.allCells.get(currentPosition);
            currentSector.setActivated(true);
            currentSector.setIcon(new ImageIcon(String.valueOf(new PathImagesForButtons(currentSector.getType(), true).getIconPath())));
        }
        System.out.println(unOpenedCells);
        if (unOpenedCells == Game.NUMBER_OF_MINES) {
            System.out.println("WON");

        }
    }

    public void rightClick(Sector sector) {
        // Setting a flag
        if (sector.isPressable() & (!sector.isActivated())) {
            sector.setFlagged(!sector.isFlagged());
            if (sector.isFlagged()) {
                sector.setIcon(new ImageIcon(new PathImagesForButtons(-1).getIconPath()));
            } else {
                sector.setIcon(new ImageIcon(new PathImagesForButtons(0, false).getIconPath()));
            }
        }
    }

}
