package src;
import src.Land;
import src.Wool;

public class Pasture extends Land {

    public Pasture()
    {
        super();
        setResource( new Wool());
    }
}