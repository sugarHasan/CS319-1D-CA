public class Robber {
    private int location;

    public Robber()
    {

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
