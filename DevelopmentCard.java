public class DevelopmentCard {
    public int typeOfCard;

    /*8
        1 knight
        2 road building 2 roads
        3 Year of plenty
        4 monopoly
        5 victory point
     */
    public DevelopmentCard(int cardType) {
        this.typeOfCard = cardType;
    }

    public int getTypeOfCard() {
        return this.typeOfCard;
    }


}
