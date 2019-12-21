package Model;

public class Offer {
    private Player sender;
    private Player receiver;
    private String offeredItem;
    private String demandedItem;
    private int offerNum;
    private int demandNum;

    public Offer(Player sender, Player receiver, String offeredItem,
                 String demandedItem, int offerNum, int demandNum) {
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

    public String getOfferedItem() {
        return offeredItem;
    }

    public void setOfferedItem(String offeredItem) {
        this.offeredItem = offeredItem;
    }

    public String getDemandedItem() {
        return demandedItem;
    }

    public void setDemandedItem(String demandedItem) {
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
