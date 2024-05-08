

public class Player {

    private int wood = 0;
    private int brick = 0;
    private int sheep = 0;
    private int wheat = 0;
    private int ore = 0;
    private int VP = 0; // victory points
    private boolean hasKnightAchievment = false;
    private boolean hasRoadAcheivment = false;
    private String color;
    private int[] developmentCards = new int[5];

    /*8
        0 knight
        1 road building 2 roads
        2 Year of plenty
        3 monopoly
        4 victory point
    */

    public Player(int wood, int brick, int sheep, int wheat, int ore, String color){
        //add cards to hand depending on what tiles their starting settlements border.
        //make method to return arraylist of cards to do this
        this.wood = wood;
        this.brick = brick;
        this.sheep = sheep;
        this.wheat = wheat;
        this.ore = ore;
        this.color = color;
    }

    public int[] getMatArray(){
        return new int[]{wood, brick, sheep, wheat, ore};
    }

    public int getMat(String material) {
        return switch (material) {
            case "wood" -> this.wood;
            case "brick" -> this.brick;
            case "sheep" -> this.sheep;
            case "wheat" -> this.wheat;
            case "ore" -> this.ore;
            default -> -1;
        };
    }

    public void updateMat(String material, int amount) {
        switch (material) {
            case "wood":
                this.wood += amount;break;
            case "brick":
                this.brick += amount;break;
            case "sheep":
                this.sheep += amount;break;
            case "wheat":
                this.wheat += amount;break;
            case "ore":
                this.ore += amount;break;
        }
    }

    public void setPlayerResources(String material, int amount) {
        switch (material) {
            case "wood":
                this.wood = amount;
            case "brick":
                this.brick = amount;
            case "sheep":
                this.sheep = amount;
            case "wheat":
                this.wheat = amount;
            case "ore":
                this.ore = amount;
        }
    }

    public void addDevelopmentCard(int typeOfCard) {
        ++developmentCards[typeOfCard];
    }

    public boolean canUseDevelopmentCard(int typeOfCard) {
        if (developmentCards[typeOfCard] > 0) {
            developmentCards[typeOfCard] = developmentCards[typeOfCard]--;
            return true;
        } else {
            return false;
        }

    }

    public int removeAllResources(String typeOfResource) {
        int amountOfResources = this.getMat(typeOfResource);
        this.setPlayerResources(typeOfResource, 0);
        return amountOfResources;
    }

    public void addVP(int amount) {
        this.VP += amount;
    }

    public int getVPs() {return this.VP;}


    public String getColor() {
        return this.color;
    }
    
}
