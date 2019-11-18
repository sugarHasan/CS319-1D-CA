package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Wool;

public class Pasture extends Land {

    public Pasture()
    {
        super();
        setResource( new Wool());
    }
}