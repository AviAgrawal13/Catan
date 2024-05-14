import javax.swing.JPanel;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CatanPanel extends JPanel implements Runnable{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    private LinkedList<TileImage> tileImages;
    private LinkedList<HouseImage> houseImages;
    private LinkedList<RoadImage> roadImages;
    private CatanImage robberImage;

    public CatanPanel(Tile[] ta, HashMap<Integer, Integer> hd) {
        //Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
        this.tileImages = new LinkedList<>();
        this.houseImages = new LinkedList<>();
        this.roadImages = new LinkedList<>();                            // for this x and y we need to fix it based on which tile is desert
        this.robberImage = new CatanImage("/catanImages/robber.png", 100, 200,(int)(49 * TileImage.scale), (int)(49 * TileImage.scale));
        setupTiles(ta, hd);
        String o = "orange";
//        for (int i = 2; i < 9; i++) {
//            updateHouse(i, 0, o);
//        }
//        for (int i = 1; i < 10; i++) {
//            updateHouse(i, 1, o);
//        }
//        for (int i = 0; i < 11; i++) {
//            updateHouse(i, 2, o);
//        }
//        for (int i = 0; i < 11; i++) {
//            updateHouse(i, 3, o);
//        }
//        for (int i = 1; i < 10; i++) {
//            updateHouse(i, 4, o);
//        }
//        for (int i = 2; i < 9; i++) {
//            updateHouse(i, 5, o);
//        }
        //updateRoad(5, 0);
        //addHouses();
        //addRoads();
        updateRoad(5, 0, "blue");
    }

    public void updateCity(int x, int y, String color) {
        if (y == 0) {
            if (x ==  2) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 195, 115);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 3) {
                removeHouseAt(238, 95);
                HouseImage houseImage = new HouseImage(color, 238, 95);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 4) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 280, 115);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 5) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 323, 95);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 6) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 365, 115);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 7) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 408, 95);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 8) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 450, 115);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            }
        } else if (y == 1) {
            if (x == 1) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 156, 190);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 2) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 195, 170);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 3) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 237, 190);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 4) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 280, 170);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 5) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 323, 190);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 6) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 365, 170);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 7) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 408, 190);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 8) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 450, 170);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 9) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 492, 190);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            }
        } else if (y == 2) {
            if (x == 0) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 113, 265);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 1) {
                removeHouseAt(195, 115);
                HouseImage houseImage = new HouseImage(color, 156, 245);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 2) {
                HouseImage houseImage = new HouseImage(color, 195, 265);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 3) {
                HouseImage houseImage = new HouseImage(color, 237, 245);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 4) {
                HouseImage houseImage = new HouseImage(color, 280, 265);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 5) {
                HouseImage houseImage = new HouseImage(color, 323, 245);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 6) {
                HouseImage houseImage = new HouseImage(color, 365, 265);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 7) {
                HouseImage houseImage = new HouseImage(color, 408, 245);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 8) {
                HouseImage houseImage = new HouseImage(color, 450, 265);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 9) {
                HouseImage houseImage = new HouseImage(color, 492, 245);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 10) {
                HouseImage houseImage = new HouseImage(color, 534, 265);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            }
        } else if (y == 3) {
            if (x == 0) {
                HouseImage houseImage = new HouseImage(color, 113, 320);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 1) {
                HouseImage houseImage = new HouseImage(color, 156, 340);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 2) {
                HouseImage houseImage = new HouseImage(color, 195, 320);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 3) {
                HouseImage houseImage = new HouseImage(color, 237, 340);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 4) {
                HouseImage houseImage = new HouseImage(color, 280, 320);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 5) {
                HouseImage houseImage = new HouseImage(color, 323, 340);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 6) {
                HouseImage houseImage = new HouseImage(color, 365, 320);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 7) {
                HouseImage houseImage = new HouseImage(color, 408, 340);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 8) {
                HouseImage houseImage = new HouseImage(color, 450, 320);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 9) {
                HouseImage houseImage = new HouseImage(color, 492, 340);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 10) {
                HouseImage houseImage = new HouseImage(color, 534, 320);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            }
        } else if (y == 4) {
            if (x == 1) {
                HouseImage houseImage = new HouseImage(color, 156, 395);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 2) {
                HouseImage houseImage = new HouseImage(color, 195, 415);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 3) {
                HouseImage houseImage = new HouseImage(color, 237, 395);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 4) {
                HouseImage houseImage = new HouseImage(color, 280, 415);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 5) {
                HouseImage houseImage = new HouseImage(color, 323, 395);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 6) {
                HouseImage houseImage = new HouseImage(color, 365, 415);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 7) {
                HouseImage houseImage = new HouseImage(color, 408, 395);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 8) {
                HouseImage houseImage = new HouseImage(color, 450, 415);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 9) {
                HouseImage houseImage = new HouseImage(color, 492, 395);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            }
        } else if (y == 5) {
            if (x ==  2) {
                HouseImage houseImage = new HouseImage(color, 195, 470);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 3) {
                HouseImage houseImage = new HouseImage(color, 238, 490);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 4) {
                HouseImage houseImage = new HouseImage(color, 280, 470);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 5) {
                HouseImage houseImage = new HouseImage(color, 323, 490);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 6) {
                HouseImage houseImage = new HouseImage(color, 365, 470);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 7) {
                HouseImage houseImage = new HouseImage(color, 408, 490);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            } else if (x == 8) {
                HouseImage houseImage = new HouseImage(color, 450, 470);
                houseImage.elevateToCity();
                houseImages.add(houseImage);
            }
        }
    } // this might not work, whatll happen is the city and house will be present

    public void removeHouseAt(int x, int y) {
        for (int i = 0; i < houseImages.size(); i++) {
            if (houseImages.get(i).equals(x, y)) {
                houseImages.remove(i);
                break;
            }
        }
    }

    public void updateHouse(int x, int y, String color) {
        if (y == 0) {
            if (x ==  2) {
                houseImages.add(new HouseImage(color, 195, 115));
            } else if (x == 3) {
                houseImages.add(new HouseImage(color, 238, 95));
            } else if (x == 4) {
                houseImages.add(new HouseImage(color, 280, 115));
            } else if (x == 5) {
                houseImages.add(new HouseImage(color, 323, 95));
            } else if (x == 6) {
                houseImages.add(new HouseImage(color, 365, 115));
            } else if (x == 7) {
                houseImages.add(new HouseImage(color, 408, 95));
            } else if (x == 8) {
                houseImages.add(new HouseImage(color, 450, 115));
            }
        } else if (y == 1) {
            if (x == 1) {
                houseImages.add(new HouseImage(color, 156, 190));
            } else if (x == 2) {
                houseImages.add(new HouseImage(color, 195, 170));
            } else if (x == 3) {
                houseImages.add(new HouseImage(color, 237, 190));
            } else if (x == 4) {
                houseImages.add(new HouseImage(color, 280, 170));
            } else if (x == 5) {
                houseImages.add(new HouseImage(color, 323, 190));
            } else if (x == 6) {
                houseImages.add(new HouseImage(color, 365, 170));
            } else if (x == 7) {
                houseImages.add(new HouseImage(color, 408, 190));
            } else if (x == 8) {
                houseImages.add(new HouseImage(color, 450, 170));
            } else if (x == 9) {
                houseImages.add(new HouseImage(color, 492, 190));
            }
        } else if (y == 2) {
            if (x == 0) {
                houseImages.add(new HouseImage(color, 113, 265));
            } else if (x == 1) {
                houseImages.add(new HouseImage(color, 156, 245));
            } else if (x == 2) {
                houseImages.add(new HouseImage(color, 195, 265));
            } else if (x == 3) {
                houseImages.add(new HouseImage(color, 237, 245));
            } else if (x == 4) {
                houseImages.add(new HouseImage(color, 280, 265));
            } else if (x == 5) {
                houseImages.add(new HouseImage(color, 323, 245));
            } else if (x == 6) {
                houseImages.add(new HouseImage(color, 365, 265));
            } else if (x == 7) {
                houseImages.add(new HouseImage(color, 408, 245));
            } else if (x == 8) {
                houseImages.add(new HouseImage(color, 450, 265));
            } else if (x == 9) {
                houseImages.add(new HouseImage(color, 492, 245));
            } else if (x == 10) {
                houseImages.add(new HouseImage(color, 534, 265));
            }
        } else if (y == 3) {
            if (x == 0) {
                houseImages.add(new HouseImage(color, 113, 320));
            } else if (x == 1) {
                houseImages.add(new HouseImage(color, 156, 340));
            } else if (x == 2) {
                houseImages.add(new HouseImage(color, 195, 320));
            } else if (x == 3) {
                houseImages.add(new HouseImage(color, 237, 340));
            } else if (x == 4) {
                houseImages.add(new HouseImage(color, 280, 320));
            } else if (x == 5) {
                houseImages.add(new HouseImage(color, 323, 340));
            } else if (x == 6) {
                houseImages.add(new HouseImage(color, 365, 320));
            } else if (x == 7) {
                houseImages.add(new HouseImage(color, 408, 340));
            } else if (x == 8) {
                houseImages.add(new HouseImage(color, 450, 320));
            } else if (x == 9) {
                houseImages.add(new HouseImage(color, 492, 340));
            } else if (x == 10) {
                houseImages.add(new HouseImage(color, 534, 320));
            }
        } else if (y == 4) {
            if (x == 1) {
                houseImages.add(new HouseImage(color, 156, 395));
            } else if (x == 2) {
                houseImages.add(new HouseImage(color, 195, 415));
            } else if (x == 3) {
                houseImages.add(new HouseImage(color, 237, 395));
            } else if (x == 4) {
                houseImages.add(new HouseImage(color, 280, 415));
            } else if (x == 5) {
                houseImages.add(new HouseImage(color, 323, 395));
            } else if (x == 6) {
                houseImages.add(new HouseImage(color, 365, 415));
            } else if (x == 7) {
                houseImages.add(new HouseImage(color, 408, 395));
            } else if (x == 8) {
                houseImages.add(new HouseImage(color, 450, 415));
            } else if (x == 9) {
                houseImages.add(new HouseImage(color, 492, 395));
            }
        } else if (y == 5) {
            if (x ==  2) {
                houseImages.add(new HouseImage(color, 195, 470));
            } else if (x == 3) {
                houseImages.add(new HouseImage(color, 238, 490));
            } else if (x == 4) {
                houseImages.add(new HouseImage(color, 280, 470));
            } else if (x == 5) {
                houseImages.add(new HouseImage(color, 323, 490));
            } else if (x == 6) {
                houseImages.add(new HouseImage(color, 365, 470));
            } else if (x == 7) {
                houseImages.add(new HouseImage(color, 408, 490));
            } else if (x == 8) {
                houseImages.add(new HouseImage(color, 450, 470));
            }
        }
    }

    public void updateRoad(int x, int y, String color) {
        if (y == 0) {
            if (x == 5) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 7) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 9) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 11) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 13) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 15) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            }
        } else if (y == 1) {
            if(x == 4) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 8) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 12) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 16) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            }
        } else if (y == 2) {
            if (x == 3) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 5) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 7) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 9) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 11) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 13) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 15) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 17) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            }
        } else if (y == 3) {
            if (x == 2) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 6) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 10) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 14) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 18) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            }
        } else if (y == 4) {
            if (x == 1) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 3) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 5) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 7) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 9) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 11) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 13) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 15) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            } else if (x == 17) {
                roadImages.add(new RoadImage(color, 220, 100, -60)); //right slant
            } else if (x == 19) {
                roadImages.add(new RoadImage(color, 220, 100, 60)); //left slant
            }
        } else if (y == 5) {
            if (x == 0) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 4) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 8) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 12) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 16) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 20) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            }
        } else if (y == 6) {
            if (x == 1) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 3) {
                roadImages.add(new RoadImage(color, 220, 100, -0));
            } else if (x == 5) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 7) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 9) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 11) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 13) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 15) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 17) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 19) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            }
        } else if (y == 7) {
            if (x == 2) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 6) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 10) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 14) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 18) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            }
        } else if (y == 8) {
            if (x == 3) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 5) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 7) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 9) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 11) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 13) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 15) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 17) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            }
        } else if (y == 9) {
            if(x == 4) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 8) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 12) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            } else if (x == 16) {
                roadImages.add(new RoadImage(color, 220, 100, 0));
            }
        } else if (y == 10) {
            if (x == 5) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 7) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 9) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 11) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            } else if (x == 13) {
                roadImages.add(new RoadImage(color, 220, 100, 60));
            } else if (x == 15) {
                roadImages.add(new RoadImage(color, 220, 100, -60));
            }
        }
    }
    public void setupTiles(Tile[] ta, HashMap<Integer, Integer> hd) {
        // hardcode all tile imgs and num imgs and add to list
        //first row - 3
        tileImages.add(new TileImage(ta[0].getType(), hd.get(1), 200, 100, robberImage));
        tileImages.add(new TileImage(ta[1].getType(), hd.get(2), 200 + (int)(TileImage.scale * 170), 100, robberImage));
        tileImages.add(new TileImage(ta[2].getType(), hd.get(3), 200 + (int)(2 * TileImage.scale * 170), 100, robberImage));

        //second row - 4
        tileImages.add(new TileImage(ta[3].getType(), hd.get(4), 200 - (int)(0.5 * TileImage.scale * 170), 100 + (int)(TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[4].getType(), hd.get(5), 200 + (int)(0.5 * TileImage.scale * 174), 100 + (int)(TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[5].getType(), hd.get(6), 200 + (int)(3 * 0.5 * TileImage.scale * 170), 100 + (int)(TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[6].getType(), hd.get(7), 200 + (int)(5 * 0.5 * TileImage.scale * 170), 100 + (int)(TileImage.scale * 152), robberImage));

        //third row - 5
        tileImages.add(new TileImage(ta[7].getType(), hd.get(8), 200 - (int)(2 * 0.5 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[8].getType(), hd.get(9), 200, 100 + (int)(2 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[9].getType(), hd.get(10), 200 + (int)(2 * 0.5 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[10].getType(), hd.get(11), 200 + (int)(2 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[11].getType(), hd.get(12), 200 + (int)(2 * 1.5 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152), robberImage));

        //fourth row - 4
        tileImages.add(new TileImage(ta[12].getType(), hd.get(13), 200 - (int)(0.5 * TileImage.scale * 170), 100 + (int)(3 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[13].getType(), hd.get(14), 200 + (int)(0.5 * TileImage.scale * 174), 100 + (int)(3 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[14].getType(), hd.get(15), 200 + (int)(3 * 0.5 * TileImage.scale * 170), 100 + (int)(3 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[15].getType(), hd.get(16), 200 + (int)(5 * 0.5 * TileImage.scale * 170), 100 + (int)(3 * TileImage.scale * 152), robberImage));

        //fifth row - 3
        tileImages.add(new TileImage(ta[16].getType(), hd.get(17), 200, 100 + (int)(4 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[17].getType(), hd.get(18), 200 + (int)(TileImage.scale * 170), 100 + (int)(4 * TileImage.scale * 152), robberImage));
        tileImages.add(new TileImage(ta[18].getType(), hd.get(19), 200 + (int)(2 * TileImage.scale * 170), 100 + (int)(4 * TileImage.scale * 152), robberImage));

    }

//    public void addHouses() {
//        houseImages.add(new HouseImage("orange", 195, 120));
//    }

//    public void addRoads() {
//        roadImages.add(new RoadImage("red", 199, 135));
//    }

    public void launchGame() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

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
                update();
                repaint(); // calls paintComponent
                delta--;
            }
        }
    }

    private void update() {

    }

    public void updateRobberPosition(int x, int y) {
        robberImage.setX(x);
        robberImage.setY(y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        for (TileImage ti : tileImages) {
            ti.draw(g2);
        }

        for (HouseImage hi : houseImages) {
            hi.draw(g2);
        }

        for (RoadImage ri : roadImages) {
            ri.draw(g2);
        }

        robberImage.draw(g2);

    }
}