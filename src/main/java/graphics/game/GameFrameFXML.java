package graphics.game;

import constants.ClientConstants;
import event.EventListener;
import event.events.gameplay.GameMoveEvent;
import event.events.gameplay.GetBoardEvent;
import event.events.gameplay.ResignEvent;
import event.events.menu.ChangeFrameEvent;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.game.Game;
import model.game.Side;
import util.Loop;

import java.net.URL;
import java.util.ResourceBundle;

// Integer "mode" is 0 for visitor, 1 for player one & 2 for player two
public class GameFrameFXML implements Initializable
{
    private EventListener listener;
    private final Object lock = new Object();
    private int timeLeftSeconds = 25;
    private String playerToken;
    private int moves = 0;
    private int mode;
    private Game game;
    private Loop loop;

    public Label gameMessageText;
    public Label timerText;
    public Text firstPlayerText;
    public Text secondPlayerText;
    public Pane firstBoard;
    public Pane secondBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void setPlayerToken(String token)
    {
        this.playerToken = token;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
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
        listener.listen(new GetBoardEvent(playerToken));
        repaintPage();
        if (!game.isRunning())
        {
            Platform.runLater(
                () -> timerText.setText("game over")
            );
            stopLoop();
        }
        if (mode != 0)
        {
            synchronized (lock)
            {
                if (moves == game.getMoves())
                {
                    timeLeftSeconds--;
                    if (timeLeftSeconds == 0)
                    {
                        if (moves % 2 != mode && mode != 0)
                        {
                            listener.listen(new GameMoveEvent(playerToken, -1, -1)); // hit (-1, -1) for timeout
                        }
                        timeLeftSeconds = 25;
                    }
                }
                else
                {
                    timeLeftSeconds = 25;
                    moves++;
                }
            }
        }
    }

    public void repaintPage()
    {
        Platform.runLater(
            () -> {
                if (mode != 0)
                {
                    timerText.setText(timeLeftSeconds + " seconds left");
                }
                else
                {
                    timerText.setText("viewing game as spectator");
                }
                gameMessageText.setText(game.getGameMessage());
                firstPlayerText.setText(game.getPlayer(Side.PLAYER_ONE).getUsername());
                secondPlayerText.setText(game.getPlayer(Side.PLAYER_TWO).getUsername());
                BoardPane playerOneBoard = new BoardPane();
                BoardPane playerTwoBoard = new BoardPane();
                playerOneBoard.getFXML().setListener(listener);
                playerTwoBoard.getFXML().setListener(listener);
                if (mode == 1)
                {
                    playerOneBoard.getFXML().giveFullAccess();
                    playerOneBoard.getFXML().buttonsAvailable(false);
                    playerTwoBoard.getFXML().buttonsAvailable(true);
                    playerTwoBoard.getFXML().setRivalToken(playerToken);
                }
                if (mode == 2)
                {
                    playerTwoBoard.getFXML().giveFullAccess();
                    playerTwoBoard.getFXML().buttonsAvailable(false);
                    playerOneBoard.getFXML().buttonsAvailable(true);
                    playerOneBoard.getFXML().setRivalToken(playerToken);
                }
                playerOneBoard.getFXML().paintBoard(game.getBoard(Side.PLAYER_ONE));
                playerTwoBoard.getFXML().paintBoard(game.getBoard(Side.PLAYER_TWO));
                firstBoard.getChildren().clear();
                secondBoard.getChildren().clear();
                firstBoard.getChildren().add(playerOneBoard.getPane());
                secondBoard.getChildren().add(playerTwoBoard.getPane());
            }
        );
    }

    public void back()
    {
        if (game.isRunning())
        {
            if (mode != 0)
            {
                listener.listen(new ResignEvent(playerToken));
            }
        }
        else
        {
            listener.listen(new ChangeFrameEvent("mainMenu"));
        }
    }
}
