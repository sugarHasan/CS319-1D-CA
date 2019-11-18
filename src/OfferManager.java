package src;
import src.Offer;
import src.Player;
import src.ResourceCard;
import java.util.ArrayList;

public class OfferManager {
    ArrayList<Offer> offers = new ArrayList<Offer>();

    public void makeOffer( Player sender, Player receiver, ResourceCard offeredItem,
                           ResourceCard demandedItem, int offerNum, int demandNum){
        offers.add( new Offer( sender, receiver, offeredItem, demandedItem, offerNum, demandNum));
    }
    public void listOffers( ArrayList<Offer>offers){
        for(int i = 0 ; i < offers.size() ; i++){
            System.out.println( (i + 1) + "st Offer: " + offers.get(i) );
        }
    }
    public Offer findOffer( int offerIndex){
        return offers.get( offerIndex);
    }
    public void closeOffer( int offerIndex){
        offers.remove( offerIndex);
    }
    public Offer acceptOffer( int offerIndex){
        Offer acceptedOffer;
        acceptedOffer = offers.get( offerIndex);
        closeOffer( offerIndex);
        return acceptedOffer;
    }
    public boolean declineOffer( int offerIndex){
        closeOffer( offerIndex);
    }
}
