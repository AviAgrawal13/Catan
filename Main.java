import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
import java.util.*;

/**
 * The Main class is the entry point of the program. It initializes the game environment, manages player actions,
 * and controls the game flow.
 */
public class Main {
    private static Game game = null; // The game instance
    private static ArrayList<Player> players = new ArrayList<>(); // List of players
    private static Board board = new Board(); // The game board
    private static boolean skipSetup = true; // Flag to skip initial setup
    private static CatanPanel cp;
    public static void main(String[] args) {
        JFrame window = new JFrame("Catan");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // Initialize players with initial resources and colors
        players.add(new Player(100, 100, 100, 100, 100, "red"));
        players.add(new Player(2, 2, 2, 2, 2, "blue"));
        players.add(new Player(2, 2, 2, 2, 2, "white"));
        players.add(new Player(2, 2, 2, 2, 2, "orange"));

        // Initialize the game with players and the game board
        game = new Game(players, board);

        // Initialize the game board
        board.initiateEverything();
        cp = new CatanPanel(board.getTileArray(), board.getTileToDice());

        window.add(cp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        cp.launchGame();

        try {
            Scanner s = new Scanner(System.in);

            // Setup the game, including placing initial houses and roads
            setupGame(s);

            // Main game loop
            while (true) {
                Player p = game.getCurrentPlayer();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println(p.getColor() + " do you want to play knight or roll? (k or r)");
                String input = s.nextLine().toLowerCase();
                if (input.equals("k")) {
                    // Check if the player can use a development card, then perform robber movement
                    if (p.canUseDevelopmentCard(0)) {
                        robberMovement(s, false);
                    } else {
                        System.out.println("You do not have a knight");
                    }
                } else if (input.equals("r")) {
                    int roll = game.rollDice();
                    if (roll == 7) {
                        System.out.println("Where would you like to move the robber? (int tile)");
                        robberMovement(s, true);
                    } else {
                        game.distributeResources(roll);
                        System.out.println(roll);
                    }

                    // Handle player actions after rolling the dice
                    while (true) {
                        boolean end = false;
                        System.out.println("You have: Wood " + p.getMatArray()[0] + " Brick " + p.getMatArray()[1] +
                                " Sheep " + p.getMatArray()[2] + " Wheat " + p.getMatArray()[3] + " Ore " +
                                p.getMatArray()[4]);
                        // System.out.println(board.runDFS(p));
                        // System.out.println(p.hasLongRoad());
                        System.out.println("What would you like to do?");
                        System.out.println("bd - buy dev card    ud - use dev card");
                        System.out.println("bh # # - buy house    uh # # - upgrade house");
                        System.out.println("br # # - buy road    tb - trade bank");
                        System.out.println("tp - trade player    e - end turn");
                        String inputs = s.next();
                        switch (inputs.charAt(0)) {
                            case 'e' -> end = true;
                            case 'b' -> {
                                switch (inputs.charAt(1)) {
                                    case 'd' -> {
                                        if (!game.buyDevelopmentCard()) {
                                            System.out.println("You cannot afford that. Please do something else.");
                                        }
                                    }
                                    case 'h' -> {
                                        int x = s.nextInt();
                                        int y = s.nextInt();
                                        if (!game.buildHouse(x, y)) {
                                            System.out.println("Something is wrong you may be too broke or unable to build there");
                                        } else {
                                            cp.updateHouse(x, y, p.getColor());
                                            p.addVP(1);
                                        }
                                    }
                                    case 'r' -> {
                                        int x = s.nextInt();
                                        int y = s.nextInt();
                                        if (!game.buildRoad(x, y)) {
                                            System.out.println("Something is wrong you may be too broke or unable to build there");
                                        } else {
                                            cp.updateRoad(x, y, p.getColor());

                                            board.runDFS(p);

                                            int player = 0 , bestScore = -1 , dupe = 0;
                                            for(int i = 0;i<4;i++){
                                                int score = players.get(i).getLongestRoad();
                                                if(score > bestScore){
                                                    dupe = 0;
                                                    bestScore = score;
                                                    player = i;
                                                } else if(score == bestScore)
                                                    dupe = 1;
                                            }

                                            if(dupe == 0) {
                                                for(int i = 0; i < 4; i++) {
                                                    players.get(i).setRoadAchievement(false);
                                                }
                                                players.get(player).setRoadAchievement(true);
                                            }
                                        }
                                    }
                                    default -> {
                                    }
                                }
                            }
                            case 'u' -> {
                                // Handle using development cards
                                switch (inputs.charAt(1)) {
                                    case 'd' -> {
                                        System.out.println("""
                                                0 knight
                                                1 road building 2 roads
                                                2 Year of plenty
                                                3 monopoly
                                                4 victory point
                                                        """);
                                        System.out.println("What you have");
                                        System.out.println(Arrays.toString(p.getDevPlayer()));

                                        System.out.println("Which one? >");
                                        int developmentcard = s.nextInt();
                                        if (p.canUseDevelopmentCard(developmentcard)) {
                                            switch (developmentcard) {
                                                case 0 -> {
                                                    robberMovement(s, false);
                                                    p.subtractDevelopmentCard(developmentcard);
                                                }
                                                case 1 -> {
                                                    System.out.println("where x y for road 1");
                                                    int x = s.nextInt();
                                                    int y = s.nextInt();
                                                    if (board.canPlaceRoad(x, y, p)) {
                                                        board.getEdge(x, y).addRoad(new Road(p));
                                                    } else {
                                                        System.out.println("Invalid placement for road, try again");
                                                    }

                                                    System.out.println("where x y for road 2");
                                                    x = s.nextInt();
                                                    y = s.nextInt();
                                                    if (board.canPlaceRoad(x, y, p)) {
                                                        board.getEdge(x, y).addRoad(new Road(p));
                                                    } else {
                                                        System.out.println("Invalid placement for road, try again");
                                                    }
                                                    p.subtractDevelopmentCard(developmentcard);
                                                }
                                                case 2 -> {
                                                    int[] resources = {0, 0, 0, 0, 0};
                                                    while (true) {
                                                        System.out.println("Which resources? (wood brick sheep wheat ore)");
                                                        for (int i = 0; i < resources.length; i++) {
                                                            resources[i] = s.nextInt();
                                                        }
                                                        double sum = 0;
                                                        for (int resource : resources)
                                                            sum += resource;
                                                        if (sum == 2) {
                                                            break;
                                                        }
                                                        System.out.println("Please select exactly two resources.");
                                                    }
                                                    game.developmentCardYearOfPlenty(resources);
                                                    p.subtractDevelopmentCard(developmentcard);
                                                }
                                                case 3 -> {
                                                    System.out.println("Which resource?");
                                                    game.developmentCardMonopoly(s.nextLine());
                                                    p.subtractDevelopmentCard(developmentcard);
                                                }
                                                case 4 -> {
                                                    game.developmentCardVP();
                                                    p.subtractDevelopmentCard(developmentcard);
                                                }
                                            }
                                        }
                                    }

                                    case 'h' -> {
                                        // Handle upgrading houses
                                        int x = s.nextInt();
                                        int y = s.nextInt();

                                        if (!game.upgradeHouse(x, y)) {
                                            System.out.println("Something is wrong, you may be too broke or no house to upgrade there");
                                        } else {
                                            cp.updateCity(x, y, p.getColor());
                                            p.addVP(1);
                                        }
                                    }
                                }
                            }
                            case 't' -> {
                                // Handle trading actions
                                switch (inputs.charAt(1)) {
                                    case 'b' -> {
                                        System.out.println("What resource do you want to give away (String)");
                                        s.nextLine();
                                        String away = s.nextLine();
                                        System.out.println("What resource do you want to get (String)");
                                        String get = s.nextLine();
                                        if (!game.bankTrade(away, get)) {
                                            System.out.println("You are probably broke");
                                        }
                                    }
                                    case 'p' -> {
                                        System.out.println("What resource do you want to give away (int[])");
                                        int[] give = new int[]{s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()};
                                        System.out.println("What resource do you want to get (int[])");
                                        int[] get = new int[]{s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()};
                                        System.out.println("With whom are you trading?");
                                        for (int i = 0; i < 4; i++) {
                                            System.out.println(players.get(i).getColor() + i);
                                        }
                                        Player play = players.get(s.nextInt());
                                        if (!game.playerTrade(give, get, play)) {
                                            System.out.println("Trade unsuccessful");
                                        }
                                    }
                                }
                            }

                            default -> System.out.println("Try again.");
                        }
                        if (end) {
                            game.endTurn();
                            break;
                        }
                    }
                }

                // Check if any player has reached 10 victory points to end the game
                if (p.getVPs() == 10) {
                    break;
                }
            }

            // Announce the winner of the game
            System.out.println("Winner is " + game.getCurrentPlayer().getColor());

            s.close();
        } catch (Error e) {
            System.out.println("An error occurred. Please follow the instructions properly.");
            System.out.println(e);
        }
    }

    /**
     * Manages the movement of the robber and potential resource stealing from players.
     *
     * @param s         Scanner object for user input
     * @param dropHalf  Flag indicating whether players need to drop half of their resources
     */
    private static void robberMovement(Scanner s, boolean dropHalf) {
        System.out.println("Where would you like to move the robber? (tile number)");
        int tile;
        while (true) {
            tile = s.nextInt();
            if (!board.moveRobber(tile)) {
                System.out.println("The robber is already there, try again.");
            } else {
                moveRobberGUI(tile);
            }
            break;
        }
        ArrayList<Player> playersToStealFrom = game.developmentCardMoveRobber(tile);

        // Dropping resources if players have more than 7 cards
        if (dropHalf) {
            for (Player p : players) {
                if (game.getNumberOfResources(p) > 7) {
                    System.out.println("Player " + p.getColor() + " you have too many cards, drop " + game.getNumberOfResources(p) / 2);
                    System.out.println("Your cards: Wood " + p.getMatArray()[0] + " Brick " + p.getMatArray()[1] +
                            " Sheep " + p.getMatArray()[2] + " Wheat " + p.getMatArray()[3] + " Ore " + p.getMatArray()[4]);
                    System.out.println("Please type in the format # # # # #");
                    System.out.println("Example: 3 2 1 0 6");
                    while (true) {
                        if (game.dropCards(p, game.getNumberOfResources(p) / 2, new int[]{s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()})) {
                            break;
                        } else {
                            System.out.println("Invalid input, try again.");
                        }
                    }
                }
            }
        }

        // Stealing resources from players if robber is moved to a tile with other players
        if (playersToStealFrom.isEmpty()) {
            System.out.println("You placed the robber where there are no players.");
        } else {
            System.out.println("You placed the robber where players are, select a player to steal from by their position:");
            for (int i = 0; i < playersToStealFrom.size(); i++) {
                System.out.println(playersToStealFrom.get(i).getColor() + " " + (i + 1));
            }

            String resourceStolen = game.getRandomResource(playersToStealFrom.get(s.nextInt() - 1));

            if (resourceStolen.equals("broke")) {
                System.out.println("That player has no resources to steal.");
            } else {
                System.out.println("You successfully stole " + resourceStolen + "!");
            }
        }
    }

    /**
     * Sets up the initial game state by placing houses and roads for each player.
     *
     * @param s Scanner object for user input
     */
    private static void setupGame(Scanner s) {
        // Predefined initial placement for players
        int[] players = new int[]{0, 1, 2, 3, 3, 2, 1, 0};

        // Skip setup if specified, otherwise, proceed with standard setup
        if (skipSetup) {
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
        } else {
            // Standard setup where each player places their initial house and road
            for (int i : players) {
                game.setCurrentPlayer(i);
                System.out.println("Player " + game.getPlayerColor() + ", where would you like to put your first house? (format x y)");

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
        }
        board.setfirstPlacing();
        game.setCurrentPlayer(0);
    }

    public static void moveRobberGUI(int tile) {
        switch (tile) {
            case (1):
                cp.updateRobberPosition(200, 100);
            case (2):
                cp.updateRobberPosition(200 + (int)(TileImage.scale * 170), 100);
            case (3):
                cp.updateRobberPosition(200 + (int)(2 * TileImage.scale * 170), 100);
            case (4):
                cp.updateRobberPosition(200 - (int)(0.5 * TileImage.scale * 170), 100 + (int)(TileImage.scale * 152));
            case (5):
                cp.updateRobberPosition(200 + (int)(0.5 * TileImage.scale * 174), 100 + (int)(TileImage.scale * 152));
            case (6):
                cp.updateRobberPosition(200 + (int)(3 * 0.5 * TileImage.scale * 170), 100 + (int)(TileImage.scale * 152));
            case (7):
                cp.updateRobberPosition(200 + (int)(5 * 0.5 * TileImage.scale * 170), 100 + (int)(TileImage.scale * 152));
            case (8):
                cp.updateRobberPosition(200 - (int)(2 * 0.5 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152));
            case (9):
                cp.updateRobberPosition(200, 100 + (int)(2 * TileImage.scale * 152));
            case (10):
                cp.updateRobberPosition(200 + (int)(2 * 0.5 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152));
            case (11):
                cp.updateRobberPosition(200 + (int)(2 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152));
            case (12):
                cp.updateRobberPosition(200 + (int)(2 * 1.5 * TileImage.scale * 170), 100 + (int)(2 * TileImage.scale * 152));
            case (13):
                cp.updateRobberPosition(200 - (int)(0.5 * TileImage.scale * 170), 100 + (int)(3 * TileImage.scale * 152));
            case (14):
                cp.updateRobberPosition(200 + (int)(0.5 * TileImage.scale * 174), 100 + (int)(3 * TileImage.scale * 152));
            case (15):
                cp.updateRobberPosition(200 + (int)(3 * 0.5 * TileImage.scale * 170), 100 + (int)(3 * TileImage.scale * 152));
            case (16):
                cp.updateRobberPosition(200 + (int)(5 * 0.5 * TileImage.scale * 170), 100 + (int)(3 * TileImage.scale * 152));
            case (17):
                cp.updateRobberPosition(200, 100 + (int)(4 * TileImage.scale * 152));
            case (18):
                cp.updateRobberPosition(200 + (int)(TileImage.scale * 170), 100 + (int)(4 * TileImage.scale * 152));
            case (19):
                cp.updateRobberPosition(200 + (int)(2 * TileImage.scale * 170), 100 + (int)(4 * TileImage.scale * 152));
        }
    }
}