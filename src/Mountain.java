package src;
import src.Land;
import src.Ore;

public class Mountain extends Land {

    public Mountain()
    {
        super();
        setResource( new Ore());
    }
}