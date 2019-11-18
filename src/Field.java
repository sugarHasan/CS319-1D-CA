package src;
import src.Land;
import src.Grain;

public class Field extends Land {

    public Field()
    {
        super();
        setResource( new Grain());
    }
}