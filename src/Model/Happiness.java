package Model;

public class Happiness {
    private int value;

    public Happiness()
    {
        value = 5;
    }

    public int getHappinessValue()
    {
        return value;
    }

    public void caughtFish( int number)
    {
        increaseHappinessValue( number);
    }

    public void newBuilding()
    {
        increaseHappinessValue( 1);
    }

    public void wealth( int resourceAmount)
    {
        int count = 0;
        while ( resourceAmount > 8)
        {
            count++;
            resourceAmount -= 8;
        }
        increaseHappinessValue( count);
    }

    public void robberInTown( int number)
    {
        decreaseHappinessValue( number);
    }

    public boolean canBuild()
    {
        if ( value > 2 )
            return true;
        return false;
    }

    public boolean loseCard()
    {
        if ( value == 0 )
            return true;
        return false;
    }

    public void increaseHappinessValue( int amount)
    {
        value += amount;
        if ( value > 10 )
            value = 10;
    }

    public void decreaseHappinessValue( int amount)
    {
        value -= amount;
        if ( value < 0 )
            value = 0;
    }
}
