/**
 * The Road class represents a road in a game environment.
 * Roads are associated with a player who traverses them.
 */
public class Road {
    private Player player; // Player object associated with this road

    /**
     * Constructs a Road object with the specified player.
     *
     * @param player The player associated with this road
     */
    public Road(Player player){
        this.player = player; // Assign the player to this road
    }

    /**
     * Retrieves the the player associated with this road.
     *
     * @return The player associated with this road
     */
    public Player getNameOfPlayer(){
        return this.player; // Return the player associated with this road
    }
}
