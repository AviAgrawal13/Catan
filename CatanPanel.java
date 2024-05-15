import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;

public class CatanPanel extends JPanel implements Runnable{

    // Dimensions of the panel containing the display
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;
    // The panel will update 60 times per second
    private final int FPS = 60;
    // Allows for the creation of a game loop that draws at 60 FPS
    private Thread gameThread;
    // All the game pieces that need to be drawn onto the panel
    private LinkedList<TileImage> tileImages;
    private LinkedList<HouseImage> houseImages;
    private LinkedList<RoadImage> roadImages;
    private CatanImage robberImage;
    private CatanImage coordinateSystem;

    /**
     * @param tileArray an array of tiles
     * @param hexToDice a hashmap of corresponding dice numbers and tile numbers
     * Constructor for the GUI Panel
     */
    public CatanPanel(Tile[] tileArray, HashMap<Integer, Integer> hexToDice) {
        //Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.tileImages = new LinkedList<>();
        this.houseImages = new LinkedList<>();
        this.roadImages = new LinkedList<>();
        this.robberImage = new CatanImage("/catanImages/robber.png", 100, 200, (int)(49 * TileImage.scale), (int)(49 * TileImage.scale));
        this.coordinateSystem = new CatanImage("/catanImages/coordinates.png", 600, 50, 541, 492);
        setupTiles(tileArray, hexToDice);
    }

    /**
     * @param x The x coordinate of the house that needs to be removed
     * @param y The y coordinate of the house that needs to be removed
     * Removes a house on the board in order to upgrade into a city
     */
    public void removeHouseAt(int x, int y) {
        for (int i = 0; i < houseImages.size(); i++) {
            if (houseImages.get(i).equals(x, y)) { //Interates through the houseImage LinkedList and finds the desired house to remove
                houseImages.remove(i);
                break;
            }
        }
    }

    /**
     * @param x The x coordinate of the house
     * @param y The y coordinate of the house
     * @param color The color of the player that is placing the house
     * Takes in the x and y coordinate and the color in order to place the house on the needed spot
     */
    public void updateHouse(int x, int y, String color, boolean elevateToCity) {
        int[][][] coordinates = {
                // y = 0
                {null, null, {195, 115}, {238, 95}, {280, 115}, {323, 95}, {365, 115}, {408, 95}, {450, 115}},
                // y = 1
                {null, {156, 190}, {195, 170}, {237, 190}, {280, 170}, {323, 190}, {365, 170}, {408, 190}, {450, 170}, {492, 190}},
                // y = 2
                {{113, 265}, {156, 245}, {195, 265}, {237, 245}, {280, 265}, {323, 245}, {365, 265}, {408, 245}, {450, 265}, {492, 245}, {534, 265}},
                // y = 3
                {{113, 320}, {156, 340}, {195, 320}, {237, 340}, {280, 320}, {323, 340}, {365, 320}, {408, 340}, {450, 320}, {492, 340}, {534, 320}},
                // y = 4
                {null, {156, 395}, {195, 415}, {237, 395}, {280, 415}, {323, 395}, {365, 415}, {408, 395}, {450, 415}, {492, 395}},
                // y = 5
                {null, null, {195, 470}, {238, 490}, {280, 470}, {323, 490}, {365, 470}, {408, 490}, {450, 470}}
        };

        if (y >= 0 && y < coordinates.length && x >= 0 && x < coordinates[y].length) {
            int[] coord = coordinates[y][x];
            if (coord != null) {
                HouseImage temp = new HouseImage(color, coord[0], coord[1]);
                if (elevateToCity) {
                    removeHouseAt(coord[0], coord[1]);
                    temp.elevateToCity();
                }
                houseImages.add(temp);
            }
        }
    }

    /**
     * @param x The x coordinate of the road
     * @param y The y coordinate of the road
     * @param color The color of the player that is placing the road
     * Takes in the x and y coordinate and the color in order to place the road on the needed spot
     */
    public void updateRoad(int x, int y, String color) {
        int[][][] coordinates = {
                // y = 0
                {null, null, null, null, null, {185, 85, -60}, null, {225, 85, 60}, null, {270, 85, -60}, null, {310, 85, 60}, null, {355, 85, -60}, null, {395, 85, 60}},
                // y = 1
                {null, null, null, null, {200, 127, 0}, null, null, null, {280, 127, 0}, null, null, null, {368, 127, 0}, null, null, null, {455, 127, 0}},
                // y = 2
                {null, null, null, {142, 162, -60}, null, {185, 162, 60}, null, {225, 162, -60}, null, {270, 162, 60}, null, {310, 162, -60}, null, {355, 162, 60}, null, {395, 162, -60}, null, {442, 162, 60}},
                // y = 3
                {null, null, {158, 202, 0}, null, null, null, {240, 202, 0}, null, null, null, {322, 202, 0}, null, null, null, {405, 202, 0}, null, null, null, {492, 202, 0}},
                // y = 4
                {null, {100, 240, -60}, null, {140, 240, 60}, null, {185, 240, -60}, null, {225, 240, 60}, null, {270, 240, -60}, null, {310, 240, 60}, null, {355, 240, -60}, null, {395, 240, 60}, null, {440, 240, -60}, null, {480, 240, 60}},
                // y = 5
                {{115, 280, 0}, null, null, null, {200, 280, 0}, null, null, null, {280, 280, 0}, null, null, null, {368, 280, 0}, null, null, null, {455, 280, 0}, null, null, null, {535, 280, 0}},
                // y = 6
                {null, {100, 312, 60}, null, {140, 312, -60}, null, {185, 312, 60}, null, {225, 312, -60}, null, {270, 312, 60}, null, {310, 312, -60}, null, {355, 312, 60}, null, {395, 312, -60}, null, {440, 312, 60}, null, {480, 312, -60}},
                // y = 7
                {null, null, {158, 355, 0}, null, null, null, {240, 355, 0}, null, null, null, {322, 355, 0}, null, null, null, {410, 355, 0}, null, null, null, {495, 355, 0}},
                // y = 8
                {null, null, null, {142, 390, 60}, null, {185, 390, -60}, null, {225, 390, 60}, null, {270, 390, -60}, null, {310, 390, 60}, null, {355, 390, -60}, null, {395, 390, 60}, null, {442, 390, -60}},
                // y = 9
                {null, null, null, null, {200, 430, 0}, null, null, null, {280, 430, 0}, null, null, null, {368, 430, 0}, null, null, null, {452, 430, 0}},
                // y = 10
                {null, null, null, null, null, {185, 465, 60}, null, {225, 465, -60}, null, {270, 465, 60}, null, {310, 465, -60}, null, {355, 465, 60}, null, {395, 465, -60}}
        };

        if (y >= 0 && y < coordinates.length && x >= 0 && x < coordinates[y].length) {
            int[] coord = coordinates[y][x];
            if (coord != null) {
                roadImages.add(new RoadImage(color, coord[0], coord[1], coord[2]));
            }
        }
    }

    /**
     * @param tileArray an array of tiles
     * @param hexToDice a hashmap of corresponding dice numbers and tile numbers
     * Adds all the correctly sized game tiles to the tileImage LinkedList to display
     */
    public void setupTiles(Tile[] tileArray, HashMap<Integer, Integer> hexToDice) {
        int[] xCoordinates = {
                200, 200 + (int)(TileImage.scale * 170), 200 + (int)(2 * TileImage.scale * 170),
                200 - (int)(0.5 * TileImage.scale * 170), 200 + (int)(0.5 * TileImage.scale * 174), 200 + (int)(3 * 0.5 * TileImage.scale * 170), 200 + (int)(5 * 0.5 * TileImage.scale * 170),
                200 - (int)(2 * 0.5 * TileImage.scale * 170), 200, 200 + (int)(2 * 0.5 * TileImage.scale * 170), 200 + (int)(2 * TileImage.scale * 170), 200 + (int)(2 * 1.5 * TileImage.scale * 170),
                200 - (int)(0.5 * TileImage.scale * 170), 200 + (int)(0.5 * TileImage.scale * 174), 200 + (int)(3 * 0.5 * TileImage.scale * 170), 200 + (int)(5 * 0.5 * TileImage.scale * 170),
                200, 200 + (int)(TileImage.scale * 170), 200 + (int)(2 * TileImage.scale * 170)
        };
        int[][] tileIndices = {
                {1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19}
        };
        for (int i = 0; i < tileIndices.length; i++) {
            for (int j = 0; j < tileIndices[i].length; j++) {
                int indexInTileArray = tileIndices[i][j];
                int x = xCoordinates[indexInTileArray-1];
                int y = 100 + (int)(i *TileImage.scale * 152);
                TileImage temp = new TileImage(tileArray[indexInTileArray-1].getType(), hexToDice.get(indexInTileArray), x, y, robberImage);
                tileImages.add(temp);
            }
        }
    }

    /**
     * Starts the game and allows for live updates
     */
    public void launchGame() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * The game loop that constantly updates the GUI by painting or redrawing the screen
     */
    @Override
    public void run() {
        //Game loop redraws and updates screen 60 times per second
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (this.gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                repaint(); // calls paintComponent
                delta--;
            }
        }
    }

    /**
     * @param x The x coordinate of the moved robber
     * @param y The y coordinate of the moved robber
     */
    public void updateRobberPosition(int x, int y) {
        robberImage.setX(x);
        robberImage.setY(y);
    }

    /**
     * @param g the pen that allows for the drawing of images
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        for (TileImage ti : tileImages) { //Draws the tiles
            ti.draw(g2);
        }

        for (RoadImage ri : roadImages) { //Draws the roads
            ri.draw(g2);
        }

        for (HouseImage hi : houseImages) { //Draws the houses
            hi.draw(g2);
        }

        robberImage.draw(g2);

        coordinateSystem.draw(g2);

    }
}
