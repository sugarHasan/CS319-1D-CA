package src;

public class KnightCard extends DevelopmentCard {
    //properties

    //constructor
    public KnightCard(){
        cardType = "Knight Card";
    }

    //methods
    public boolean changeRobber(){
        return true;
    }

    public boolean drawCard(){
        return true;
    }
}
