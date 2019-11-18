package src;
import src.*;

public class Land{

    private final int NO_OF_CORNERS = 6;
    private ResourceCard type;
    private Building[] corners;

    public Land()
    {

    }

    public ResourceCard getResource()
    {
        return type;
    }

    public void setResource( ResourceCard resource)
    {
        type = resource;
    }

    public Building[] getBuildings()
    {
        return corners;
    }
}
