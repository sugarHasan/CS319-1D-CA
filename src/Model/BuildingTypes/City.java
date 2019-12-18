package Model.BuildingTypes;
import Model.Player;
import Model.Building;

public class City extends Building {
    public City( Player player, int location) {
        super( player, location);
        setVictoryPoint( 2);
    }
}
