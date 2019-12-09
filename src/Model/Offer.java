package Model;

public class Offer {
    private Player sender;
    private Player receiver;
    private ResourceCard offeredItem;
    private ResourceCard demandedItem;
    private int offerNum;
    private int demandNum;

    public Offer(Player sender, Player receiver, ResourceCard offeredItem,
                 ResourceCard demandedItem, int offerNum, int demandNum) {
        this.sender = sender;
        this.receiver = receiver;
        this.offeredItem = offeredItem;
        this.demandedItem = demandedItem;
        this.offerNum = offerNum;
        this.demandNum = demandNum;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public ResourceCard getOfferedItem() {
        return offeredItem;
    }

    public void setOfferedItem(ResourceCard offeredItem) {
        this.offeredItem = offeredItem;
    }

    public ResourceCard getDemandedItem() {
        return demandedItem;
    }

    public void setDemandedItem(ResourceCard demandedItem) {
        this.demandedItem = demandedItem;
    }

    public int getOfferNum() {
        return offerNum;
    }

    public void setOfferNum(int offerNum) {
        this.offerNum = offerNum;
    }

    public int getDemandNum() {
        return demandNum;
    }

    public void setDemandNum(int demandNum) {
        this.demandNum = demandNum;
    }
}
