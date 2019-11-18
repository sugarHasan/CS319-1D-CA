package src;
import src.Player;
import src.Building;

public class Settlement extends Building {
    public Settlement( Player player, int location) {
        super( player, location);
        setVictoryPoint( 1);
    }
}
