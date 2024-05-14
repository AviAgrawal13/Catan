/**
 * Represents a house (settlement or city) owned by a player on a game map.
 */
public class House {
    private int level;        // Indicates the level of the house (1 for settlement, 2 for city)
    private Player player;    // The player who owns this house

    /**
     * Constructs a House with an initial level of settlement (level 1) owned by the specified player.
     * @param player The player who owns this house
     */
    public House(Player player) {
        this.level = 1;
        this.player = player;
    }

    /**
     * Gets the level of this house.
     * @return The level of the house (1 for settlement, 2 for city)
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the player who owns this house.
     * @return The player who owns this house
     */
    public Player getNameOfPlayer() {
        return player;
    }

    /**
     * Increases the level of this house from settlement (level 1) to city (level 2).
     */
    public void increaseLevel() {
        level++;
    }
}
