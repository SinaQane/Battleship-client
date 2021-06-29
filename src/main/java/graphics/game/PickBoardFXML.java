package graphics.game;

import constants.ClientConstants;
import event.EventListener;
import event.events.menu.ChangeFrameEvent;
import event.events.startgame.StartGameEvent;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Board;
import util.Loop;

import java.net.URL;
import java.util.ResourceBundle;

public class PickBoardFXML implements Initializable
{
    private EventListener listener;
    private final Object lock = new Object();
    private int remainingAttempts = 2;
    private int timeLeftSeconds = 30;
    private Board[] boards;
    private Loop loop;
    private String token;

    public Label timerText;
    public Text attemptsText;
    public Pane boardPane;
    public Button selectButton;
    public Button cancelButton;
    public Button nextButton;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(
                () -> timerText.setText(timeLeftSeconds + " seconds left")
        );
    }

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void setBoards(Board[] boards)
    {
        this.boards = boards;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public void startLoop()
    {
        loop = new Loop(ClientConstants.UPDATE_FPS, this::update);
        loop.start();
    }

    public void stopLoop()
    {
        loop.stop();
    }

    public void update()
    {
        repaintPage();
        synchronized (lock)
        {
            timeLeftSeconds--;
            if (timeLeftSeconds == 0)
            {
                stopLoop();
                select();
            }
        }
    }

    public void repaintPage()
    {
        Platform.runLater(
            () -> {
                timerText.setText(timeLeftSeconds + " seconds left");
                attemptsText.setText("Remaining attempts: " + remainingAttempts);
                BoardPane board = new BoardPane();
                board.getFXML().setListener(listener);
                board.getFXML().buttonsAvailable(false);
                board.getFXML().giveFullAccess();
                board.getFXML().paintBoard(boards[remainingAttempts]);
                boardPane.getChildren().clear();
                boardPane.getChildren().add(board.getPane());
            }
        );
    }

    public void select()
    {
        Platform.runLater(
                () -> {
                    cancelButton.setDisable(true);
                    selectButton.setDisable(true);
                    nextButton.setDisable(true);
                }
        );
        listener.listen(new StartGameEvent(token, boards[remainingAttempts]));
    }

    public void next()
    {
        if (remainingAttempts > 0)
        {
            remainingAttempts--;
            synchronized (lock)
            {
                timeLeftSeconds += 10;
            }
            Platform.runLater(
                () -> attemptsText.setText("Remaining attempts: " + remainingAttempts)
            );
        }
    }

    public void cancel()
    {
        stopLoop();
        listener.listen(new ChangeFrameEvent("mainMenu"));
    }
}
