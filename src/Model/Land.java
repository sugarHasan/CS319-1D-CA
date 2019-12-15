package Model;
import Model.Building;
import Model.ResourceCard;

public class Land {

    private final int NO_OF_CORNERS = 6;
    private int number;
    private int[] cornerLocations;
    private ResourceCard type;

    public Land( int number)
    {
        this.number = number;
        cornerLocations = new int[NO_OF_CORNERS];
    }

    public boolean setCornerLocations( int[] locs)
    {
        if ( locs.length != 6)
            return false;
        cornerLocations = locs;
        return true;
    }

    public int[] getCornerLocations()
    {
        return cornerLocations;
    }

    public int getNumber()
    {
        return number;
    }

    public String getResource()
    {
        if ( type != null)
            return type.getCardType();
        return "";
    }

    public void setResource( ResourceCard resource)
    {
        type = resource;
    }
}
