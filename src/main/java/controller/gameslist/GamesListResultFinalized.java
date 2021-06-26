package controller.gameslist;

import javafx.beans.property.SimpleIntegerProperty;

public class GamesListResultFinalized
{
    private final SimpleIntegerProperty index;
    private final SimpleIntegerProperty playerOneDroppedBombs;
    private final SimpleIntegerProperty playerTwoDroppedBombs;
    private final SimpleIntegerProperty playerOneSuccessfulBombs;
    private final SimpleIntegerProperty playerTwoSuccessfulBombs;
    private final SimpleIntegerProperty playerOneSunkenShips;
    private final SimpleIntegerProperty playerTwoSunkenShips;

    public GamesListResultFinalized(int index, int db1, int db2, int sb1, int sb2, int ss1, int ss2)
    {
        this.index = new SimpleIntegerProperty(index);
        this.playerOneDroppedBombs = new SimpleIntegerProperty(db1);
        this.playerTwoDroppedBombs = new SimpleIntegerProperty(db2);
        this.playerOneSuccessfulBombs = new SimpleIntegerProperty(sb1);
        this.playerTwoSuccessfulBombs = new SimpleIntegerProperty(sb2);
        this.playerOneSunkenShips = new SimpleIntegerProperty(ss1);
        this.playerTwoSunkenShips = new SimpleIntegerProperty(ss2);
    }

    public int getIndex()
    {
        return index.get();
    }

    public int getPlayerOneDroppedBombs()
    {
        return playerOneDroppedBombs.get();
    }

    public int getPlayerTwoDroppedBombs()
    {
        return playerTwoDroppedBombs.get();
    }

    public int getPlayerOneSuccessfulBombs()
    {
        return playerOneSuccessfulBombs.get();
    }

    public int getPlayerTwoSuccessfulBombs()
    {
        return playerTwoSuccessfulBombs.get();
    }

    public int getPlayerOneSunkenShips()
    {
        return playerOneSunkenShips.get();
    }

    public int getPlayerTwoSunkenShips()
    {
        return playerTwoSunkenShips.get();
    }
}