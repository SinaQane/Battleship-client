package graphics;

import controller.gameslist.GamesListResultFinalized;
import controller.scoreboard.ScoreboardResultFinalized;
import event.EventListener;
import graphics.pages.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.game.Game;
import util.Loop;

public class GraphicalAgent
{
    private String authToken;
    private Game currentGame;
    private Loop loop;

    private final EventListener listener;
    private final Stage stage;

    private final FirstPage firstPage = new FirstPage();
    private final MainMenu mainMenu = new MainMenu();
    private final Login loginPage = new Login();
    private final SignUp signUpPage = new SignUp();
    private final Scoreboard scoreboard = new Scoreboard();
    private final GamesList gamesList = new GamesList();

    public GraphicalAgent(EventListener listener, Stage stage)
    {
        this.stage = stage;
        this.listener = listener;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public void initialize()
    {
        Platform.runLater(
                () -> {
                    firstPage.setListener(listener);
                    stage.setScene(firstPage.getScene());
                }
        );
    }

    public void showLoginPage()
    {
        Platform.runLater(
                () -> {
                    loginPage.clear();
                    loginPage.setListener(listener);
                    stage.setScene(loginPage.getScene());
                }
        );
    }

    public void setLoginPageMessage(String message)
    {
        Platform.runLater(
                () -> loginPage.setMessage(message)
        );
    }

    public void showSignUpPage()
    {
        Platform.runLater(
                () -> {
                    signUpPage.clear();
                    signUpPage.setListener(listener);
                    stage.setScene(signUpPage.getScene());
                }
        );
    }

    public void setSignUpPageMessage(String message)
    {
        Platform.runLater(
                () -> signUpPage.setMessage(message)
        );
    }

    public void showMainMenu()
    {
        Platform.runLater(
                () -> {
                    mainMenu.clear();
                    mainMenu.setListener(listener);
                    mainMenu.setAuthToken(authToken);
                    stage.setScene(mainMenu.getScene());
                }
        );
    }

    public void setMainMenuMessage(String message)
    {
        Platform.runLater(
                () -> mainMenu.setMessage(message)
        );

    }

    public void showScoreboard(ObservableList<ScoreboardResultFinalized> list) // TODO update scoreboard
    {
        Platform.runLater(
                () -> {
                    scoreboard.setScoreboard(list);
                    scoreboard.updateScoreboard();
                    scoreboard.setListener(listener);
                    stage.setScene(scoreboard.getScene());
                }
        );
    }

    public void showGamesList(ObservableList<GamesListResultFinalized> list)  // TODO update gamesList
    {
        Platform.runLater(
                () -> {
                    gamesList.setGamesList(list);
                    gamesList.updateGamesList();
                    gamesList.setListener(listener);
                    stage.setScene(gamesList.getScene());
                }
        );
    }

    // These functions should be worked on later (They're too important)

    public void visitGame()
    {
        // TODO
    }

    private void updateWatchingGameBoard()
    {
        // TODO Same as updateCurrentGameBoard()
    }

    public void showBoardPickingPage()
    {
        // TODO
    }

    public void showGamePage()
    {
        // TODO
    }

    private void updateCurrentGameBoard()
    {
        // TODO listener.listen(new GetBoardEvent(authToken));
    }
}
