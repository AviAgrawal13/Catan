import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CatanImage {
    private BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int length;

    public CatanImage(String path, int x1, int y1, int w, int l) {
        this.setImage(path);
        this.x = x1;
        this.y = y1;
        this.width = w;
        this.length = l;
    }

    public void setImage(String path) {
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, length, null);
    }

    public boolean equals(CatanImage other) {
        return (this.x == other.x && this.y == other.y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
