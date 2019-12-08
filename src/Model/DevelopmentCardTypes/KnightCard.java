package Model.DevelopmentCardTypes;
import Model.DevelopmentCard;

public class KnightCard extends DevelopmentCard {
    //properties

    //constructor
    public KnightCard()
    {
        setCardType( "Knight");
    }

    //methods
    public boolean changeRobber(){
        return true;
    }

    public boolean drawCard(){
        return true;
    }
}
