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

    public void stopLoops(String displayedPage)
    {
        if (scoreboard.isDisplayed() && !displayedPage.equals("scoreboard"))
        {
            Platform.runLater(
                    scoreboard::stopLoop
            );
        }
        if (gamesList.isDisplayed() && !displayedPage.equals("gamesList"))
        {
            Platform.runLater(
                    gamesList::stopLoop
            );
        }
        // TODO add more conditions
    }

    public void initialize()
    {
        stopLoops("firstPage");
        Platform.runLater(
                () -> {
                    firstPage.setListener(listener);
                    stage.setScene(firstPage.getScene());
                }
        );
    }

    public void showLoginPage()
    {
        stopLoops("login");
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
        stopLoops("login");
        Platform.runLater(
                () -> loginPage.setMessage(message)
        );
    }

    public void showSignUpPage()
    {
        stopLoops("signUp");
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
        stopLoops("signUp");
        Platform.runLater(
                () -> signUpPage.setMessage(message)
        );
    }

    public void showMainMenu()
    {
        stopLoops("mainMenu");
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
        stopLoops("mainMenu");
        Platform.runLater(
                () -> mainMenu.setMessage(message)
        );

    }

    public void showScoreboard(ObservableList<ScoreboardResultFinalized> list)
    {
        stopLoops("scoreboard");
        if (!scoreboard.isDisplayed())
        {
            Platform.runLater(
                    scoreboard::startLoop
            );
        }
        Platform.runLater(
                () -> {
                    scoreboard.setScoreboard(list);
                    scoreboard.updateScoreboard();
                    scoreboard.setListener(listener);
                    stage.setScene(scoreboard.getScene());
                }
        );
    }

    public void showGamesList(ObservableList<GamesListResultFinalized> list)
    {
        stopLoops("gamesList");
        if (!gamesList.isDisplayed())
        {
            Platform.runLater(
                    gamesList::startLoop
            );
        }
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
