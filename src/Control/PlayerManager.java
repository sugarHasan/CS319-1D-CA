package Control;
import Model.DevelopmentCardTypes.KnightCard;
import Model.Offer;
import Model.Player;
import Model.Card;

import java.util.ArrayList;

public class PlayerManager {
    private Player[] players;
    private OfferManager offerManager;

    public PlayerManager(String player1, String player2 , String player3 , String player4) {
        offerManager = new OfferManager();

        players = new Player[4];
        players[0] = new Player(player1);
        players[1] = new Player(player2);
        players[2] = new Player(player3);
        players[3] = new Player(player4);
    }

    public void distributeResources(int town, String resource){
        for(int i = 0 ; i < players.length ; i++){
            players[i].addResource(town , resource);
        }
    }

    public int[] scoreBoard(int turnedPlayer){
        int[] scores = new int[4];
        for(int i = 0 ; i < players.length ; i++){
            scores[i] = players[i].getVictoryPointsOffTurn();
        }
        scores[turnedPlayer] = players[turnedPlayer].getVictoryPointsOnTurn();
        return scores;
    }

    public int whoHasLongestRoad(){
        int longestRoad = 0;
        int playerIndex = -1;
        for(int i = 0 ; i < players.length ; i++){
            int playersLongestRoad = players[i].longestRoad();
            if( playersLongestRoad > longestRoad && playersLongestRoad >= 5){
                longestRoad = playersLongestRoad;
                playerIndex = i;
            }
        }
        if(playerIndex!=-1) return playerIndex;
        return -1;
    }

    public void robberSteals()
    {
        for ( int i = 0; i < players.length; i++)
        {
            players[i].robberSteals();
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void fishing()
    {
        for ( int i = 0; i < players.length; i++ )
            players[i].fishing();
    }

    public void checkRobberPresence( int playerNo, int robberLocation)
    {
        players[playerNo].checkRobberPresence( robberLocation);
    }

    public boolean tradeResource(int playerNo , String given , String wanted){
        return players[playerNo].tradeResource(given , wanted);
    }

    public void makeOffer( int senderNo, int receiverNo, String offeredItem,
                           String demandedItem, int offerNum, int demandNum)
    {
        offerManager.makeOffer( players[senderNo], players[receiverNo], offeredItem, demandedItem, offerNum, demandNum);
    }

    public ArrayList<Offer> listOffers( int playerNo)
    {
        return offerManager.listOffers( players[playerNo]);
    }

    public boolean acceptOffer( Offer offer)
    {
        if ( offerManager.acceptOffer( offer) )
        {
            int senderNo = -1, receiverNo = -1;
            for ( int i = 0; i < players.length; i++ )
            {
                if ( offer.getSender().getName().equals( players[i].getName()))
                {
                    senderNo = i;
                    break;
                }
            }
            for ( int i = 0; i < players.length; i++ )
            {
                if ( offer.getReceiver().getName().equals( players[i].getName()))
                {
                    receiverNo = i;
                    break;
                }
            }

            if ( senderNo >= 0 && senderNo < players.length && receiverNo >= 0 && receiverNo < players.length
                    && players[senderNo].hasCard( offer.getOfferedItem(), offer.getOfferNum())
                    && players[receiverNo].hasCard( offer.getDemandedItem(), offer.getDemandNum()) )
            {
                players[senderNo].manageOffer( offer.getDemandedItem(), offer.getDemandNum(),
                                               offer.getOfferedItem(), offer.getOfferNum());
                players[receiverNo].manageOffer( offer.getOfferedItem(), offer.getOfferNum(),
                                                 offer.getDemandedItem(), offer.getDemandNum());
                return true;
            }
        }
        return false;
    }

    public boolean declineOffer( Offer offer)
    {
        return offerManager.declineOffer( offer);
    }

    public boolean buyDevelopmentCard(int playerNo) {
        return players[playerNo].buyDevelopmentCard();
    }

    public void addProgressCard(int playerNo , Card card) {
        players[playerNo].addProgressCard(card);
    }

    public void playMonopoly(String resourceType , int playerNo) {
        if ( players[playerNo].hasCard( "Monopoly", 1))
        {
            int totalResource = 0;
            for(int i = 0 ; i < players.length ; i++){
                if(i!=playerNo){
                    totalResource += players[playerNo].affectMonopoly(resourceType);
                }
            }
            players[playerNo].playMonopoly(resourceType , totalResource);
        }
    }

    public void playYearOfPlenty(String resourceType, int playerNo) {
        this.players[playerNo].playYearOfPlenty(resourceType);
    }

    public void playRoadBuilding(int playerNo) {
        this.players[playerNo].playRoadBuilding();
    }

    public boolean playKnightCard( int currentPlayerNo, ArrayList<Player> toDraw)
    {
        if ( players[currentPlayerNo].hasCard( "Knight", 1))
        {
            String resource, playerName;
            int random;

            resource = "";
            while ( toDraw.size() > 0 )
            {
                random = (int) Math.ceil( Math.random() * toDraw.size());
                playerName = toDraw.get(random).getName();

                for ( int j = 0; j < players.length; j++)
                {
                    if ( j != currentPlayerNo && players[j].getName().equals( playerName) )
                    {
                        resource = players[j].stealRandomResource();
                        break;
                    }
                }
                if ( resource.equals("") )
                {
                    toDraw.remove( random);
                }
                else
                    break;
            }
            if ( !resource.equals( ""))
                players[currentPlayerNo].addResource( resource);
            players[currentPlayerNo].playKnightCard();
            return true;
        }
        return false;
    }

    public int largestArmy() {
        int largestArmyOwner = -1;
        int largestArmy = 0;
        for(int i = 0 ; i < players.length ; i++){
            if(largestArmy < players[i].armySize()){
                largestArmy = players[i].armySize();
                largestArmyOwner = i;
            }
        }
        return largestArmyOwner;
    }

    public String getName(int playerNo) {
        return players[playerNo].getName();
    }

    public void changeIndexPlayerName(String substring, int i) {
        players[i].changeName(substring);
    }
}
