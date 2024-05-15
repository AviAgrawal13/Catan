import java.util.ArrayList;

/**
 * Represents the main game controller managing players, game board, and game actions.
 */
public class Game {

    private ArrayList<Player> players;  // List of players participating in the game
    private Board gameBoard;            // Game board containing tiles, nodes, and edges
    private int currentPlayer = 0;      // Index of the current player taking their turn

    /**
     * Constructs a Game with the specified players and game board.
     * @param players List of players participating in the game
     * @param board Game board containing tiles, nodes, and edges
     */
    public Game(ArrayList<Player> players, Board board) {
        this.players = players;
        this.gameBoard = board;
    }

    /**
     * Simulates rolling two dice and returns the sum of the results.
     * @return Sum of two dice rolls (between 2 and 12)
     */
    public int rollDice() {
        int a = (int) (Math.random() * 6 + 1);
        int b = (int) (Math.random() * 6 + 1);
        return a + b;
    }

    /**
     * Distributes resources to players based on the dice roll.
     * @param dice Sum of the dice roll
     */
    public void distributeResources(int dice) {
        for (int i : gameBoard.getDiceToHex(dice)) {
            Tile t = gameBoard.getTile(i);
            for (Node n : gameBoard.getTileToNode(i)) {
                if (n.getHouse() != null) {
                    n.getHouse().getNameOfPlayer().updateMat(t.getType(), n.getHouse().getLevel());
                }
            }
        }
    }

    /**
     * Retrieves the color of the current player.
     * @return Color of the current player
     */
    public String getPlayerColor() {
        return this.players.get(currentPlayer).getColor();
    }

    /**
     * Retrieves the current player object.
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return this.players.get(currentPlayer);
    }

    /**
     * Ends the turn of the current player and moves to the next player in a circular manner.
     */
    public void endTurn() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Sets the current player index.
     * @param currentPlayer Index of the current player
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Initiates a bank trade for the current player.
     * @param resourceOld The type of resource to trade away
     * @param resourceNew The type of resource to receive in return
     * @return True if the trade is successful; false otherwise
     */
    public boolean bankTrade(String resourceOld, String resourceNew) {
        Player p = players.get(currentPlayer);
        if (p.getMat(resourceOld) > 2) {
            p.updateMat(resourceOld, -3);
            p.updateMat(resourceNew, 1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initiates a player-to-player resource trade.
     * @param playerSending Array of resource amounts offered by the current player
     * @param playerReceiving Array of resource amounts requested by the receiving player
     * @param receive Player who will receive the offered resources
     * @return True if the trade is successful; false otherwise
     */
    public boolean playerTrade(int[] playerSending, int[] playerReceiving, Player receive) {
        Player p = players.get(currentPlayer);
        if (canPerformPlayerTrade(p, playerSending) && canPerformPlayerTrade(receive, playerReceiving)) {
            playerTradeSetter(playerSending, playerReceiving, p);
            playerTradeSetter(playerReceiving, playerSending, receive);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Helper method to check if a player can perform a resource trade.
     * @param p Player attempting the trade
     * @param resources Array of resource amounts being traded
     * @return True if the player has enough resources for the trade; false otherwise
     */
    private boolean canPerformPlayerTrade(Player p, int[] resources) {
        return (resources[0] <= p.getMat("wood") && resources[1] <= p.getMat("brick") &&
                resources[2] <= p.getMat("sheep") && resources[3] <= p.getMat("wheat") &&
                resources[4] <= p.getMat("ore"));
    }

    /**
     * Executes the resource trade between two players.
     * @param playerSending Array of resource amounts offered by the current player
     * @param playerReceiving Array of resource amounts requested by the receiving player
     * @param p Player involved in the trade
     */
    private void playerTradeSetter(int[] playerSending, int[] playerReceiving, Player p) {
        p.updateMat("wood", -playerSending[0]);
        p.updateMat("brick", -playerSending[1]);
        p.updateMat("sheep", -playerSending[2]);
        p.updateMat("wheat", -playerSending[3]);
        p.updateMat("ore", -playerSending[4]);
        p.updateMat("wood", playerReceiving[0]);
        p.updateMat("brick", playerReceiving[1]);
        p.updateMat("sheep", playerReceiving[2]);
        p.updateMat("wheat", playerReceiving[3]);
        p.updateMat("ore", playerReceiving[4]);
    }

    /**
     * Simulates a player receiving a random resource from another player.
     * @param player Player from whom the resource is received
     * @return The type of resource received, or "broke" if the player has no resources to offer
     */
    public String getRandomResource(Player player) {
        Player p = players.get(currentPlayer);
        if (getNumberOfResources(player) > 0) {
            while (true) {
                int resource = (int) (Math.random() * 5);
                switch (resource) {
                    case 0:
                        if (player.getMat("wood") > 0) {
                            player.updateMat("wood", -1);
                            p.updateMat("wood", 1);
                            return "wood";
                        }
                    case 1:
                        if (player.getMat("brick") > 0) {
                            player.updateMat("brick", -1);
                            p.updateMat("brick", 1);
                            return "brick";
                        }
                    case 2:
                        if (player.getMat("sheep") > 0) {
                            player.updateMat("sheep", -1);
                            p.updateMat("sheep", 1);
                            return "sheep";
                        }
                    case 3:
                        if (player.getMat("wheat") > 0) {
                            player.updateMat("wheat", -1);
                            p.updateMat("wheat", 1);
                            return "wheat";
                        }
                    case 4:
                        if (player.getMat("ore") > 0) {
                            player.updateMat("ore", -1);
                            p.updateMat("ore", 1);
                            return "ore";
                        }
                }
            }
        }
        return "broke";
    }

    /**
     * Builds a house at the specified location for the current player.
     * @param x X-coordinate of the node location
     * @param y Y-coordinate of the node location
     * @param cp the catan panel
     * @return True if the house is successfully built; false otherwise
     */
    public boolean buildHouse(int x, int y, CatanPanel cp) {
        Player p = players.get(currentPlayer);
        if (p.getMat("wood") > 0 && p.getMat("brick") > 0 && p.getMat("wheat") > 0 && p.getMat("sheep") > 0 &&
                gameBoard.canPlaceHouse(x, y, p)) {
            gameBoard.addHouse(x, y, p);
            p.updateMat("wood", -1);
            p.updateMat("brick", -1);
            p.updateMat("wheat", -1);
            p.updateMat("sheep", -1);
            cp.updateHouse(x, y, p.getColor(), false);
            return true;
        }
        return false;
    }

    /**
     * Upgrades a house to a city at the specified location for the current player.
     * @param x X-coordinate of the node location
     * @param y Y-coordinate of the node location
     * @param cp the catan panel
     * @return True if the house is successfully upgraded; false otherwise
     */
    public boolean upgradeHouse(int x, int y, CatanPanel cp) {
        Player p = players.get(currentPlayer);
        if (p.getMat("wheat") > 1 && p.getMat("ore") > 2) {
            if (gameBoard.hasHouse(x, y, p)) {
                gameBoard.upgradeHouse(x, y);
                p.updateMat("wheat", -2);
                p.updateMat("ore", -3);
                cp.updateHouse(x, y, p.getColor(), true);
                return true;
            }
        }
        return false;
    }

    /**
     * Builds a road at the specified location for the current player.
     * @param x X-coordinate of the edge location
     * @param y Y-coordinate of the edge location
     * @param cp the catan panel
     * @return True if the road is successfully built; false otherwise
     */
    public boolean buildRoad(int x, int y, CatanPanel cp) {
        Player p = players.get(currentPlayer);
        if (p.getMat("wood") > 0 && p.getMat("brick") > 0 && gameBoard.canPlaceRoad(x, y, p)) {
            p.updateMat("wood", -1);
            p.updateMat("brick", -1);
            gameBoard.getEdge(x, y).addRoad(new Road(p));
            cp.updateRoad(x, y, p.getColor());
            return true;
        }
        return false;
    }

    /**
     * Allows the current player to buy a development card.
     * @return True if the purchase is successful; false otherwise
     */
    public boolean buyDevelopmentCard() {
        Player p = players.get(currentPlayer);
        if (p.getMat("sheep") > 0 && p.getMat("wheat") > 0 && p.getMat("ore") > 0) {
            p.updateMat("sheep", -1);
            p.updateMat("wheat", -1);
            p.updateMat("ore", -1);
            p.addDevelopmentCard((int) Math.floor(Math.random() * (4 + 1) + 0));
            return true;
        }
        return false;
    }

    /**
     * Executes the Year of Plenty development card, granting resources to the current player.
     * @param resources Array of resources to receive [wood, brick, sheep, wheat, ore]
     */
    public void developmentCardYearOfPlenty(int[] resources) {
        Player p = players.get(currentPlayer);
        p.updateMat("wood", resources[0]);
        p.updateMat("brick", resources[1]);
        p.updateMat("sheep", resources[2]);
        p.updateMat("wheat", resources[3]);
        p.updateMat("ore", resources[4]);
    }

    /**
     * Moves the robber to a specified tile and collects resources from adjacent players.
     * @param tile Index of the tile to move the robber to
     * @return List of players from whom resources can be stolen
     */
    public ArrayList<Player> developmentCardMoveRobber(int tile) {
        ArrayList<Player> playersToStealFrom = new ArrayList<>();
        if (gameBoard.moveRobber(tile)) {
            Node[] nodesOnTile = gameBoard.getTileToNode(tile);
            for (Node node : nodesOnTile) {
                if (node.hasHouse()) {
                    playersToStealFrom.add(node.getHouse().getNameOfPlayer());
                }
            }
            return playersToStealFrom;
        } else {
            return null;
        }
    }

    /**
     * Executes the Monopoly development card, allowing the current player to monopolize a specific resource.
     * That is, take all of that resource that is in play.
     * @param typeResource Type of resource to monopolize (e.g., "wood", "brick", "sheep", "wheat", "ore")
     */
    public void developmentCardMonopoly(String typeResource) {
        for (int i = 0; i < players.size(); i++) {
            if (!(i == currentPlayer) && players.get(i).getMat(typeResource) > 0) {
                players.get(currentPlayer).updateMat(typeResource, players.get(i).removeAllResources(typeResource));
            }
        }
    }

    /**
     * Executes the Victory Point development card, adding a victory point to the current player.
     */
    public void developmentCardVP() {
        players.get(currentPlayer).addVP(1);
    }

    /**
     * Calculates the total number of resources owned by a player.
     * @param p Player to calculate resources for
     * @return Total number of resources owned by the player
     */
    public int getNumberOfResources(Player p) {
        return p.getMat("wood") + p.getMat("brick") + p.getMat("sheep") + p.getMat("wheat") + p.getMat("ore");
    }

    /**
     * Checks if a player can drop a specific number of cards.
     * @param p Player attempting to drop cards
     * @param numOfCards Number of cards to be dropped
     * @param arr Array representing the number of each type of card to be dropped
     * @return True if the player can drop the specified cards; false otherwise
     */
    public boolean dropCards(Player p, int numOfCards, int[] arr) {
        int totalCards = 0;
        for (int i : arr) {
            totalCards += i;
        }
        if (totalCards == numOfCards && p.getMat("wood") >= arr[0] && p.getMat("brick") >= arr[1] &&
                p.getMat("sheep") >= arr[2] && p.getMat("wheat") >= arr[3] && p.getMat("ore") >= arr[4]) {
            p.updateMat("wood", -arr[0]);
            p.updateMat("brick", -arr[1]);
            p.updateMat("sheep", -arr[2]);
            p.updateMat("wheat", -arr[3]);
            p.updateMat("ore", -arr[4]);
            return true;
        } else {
            return false;
        }
    }
}
