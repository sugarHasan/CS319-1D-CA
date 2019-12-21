package Model;

public class Happiness {
    private int value;

    public Happiness()
    {
        value = 6;
    }

    public int getHappinessValue()
    {
        return value;
    }

    public void caughtFish( int number)
    {
        increaseHappinessValue( number);
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
