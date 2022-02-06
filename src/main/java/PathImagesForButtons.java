public class PathImagesForButtons {

    public String iconPath;


    public PathImagesForButtons(int type, boolean discovered) {
        // discovered = true if button visible, false if was not
        //TODO: Change to ENUM
        // type:
        //    -2   - mine
        //    -1   - flag
        //     0   - empty
        //     1-8 - number of mines around

        this.iconPath = "src/main/resources/-3.png";

        if ((type <= 8) && (type >= -2) && discovered) {
            this.iconPath = "src/main/resources/" + type + ".png";
        } else if ((type == 9) && !discovered) {
            this.iconPath = "src/main/resources/hover.png";
        } else if (type == 10) {
            this.iconPath = "src/main/resources/explosion.png";
        }

        // TODO: Throw exception on else

    }

    public PathImagesForButtons(int type) {
        // discovered = true if button visible, false if was not
        //TODO: Change to ENUM
        // type:
        //    -2   - mine
        //    -1   - flag
        //     0   - empty
        //     1-8 - number of mines around

        if (type == -1) {
            this.iconPath = "src/main/resources/" + type + ".png";
        } else if (type == 10) {
            this.iconPath = "src/main/resources/explosion.png";
        } else if (type == 11) {
            this.iconPath = "src/main/resources/wrong.png";
        }

        // TODO: Throw exception on else

    }

    public String getIconPath() {
        return iconPath;
    }
}
