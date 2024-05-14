import java.awt.*;

public class TileImage {
    private CatanImage resourceImage;
    private CatanImage numberToken;
    public static double scale = 0.5;


    public TileImage(String resource, int token, int x, int y, CatanImage robberImage) {
        resourceImage = new CatanImage("/catanImages/" + resource + ".png", x, y, (int)(175 * scale), (int)(201*scale));
        // using x and y of image, develop some math thing for x and y of number token
        if (!resource.equals("desert")) {
            numberToken = new CatanImage("/catanImages/" + token + ".png", x+ (int)(63 * scale), y+ (int)(76*scale), (int)(49 * scale), (int)(49 * scale));
        } else {
            numberToken = robberImage;
            robberImage.setX(x + (int)(63 * scale));
            robberImage.setY(y + (int)(76*scale));
        }
    }

    public void draw(Graphics2D g2) {
        resourceImage.draw(g2);
        if (numberToken != null) {
            numberToken.draw(g2);
        }
    }
}
