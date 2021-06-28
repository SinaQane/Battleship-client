package graphics.pages;

import event.EventListener;
import event.events.authentication.LogoutEvent;
import event.events.menu.GamesListEvent;
import event.events.menu.ScoreboardEvent;
import event.events.startgame.PickBoardEvent;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuFXML implements Initializable
{
    private EventListener listener;
    private String authToken = "";

    public Label messageText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public void setMessage(String message)
    {
        Platform.runLater(
            () -> {
                messageText.setText(message);
                messageText.setVisible(true);
            }
        );
    }

    public void clear()
    {
        Platform.runLater(
            () -> messageText.setVisible(false)
        );
    }

    public void newGame()
    {
        clear();
        listener.listen(new PickBoardEvent(authToken));
    }

    public void viewGames()
    {
        clear();
        listener.listen(new GamesListEvent());
    }

    public void scoreboard()
    {
        clear();
        listener.listen(new ScoreboardEvent());
    }

    public void logout()
    {
        clear();
        listener.listen(new LogoutEvent(authToken));
    }
}
