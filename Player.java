import java.util.ArrayList;
import java.util.Vector;

public class Player {
    private ArrayList<Building> buildings;
    private Map<Card , int> cards;
    private int victoryPoints;
    private String name;

    public Player(String name){
        this.name = name;
        cards = new Map<Card , int>();
        victoryPoints = 0;
        buildings = new ArrayList<Building>();
    }

    public  boolean playCard(){
        return true;
    }
    public int longestRoad(){
        Vector<Vector<Integer>> adjList = new Vector<Vector<Integer>>();
        for(int i = 0 ; i < buildings.size() ; i++){
            if(buildings.get(i) instanceof Road){

            }
        }
        int longestRoad = 0;
        for(int i = 0 ; i < roads.size() ; i++){

        }
    }

    private int dfs(){

    }


    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }
}
