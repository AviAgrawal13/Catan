public class House {
    private int level; // 1 or 2 based (city or settlement respectively)
    private Player player;

    public House(Player player) {
        this.level = 1;
        this.player = player;
    }

    public int getLevel() {
        return level;
    }

    public Player getNameOfPlayer() {return player;}
    public void increaseLevel() {level++;}
}
