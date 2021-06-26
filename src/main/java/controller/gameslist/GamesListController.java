package controller.gameslist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.game.Game;
import model.game.Side;

import java.util.LinkedList;
import java.util.List;

public class GamesListController
{
    public static ObservableList<GamesListResultFinalized> getScoreboard(Game[] games)
    {
        List<GamesListResultFinalized> gamesList = new LinkedList<>();
        for (int i = 0; i < games.length; i++)
        {
            int db1 = games[i].getDroppedBombs(Side.PLAYER_ONE);
            int db2 = games[i].getDroppedBombs(Side.PLAYER_TWO);
            int sb1 = games[i].getSuccessfulBombs(Side.PLAYER_ONE);
            int sb2 = games[i].getSuccessfulBombs(Side.PLAYER_TWO);
            int ss1 = games[i].getSunkenShips(Side.PLAYER_ONE);
            int ss2 = games[i].getSunkenShips(Side.PLAYER_TWO);
            gamesList.add(new GamesListResultFinalized(i + 1, db1, db2, sb1, sb2, ss1, ss2));
        }
        return FXCollections.observableArrayList(gamesList);
    }
}
