package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Wool;

public class Pasture extends Land {

    public Pasture( int number)
    {
        super( number);
        setResource( new Wool());
    }
}