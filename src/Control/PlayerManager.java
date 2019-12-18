package Control;
import Model.Player;
import Model.Card;

import java.util.ArrayList;

public class PlayerManager {
    private Player[] players;

    public PlayerManager(String player1, String player2 , String player3 , String player4) {
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

    public boolean tradeResource(int playerNo , String given , String wanted){
        return players[playerNo].tradeResource(given , wanted);
    }

    public boolean buyDevelopmentCard(int playerNo) {
        return players[playerNo].buyDevelopmentCard();
    }

    public void addProgressCard(int playerNo , Card card) {
        players[playerNo].addProgressCard(card);
    }

    public void playMonopoly(String resourceType , int playerNo) {
        int totalResource = 0;
        for(int i = 0 ; i < players.length ; i++){
            if(i!=playerNo){
                totalResource += players[playerNo].affectMonopoly(resourceType);
            }
        }
        players[playerNo].playMonopoly(resourceType , totalResource);
    }

    public void playYearOfPlenty(String resourceType, int playerNo) {
        this.players[playerNo].playYearOfPlenty(resourceType);
    }

    public void playRoadBuilding(int playerNo) {
        this.players[playerNo].playRoadBuilding();
    }

    public void playKnightCard( int currentPlayerNo, ArrayList<Player> toDraw)
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
                if ( players[j].getName().equals( playerName) && j != currentPlayerNo )
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
        players[currentPlayerNo].addResource( resource);
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
}
