package Model.BuildingTypes;

import Model.Building;
import Model.Player;

public class Capital  extends Building {
    public Capital(Player player, int location) {
        super( player, location);
        setVictoryPoint( 3);
    }
}
