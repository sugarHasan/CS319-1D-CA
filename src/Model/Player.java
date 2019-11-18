package Model;
import Model.Building;
import Model.BuildingTypes.*;
import Model.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private final int NO_OF_EDGE_FOR_ROAD = 72;

    private ArrayList<Building> buildings;
    private HashMap<String , Integer> cards;
    private int victoryPoints;
    private String name;

    public Player(String name){
        this.name = name;
        cards = new HashMap<String , Integer>();
        cards.put("Wool" , 0);
        cards.put("Ore" , 0);
        cards.put("Lumber" , 0);
        cards.put("Grain" , 0);
        cards.put("Brick" , 0);
        cards.put("Knight" , 0);
        cards.put("RoadBuilding" , 0);
        cards.put("YearOfPlenty" , 0);
        cards.put("Monopoly" , 0);
        cards.put("VictoryPoint" , 0);


        victoryPoints = 0;
        buildings = new ArrayList<Building>();
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


    public void addBuilding(Building newBuilding){
        buildings.add(newBuilding);
    }

    public int getVictoryPointsOffTurn() {
        return victoryPoints;
    }

    public int getVictoryPointsOnTurn() {
        return victoryPoints+cards.get("VictoryPoint");
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }


    public int longestRoad(){
        int[][] adjList = new int[NO_OF_EDGE_FOR_ROAD][NO_OF_EDGE_FOR_ROAD];
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i) instanceof Road){
                Road road = (Road)buildings.get(i);
                int[] adjacentRoads = road.getAdjacentRoads();
                for(int j = 0 ; j < adjacentRoads.length ; j++){
                    adjList[road.getLocation()][adjacentRoads[j]] = 1;
                    adjList[adjacentRoads[j]][road.getLocation()] = 1;
                }
            }
        }
        int longestRoad = 0;
        boolean[] visited = new boolean[NO_OF_EDGE_FOR_ROAD];
        for(int i = 0 ; i < NO_OF_EDGE_FOR_ROAD ; i++){
            longestRoad = Math.max(dfs(adjList , visited , i) , longestRoad);
        }
        return longestRoad;
    }

    private int dfs(int[][] adjList , boolean[] visited , int index ){
        if(visited[index])  return 0;
        visited[index] = true;
        int res = 1;
        for(int i = 0 ; i < NO_OF_EDGE_FOR_ROAD ; i++){
            if(adjList[index][i] == 1){
                if(!visited[i]){
                    res += dfs(adjList , visited , i);
                }
            }
        }
        return res;
    }

    public void removeSettlement(int location){
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

    public void addResource(int town , String resource){
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i) instanceof City){
                if(buildings.get(i).getLocation() == town){
                    cards.put(resource , cards.get(resource)+1);
                }
            }
            if(buildings.get(i) instanceof Settlement){
                if(buildings.get(i).getLocation() == town){
                    cards.put(resource , cards.get(resource)+2);
                }
            }
        }
    }

    public int armySize() {
        return cards.get("Knight");
    }

    public boolean tradeResource(String given , String wanted){
        if(cards.get(given)>=4){
            cards.put(given , cards.get(given) - 4);
            cards.put(wanted , cards.get(wanted)+1);
            return true;
        }
        return false;
    }

    public boolean buyDevelopmentCard() {
        if(cards.get("Wool")>= 1 && cards.get("Grain")>= 1 &&cards.get("Ore")>= 1 ){
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
}
