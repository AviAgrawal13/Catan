
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

    public void setfirstPlacing() {
        this.firstPlacing = false;
    }

    public void initiateEverything(){
        createNode2DArray();
        createTileArray();
        createEdge();
        createTileToNode();
        setTileTypes(createDiceToHex());
    }

    public Edge getEdge(int x, int y){
        return edges[y][x];
    }

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

    private int createDiceToHex() { // https://onlinegdb.com/JOivmh0zW		
        ArrayList<Integer> tempTileArray = new ArrayList<>();
        
        for(int i = 1; i < 20; i++) {
            tempTileArray.add(i);
        }
       
        
        this.diceToHex.put(2, new int[]{tempTileArray.remove((int) (Math.random() * tempTileArray.size()))});
        this.diceToHex.put(12, new int[]{tempTileArray.remove((int) (Math.random() * tempTileArray.size()))});
        
        
        for(int i = 3; i < 12; i++) {
            if(i != 7) {
                int[] twoTiles = new int[2];
                twoTiles[0] = tempTileArray.remove((int) (Math.random() * tempTileArray.size()));
                twoTiles[1] = tempTileArray.remove((int) (Math.random() * tempTileArray.size()));
                this.diceToHex.put(i, twoTiles);
            }

        }     
        return tempTileArray.remove(0);
    }

    public int[] getDiceToHex(int dice){
        return diceToHex.get(dice);
    }

    public Tile getTile(int i){
        return tileArray[i-1];
    }

    public Node[] getTileToNode(int tile){
        return tileToNode.get(tile);
    }

    private void createTileArray(){
        for(int i = 0; i < 19; i++){
            this.tileArray[i] = new Tile(i+1);
        }
    }

    private void setTileTypes(int desert) {
        ArrayList<String> tiletype = new ArrayList<>();
        for(int i = 0; i < 3; i ++) {
            tiletype.add("brick");
            tiletype.add("ore");
            tiletype.add("sheep");
            tiletype.add("wheat");
            tiletype.add("wood");
        }
        tiletype.add("sheep");
        tiletype.add("wheat");
        tiletype.add("wood");
    
        Collections.shuffle(tiletype);
        for(int i = 0; i < tiletype.size(); i++) {
            if(i != (desert - 1)){
                tileArray[i].setType(tiletype.get(i));
            }else{
                tileArray[i].setType("desert");
            }
        }


    }


    private void createNode2DArray() {
        for(int i = 2; i < 9; i++) {
            node2DArray[0][i] = new Node(0, i);
            node2DArray[5][i] = new Node(5, i);
        }

        for(int i = 1; i < 10; i++) {
            node2DArray[1][i] = new Node(1, i);
            node2DArray[4][i] = new Node(4, i);
        }

        for(int i = 0; i < 11; i++) {
            node2DArray[2][i] = new Node(2, i);
            node2DArray[3][i] = new Node(3, i);
        }
    }

    public Node[] getPoints(Integer i){
        return this.tileToNode.get(i);    
        
    }

    public boolean canPlaceHouse(int x, int y, Player p) {
        if(node2DArray[y][x].hasHouse()) {
            return false;
        }
        int conditions = 0;
        int bound;
        int upordown;
        if(x != 10){
            if(!node2DArray[y][x+1].hasHouse()){
                conditions++;
            }
        }else{
            conditions++;
        }
        if(x != 0){
            if(!node2DArray[y][x-1].hasHouse()){
                conditions++;
            }       
        }else{
            conditions++;
        }
        if((y % 2) == (x % 2)){
            bound = 5;
            upordown = 1;
        }else{
            bound = 0;
            upordown = -1;
            
        }
        if(y != bound) {
            if(!node2DArray[y+upordown][x].hasHouse()){
                conditions++;
            }
        }else{
            conditions++;
        }

        if(firstPlacing){
            return conditions == 3;
        } else {
            return conditions == 3 && roadNextToNode(x, y, p);
        }
    }

    public boolean roadNextToNode(int x, int y, Player p) {
        System.out.println("node");
        System.out.println(x);
        System.out.println(y);
        System.out.println("road 1");
        System.out.println(2*x+1);
        System.out.println(2*y);
        if(x != 10){
            if(edges[2*y][2*x+1].getRoad() != null) {
                if(edges[2*y][2*x+1].getRoad().getNameOfPlayer() == p){
                    System.out.println("detected road" + 2*x+1 + " " + 2*y);
                    return true;
                }
            }
        }
        System.out.println("road 2");
        System.out.println(2*x-1);
        System.out.println(2*y);
        if(x != 0){
            if(edges[2*y][2*x-1].getRoad() != null) {
                if(edges[2*y][2*x-1].getRoad().getNameOfPlayer() == p){
                    System.out.println("detected road" + (2*x-1) + " " + (2*y));
                    return true;
                } 
            }     
        }
        if(firstPlacing) {
            int bound;
            int upordown;
            if((y % 2) == (x % 2)){
                bound = 5;
                upordown = 1;
            }else{
                bound = 0;
                upordown = -1;
                
            }
            if(y != bound) {
                return !node2DArray[2 * y + upordown][x].hasHouse();
            }
        }

        return false;
    }

    public boolean canPlaceRoad(int x, int y, Player p) {
        int x1;
        int y1;
        int x2;
        int y2;

        x1 = x/2;
        y1 = y/2;

        x2 = (y % 2 == 0) ? x/2 + 1 : x/2;
        y2 = (y % 2 == 0) ? y/2 : y/2 + 1;

        if(edges[y][x].hasRoad()) {
            return false;
        }

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

        return roadNextToNode(x1, y1, p) || roadNextToNode(x2, y2, p);
    }

    

    public void addHouse(int x, int y, Player player){
        this.node2DArray[y][x].addHouse(new House(player));
    }

    public void upgradeHouse(int x, int y){
        this.node2DArray[y][x].getHouse().increaseLevel();
        
    }

    public void addRoad(int x, int y, Player p){
        edges[y][x].addRoad(new Road(p));
    }

    public boolean moveRobber(int tileNumber) {
        for (int i = 0; i < tileArray.length; i++) {
            if (tileArray[i].hasRobber()) {
                if(i + 1 == tileNumber) {
                    return false;
                }else{
                    tileArray[i].setRobberTile(false);
                    break;
                }
            }
        }
        tileArray[tileNumber - 1].setRobberTile(true);
        return true;
    }
    public void printMap() {
        for(Edge[] e : edges){
            for(Edge j : e) {
                System.out.print((j == null ? "0" : "1") + " ");
            }
            System.out.println();
        }
    } 
}  
