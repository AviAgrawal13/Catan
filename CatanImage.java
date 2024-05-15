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

    /**
     * @param path The path of the image needing to be drawn
     * @param x1 The x coordinate of the image
     * @param y1 The y coordinate of the image
     * @param w The widtn of the image
     * @param l The length of the image
     * Constructor for a CatanImage
     */
    public CatanImage(String path, int x1, int y1, int w, int l) {
        this.setImage(path);
        this.x = x1;
        this.y = y1;
        this.width = w;
        this.length = l;
    }

    /**
     * @param path The path of the image needing to be drawn
     * Sets the bufferedImage to a specific path in order for the correct image to be shown
     */
    public void setImage(String path) {
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream(path)); //Reads the catanImages file for all the images needed
        } catch (IOException e) {
            System.out.println("something went wrong");
        }
    }

    /**
     * @param g2 the pen that allows for the drawing of images
     * Draws the CatanImage
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, width, length, null);
    }

    /**
     * @param other the other CatanImage needing to be checked if coordinates are equal
     * @return true if the images coordinates are the same and false if they are not
     * Checks if the coordinates of two CatanImages are the same
     */
    public boolean equals(CatanImage other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * @param x The x coordinate that the current x coordinate needs to be changed to
     * Sets the old x coordinate to the desired one
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y The y coordinate that the current y coordinate needs to be changed to
     * Sets the old y coordinate to the desired one
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return The cuurent x-coordinate of the image
     * Gets the current x-coordinate of the image
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return The cuurent y-coordinate of the image
     * Gets the current y-coordinate of the image
     */
    public int getY() {
        return this.y;
    }
}
