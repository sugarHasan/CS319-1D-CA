package Model;
import Model.BuildingTypes.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Player {
    private final int NO_OF_PATHS = 72;
    private final int[][] DOCK_LOCATIONS = { {0,3,10,15,26,32,47,51},{1,5},{11,16},{33,38},{42,46},{49,52}};

    private ArrayList<Building> buildings;
    private HashMap<String , Integer> cards;
    private int victoryPoints;
    private String name;
    private Happiness happiness;

    public Player(String name){
        this.name = name;
        cards = new HashMap<String , Integer>();
        cards.put("Wool" , 12);
        cards.put("Ore" , 10);
        cards.put("Lumber" , 14);
        cards.put("Grain" , 12);
        cards.put("Brick" , 14);
        cards.put("Knight" , 0);
        cards.put("RoadBuilding" , 0);
        cards.put("YearOfPlenty" , 0);
        cards.put("Monopoly" , 0);
        cards.put("VictoryPoint" , 0);


        victoryPoints = 0;
        buildings = new ArrayList<Building>();
        happiness = new Happiness();
    }

    public void addCard(Card newCard){
        String cardName = newCard.getCardType();
        int numberOfCard = cards.get(cardName);
        cards.put(cardName , cards.get(cardName)+1);
    }

    public boolean removeCard(Card removeCard){
        String cardName = removeCard.getCardType();
        int numberOfCard = cards.get(cardName);
        if(numberOfCard != 0){
            cards.put(cardName , cards.get(cardName)-1);
            return true;
        }
        return false;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void robberSteals()
    {
        int wool = cards.get( "Wool");
        int ore = cards.get( "Ore");
        int lumber = cards.get( "Lumber");
        int grain = cards.get( "Grain");
        int brick = cards.get( "Brick");
        int random;

        int steal = (wool + ore + lumber + grain + brick) / 2;
        while ( steal > 0 )
        {
            random = (int) Math.ceil( Math.random() * 5);
            if ( random == 1 && wool > 0 )
            {
                cards.put( "Wool", cards.get( "Wool")-1);
                wool--;
            }
            else if ( random == 2 && ore > 0 )
            {
                cards.put( "Ore", cards.get( "Ore")-1);
                ore--;
            }
            else if ( random == 3 && lumber > 0 )
            {
                cards.put( "Lumber", cards.get( "Lumber")-1);
                lumber--;
            }
            else if ( random == 4 && grain > 0 )
            {
                cards.put( "Grain", cards.get( "Grain")-1);
                grain--;
            }
            else if ( random == 5 && brick > 0)
            {
                cards.put( "Brick", cards.get( "Brick")-1);
                brick--;
            }
            else
                continue;
            steal--;
        }
    }

    public String stealRandomResource()
    {
        if ( cards.get( "Wool") < 0
            && cards.get( "Ore") < 0
            && cards.get( "Lumber") < 0
            && cards.get( "Grain") < 0
            && cards.get( "Brick") < 0)
            return "";

        int random;
        while ( true)
        {
            random = (int) Math.ceil( Math.random() * 5);
            if ( random == 1 && cards.get( "Wool") > 0 )
            {
                cards.put( "Wool", cards.get( "Wool")-1);
                return "Wool";
            }
            else if ( random == 2 && cards.get( "Ore") > 0 )
            {
                cards.put( "Ore", cards.get( "Ore")-1);
                return "Ore";
            }
            else if ( random == 3 && cards.get( "Lumber") > 0 )
            {
                cards.put( "Lumber", cards.get( "Lumber")-1);
                return "Lumber";
            }
            else if ( random == 4 && cards.get( "Grain") > 0 )
            {
                cards.put( "Grain", cards.get( "Grain")-1);
                return "Grain";
            }
            else if ( random == 5 && cards.get( "Brick") > 0)
            {
                cards.put( "Brick", cards.get( "Brick")-1);
                return "Brick";
            }
        }
    }

    public void fishing()
    {
        int count = 0;
        double random;
        for ( int i = 0; i < buildings.size(); i++ )
        {
            for ( int j = 0; j < DOCK_LOCATIONS.length; j++ )
            {
                for ( int k = 0; k < DOCK_LOCATIONS[j].length; k++)
                {
                    if ( buildings.get( i).getLocation() == DOCK_LOCATIONS[j][k] )
                    {
                        random = Math.random();
                        if ( random < 0.33 )
                        {
                            System.out.println( "Thats fish");
                            count++;
                        }
                        break;
                    }
                }
            }
        }
        happiness.caughtFish( count);
    }

    public void checkRobberPresence( int robberLocation)
    {
        int count = 0;
        for ( int i = 0; i < buildings.size(); i++ )
        {
            if ( buildings.get(i).getLocation() == robberLocation)
                count++;
        }
        happiness.robberInTown( count);
    }

    public void addBuilding(Building newBuilding){
        buildings.add(newBuilding);
    }

    public int getVictoryPointsOffTurn() {
        int pointOfBuilding = 0;
        for ( int i = 0; i < buildings.size(); i++)
        {
            pointOfBuilding = pointOfBuilding + buildings.get(i).getVictoryPoint();
        }
        return victoryPoints+pointOfBuilding;
    }

    public int getVictoryPointsOnTurn() {
        int pointOfBuilding = 0;
        for ( int i = 0; i < buildings.size(); i++)
        {
            pointOfBuilding = pointOfBuilding + buildings.get(i).getVictoryPoint();
        }
        return victoryPoints+cards.get("VictoryPoint")+pointOfBuilding;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int longestRoad(){
        int[][] adjList = new int[NO_OF_PATHS][NO_OF_PATHS];
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i) instanceof Road){
                Road road = (Road)buildings.get(i);
                int[] adjacentRoads = road.getAdjacentRoads();
                for(int j = 0 ; j < adjacentRoads.length ; j++){
                    for(int k = 0 ; k < buildings.size() ; k++) {
                        if(buildings.get(k) instanceof Road) {
                            if(buildings.get(k).getLocation() == adjacentRoads[j]) {
                                adjList[road.getLocation()][adjacentRoads[j]] = 1;
                                adjList[adjacentRoads[j]][road.getLocation()] = 1;
                            }
                        }
                    }
                }
            }
        }
        int longestRoad = 0;
        for(int i = 0; i < NO_OF_PATHS; i++){
            boolean[] visited = new boolean[NO_OF_PATHS];
            longestRoad = Math.max(dfs(adjList , visited , i) , longestRoad);
        }
        return longestRoad;
    }

    private int dfs(int[][] adjList , boolean[] visited , int index ){
        if(visited[index])  return 0;
        visited[index] = true;
        int res = 1;
        for(int i = 0; i < NO_OF_PATHS; i++){
            if(adjList[index][i] == 1){
                if(!visited[i]){
                    res += dfs(adjList , visited , i);
                }
            }
        }
        return res;
    }

    public void removeBuilding(int location){
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i).getLocation() == location){
                buildings.remove(i);
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addResource( String resource)
    {
        cards.put( resource, cards.get( resource) + 1);
    }

    public void addResource(int town , String resource){
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i) instanceof Settlement){
                if(buildings.get(i).getLocation() == town){
                    cards.put(resource , cards.get(resource)+1);
                }
            }
            if(buildings.get(i) instanceof City){
                if(buildings.get(i).getLocation() == town){
                    cards.put(resource , cards.get(resource)+2);
                }
            }
        }
    }

    public int armySize() {
        return cards.get("Knight");
    }

    public boolean tradeResource( String given , String wanted){
        int ratio = 4, resourceMap = 0;

        if ( given.equals( "Wool"))
        {
            resourceMap = 1;
        }
        else if ( given.equals( "Ore"))
        {
            resourceMap = 2;
        }
        else if ( given.equals( "Grain"))
        {
            resourceMap = 3;
        }
        else if ( given.equals( "Brick"))
        {
            resourceMap = 4;
        }
        else if ( given.equals( "Lumber"))
        {
            resourceMap = 5;
        }

        for ( int i = 0; i < buildings.size(); i++ )
        {
            for ( int j = 0; j < DOCK_LOCATIONS[resourceMap].length; j++ )
            {
                if ( buildings.get( i).getLocation() == DOCK_LOCATIONS[resourceMap][j] )
                {
                    ratio = 2;
                    break;
                }
            }
            if ( ratio != 4 )
                break;
        }

        if ( ratio == 4 )
            for ( int i = 0; i < buildings.size(); i++ )
            {
                for ( int j = 0; j < DOCK_LOCATIONS[0].length; j++ )
                {
                    if ( buildings.get( i).getLocation() == DOCK_LOCATIONS[0][j] )
                    {
                        ratio = 3;
                        break;
                    }
                }
                if ( ratio != 4 )
                    break;
            }

        if ( hasCard( given, ratio))
        {
            cards.put( given, cards.get( given) - ratio);
            cards.put( wanted, cards.get( wanted) + 1);
            return true;
        }
        return false;
    }

    public void manageOffer( String givenResource, int givenResourceNumber, String wantedResource, int wantedResourceNumber)
    {
        cards.put( givenResource, cards.get( givenResource) + givenResourceNumber);
        cards.put( wantedResource, cards.get( wantedResource) - wantedResourceNumber);
    }

    public boolean hasCard(String type, int value)
    {
        if ( cards.get( type) >= value )
            return true;
        return false;
    }

    public boolean buyDevelopmentCard() {
        if(cards.get("Wool")>= 1 && cards.get("Grain")>= 1 && cards.get("Ore")>= 1 ){
            cards.put("Wool" , cards.get("Wool") - 1);
            cards.put("Grain" , cards.get("Grain") - 1);
            cards.put("Ore" , cards.get("Ore") - 1);
            return true;
        }
        return false;
    }

    public void addProgressCard(Card card) {
        cards.put(card.getCardType() , cards.get(card.getCardType()) + 1);
    }

    public int affectMonopoly(String resourceType) {
        int resourceNumber = cards.get(resourceType);
        cards.put(resourceType , 0);
        return resourceNumber;
    }

    public boolean playMonopoly(String resourceType, int totalResource) {
        if ( hasCard( "Monopoly", 1) )
        {
            cards.put("Monopoly" , cards.get("Monopoly") - 1);
            cards.put(resourceType , totalResource+cards.get(resourceType));
            return true;
        }
        return false;
    }

    public boolean playYearOfPlenty(String resourceType) {
        if ( hasCard("YearOfPlenty", 1))
        {
            cards.put("YearOfPlenty" , cards.get("YearOfPlenty") - 1);
            cards.put(resourceType , cards.get(resourceType)+2);
            return true;
        }
        return false;
    }

    public boolean playRoadBuilding() {
        if ( hasCard( "RoadBuilding", 1))
        {
            cards.put( "RoadBuilding" , cards.get( "RoadBuilding") - 1);
            cards.put( "Brick", cards.get( "Brick") + 2);
            cards.put( "Lumber", cards.get( "Lumber") + 2);
            return true;
        }
        return false;
    }

    public boolean playKnightCard()
    {
        if ( hasCard( "Knight", 1) )
        {
            cards.put( "Knight", cards.get( "Knight") - 1);
            return true;
        }
        return false;
    }

    public int[] getResources() {
        int [] resources = new int[5];
        resources[0] = cards.get("Grain");
        resources[1] = cards.get("Brick");
        resources[2] = cards.get("Ore");
        resources[3] = cards.get("Lumber");
        resources[4] = cards.get("Wool");
        return resources;
    }

    public int[] getDevelopmentCards() {
        int [] developments = new int[5];
        developments[0] = cards.get("Knight");
        developments[1] = cards.get("VictoryPoint");
        developments[2] = cards.get("RoadBuilding");
        developments[3] = cards.get("YearOfPlenty");
        developments[4] = cards.get("Monopoly");
        return developments;
    }

    public boolean hasCapital()
    {
        for ( int i = 0; i < buildings.size(); i++)
        {
            if ( buildings.get(i) instanceof Capital)
                return true;
        }
        return false;
    }

    public boolean canBuild()
    {
        return happiness.canBuild();
    }

    public boolean buyRoad(){
        if( hasCard("Brick", 1) && hasCard("Lumber", 1)){
            cards.put("Lumber" , cards.get("Lumber")-1);
            cards.put("Brick" , cards.get("Brick")-1);
            return true;
        }
        return false;
    }

    public boolean buySettlement(){
        if( hasCard("Brick", 1) && hasCard("Lumber", 1)
                && hasCard("Grain", 1) && hasCard("Wool", 1) ){
            cards.put("Lumber" , cards.get("Lumber")-1);
            cards.put("Brick" , cards.get("Brick")-1);
            cards.put("Grain" , cards.get("Grain")-1);
            cards.put("Wool" , cards.get("Wool")-1);
            return true;
        }
        return false;
    }

    public boolean buyCity() {
        if( hasCard("Grain", 2) && hasCard("Ore", 3) ){
            cards.put("Grain" , cards.get("Grain")-2);
            cards.put("Ore" , cards.get("Ore")-3);
            return true;
        }
        return false;
    }

    public boolean buyCapital()
    {
        if( hasCard("Brick", 3) && hasCard("Ore", 4) ){
            cards.put("Brick" , cards.get("Brick")-3);
            cards.put("Ore" , cards.get("Ore")-4);
            return true;
        }
        return false;
    }

    public void changeName(String substring) {
        name = substring;
    }
}
