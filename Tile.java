/**
 * Represents a tile on the game board.
 */
public class Tile {
    private String type;        // Type of the tile (e.g., "Forest", "Hills", etc.)
    private boolean hasRobber;  // Indicates if the robber is currently on this tile
    private int tileNumber;     // Number associated with this tile (for game numbering)

    /**
     * Constructs a Tile with a specified type and number.
     * @param type The type of the tile (e.g., "Forest", "Hills", etc.)
     * @param number The number associated with this tile
     */
    public Tile(String type, int number) {
        this.type = type;
        this.tileNumber = number;
    }

    /**
     * Constructs a Tile with a specified number.
     * @param number The number associated with this tile
     */
    public Tile(int number) {
        this.tileNumber = number;
    }

    /**
     * Gets the type of the tile.
     * @return The type of the tile
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type of the tile.
     * @param type The type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the presence of the robber on this tile.
     * @param set True if the robber is on this tile; false otherwise
     */
    public void setRobberTile(boolean set) {
        this.hasRobber = set;
    }

    /**
     * Checks if the tile has the robber.
     * @return True if the tile has the robber; false otherwise
     */
    public boolean hasRobber() {
        return this.hasRobber;
    }
}
