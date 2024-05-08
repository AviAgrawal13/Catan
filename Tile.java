
public class Tile {
    private String type;

    private boolean hasRobber;

    private int tileNumber;

    public Tile(String type, int number){
        this.type = type;
        this.tileNumber = number;
    }

    public Tile(int number){
        this.tileNumber = number;

    }

    public String getType(){
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return this.tileNumber;
    }

    public int getRobberTileNumber() {
        if(this.hasRobber) {
            return this.tileNumber;
        } else {
            return -1;
        }
    }

    public void setRobberTile(boolean set) {
        this.hasRobber = set;
    }

    public boolean hasRobber() {
        return !this.hasRobber;
    }

}
