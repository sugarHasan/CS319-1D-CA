package src;
import java.util.ArrayList;

public class TradeManager {

    private ArrayList<Trade> trades = new ArrayList<Trade>();

    public boolean listTrades()
    {
        for(int i = 0; i < trades.size(); i++ )
        {
            System.out.println( (i+1 + "st Trades: " + trades.get(i)));
        }
    }
    public boolean tradeResources()
    {

    }
    public boolean buyProgressCard()
    {

    }
    //int [][] roadToEdge = {{0,3},{0,4},{1,5},{2,5},{2,6},{3,7},{4,8},{5,9},{6,10},{7,11},{7,12},{8,12},{8,13},{9,13},{9,14},{10,14},{10,15},{11,16},{12,17},{13,18},{14,19},{15,20},{16,21},{16,22},{17,22},{17,23},{18,23},{18,24},{19,24},{19,25},{20,25},{20,26},{21,27},{22,28},{23,29},{24,30},{25,31},{26,32},{27,33},{28,33},{28,34},{29,34},{29,35},{30,35},{30,36},{31,36},{31,37},{33,38},{34,39},{35,40},{36,41},{37,42},{38,43},{39,43},{39,44},{40,44},{40,45},{41,45},{41,46},{43,47},{44,48},{45,49},{46,50},{47,51},{48,51},{48,52},{49,52},{49,53},{50,53}};
}
