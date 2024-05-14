/**
 * Represents a player in a game, managing resources, achievements, development cards, and victory points.
 */
public class Player {

    private int wood = 0;                       // Amount of wood resources owned by the player
    private int brick = 0;                      // Amount of brick resources owned by the player
    private int sheep = 0;                      // Amount of sheep resources owned by the player
    private int wheat = 0;                      // Amount of wheat resources owned by the player
    private int ore = 0;                        // Amount of ore resources owned by the player
    private int VP = 0;                         // Victory points accumulated by the player
    private boolean hasLongestRoad = false;         // If player has longest road
    private int lengthOfRoads = 1;    // Indicates if the player has the road achievement
    private String color;                       // Color assigned to the player
    private int[] developmentCards = new int[5]; // Array to track counts of different development cards
    private int knightsPlayed = 0;

    /**
     * Constructs a Player with initial resource amounts and assigned color.
     * @param wood Initial amount of wood resources
     * @param brick Initial amount of brick resources
     * @param sheep Initial amount of sheep resources
     * @param wheat Initial amount of wheat resources
     * @param ore Initial amount of ore resources
     * @param color Color assigned to the player
     */
    public Player(int wood, int brick, int sheep, int wheat, int ore, String color) {
        this.wood = wood;
        this.brick = brick;
        this.sheep = sheep;
        this.wheat = wheat;
        this.ore = ore;
        this.color = color;
    }

    /**
     * Retrieves an array containing the current amounts of each resource owned by the player.
     * @return Array of resource amounts: [wood, brick, sheep, wheat, ore]
     */
    public int[] getMatArray() {
        return new int[]{wood, brick, sheep, wheat, ore};
    }

    /**
     * Retrieves the array of counts for each type of development card owned by the player.
     * @return Array of development card counts
     */
    public int[] getDevPlayer() {
        return developmentCards;
    }

    /**
     * Retrieves the amount of a specific type of resource owned by the player.
     * @param material The type of resource (e.g., "wood", "brick", "sheep", "wheat", "ore")
     * @return The amount of the specified resource
     */
    public int getMat(String material) {
        return switch (material) {
            case "wood" -> this.wood;
            case "brick" -> this.brick;
            case "sheep" -> this.sheep;
            case "wheat" -> this.wheat;
            case "ore" -> this.ore;
            default -> -1;  // Return -1 if an invalid material is specified
        };
    }

    /**
     * Updates the amount of a specific type of resource owned by the player.
     * @param material The type of resource to update (e.g., "wood", "brick", "sheep", "wheat", "ore")
     * @param amount The amount to add (positive) or subtract (negative)
     */
    public void updateMat(String material, int amount) {
        switch (material) {
            case "wood" -> this.wood += amount;
            case "brick" -> this.brick += amount;
            case "sheep" -> this.sheep += amount;
            case "wheat" -> this.wheat += amount;
            case "ore" -> this.ore += amount;
        }
    }

    /**
     * Sets the amount of a specific type of resource owned by the player.
     * @param material The type of resource to set (e.g., "wood", "brick", "sheep", "wheat", "ore")
     * @param amount The new amount of the specified resource
     */
    public void setPlayerResources(String material, int amount) {
        switch (material) {
            case "wood" -> this.wood = amount;
            case "brick" -> this.brick = amount;
            case "sheep" -> this.sheep = amount;
            case "wheat" -> this.wheat = amount;
            case "ore" -> this.ore = amount;
        }
    }

     /*
     * Development card types:
     *   0: Knight
     *   1: Road Building (allows building 2 roads)
     *   2: Year of Plenty
     *   3: Monopoly
     *   4: Victory Point
     */

    /**
     * Adds a development card of the specified type to the player's collection.
     * @param typeOfCard The type of development card to add
     */
    public void addDevelopmentCard(int typeOfCard) {
        ++developmentCards[typeOfCard];
    }

    /**
     * Removes a development card of the specified type from the player's collection. 
     * If the player uses 3 knights, it gives the user 2 VP's due to the achievement.
     * 
     * @param typeOfCard The type of development card to remove
     */
    public void subtractDevelopmentCard(int typeOfCard) {
        --developmentCards[typeOfCard];
        if(typeOfCard == 0) {
            knightsPlayed++;
        }
        if(knightsPlayed == 3) {
            addVP(2);
        }
    }

    /**
     * Checks if the player can use a development card of the specified type.
     * If able to use, the card count is reduced by one.
     * @param typeOfCard The type of development card to use
     * @return True if the player can use the card; false otherwise
     */
    public boolean canUseDevelopmentCard(int typeOfCard) {
        if (developmentCards[typeOfCard] > 0) {
            developmentCards[typeOfCard]--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes all of a specific type of resource owned by the player and returns the amount removed.
     * @param typeOfResource The type of resource to remove (e.g., "wood", "brick", "sheep", "wheat", "ore")
     * @return The amount of the specified resource that was removed
     */
    public int removeAllResources(String typeOfResource) {
        int amountOfResources = this.getMat(typeOfResource);
        this.setPlayerResources(typeOfResource, 0);
        return amountOfResources;
    }

    /**
     * Adds victory points to the player's total.
     * @param amount The number of victory points to add
     */
    public void addVP(int amount) {
        this.VP += amount;
    }

    /**
     * Retrieves the current total of victory points accumulated by the player.
     * @return The total number of victory points
     */
    public int getVPs() {
        if(hasLongestRoad){
            return this.VP + 2;
        }
        return this.VP;
    }

    /**
     * Retrieves the color assigned to the player.
     * @return The color of the player
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Updates length of longest road.
     * @param length longest length
     */
    public void updateLongestRoad(int length){
        this.lengthOfRoads = length;
    }

    /**
     * Returns the longest road of the player
     * @return longest length
     */
    public int getLongestRoad(){
        return this.lengthOfRoads;
    }

    public boolean hasLongRoad(){
        return this.hasLongestRoad;
    }

    /**
     * Sets the longest road acheivement to the boolean that is passed including
     * @param ach the new value of hasLongestRoad
     */
    public void setRoadAchievement(boolean ach){
        this.hasLongestRoad = ach;
    }
}
