package Control;
import Control.PlayerManager;
import Model.Map;
import Model.Player;
import Model.Card;
import Model.DevelopmentCardTypes.*;
import Model.DevelopmentCardTypes.ProgressCardTypes.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    private final String[] PLAYER_COLORS = {"#FFA500" , "#FF6347" , "#98FB98" , "#87CEFA"};
    private int[][] DICE_TO_ADJACENT_TOWNS_WOOL = {{} , {} , {} , {40,44,45,48,49,52} , {31,36,37,41,42,46} , {17,22,23,28,29,34} , {20,25,26,31,32,37} , {} , {} , {} , {} , {} , {} };
    private int[][] DICE_TO_ADJACENT_TOWNS_LUMBER = {{} , {} , {} , {} , {} , {19,24,25,30,31,36} , {} , {} , {16,21,22,27,28,33} , {} , {7,11,12,16,17,22} , {29,34,35,39,40,44} , {} };
    private int[][] DICE_TO_ADJACENT_TOWNS_GRAIN = {{} , {} , {} , {28,33,34,38,39,43} , {9,13,14,18,19,24} , {} , {} , {} , {} , {41,45,46,49,50,53} , {} , {} , {} };
    private int[][] DICE_TO_ADJACENT_TOWNS_BRICK = {{} , {} , {} , {} , {} , {} , {} , {} , {30,35,36,40,41,45} , {} , {} , {0,3,4,7,8,12} , {10,14,15,19,20,25} };
    private int[][] DICE_TO_ADJACENT_TOWNS_ORE = {{} , {} , {2,5,6,9,10,14} , {} , {} , {} , {39,43,44,47,48,51} , {} , {} , {18,23,24,29,30,35} , {1,4,5,8,9,13} , {} , {} };
    private PlayerManager playerManager;
    private BuildingManager buildingManager;
    private Map map;
    int playerNo;
    private int turnNo;
    private boolean firstTurn;
    private boolean secondTurn;
    private int turnDice;
    private ArrayList<Card> initialDevelopmentCardStack = new ArrayList<Card>();

    public GameManager(String player1, String player2, String player3 , String player4) {
        this.playerManager = new PlayerManager( player1, player2, player3 , player4);
        playerNo = 0;
        turnNo = 1;
        firstTurn = false;
        secondTurn = false;
        initialDevelopmentCardStack = createInitialCardStack();
        buildingManager = new BuildingManager();
        map = new Map();
        synchronizeMap();
    }

    public String returnPlayerColor(){
        return PLAYER_COLORS[playerNo];
    }

    private void synchronizeMap() {
        DICE_TO_ADJACENT_TOWNS_BRICK = map.getDiceAdjacency( "Brick");
        DICE_TO_ADJACENT_TOWNS_GRAIN = map.getDiceAdjacency( "Grain");
        DICE_TO_ADJACENT_TOWNS_LUMBER = map.getDiceAdjacency( "Lumber");
        DICE_TO_ADJACENT_TOWNS_ORE = map.getDiceAdjacency( "Ore");
        DICE_TO_ADJACENT_TOWNS_WOOL = map.getDiceAdjacency( "Wool");
    }

    private ArrayList<Card> createInitialCardStack() {
        ArrayList<Card> stack = new ArrayList<Card>();
        for(int i = 0 ; i < 14 ; i++){
            stack.add(new KnightCard());
        }
        for(int i = 0 ; i < 5 ; i++){
            stack.add(new VictoryPointCard());
        }
        for(int i = 0 ; i < 2 ; i++){
            stack.add(new RoadCard());
        }
        for(int i = 0 ; i < 2 ; i++){
            stack.add(new MonopolyCard());
        }
        for(int i = 0 ; i < 2 ; i++){
            stack.add(new YearOfPlentyCard());
        }
        Collections.shuffle(stack);
        return stack;
    }

    public int rollDice()
    {
        return (int) Math.ceil( Math.random() * 11) + 1;
    }

    public boolean distributeResources(int dice)
    {
        if(dice!=7){
            for(int i = 0 ; i < DICE_TO_ADJACENT_TOWNS_WOOL[dice].length ; i++){
                playerManager.distributeResources(DICE_TO_ADJACENT_TOWNS_WOOL[dice][i] , "Wool");
            }
            for(int i = 0 ; i < DICE_TO_ADJACENT_TOWNS_BRICK[dice].length ; i++){
                playerManager.distributeResources(DICE_TO_ADJACENT_TOWNS_BRICK[dice][i] , "Brick");
            }
            for(int i = 0 ; i < DICE_TO_ADJACENT_TOWNS_LUMBER[dice].length ; i++){
                playerManager.distributeResources(DICE_TO_ADJACENT_TOWNS_LUMBER[dice][i] , "Lumber");
            }
            for(int i = 0 ; i < DICE_TO_ADJACENT_TOWNS_GRAIN[dice].length ; i++){
                playerManager.distributeResources(DICE_TO_ADJACENT_TOWNS_GRAIN[dice][i] , "Grain");
            }
            for(int i = 0 ; i < DICE_TO_ADJACENT_TOWNS_ORE[dice].length ; i++){
                playerManager.distributeResources(DICE_TO_ADJACENT_TOWNS_ORE[dice][i] , "Ore");
            }
            return true;
        }
        return false;
    }

    public Player gameOver()
    {
        return new Player("ads");
    }

    public boolean tradeResource( String given , String wanted){
        return this.playerManager.tradeResource(playerNo , given , wanted);
    }

    public ArrayList<Card> getInitialDevelopmentCardStack() {
        return initialDevelopmentCardStack;
    }
    public boolean buyDevelopmentCard(){
        if(!initialDevelopmentCardStack.isEmpty()) {
            if (this.playerManager.buyDevelopmentCard(playerNo)) {
                this.playerManager.addProgressCard(playerNo , initialDevelopmentCardStack.get(0));
                initialDevelopmentCardStack.remove(0);
                return true;
            }
        }
        return false;
    }
    public void playMonopoly(String resourceType){
        this.playerManager.playMonopoly(resourceType , playerNo);
    }
    public void playYearOfPlenty(String resourceType){
        this.playerManager.playYearOfPlenty(resourceType , playerNo);
    }
    public void playRoadBuilding(){
        this.playerManager.playRoadBuilding(playerNo);
    }
    public boolean addRoad(int location){
        return buildingManager.buildRoad(playerManager.getPlayers()[playerNo] , location);
    }
    public boolean addSettlement(int location){
        return buildingManager.buildSettlement(playerManager.getPlayers()[playerNo] , location);
    }
    public String nextTurn(){
        playerNo++;
        if(playerNo == 4) {
            playerNo = 0;
            turnNo++;
        }
        turnDice = this.rollDice();
        this.distributeResources(turnDice);
        return playerManager.getPlayers()[playerNo].getName();
    }
    public int getTurnDice() {
        return turnDice;
    }

    public int[] getResources(){
        return playerManager.getPlayers()[playerNo].getResources();
    }
    public int[] getDevelopmentCards(){
        return playerManager.getPlayers()[playerNo].getDevelopmentCards();
    }

    public int[] getScoreBoard(){
        return playerManager.scoreBoard(playerNo);
    }
    public int largestArmy(){
        return playerManager.largestArmy();
    }
    public int longestRoad(){
        return playerManager.whoHasLongestRoad();
    }

    public String getPlayerName() {
        return playerManager.getName(playerNo);
    }
}
