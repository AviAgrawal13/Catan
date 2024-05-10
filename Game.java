import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;

    private Board gameBoard;

    private int currentPlayer = 0;

    private boolean dicerolled = false;

    public Game(ArrayList<Player> players, Board board) {
        this.players = players;
        this.gameBoard = board;
    }

    public int rollDice(){
        int a = (int) (Math.random() * 6 + 1);
        int b = (int) (Math.random() * 6 + 1);
        return a + b;
    }

    public void distributeResources(int dice){
        for(int i : gameBoard.getDiceToHex(dice)){
            Tile t = gameBoard.getTile(i);
            for(Node n : gameBoard.getTileToNode(i)){
                if(n.getHouse() != null){
                    n.getHouse().getNameOfPlayer().updateMat(t.getType(), n.getHouse().getLevel());
                }
            }
        }
    }

    public String getPlayerColor(){
        return this.players.get(currentPlayer).getColor();
    }

    public Player getCurrentPlayer(){
        return this.players.get(currentPlayer);
    }

    public void endTurn(){
        currentPlayer = (currentPlayer + 1) % 4;        
    }

    public void setCurrentPlayer(int currentPlayer){
        this.currentPlayer = currentPlayer;
    }

    public boolean bankTrade(String resourceold, String resourcenew){
        Player p = players.get(currentPlayer);
        if(p.getMat(resourceold) > 2 ) {
            p.updateMat(resourceold, -3);
            p.updateMat(resourcenew, 1);
            return true;
        } else {
            return false;
        }
    }
    public boolean playerTrade(int[] playersending, int[] playerrecieving, Player recieve){
        Player p = players.get(currentPlayer);
        // wood, brick, sheep, wheat, ore 
        if((playersending[0] <= p.getMat("wood") && playersending[1] <= p.getMat("brick") && playersending[2] <= p.getMat("sheep") && playersending[3] <= p.getMat("wheat") && playersending[4] <= p.getMat("ore"))
        && (playerrecieving[0] <= recieve.getMat("wood") && playerrecieving[1] <= recieve.getMat("brick") && playerrecieving[2] <= recieve.getMat("sheep") && playerrecieving[3] <= recieve.getMat("wheat") && playerrecieving[4] <= recieve.getMat("ore"))) {
            playerTradeSetter(playersending, playerrecieving, p);
            playerTradeSetter(playerrecieving, playersending, recieve);
            return true;
        } else {
            return false;
        }
    } 

    public String getRandomResource(Player player) {
        Player p = players.get(currentPlayer);
        if(getNumberOfResources(player) > 0) {
            while(true) {
                int resource = (int) (Math.random() * 5);
                switch(resource) {
                    case 0:
                        if(player.getMat("wood") > 0) {
                            player.updateMat("wood", -1);
                            p.updateMat("wood", 1);
                            return "wood";
                        }
                    case 1:
                        if(player.getMat("brick") > 0) {
                            player.updateMat("brick", -1);
                            p.updateMat("brick", 1);
                            return "brick";
                        }
                    case 2:
                        if(player.getMat("sheep") > 0) {
                            player.updateMat("sheep", -1);
                            p.updateMat("sheep", 1);
                            return "sheep";
                        }
                    case 3:
                        if(player.getMat("wheat") > 0) {
                            player.updateMat("wheat", -1);
                            p.updateMat("wheat", 1);
                            return "wheat";
                        }
                    case 4:
                        if(player.getMat("ore") > 0) {
                            player.updateMat("ore", -1);
                            p.updateMat("ore", 1);
                            return "ore";
                        }
                }


            }
        }
        return "broke";

    }

    private void playerTradeSetter(int[] playersending, int[] playerrecieving, Player p) {
        p.updateMat("wood", -playersending[0]);
        p.updateMat("brick", -playersending[1]);
        p.updateMat("sheep", -playersending[2]);
        p.updateMat("wheat", -playersending[3]);
        p.updateMat("ore", -playersending[4]);
        p.updateMat("wood", playerrecieving[0]);
        p.updateMat("brick", playerrecieving[1]);
        p.updateMat("sheep", playerrecieving[2]);
        p.updateMat("wheat", playerrecieving[3]);
        p.updateMat("ore", playerrecieving[4]);
    }
    
    public boolean buildHouse(int x, int y){
        Player p = players.get(currentPlayer);
        if(p.getMat("wood") > 0 && p.getMat("brick") > 0 && p.getMat("wheat") > 0 && p.getMat("sheep") > 0 && gameBoard.canPlaceHouse(x, y, p)){
            gameBoard.addHouse(x, y, p);
            p.updateMat("wood", -1);
            p.updateMat("brick", -1);
            p.updateMat("wheat", -1);
            p.updateMat("sheep", -1);
            return true;
        }
        return false;
    }

    public boolean upgradeHouse(int x, int y){
        Player p = players.get(currentPlayer);
        if(p.getMat("wheat") > 1 && p.getMat("ore") > 2){
            if(gameBoard.hasHouse(x, y, p)) {
                gameBoard.upgradeHouse(x, y);
                p.updateMat("wheat", -2);
                p.updateMat("ore", -3);
                return true;
            }
        }
        return false;
    }

    public boolean buildRoad(int x, int y){
        Player p = players.get(currentPlayer);
        if(p.getMat("wood") > 0 && p.getMat("brick") > 0 && gameBoard.canPlaceRoad(x, y, p)){
            p.updateMat("wood", -1);
            p.updateMat("brick", -1);
            gameBoard.getEdge(x, y).addRoad(new Road(p));
            return true;
        }
        return false;
    }

    /*8
        0 knight
        1 road building 2 roads
        2 Year of plenty
        3 monopoly
        4 victory point
    */

    public boolean buyDevelopmentCard() {
        Player p = players.get(currentPlayer);
        if(p.getMat("sheep") > 0 && p.getMat("wheat") > 0 && p.getMat("ore") > 0) {
            p.updateMat("sheep", -1);
            p.updateMat("wheat", -1);
            p.updateMat("ore", -1);
            p.addDevelopmentCard((int) Math.floor(Math.random() * (4 + 1) + 0));
            return true;
        }
        return false;
    }
    public void developmentCardYearOfPlenty(int[] resources) {
        Player p = players.get(currentPlayer);
        p.updateMat("wood", resources[0]);
        p.updateMat("brick", resources[1]);
        p.updateMat("sheep", resources[2]);
        p.updateMat("wheat", resources[3]);
        p.updateMat("ore", resources[4]);
    }

    public ArrayList<Player> developmentCardMoveRobber(int tile) {
        ArrayList<Player> playersToStealFrom = new ArrayList<>();
        if(gameBoard.moveRobber(tile)) {
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

    public void developmentCardMonoply(String typeResource) {
        for(int i = 0; i < players.size(); i++){
            if(!(i == currentPlayer) && players.get(i).getMat(typeResource) > 0) {
                players.get(currentPlayer).updateMat(typeResource, players.get(i).removeAllResources(typeResource));
            }
        }
    }

    public void developmentCardVP(){
        players.get(currentPlayer).addVP(1);
    }

    public int getNumberOfResources(Player p){
        return p.getMat("wood") + p.getMat("brick") + p.getMat("sheep") + p.getMat("wheat") + p.getMat("ore");
    }

    public boolean dropCards(Player p, int numOfCards, int[] arr){
            int number = 0;
            for(int i : arr){
                number+=i;
            }
            if(number == numOfCards && p.getMat("wood") >= arr[0] && p.getMat("brick") >= arr[1] && p.getMat("sheep") >= arr[2] && p.getMat("wheat") >= arr[3] && p.getMat("ore") >= arr[4]){
                p.updateMat("wood", -arr[0]);
                p.updateMat("brick", -arr[1]);
                p.updateMat("sheep", -arr[2]);
                p.updateMat("wheat", -arr[3]);
                p.updateMat("ore", -arr[4]);
                return true;
            }else{
                return false;
            }
    }

   
}