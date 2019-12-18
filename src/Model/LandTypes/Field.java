package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Grain;

public class Field extends Land {

    public Field( int number)
    {
        super( number);
        setResource( new Grain());
    }
}