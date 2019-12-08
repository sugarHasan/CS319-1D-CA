package Control;

import Model.Building;
import Model.BuildingTypes.City;
import Model.BuildingTypes.Road;
import Model.BuildingTypes.Settlement;
import Model.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildingManager {

    private final int NO_OF_CORNERS_FOR_CITY_AND_BUILDINGS = 54;
    private final int NO_OF_EDGE_FOR_ROAD = 72;


    private Building[] buildingsCityAndSettlement;
    private Building[] buildingsRoad;

    public BuildingManager() {
        buildingsCityAndSettlement = new Building[NO_OF_CORNERS_FOR_CITY_AND_BUILDINGS];
        buildingsRoad = new Building[NO_OF_EDGE_FOR_ROAD];
    }

    public boolean buildRoad(Player player, int location){
        if(buildingsRoad[location] == null){
            if(player.buyRoad()) {
                player.addBuilding(new Road(player, location));
                buildingsRoad[location] = new Road(player, location);
                return true;
            }
        }
        return false;
    }

    public boolean buildSettlement(Player player, int location){
        if(buildingsCityAndSettlement[location] == null) {
            ArrayList<Building> buildings = player.getBuildings();
            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i) instanceof Road) {
                    Road road = (Road)buildings.get(i);
                    int[] adjCity = road.getAdjacentCity();
                    for(int j = 0 ; j < adjCity.length ; j++){
                        if(adjCity[j] == location){
                            if(player.buySettlement()) {
                                player.addBuilding(new Settlement(player, location));
                                buildingsCityAndSettlement[location] = new Settlement(player, location);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean buildCity(Player player, int location){
        if(buildingsCityAndSettlement[location] != null){
            if(buildingsCityAndSettlement[location].getPlayer().getName().equals(player.getName()) ) {
                ArrayList<Building> buildings = player.getBuildings();
                for (int i = 0; i < buildings.size(); i++) {
                    if (buildings.get(i) instanceof Road) {
                        Road road = (Road) buildings.get(i);
                        int[] adjCity = road.getAdjacentCity();
                        for (int j = 0; j < adjCity.length; j++) {
                            if (adjCity[j] == location) {
                                player.addBuilding(new City(player, location));
                                player.removeSettlement(location);
                                buildingsCityAndSettlement[location] = new City(player, location);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
