package Control;

import Model.Building;
import Model.BuildingTypes.City;
import Model.BuildingTypes.Road;
import Model.BuildingTypes.Settlement;
import Model.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BuildingManager {

    private final int NO_OF_CORNERS_FOR_CITY_AND_BUILDINGS = 54;
    private final int NO_OF_EDGE_FOR_ROAD = 72;

    private Building[] buildingsCityAndSettlement;
    private Building[] buildingsRoad;
    private ImageView[] buildingsImages;

    private int[] road1Image = {6,7,8,9,18,19,20,21,22,33,34,35,36,37,38,49,50,51,52,53,62,63,64,65};

    public BuildingManager() {
        buildingsCityAndSettlement = new Building[NO_OF_CORNERS_FOR_CITY_AND_BUILDINGS];
        buildingsRoad = new Building[NO_OF_EDGE_FOR_ROAD];
        buildingsImages = new ImageView[NO_OF_CORNERS_FOR_CITY_AND_BUILDINGS];
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
                                if(player.buyCity()) {
                                    //player.removeSettlement(location);
                                    player.addBuilding(new City(player, location));
                                    buildingsCityAndSettlement[location] = new City(player, location);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public void setBuildingImage(String color, int location, AnchorPane MapBuildings) throws URISyntaxException {
        int x = 0;
        int y = 0;

        //"#FFA500" , "#FF6347" , "#98FB98" , "#87CEFA"};

        String playerColor = "";
        if(color.equals("#FFA500"))
            playerColor = "Yellow";
        else if(color.equals("#FF6347"))
            playerColor = "Red";
        else if(color.equals("#98FB98"))
            playerColor = "Green";
        else if(color.equals("#87CEFA"))
            playerColor = "Blue";

        if(0 <= location && location <=2)
        {
            x = 144 + (106 * location);
            y = -20;
        }
        else if(3 <= location && location <=6)
        {
            x = 91 + (106 * (location - 3));
            y = 4;
        }
        else if(7 <= location && location <=10)
        {
            x = 91 + (106 * (location - 7));
            y = 59;
        }
        else if(11 <= location && location <=15)
        {
            x = 38 + (106 * (location - 11));
            y = 83;
        }
        else if(16 <= location && location <=20)
        {
            x = 38 + (106 * (location - 16));
            y = 128;
        }
        else if(21 <= location && location <=26)
        {
            x = -15 + (106 * (location - 21));
            y = 152;
        }
        else if(27 <= location && location <=32)
        {
            x = -15 + (106 * (location - 27));
            y = 207;
        }
        else if(33 <= location && location <=38)
        {
            x = 38 + (106 * (location - 33));
            y = 231;
        }
        else if(38 <= location && location <=42)
        {
            x = 38 + (106 * (location - 38));
            y = 286;
        }
        else if(43 <= location && location <=46)
        {
            x = 91 + (106 * (location - 43));
            y = 310;
        }
        else if(47 <= location && location <=50)
        {
            x = 91 + (106 * (location - 47));
            y = 365;
        }
        else if(51 <= location && location <=53)
        {
            x = 144 + (106 * (location - 51));
            y = 389;
        }

        Image img;
        if(buildingsImages[location] == null)
        {
            img = new Image(getClass().getResource("/images/Settlements/" + playerColor + "Settlement.png").toURI().toString());

            ImageView building = new ImageView(img);

            MapBuildings.getChildren().add(building);

            buildingsImages[location] = ((ImageView) MapBuildings.getChildren().get(MapBuildings.getChildren().size() -1 ));

            //((ImageView) MapBuildings.getChildren().get(MapBuildings.getChildren().size() -1 )).setId("" + location);

            buildingsImages[location].setFitHeight(40);
            buildingsImages[location].setFitWidth(40);

            buildingsImages[location].setLayoutX(x);
            buildingsImages[location].setLayoutY(y);
        }
        else
        {
            //MapBuildings.getChildren().remove(""+ location);
            img = new Image(getClass().getResource("/images/Cities/" + playerColor + "City.png").toURI().toString());

            buildingsImages[location].setImage(img);
            buildingsImages[location].setFitHeight(50);
            buildingsImages[location].setFitWidth(50);
        }

    }

    public void setRoadImage(String color, int location, AnchorPane mapRoads) throws URISyntaxException {
        int x = 0;
        int y = 0;

        String playerColor = "";
        if(color.equals("#FFA500"))
            playerColor = "Yellow";
        else if(color.equals("#FF6347"))
            playerColor = "Red";
        else if(color.equals("#98FB98"))
            playerColor = "Green";
        else if(color.equals("#87CEFA"))
            playerColor = "Blue";

        if(contains(road1Image,location))
        {
            // {6,7,8,9,18,19,20,21,22,33,34,35,36,37,38,49,50,51,52,53,62,63,64,65}
            if(6 <= location && location <=9)
            {
                x = 113 + (106 * (location - 6));
                y = 43;
            }
            else if(18 <= location && location <=22)
            {
                x = 60 + (106 * (location - 18));
                y = 118;
            }
            else if(33 <= location && location <=38)
            {
                x = 7 + (106 * (location - 33));
                y = 195;
            }
            else if(49 <= location && location <=53)
            {
                x = 60 + (106 * (location - 49));
                y = 272;
            }
            else if(62 <= location && location <=65)
            {
                x = 113 + (106 * (location - 62));
                y = 349;
            }

            Image img = new Image(getClass().getResource("/images/Roads/" + playerColor + "Road1.png").toURI().toString());

            ImageView road = new ImageView(img);

            mapRoads.getChildren().add(road);

            //road = ((ImageView) mapRoads.getChildren().get(mapRoads.getChildren().size() -1 ));

            //((ImageView) MapBuildings.getChildren().get(MapBuildings.getChildren().size() -1 )).setId("" + location);

            ((ImageView) mapRoads.getChildren().get(mapRoads.getChildren().size() -1 )).setFitHeight(50);
            ((ImageView) mapRoads.getChildren().get(mapRoads.getChildren().size() -1 )).setFitWidth(14.95);

            ((ImageView) mapRoads.getChildren().get(mapRoads.getChildren().size() -1 )).setLayoutX(x);
            ((ImageView) mapRoads.getChildren().get(mapRoads.getChildren().size() -1 )).setLayoutY(y);
        }
        //"#FFA500" , "#FF6347" , "#98FB98" , "#87CEFA"};
    }
    public static boolean contains(final int[] array, final int v) {

        boolean result = false;

        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }

        return result;
    }

}
