package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Ore;

public class Mountain extends Land {

    public Mountain( int number)
    {
        super( number);
        setResource( new Ore());
    }
}