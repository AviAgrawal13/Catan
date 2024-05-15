import java.awt.*;

public class RoadImage {
    private CatanImage roadImage;
    private String playerColor;

    private int rotation;
    public static double scale = 0.5;

    /**
     * @param playerColor The color of the player
     * @param x The x coordinate of the house
     * @param y The y coordinate of the hosue
     * @param rotation The rotation or orientation of the road (For the slanted roads)
     * Constructor for roads
     */
    public RoadImage(String playerColor, int x, int y, int rotation) {
        if(rotation == -60) { //right slanted one
            this.roadImage = new CatanImage("/catanImages/" + playerColor + "Road-60-removebg-previewnew.png", x, y, 75, 55);
        } else if (rotation == 60) { //left slanted one
            this.roadImage = new CatanImage("/catanImages/" + playerColor + "Road60-removebg-previewnew.png", x, y, 75, 55);
        } else if (rotation == 0) { //normal vertical one
            this.roadImage = new CatanImage("/catanImages/" + playerColor + "Road.png", x, y, 8, 45);
        }
        this.playerColor = playerColor;
    }

    /**
     * @param g2 the pen that allows for the drawing of images
     * Draws the roads
     */
    public void draw(Graphics2D g2) {
        roadImage.draw(g2);
    }

}
