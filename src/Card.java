package src;
import java.util.*;

public class Card
{
    private String cardType;

    public Card(){
        cardType = "";
    }

    public String getCardType(){
        return cardType;
    }

    public void setCardType( String cardType) {
        this.cardType = cardType;
    }
}