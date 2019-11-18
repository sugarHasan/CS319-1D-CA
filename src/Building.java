import java.util.ArrayList;
import java.util.Vector;

public class Building {

    protected int victoryPoint;
    private int location;
    private Player player;


    public Building(Player player , int location) {
        this.player = player;
        victoryPoint = 0;
        this.location = location;
    }

    public int getVictoryPoint() {
        return victoryPoint;
    }

    public void setVictoryPoint(int victoryPoint) {
        this.victoryPoint = victoryPoint;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int locationX) {
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }




}
