package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Brick;

public class Hill extends Land {

    public Hill()
    {
        super();
        setResource( new Brick());
    }
}