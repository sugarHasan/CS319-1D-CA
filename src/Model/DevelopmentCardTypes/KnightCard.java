package Model.DevelopmentCardTypes;
import Model.DevelopmentCard;

public class KnightCard extends DevelopmentCard {
    //properties

    //constructor
    public KnightCard()
    {
        setCardType( "KnightCard");
    }

    //methods
    public boolean changeRobber(){
        return true;
    }

    public boolean drawCard(){
        return true;
    }
}
