/**
 * Represents a node on a game map grid where a house can potentially be placed.
 */
public class Node {
    private int x;           // X-coordinate of the node on the grid
    private int y;           // Y-coordinate of the node on the grid
    private House house = null;  // House object placed on this node (if any)

    /**
     * Constructs a Node with specified coordinates.
     * @param y The y-coordinate of the node
     * @param x The x-coordinate of the node
     */
    public Node(int y, int x) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the house placed on this node.
     * @return The House object placed on this node, or null if no house is present
     */
    public House getHouse() {
        if (this.house == null) {
            return null;
        }
        return this.house;
    }

    /**
     * Adds a house to this node.
     * @param house The House object to be added to this node
     */
    public void addHouse(House house) {
        this.house = house;
    }

    /**
     * Checks if this node has a house placed on it.
     * @return True if a house is present on this node; false otherwise
     */
    public boolean hasHouse() {
        return this.house != null;
    }
}
