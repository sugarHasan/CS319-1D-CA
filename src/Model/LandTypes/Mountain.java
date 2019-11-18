package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Ore;

public class Mountain extends Land {

    public Mountain()
    {
        super();
        setResource( new Ore());
    }
}