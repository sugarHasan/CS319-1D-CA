package src;
import src.Player;
import src.Building;

public class City extends Building {
    public City( Player player, int location) {
        super( player, location);
        setVictoryPoint( 2);
    }
}
