package src;
import src.Player;
import src.ResourceCard;

public class Trade {
    private Player player;
    private ResourceCard tradeItem;
    private int tradeRatio;
    private ResourceCard demandedItem;

    public Trade(Player player, ResourceCard tradeItem, int tradeRatio, ResourceCard demandedItem) {
        this.player = player;
        this.tradeItem = tradeItem;
        this.tradeRatio = tradeRatio;
        this.demandedItem = demandedItem;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ResourceCard getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(ResourceCard tradeItem) {
        this.tradeItem = tradeItem;
    }

    public int getTradeRatio() {
        return tradeRatio;
    }

    public void setTradeRatio(int tradeRatio) {
        this.tradeRatio = tradeRatio;
    }

    public ResourceCard getDemandedItem() {
        return demandedItem;
    }

    public void setDemandedItem(ResourceCard demandedItem) {
        this.demandedItem = demandedItem;
    }
}
