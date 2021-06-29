package graphics.game;

import constants.ClientConstants;
import event.EventListener;
import event.events.gameplay.GameMoveEvent;
import event.events.gameplay.GetBoardEvent;
import event.events.gameplay.ResignEvent;
import event.events.gameplay.UpdateTimerEvent;
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
    private String playerToken;
    private Game game;
    private Loop loop;

    private int mode;

    public Label gameMessageText;
    public Label timerText;
    public Text firstPlayerText;
    public Text secondPlayerText;
    public Pane firstBoard;
    public Pane secondBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        gameMessageText.setText("New Game");
        timerText.setText("waiting for opponent");
    }

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
        if (game != null)
        {
            repaintPage();
            if (!game.isRunning())
            {
                Platform.runLater(
                        () -> {
                            gameMessageText.setText(game.getGameMessage());
                            timerText.setText("game over");
                        }
                );
                stopLoop();
            }
            else if (game.isRunning() && mode != 0)
            {
                listener.listen(new UpdateTimerEvent());
                if (game.getTime() <= 1 && game.getSide() == mode)
                {
                    listener.listen(new GameMoveEvent(playerToken, -1, -1)); // hit (-1, -1) for timeoutEvent
                }
            }
        }
    }

    public void repaintPage()
    {
        Platform.runLater(
            () -> {
                timerText.setText(game.getTime() / 2 + " seconds left");
                gameMessageText.setText(game.getGameMessage());
                firstPlayerText.setText("Player 1: " + game.getPlayer(Side.PLAYER_ONE).getUsername());
                secondPlayerText.setText("Player 2: " + game.getPlayer(Side.PLAYER_TWO).getUsername());
                BoardPane playerOneBoard = new BoardPane();
                BoardPane playerTwoBoard = new BoardPane();
                playerOneBoard.getFXML().setListener(listener);
                playerTwoBoard.getFXML().setListener(listener);
                if (mode == 0)
                {
                    playerOneBoard.getFXML().buttonsAvailable(false);
                    playerTwoBoard.getFXML().buttonsAvailable(false);
                }
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
        if (game != null)
        {
            if (game.isRunning())
            {
                if (mode != 0)
                {
                    listener.listen(new ResignEvent(playerToken));
                }
                else
                {
                    listener.listen(new ChangeFrameEvent("mainMenu"));
                }
            }
            else
            {
                listener.listen(new ChangeFrameEvent("mainMenu"));
            }
        }
        // TODO else{}
    }
}
