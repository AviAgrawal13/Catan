
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Game game = null;
    private static ArrayList<Player> players = new ArrayList<>();
    private static Board board = new Board();
    public static void main(String[] args) {

        players.add(new Player(2, 2, 2, 2, 2, "red"));
        players.add(new Player(2, 2, 2, 2, 2, "blue"));
        players.add(new Player(2, 2, 2, 2, 2, "white"));
        players.add(new Player(2, 2, 2, 2, 2, "orange"));

        game = new Game(players, board);

        board.initiateEverything();

        try{
            Scanner s = new Scanner(System.in);

            setupGame(s);

            while(true){
                Player p = game.getCurrentPlayer();
                p.addDevelopmentCard(0);
                System.out.println(p.getColor() +  " do you want to play knight or roll? (k or r)");
//                System.out.println("you have " + )
                String input = s.nextLine().toLowerCase();
                if(input.equals("k")){
                    if(p.canUseDevelopmentCard(0)) {
                        robberMovement(s, false);
                    }
                }else if(input.equals("r")){
                    int roll = game.rollDice();
                    if(roll == 7){
                        System.out.println("Where would you like to move the robber? (int tile)");
                        robberMovement(s, true);
                    }else{
                        game.distributeResources(roll);
                        System.out.println(roll);
                    }
                }
                while (true){
                    boolean end = false;
                    System.out.println("You have: Wood " + p.getMatArray()[0] + " Brick " + p.getMatArray()[1] + " Sheep " + p.getMatArray()[2] + " Wheat " + p.getMatArray()[3] + " Ore " + p.getMatArray()[4]);
                    System.out.println("What would you like to do?");
                    System.out.println("bd - buy dev card    ud # - use dev card");
                    System.out.println("bh # # - buy house    uh # # - upgrade house");
                    System.out.println("br # # - buy road");
                    System.out.println("e - end turn");
                    String inputs = s.next();
                    switch (inputs.charAt(0)){
                        case 'e' -> end = true;
                        case 'b' -> {
                        switch (inputs.charAt(1)) {
                            case 'd' -> {
                                if(!game.buyDevelopmentCard()){
                                    System.out.println("You cannot afford that. Please do something else.");
                                }
                            }
                            case 'h' -> {
                                if(!game.buildHouse(s.nextInt(), s.nextInt())){
                                    System.out.println("Something is wrong you may be too broke or unable to build there");
                                }
                            }
                            case 'r' -> {
                                if (!game.buildRoad(s.nextInt(), s.nextInt())) {
                                    System.out.println("Something is wrong you may be too broke or unable to build there");
                                }
                            }
                            default -> {
                            }
                        }
                        }
                        case 'u' -> {
                        }

                        default -> System.out.println("Try again.");
                    }
                    if(end){
                        game.endTurn();
                        break;
                    }
                }


                if(p.getVPs() == 10) {
                    break;
                }
            }
            System.out.println("Winner is " + game.getCurrentPlayer().getColor());



            s.close();
        }catch(Error e){
            System.out.println("Just follow the instructions bro :(");
            System.out.println(e);
        }

    }

    private static void robberMovement(Scanner s, boolean dropHalf) {

        System.out.println("what tile do you wanna move it to");
        int tile;
        while(true){
            tile = s.nextInt();
            if(!board.moveRobber(tile)){
                System.out.println("The robber is already there, try again.");
            }
            break;
        }
        ArrayList<Player> playersToStealFrom = game.developmentCardMoveRobber(tile);

        if (dropHalf) {
            for (Player p : players) {
                if (game.getNumberOfResources(p) > 7) {
                    System.out.println("Player " + p.getColor() + " you have too many cards, drop " + game.getNumberOfResources(p) / 2);
                    System.out.println("The cards you have are: Wood " + p.getMatArray()[0] + " Brick " + p.getMatArray()[1] + " Sheep " + p.getMatArray()[2] + " Wheat " + p.getMatArray()[3] + " Ore " + p.getMatArray()[4]);
                    System.out.println("Please type in format # # # # #");
                    System.out.println("ex: 3 2 1 0 6");
                    while (true) {
                        if (game.dropCards(p, game.getNumberOfResources(p) / 2, new int[]{s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()})) {
                            break;
                        } else {
                            System.out.println("Someone is trying to cheat the system... try again.");
                        }
                    }
                }
            }
        }
        if (playersToStealFrom.isEmpty()) {
            System.out.println("you placed it where there is nobody");
        } else {
            System.out.println("you placed where people are, who u wanna steal from int of place");
            for(int i = 0; i < playersToStealFrom.size(); i++){
                System.out.println(playersToStealFrom.get(i).getColor() + " " + (i+1));
            }

            String resourceStolen = game.getRandomResource(playersToStealFrom.get(s.nextInt() - 1));

            if (resourceStolen.equals("broke")) {
                System.out.println("that mofo was broke and had no mula");
            } else {
                System.out.println("You successfully stole " + resourceStolen + "!");
            }
        }
    }

    private static void setupGame(Scanner s){
        int[] players = new int[]{0, 1, 2, 3, 3, 2, 1, 0};

        game.setCurrentPlayer(0);
        board.addHouse(2, 2, game.getCurrentPlayer());
        board.addHouse(3, 3, game.getCurrentPlayer());
        board.addRoad(4, 5, game.getCurrentPlayer());
        board.addRoad(7, 6, game.getCurrentPlayer());

        game.setCurrentPlayer(1);
        board.addHouse(5, 3, game.getCurrentPlayer());
        board.addHouse(6, 2, game.getCurrentPlayer());
        board.addRoad(9, 6, game.getCurrentPlayer());
        board.addRoad(12, 5, game.getCurrentPlayer());

        game.setCurrentPlayer(2);
        board.addHouse(8, 3, game.getCurrentPlayer());
        board.addHouse(5, 1, game.getCurrentPlayer());
        board.addRoad(16, 5, game.getCurrentPlayer());
        board.addRoad(11, 2, game.getCurrentPlayer());

        game.setCurrentPlayer(3);
        board.addHouse(9, 2, game.getCurrentPlayer());
        board.addHouse(7, 1, game.getCurrentPlayer());
        board.addRoad(17, 4, game.getCurrentPlayer());
        board.addRoad(13, 2, game.getCurrentPlayer());

        board.setfirstPlacing();
        game.setCurrentPlayer(0);

        /*
        for (int i : players) {
            game.setCurrentPlayer(i);
            System.out.println("Player " + game.getPlayerColor() + " where would you like to put your first house? (format x y)");

            boolean placedHouse = false;
            boolean placedRoad = false;

            while (!placedHouse) {
                int x = s.nextInt();
                int y = s.nextInt();
                if (board.canPlaceHouse(x, y, game.getCurrentPlayer())) {
                    board.addHouse(x, y, game.getCurrentPlayer());
                    placedHouse = true;
                } else {
                    System.out.println("That is not a valid location, please try again. (format x y)");
                }
            }

            System.out.println("First road? (format x y)");

            while (!placedRoad) {
                int x = s.nextInt();
                int y = s.nextInt();
                if (board.canPlaceRoad(x, y, game.getCurrentPlayer())) {
                    board.addRoad(x, y, game.getCurrentPlayer());
                    placedRoad = true;
                } else {
                    System.out.println("That is not a valid location, please try again. (format x y)");
                }
            }
        }
        */
        //2 2, 4 5, 5 3, 9 6, 8 3, 16 5, 9 2, 17 4, 7 1, 13 2, 5 1, 11 2, 6 2, 12 5, 3 3, 7 6
    }
}