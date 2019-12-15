package Model;

public class Robber {
    private int location;

    public Robber( int loc)
    {
        location = loc;
    }

    public boolean changeLocation( int newLoc)
    {
        if ( newLoc == location )
            return false;
        location = newLoc;
        return true;
    }

    public int getLocation()
    {
        return location;
    }
}
