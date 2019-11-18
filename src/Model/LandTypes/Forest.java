package Model.LandTypes;
import Model.Land;
import Model.ResourceCardTypes.Lumber;

public class Forest extends Land {

    public Forest()
    {
        super();
        setResource( new Lumber());
    }
}
