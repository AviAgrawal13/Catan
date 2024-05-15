import java.awt.*;

public class HouseImage {
    private CatanImage houseImage;
    private boolean isCity;
    private String playerColor;
    public static double scale = 0.5;

    /**
     * @param playerColor The color of the player
     * @param x The x coordinate of the house
     * @param y The y coordinate of the house
     * Constructor for a house
     */
    public HouseImage(String playerColor, int x, int y) {
        this.houseImage = new CatanImage("/catanImages/" + playerColor + "House.png", x, y, 12, 12); //Makes a new house of the inputted playerColor
        this.playerColor = playerColor;
        this.isCity = false;
    }

    /**
     * Converts the house to a city
     */
    public void elevateToCity() {
        this.isCity = true;
        this.houseImage.setImage("/catanImages/" + playerColor + "City.png"); //Sets the house to the correpoding color's city
    }

    /**
     * @param g2 the pen that allows for the drawing of images
     * Draws the house on the GUI
     */
    public void draw(Graphics2D g2) {
        houseImage.draw(g2);
    }

    /**
     *
     * @param x The x coordinate that needs to be checked if same as the house's x coordinate
     * @param y The y coordinate that needs to be checked if same as the house's y coordinate
     * @return true if it is the same false if it is not
     * Checks if the coordinates of the house is equal to the inputted coordinates
     */
    public boolean equals(int x, int y) {
        return this.houseImage.getX() == x && this.houseImage.getY() == y;
    }
}
