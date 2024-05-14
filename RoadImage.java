import java.awt.*;

public class RoadImage {
    private CatanImage roadImage;
    private String playerColor;

    private int rotation;
    public static double scale = 0.5;

    public RoadImage(String playerColor, int x, int y, int rotation) {
        if(rotation == -60) { //right slanted one
            this.roadImage = new CatanImage("/catanImages/" + playerColor + "Road-60-removebg-previewnew.png", x, y, 75, 55); //change to good path
        } else if (rotation == 60) { //left slanted one
            this.roadImage = new CatanImage("/catanImages/" + playerColor + "Road60-removebg-previewnew.png", x, y, 75, 55); //change to good path
        } else if (rotation == 0) { //normal vertical one
            this.roadImage = new CatanImage("/catanImages/" + playerColor + "Road.png", x, y, 8, 45); //change to good path
        }
        this.playerColor = playerColor;
    }

    public void draw(Graphics2D g2) {
        roadImage.draw(g2);
    }

}
