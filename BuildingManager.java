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
            buildingsRoad[location] =  new Road(player , location);
            return true;
        }
        return false;
    }

    public boolean buildSettlement(Player player, int location){
        if(buildingsCityAndSettlement[location] == null){
            buildingsCityAndSettlement[location] =  new Settlement(player , location);
            return true;
        }
        return false;
    }

    public boolean buildCity(Player player, int location){
        if(buildingsCityAndSettlement[location] == null){
            buildingsCityAndSettlement[location] =  new City(player , location);
            return true;
        }
        return false;
    }

}
