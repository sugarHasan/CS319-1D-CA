package Model;
import Model.LandTypes.*;
import Model.Robber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import java.net.URISyntaxException;

public class Map
{
    private final int[][] ADJACENT_INTERSECTIONS_TO_LANDS = {{0,3,4,7,8,12},{1,4,5,8,9,13},{2,5,6,9,10,14},
                                {7,11,12,16,17,22},{8,12,13,17,18,23},{9,13,14,18,19,24},{10,14,15,19,20,25},
                                {16,21,22,27,28,33},{17,22,23,28,29,34},{18,23,24,29,30,35},{19,24,25,30,31,36},
                                {20,25,26,31,32,37},{28,33,34,38,39,43},{29,34,35,39,40,44},{30,35,36,40,41,45},
                                {31,36,37,41,42,46},{39,43,44,47,48,51},{40,44,45,48,49,52},{41,45,46,49,50,53}};
    private final int NO_OF_LANDS = 19;
    private final int NO_OF_PATHS = 72;
    private final int NO_OF_INTERSECTIONS = 54;
    private Land[] lands;
    private Robber robber;

    public Map(AnchorPane robberImage) throws URISyntaxException {
        lands = new Land[NO_OF_LANDS];
        createMap(robberImage);
    }

    public void visualizeMap(AnchorPane anchorPane) throws URISyntaxException {
        for(int i = 0; i < NO_OF_LANDS; i++)
        {
            lands[i].setHex(i, anchorPane);
        }
    }

    public void copyMap( Land[] map, AnchorPane robberImage) throws URISyntaxException {
        for ( int i = 0; i < map.length; i++)
        {
            if ( map[i] instanceof Desert )
                robber = new Robber( i, robberImage);
            lands[i] = map[i];
        }
    }

    private void createMap( AnchorPane robberImage) throws URISyntaxException {
        int hill = 3;
        int mountain = 4;
        int forest = 4;
        int field = 3;
        int pasture = 4;
        int desert = 1;
        int random;
        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12));
        Collections.shuffle( numbers);

        int i = 0;
        while ( i < NO_OF_LANDS )
        {
            random = (int) Math.ceil( Math.random() * 6);
            if ( random == 1 && hill > 0 )
            {
                hill--;
                lands[i] = new Hill( (int) numbers.get( 0));
            }
            else if ( random == 2 && mountain > 0 )
            {
                mountain--;
                lands[i] = new Mountain( (int) numbers.get( 0));
            }
            else if ( random == 3 && forest > 0 )
            {
                forest--;
                lands[i] = new Forest( (int) numbers.get( 0));
            }
            else if ( random == 4 && field > 0 )
            {
                field--;
                lands[i] = new Field( (int) numbers.get( 0));
            }
            else if ( random == 5 && pasture > 0)
            {
                pasture--;
                lands[i] = new Pasture( (int) numbers.get( 0));
            }
            else if ( random == 6 && desert > 0)
            {
                desert--;
                lands[i] = new Desert( 7);
                robber = new Robber(i,robberImage);
                i++;
                continue;
            }
            else
                continue;
            numbers.remove( 0);
            lands[i].setCornerLocations( ADJACENT_INTERSECTIONS_TO_LANDS[i]);
            i++;
        }
    }

    public boolean moveRobber( int location) {
        return robber.changeLocation( location);
    }

    public int getRobbersLocation()
    {
        return robber.getLocation();
    }

    public int[] getLandCornerLocation( int location)
    {
        return lands[location].getCornerLocations();
    }

    public int[][] getDiceAdjacency( String resource)
    {
        int landCount;
        int[][] adjacency = new int[13][];

        for ( int i = 0; i <= 12; i++) {
            landCount = 0;
            if (i > 1)
                for (int j = 0; j < lands.length; j++) {
                    if (lands[j].getResource().equals(resource) && lands[j].getNumber() == i)
                        landCount += 6;
                }

            adjacency[i] = new int[landCount];
            if (landCount > 0)
            {
                int location = 0;
                for (int j = 0; j < lands.length; j++)
                {
                    if (lands[j].getResource().equals(resource) && lands[j].getNumber() == i)
                    {
                        int[] corners = lands[j].getCornerLocations();
                        for (int k = 0; k < corners.length; k++)
                        {
                            adjacency[i][location] = corners[k];
                            location++;
                        }
                    }
                }
            }
        }
        return adjacency;
    }

    public String encodeMap()
    {
        String result = "";
        for ( int i = 0; i < NO_OF_LANDS; i++ )
        {
            result = result + i;
            if ( lands[i] instanceof Desert )
                result = result + "DE";
            else if ( lands[i] instanceof Field )
                result = result + "FI";
            else if ( lands[i] instanceof Forest )
                result = result + "FO";
            else if ( lands[i] instanceof Hill )
                result = result + "HI";
            else if ( lands[i] instanceof Mountain )
                result = result + "MO";
            else if ( lands[i] instanceof Pasture )
                result = result + "PA";
            else
                result = result + "  ";
            if ( lands[i].getNumber() < 10 )
                result = result + 0;
            result = result + lands[i].getNumber();
        }
        return result;
    }

    public Land[] decodeMap( String source)
    {
        Land[] map = new Land[ NO_OF_LANDS];
        int location, number;
        String type;
        int i = 0;
        while ( i < NO_OF_LANDS * 5 && i+4 < source.length())
        {
            location = Integer.parseInt( source.substring( i, i+1));
            type = source.substring( i+2, i+4);
            number = Integer.parseInt( source.substring( i+3, i+5));

            if ( type.equals( "DE"))
                map[location] = new Desert( number);
            else if ( type.equals( "FI"))
                map[location] = new Field( number);
            else if ( type.equals( "FO"))
                map[location] = new Forest( number);
            else if ( type.equals( "HI"))
                map[location] = new Hill( number);
            else if ( type.equals( "MO"))
                map[location] = new Mountain( number);
            else if ( type.equals( "PA"))
                map[location] = new Pasture( number);

            if ( location == NO_OF_LANDS - 1)
                break;
        }
        return map;
    }
}
