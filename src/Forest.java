package src;
import src.Land;
import src.Lumber;

public class Forest extends Land {

    public Forest()
    {
        super();
        setResource( new Lumber());
    }
}
