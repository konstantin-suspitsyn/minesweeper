public class Game {

    // Size of one button
    public static final int BUTTON_HEIGHT;
    // Top margin
    public static final int TOP;
    // Bottom margin
    public static final int BOTTOM;
    // Left margin
    public static final int LEFT;
    // Right margin
    public static final int RIGHT;

    // Number of buttons in row
    public static final int ROWS;
    // Number of buttons in column
    public static final int COLUMNS;

    // Total number of mines
    public static final int NUMBER_OF_MINES;

    public static final int WIDTH_CORRECTION;
    public static final int HEIGHT_CORRECTION;

    public GameRules gameRules = new GameRules();


    static {
        BUTTON_HEIGHT = 22;
        TOP = 82;
        BOTTOM = 50;
        LEFT = 50;
        RIGHT = 50;
        ROWS = 10;
        COLUMNS = 10;
        NUMBER_OF_MINES = 10;
        WIDTH_CORRECTION = 14;
        HEIGHT_CORRECTION = 37;
    }

    public Game() {
        int windowHeight = BOTTOM + TOP + ROWS * BUTTON_HEIGHT + HEIGHT_CORRECTION;
        int windowWidth = LEFT + RIGHT + COLUMNS * BUTTON_HEIGHT + WIDTH_CORRECTION;
        Window run = new Window("Minesweeper", windowWidth, windowHeight, gameRules);
    }



}
