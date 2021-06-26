package controller.scoreboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.util.LinkedList;
import java.util.List;

public class ScoreboardController
{
    public static ObservableList<ScoreboardResultFinalized> getScoreboard(User[] users)
    {
        List<ScoreboardResultFinalized> scoreboard = new LinkedList<>();
        for (int i = 0; i < users.length; i++)
        {
            int index = i;
            while (index != 0 && users[i].getScore().equals(users[i - 1].getScore()))
            {
                index--;
            }
            scoreboard.add(new ScoreboardResultFinalized(index + 1, users[i].getUsername(), users[i].getScore(), users[i].isOnline()));
        }
        return FXCollections.observableArrayList(scoreboard);
    }
}
