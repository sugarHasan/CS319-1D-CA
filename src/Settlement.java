package src;
import src.Player;

public class Settlement extends Building {
    public Settlement(Player player, int location) {
        super( player, location);
        victoryPoint = 1;
    }
}
