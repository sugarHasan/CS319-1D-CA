package Model;
import Model.Robber;

public class Map
{
    private final int NO_OF_LANDS = 19;
    private final int NO_OF_PATHS = 72;
    private final int NO_OF_INTERSECTIONS = 54;
    private Land[] lands;
    private Building[] paths;
    private Building[] intersections;
    private Robber robber;

    public Map()
    {
        lands = new Land[NO_OF_LANDS];
        paths = new Building[NO_OF_PATHS];
        intersections = new Building[NO_OF_INTERSECTIONS];
    }

    public void create()
    {
        // will randomly create the map
    }

    public boolean moveRobber() {
        // changes the location of robber
        return false;
    }
}
