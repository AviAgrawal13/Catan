import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Board {
    private HashMap<Integer, Node[]> tileToNode = new HashMap<>();
    private Node[][] node2DArray = new Node[6][11];
    private Tile[] tileArray = new Tile[19];
    private HashMap<Integer, int[]> diceToHex = new HashMap<>();
    private Edge[][] edges = new Edge[11][21];
    private boolean firstPlacing = true;
    private HashMap<Integer, String> tileToType = new HashMap<>();
    private HashMap<Integer, Integer> tileToDice = new HashMap<>();
    private int dfsRet = 0;

    /**
     * Sets variable for whether or not the placing at the start of the game is occuring
     * as that follows a different rule set.
     */
    public void setfirstPlacing() {
        this.firstPlacing = false;
    }

    /**
     * Initiates all the essential data structures needed for the game to function properly.
     */
    public void initiateEverything(){
        createNode2DArray();
        createTileArray();
        createEdge();
        createTileToNode();
        setTileTypes(createDiceToHex());
    }

    /**
     * returns the edge at a coordinate (x, y) that is contained in the edges array.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return returns the edge at that coordinate (or null if no edge has been
     *     initialized there).
     */
    public Edge getEdge(int x, int y){
        return edges[y][x];
    }

    /**
     * Initalizes all the valid edges in the edge array.
     */
    private void createEdge(){
        for(int i = 5; i < 16; i += 2) {
            edges[0][i] = new Edge();
            edges[10][i] = new Edge();
        }

        for(int i = 4; i < 17; i += 4) {
            edges[1][i] = new Edge();
            edges[9][i] = new Edge();
        }

        for(int i = 3; i < 18; i += 2) {
            edges[2][i] = new Edge();
            edges[8][i] = new Edge();
        }

        for(int i = 2; i < 19; i += 4) {
            edges[3][i] = new Edge();
            edges[7][i] = new Edge();
        }

        for(int i = 1; i < 20; i += 2) {
            edges[4][i] = new Edge();
            edges[6][i] = new Edge();
        }

        for(int i = 0; i < 21; i += 4) {
            edges[5][i] = new Edge();
        }
    }

    /**
     * Maps each tile to the nodes that it contains.
     */
    private void createTileToNode(){
        this.tileToNode.put(1, new Node[]{this.node2DArray[0][2], this.node2DArray[0][3], this.node2DArray[0][4],
                this.node2DArray[1][2], this.node2DArray[1][3], this.node2DArray[1][4]});
        this.tileToNode.put(2, new Node[]{this.node2DArray[0][4], this.node2DArray[0][5], this.node2DArray[0][6],
                this.node2DArray[1][4], this.node2DArray[1][5], this.node2DArray[1][6]});
        this.tileToNode.put(3, new Node[]{this.node2DArray[0][6], this.node2DArray[0][7], this.node2DArray[0][8],
                this.node2DArray[1][6], this.node2DArray[1][7], this.node2DArray[1][8]});

        this.tileToNode.put(4, new Node[]{this.node2DArray[1][1], this.node2DArray[1][2], this.node2DArray[1][3],
                this.node2DArray[2][1], this.node2DArray[2][2], this.node2DArray[2][3]});
        this.tileToNode.put(5, new Node[]{this.node2DArray[1][3], this.node2DArray[1][4], this.node2DArray[1][5],
                this.node2DArray[2][3], this.node2DArray[2][4], this.node2DArray[2][5]});
        this.tileToNode.put(6, new Node[]{this.node2DArray[1][5], this.node2DArray[1][6], this.node2DArray[1][7],
                this.node2DArray[2][5], this.node2DArray[2][6], this.node2DArray[2][7]});
        this.tileToNode.put(7, new Node[]{this.node2DArray[1][7], this.node2DArray[1][8], this.node2DArray[1][9],
                this.node2DArray[2][7], this.node2DArray[2][8], this.node2DArray[2][9]});

        this.tileToNode.put(8, new Node[]{this.node2DArray[2][0], this.node2DArray[2][1], this.node2DArray[2][2],
                this.node2DArray[3][0], this.node2DArray[3][1], this.node2DArray[3][2]});
        this.tileToNode.put(9, new Node[]{this.node2DArray[2][2], this.node2DArray[2][3], this.node2DArray[2][4],
                this.node2DArray[3][2], this.node2DArray[3][3], this.node2DArray[3][4]});
        this.tileToNode.put(10, new Node[]{this.node2DArray[2][4], this.node2DArray[2][5], this.node2DArray[2][6],
                this.node2DArray[3][4], this.node2DArray[3][5], this.node2DArray[3][6]});
        this.tileToNode.put(11, new Node[]{this.node2DArray[2][6], this.node2DArray[2][7], this.node2DArray[2][8],
                this.node2DArray[3][6], this.node2DArray[3][7], this.node2DArray[3][8]});
        this.tileToNode.put(12, new Node[]{this.node2DArray[2][8], this.node2DArray[2][9], this.node2DArray[2][10],
                this.node2DArray[3][8], this.node2DArray[3][9], this.node2DArray[3][10]});

        this.tileToNode.put(13, new Node[]{this.node2DArray[3][1], this.node2DArray[3][2], this.node2DArray[3][3],
                this.node2DArray[4][1], this.node2DArray[4][2], this.node2DArray[4][3]});
        this.tileToNode.put(14, new Node[]{this.node2DArray[3][3], this.node2DArray[3][4], this.node2DArray[3][5],
                this.node2DArray[4][3], this.node2DArray[4][4], this.node2DArray[4][5]});
        this.tileToNode.put(15, new Node[]{this.node2DArray[3][5], this.node2DArray[3][6], this.node2DArray[3][7],
                this.node2DArray[4][5], this.node2DArray[4][6], this.node2DArray[4][7]});
        this.tileToNode.put(16, new Node[]{this.node2DArray[3][7], this.node2DArray[3][8], this.node2DArray[3][9],
                this.node2DArray[4][7], this.node2DArray[4][8], this.node2DArray[4][9]});

        this.tileToNode.put(17, new Node[]{this.node2DArray[4][2], this.node2DArray[4][3], this.node2DArray[4][4],
                this.node2DArray[5][2], this.node2DArray[5][3], this.node2DArray[5][4]});
        this.tileToNode.put(18, new Node[]{this.node2DArray[4][2], this.node2DArray[4][3], this.node2DArray[4][4],
                this.node2DArray[5][2], this.node2DArray[5][3], this.node2DArray[5][4]});
        this.tileToNode.put(19, new Node[]{this.node2DArray[4][2], this.node2DArray[4][3], this.node2DArray[4][4],
                this.node2DArray[5][2], this.node2DArray[5][3], this.node2DArray[5][4]});
    }

    /**
     * Randomly maps the dice numbers 2-6, 8-12 onto the tiles so that the game board functions properly.
     * @return returns the index of the only tile that does not get a number, the desert tile.
     */
    private int createDiceToHex() { // https://onlinegdb.com/JOivmh0zW		
        ArrayList<Integer> tempTileArray = new ArrayList<>();

        for(int i = 1; i < 20; i++) {
            tempTileArray.add(i);
        }


        this.diceToHex.put(2, new int[]{tempTileArray.remove((int) (Math.random() * tempTileArray.size()))});
        this.tileToDice.put(this.diceToHex.get(2)[0],2);
        this.diceToHex.put(12, new int[]{tempTileArray.remove((int) (Math.random() * tempTileArray.size()))});
        this.tileToDice.put(this.diceToHex.get(12)[0],12);

        for(int i = 3; i < 12; i++) {
            if(i != 7) {
                int[] twoTiles = new int[2];
                twoTiles[0] = tempTileArray.remove((int) (Math.random() * tempTileArray.size()));
                twoTiles[1] = tempTileArray.remove((int) (Math.random() * tempTileArray.size()));
                this.diceToHex.put(i, twoTiles);
                this.tileToDice.put(this.diceToHex.get(i)[0],i);
                this.tileToDice.put(this.diceToHex.get(i)[1],i);
            }

        }
        int desertTile = tempTileArray.remove(0);
        this.tileToDice.put(desertTile, 7);
        return desertTile;
    }


    /**
     * Gets the value from the dice to hex map.
     * @param dice the dice number that was rolled.
     * @return returns the index of the tile(s) that have that dice number.
     */
    public int[] getDiceToHex(int dice){
        return diceToHex.get(dice);
    }

    /**
     * Returns the tile for a given index.
     * @param i the index of the desired tile.
     * @return returns the tile that has that index.
     */
    public Tile getTile(int i){
        return tileArray[i-1];
    }
    /**
     * Retrieves an array of nodes associated with a particular tile.
     * 
     * @param tile The number of the tile.
     * @return An array of nodes associated with the specified tile.
     */
    public Node[] getTileToNode(int tile){
        return tileToNode.get(tile);
    }

    /**
     * Initializes the array of tiles.
     * Each tile is assigned a number and initialized.
     */
    private void createTileArray(){
        for(int i = 0; i < 19; i++){
            this.tileArray[i] = new Tile(i+1);
        }
    }

    /**
     * Sets the types of tiles, including assigning a desert tile.
     * 
     * @param desert The number representing the desert tile.
     */
    private void setTileTypes(int desert) {
        ArrayList<String> tileType = new ArrayList<>();
        // Add resource types to the tile type list
        for(int i = 0; i < 3; i ++) {
            tileType.add("brick");
            tileType.add("ore");
            tileType.add("sheep");
            tileType.add("wheat");
            tileType.add("wood");
        }
        // Add extra resource types for uneven distribution
        tileType.add("sheep");
        tileType.add("wheat");
        tileType.add("wood");

        // Shuffle the tile types
        Collections.shuffle(tileType);
        // Insert the desert tile at the specified position
        tileType.add(desert - 1, "desert");
        
        // Assign each tile its type and update the tile-to-type mapping
        for(int i = 0; i < tileType.size(); i++) {
            tileArray[i].setType(tileType.get(i));
            tileToType.put(i+1, tileType.get(i));
        }
    }


    /**
     * Initializes the 2D array of nodes representing the game board.
     * Nodes are placed in a specific pattern to match the game board layout.
     */
    private void createNode2DArray() {
        // Rows 0 and 5
        for(int i = 2; i < 9; i++) {
            node2DArray[0][i] = new Node(0, i);
            node2DArray[5][i] = new Node(5, i);
        }

        // Rows 1 and 4
        for(int i = 1; i < 10; i++) {
            node2DArray[1][i] = new Node(1, i);
            node2DArray[4][i] = new Node(4, i);
        }

        // Rows 2 and 3
        for(int i = 0; i < 11; i++) {
            node2DArray[2][i] = new Node(2, i);
            node2DArray[3][i] = new Node(3, i);
        }
    }

    /**
     * Checks if there is a house at the specified coordinates for a given player.
     * 
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @param p The player whose house presence is being checked.
     * @return True if there is a house at the specified coordinates for the given player, false otherwise.
     */
    public boolean hasHouse(int x, int y, Player p) {
        if(!node2DArray[y][x].hasHouse()) {
            return false;
        } else {
            if(node2DArray[y][x].getHouse().getNameOfPlayer().equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a house can be placed at the specified coordinates for a given player.
     * 
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @param p The player attempting to place the house.
     * @return True if a house can be placed at the specified coordinates for the given player, false otherwise.
     */
    public boolean canPlaceHouse(int x, int y, Player p) {
        // If there's already a house at the specified coordinates, return false
        if(node2DArray[y][x].hasHouse()) {
            return false;
        }
        
        // Count the number of adjacent nodes without houses
        int conditions = 0;
        int bound;
        int upOrDown;
        if(x != 10){
            if(!node2DArray[y][x+1].hasHouse()){
                conditions++;
            }
        } else {
            conditions++;
        }
        if(x != 0){
            if(!node2DArray[y][x-1].hasHouse()){
                conditions++;
            }
        } else {
            conditions++;
        }
        if((y % 2) == (x % 2)){
            bound = 5;
            upOrDown = 1;
        } else {
            bound = 0;
            upOrDown = -1;
        }
        if(y != bound) {
            if(!node2DArray[y+upOrDown][x].hasHouse()){
                conditions++;
            }
        } else {
            conditions++;
        }

        // For the initial placement phase, check if there are three adjacent nodes without houses
        if(firstPlacing){
            return conditions == 3;
        } else {
            // For subsequent placements, also check if there is a road adjacent to the node
            return conditions == 3 && roadNextToNode(x, y, p);
        }
    }


    /**
     * Checks if there is a road adjacent to the specified node coordinates for a given player.
     * 
     * @param x The x-coordinate of the node.
     * @param y The y-coordinate of the node.
     * @param p The player whose road adjacency is being checked.
     * @return True if there is a road adjacent to the node for the given player, false otherwise.
     */
    public boolean roadNextToNode(int x, int y, Player p) {
        // Check right
        if(x != 10){
            if(edges[2*y][2*x+1] != null) {
                if(edges[2*y][2*x+1].getRoad() != null) {
                    if(edges[2*y][2*x+1].getRoad().getNameOfPlayer() == p){
                        return true;
                    }
                }
            }
        }

        // Check left
        if(x != 0){
            if(edges[2*y][2*x-1] != null) {
                if(edges[2*y][2*x-1].getRoad() != null) {
                    if(edges[2*y][2*x-1].getRoad().getNameOfPlayer() == p){
                        return true;
                    }
                }
            }
        }

        // Check up and down
        if(y != 0 && y!= 5) {
            if(edges[2*y+1][2*x] != null){
                if(edges[2*y+1][2*x].getRoad() != null) {
                    if(edges[2*y+1][2*x].getRoad().getNameOfPlayer() == p){
                        return true;
                    }
                }
            }else if(edges[2*y-1][2*x] != null){
                if(edges[2*y-1][2*x].getRoad() != null) {
                    if(edges[2*y-1][2*x].getRoad().getNameOfPlayer() == p){
                        return true;
                    }
                }
            }
        } else if(y == 0) {
            // Check down
            if(edges[2*y+1][2*x] != null) {
                if(edges[2*y+1][2*x].getRoad() != null) {
                    if(edges[2*y+1][2*x].getRoad().getNameOfPlayer() == p){
                        return true;
                    }
                }
            }
        } else if(y == 5) {
            // Check up
            if(edges[2*y-1][2*x] != null) {
                if(edges[2*y-1][2*x].getRoad() != null) {
                    if(edges[2*y-1][2*x].getRoad().getNameOfPlayer() == p){
                        return true;
                    }
                }
            }
        }

        // For the initial placement phase, check if a road can be placed next to the node
        if(firstPlacing) {
            int bound;
            int upOrDown;
            if((y % 2) == (x % 2)){
                bound = 5;
                upOrDown = 1;
            } else {
                bound = 0;
                upOrDown = -1;
            }
            if(y != bound) {
                return !node2DArray[2 * y + upOrDown][x].hasHouse();
            }
        }

        return false;
    }

    /**
     * Checks if a road can be placed at the specified coordinates for a given player.
     * 
     * @param x The x-coordinate of the road.
     * @param y The y-coordinate of the road.
     * @param p The player attempting to place the road.
     * @return True if the road can be placed at the specified coordinates for the given player, false otherwise.
     */
    public boolean canPlaceRoad(int x, int y, Player p) {
        int x1;
        int y1;
        int x2;
        int y2;

        // Calculate coordinates of nodes connected by the road
        x1 = x/2;
        y1 = y/2;
        x2 = (y % 2 == 0) ? x/2 + 1 : x/2;
        y2 = (y % 2 == 0) ? y/2 : y/2 + 1;

        // Check if road already exists
        if(edges[y][x].hasRoad()) {
            return false;
        }

        // For the initial placement phase, check if a house is present next to the road
        if(firstPlacing) {
            if(node2DArray[y1][x1].hasHouse() || node2DArray[y2][x2].hasHouse()) {
                if (node2DArray[y1][x1].hasHouse()) {
                    return p == node2DArray[y1][x1].getHouse().getNameOfPlayer();
                }
                if (node2DArray[y2][x2].hasHouse()) {
                    return p == node2DArray[y2][x2].getHouse().getNameOfPlayer();
                }
            } else {
                return false;
            }
        }

        // Check if there is a road adjacent to either of the nodes connected by the road
        return roadNextToNode(x1, y1, p) || roadNextToNode(x2, y2, p);
    }
    
    public int runDFS(Player p){
        for(int i = 0; i < 21; i++){
            for(int j = 0; j < 11; j++){
                if(edges[j][i] != null) {
                    if(edges[j][i].getRoad() != null) {
                        if(edges[j][i].getRoad().getNameOfPlayer() == p){
                            this.dfsRet = 0;
                            boolean[][] vis = new boolean[11][21];
                            vis[j][i] = true;
                            dfs(1, vis, new Point(i, j), p);
                            return Math.max(dfsRet, p.getLongestRoad());
                        }
                    }
                }
            }
        }
        return -1;
    }

    public ArrayList<Point> getRoadsFromNode(int x, int y, Player p) {
        ArrayList<Point> roadsOnPoint = new ArrayList<>();
        if(x != 10){
            if(edges[2*y][2*x+1] != null) {
                if(edges[2*y][2*x+1].getRoad() != null) {
                    if(edges[2*y][2*x+1].getRoad().getNameOfPlayer() == p){
                        roadsOnPoint.add(new Point(2*x+1, 2*y));
                    }
                }
            }
        }
        
        // Check left
        if(x != 0){
            if(edges[2*y][2*x-1] != null) {
                if(edges[2*y][2*x-1].getRoad() != null) {
                    if(edges[2*y][2*x-1].getRoad().getNameOfPlayer() == p){
                        roadsOnPoint.add(new Point(2*x-1, 2*y));
                    }
                }
            }
        }
        // Check up and down
        if(y != 0 && y!= 5) {
            if(edges[2*y+1][2*x] != null){
                if(edges[2*y+1][2*x].getRoad() != null) {
                    if(edges[2*y+1][2*x].getRoad().getNameOfPlayer() == p){
                        roadsOnPoint.add(new Point(2*x, 2*y+1));
                    }
                }
            
            }else if(edges[2*y-1][2*x] != null){
                if(edges[2*y-1][2*x].getRoad() != null) {
                    if(edges[2*y-1][2*x].getRoad().getNameOfPlayer() == p){
                        roadsOnPoint.add(new Point(2*x, 2*y-1));
                    }
                }
            }
        } else if(y == 0) {
            // Check down
            if(edges[2*y+1][2*x] != null) {
                if(edges[2*y+1][2*x].getRoad() != null) {
                    if(edges[2*y+1][2*x].getRoad().getNameOfPlayer() == p){
                        roadsOnPoint.add(new Point(2*x, 2*y+1));
                    }
                }
            }
        } else if(y == 5) {
            // Check up
            if(edges[2*y-1][2*x] != null) {
                if(edges[2*y-1][2*x].getRoad() != null) {
                    if(edges[2*y-1][2*x].getRoad().getNameOfPlayer() == p){
                        roadsOnPoint.add(new Point(2*x, 2*y-1));
                    }
                }
            }
        }
        return roadsOnPoint;
    }
    
    public ArrayList<Point> getNeighbors(int x, int y, Player p) {
        int x1;
        int y1;
        int x2;
        int y2;

        // Calculate coordinates of nodes connected by the road
        x1 = x/2;
        y1 = y/2;
        x2 = (y % 2 == 0) ? x/2 + 1 : x/2;
        y2 = (y % 2 == 0) ? y/2 : y/2 + 1;

        ArrayList<Point> roadsNeighboring = new ArrayList<>();
        
        ArrayList<Point> point1 = getRoadsFromNode(x1, y1, p);
        ArrayList<Point> point2 = getRoadsFromNode(x2, y2, p);

        String jeff = "From node " + x1 + " " + y1 + " ";
        
        for(Point i : point1) {
            roadsNeighboring.add(i);
            jeff += i + " ";
        }
        
        jeff += " From node " + x2 + " " + y1 + " ";
        
        for(Point i : point2) {
            if(!roadsNeighboring.contains(i)){
                roadsNeighboring.add(i);
                jeff += i;
            }
        }

        System.out.println(jeff);
        return roadsNeighboring;    
    }

    public void dfs(int length, boolean[][] vis, Point poi, Player play) {
        dfsRet = Math.max(dfsRet, length);
        ArrayList<Point> neighbors = getNeighbors(poi.getX(), poi.getY(), play);
        for(Point p : neighbors){
            if(!vis[p.getY()][p.getX()] && nullCheck(p)){
                if(edges[p.getY()][p.getX()].getRoad().getNameOfPlayer() == play){
                    System.out.print("dfs + ");
                    System.out.println(p);
                    vis[p.getY()][p.getX()] = true;
                    dfs(length+1, vis, new Point(p.getX(), p.getY()), play);
                    vis[p.getY()][p.getX()] = false;
                }
            }
        }
    }

    public boolean nullCheck(Point p){
        if(edges[p.getY()][p.getX()] != null){
            if(edges[p.getY()][p.getX()].getRoad() != null){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a house to the specified coordinates on the game board for a given player.
     * 
     * @param x The x-coordinate of the position to add the house.
     * @param y The y-coordinate of the position to add the house.
     * @param player The player who owns the house.
     */
    public void addHouse(int x, int y, Player player){
        this.node2DArray[y][x].addHouse(new House(player));
    }

    /**
     * Upgrades the house at the specified coordinates on the game board.
     * 
     * @param x The x-coordinate of the position of the house to upgrade.
     * @param y The y-coordinate of the position of the house to upgrade.
     */
    public void upgradeHouse(int x, int y){
        this.node2DArray[y][x].getHouse().increaseLevel();
    }

    /**
     * Adds a road to the specified coordinates on the game board for a given player.
     * 
     * @param x The x-coordinate of the position to add the road.
     * @param y The y-coordinate of the position to add the road.
     * @param p The player who owns the road.
     */
    public void addRoad(int x, int y, Player p){
        edges[y][x].addRoad(new Road(p));
    }

    /**
     * Moves the robber to a new tile on the game board.
     * 
     * @param tileNumber The number of the tile to move the robber to.
     * @return True if the robber was successfully moved, false otherwise.
     */
    public boolean moveRobber(int tileNumber) {
        for (int i = 0; i < tileArray.length; i++) {
            if (tileArray[i].hasRobber()) {
                if(i + 1 == tileNumber) {
                    return false;
                } else {
                    tileArray[i].setRobberTile(false);
                    break;
                }
            }
        }
        tileArray[tileNumber - 1].setRobberTile(true);
        return true;
    }

    /**
     * Prints a representation of the game map, showing the presence of edges (roads).
     */
    public void printMap() {
        for(Edge[] e : edges){
            for(Edge j : e) {
                System.out.print((j == null ? "0" : "1") + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints debugging information about tile-to-dice and tile-to-type mappings.
     */
    public void printDebug() {
        System.out.println(tileToDice);
        System.out.println(tileToType);
    }

}
