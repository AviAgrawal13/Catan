import java.awt.*;

public class HouseImage {
    private CatanImage houseImage;
    private boolean isCity;
    private String playerColor;
    public static double scale = 0.5;

    public HouseImage(String playerColor, int x, int y) {
        this.houseImage = new CatanImage("/catanImages/" + playerColor + "House.png", x, y, 12, 12);
        this.playerColor = playerColor;
        this.isCity = false;
    }

    public void elevateToCity() {
        this.isCity = true;
        this.houseImage.setImage("/catanImages/" + playerColor + "City.png");
    }

    public void draw(Graphics2D g2) {
        houseImage.draw(g2);
    }

    public boolean equals(HouseImage other) {
        return this.houseImage.equals(other.houseImage);
    }
}
