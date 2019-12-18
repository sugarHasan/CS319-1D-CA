package Model.BuildingTypes;
import Model.Player;
import Model.Building;

public class Settlement extends Building {
    public Settlement( Player player, int location) {
        super( player, location);
        setVictoryPoint( 1);
    }
}
