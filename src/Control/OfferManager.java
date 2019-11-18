package Control;
import Model.Offer;
import Model.Player;
import Model.ResourceCard;
import java.util.ArrayList;

public class OfferManager {
    ArrayList<Offer> offers = new ArrayList<Offer>();

    public void makeOffer( Player sender, Player receiver, ResourceCard offeredItem,
                           ResourceCard demandedItem, int offerNum, int demandNum)
    {
        offers.add( new Offer( sender, receiver, offeredItem, demandedItem, offerNum, demandNum));
    }
    public ArrayList<Offer> listOffers( Player player)
    {
        /*
        for( int i = 0 ; i < offers.size() ; i++){
            System.out.println( (i + 1) + "st Offer: " + offers.get(i) );
        }
        */
        ArrayList<Offer> playersOffers = new ArrayList<Offer>();
        for ( int i = 0; i < offers.size(); i++)
        {
            if ( offers.get(i).getReceiver().getName().equals( player.getName()) )
                playersOffers.add( offers.get(i));
        }
        return playersOffers;
    }

    public int findOffer( Offer offer)
    {
        for ( int i = 0; i < offers.size(); i++)
        {
            if ( offers.get(i) == offer )
                return i;
        }
        return -1;
    }

    public void closeOffer( int offerIndex)
    {
        offers.remove( offerIndex);
    }

    public Offer acceptOffer( int offerIndex)
    {
        Offer acceptedOffer;
        acceptedOffer = offers.get( offerIndex);
        closeOffer( offerIndex);
        return acceptedOffer;
    }

    public void declineOffer( int offerIndex)
    {
        closeOffer( offerIndex);
    }
}
