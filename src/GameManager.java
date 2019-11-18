import java.util.ArrayList;

public class GameManager {
    private int turnNo;
    private boolean firstTurn;
    private boolean secondTurn;
    private ArrayList<Card> initialDevelopmentCardStack = new ArrayList<Card>();

    public int rollDice()
    {
        return (int)(Math.random() * 12 + 1);
    }
    public boolean distributeResources()
    {

    }
    public void startTurn()
    {

    }
    public void endTurn()
    {

    }
    public Player gameOver()
    {

    }

    public int getTurnNo() {
        return turnNo;
    }

    public void setTurnNo(int turnNo) {
        this.turnNo = turnNo;
    }

    public boolean isFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    public boolean isSecondTurn() {
        return secondTurn;
    }

    public void setSecondTurn(boolean secondTurn) {
        this.secondTurn = secondTurn;
    }

    public ArrayList<Card> getInitialDevelopmentCardStack() {
        return initialDevelopmentCardStack;
    }

    public void setInitialDevelopmentCardStack(ArrayList<Card> initialDevelopmentCardStack) {
        this.initialDevelopmentCardStack = initialDevelopmentCardStack;
    }
}
