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
    public Player whoHasLongestRoad(){
        int longestRoad = 0;
        int playerIndex = -1;
        for(int i = 0 ; i < players.length ; i++){
            int playersLongestRoad = players[i].longestRoad();
            if( playersLongestRoad > longestRoad && playersLongestRoad >= 5){
                longestRoad = playersLongestRoad;
                playerIndex = i;
            }
        }
        if(playerIndex!=-1) return players[playerIndex];
        return null;
    }

    public Player whoHasMostSoldier(){
        int largestArmy = 0;
        int playerIndex = -1;
        for(int i = 0 ; i < players.length ; i++){
            int playersLargestArmy = players[i].armySize();
            if( playersLargestArmy > largestArmy ){
                largestArmy = playersLargestArmy;
                playerIndex = i;
            }
        }
        if(playerIndex!=-1) return players[playerIndex];
        return null;
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
}
