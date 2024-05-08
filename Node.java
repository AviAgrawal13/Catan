
public class Node {
    private int x;
    private int y;
    private House house = null;

    public Node(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public House getHouse() {
        if(this.house == null) {
            return null;
        }
        return this.house;
    }
    public void addHouse(House house) {
        this.house = house;
    }

    public boolean hasHouse() {
        return this.house != null;
    }
}
