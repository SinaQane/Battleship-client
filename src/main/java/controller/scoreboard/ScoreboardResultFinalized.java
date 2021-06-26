package controller.scoreboard;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ScoreboardResultFinalized
{
    private final SimpleIntegerProperty rank;
    private final SimpleStringProperty username;
    private final SimpleIntegerProperty score;
    private final SimpleBooleanProperty online;

    public ScoreboardResultFinalized(int rank, String username, int score, boolean online)
    {
        this.rank = new SimpleIntegerProperty(rank);
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
        this.online = new SimpleBooleanProperty(online);
    }

    public int getRank()
    {
        return rank.get();
    }

    public String getUsername()
    {
        return username.get();
    }

    public int getScore()
    {
        return score.get();
    }

    public boolean isOnline()
    {
        return online.get();
    }
}
