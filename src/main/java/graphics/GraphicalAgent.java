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
        if (scoreboard.getFXML().isDisplayed() && !displayedPage.equals("scoreboard"))
        {
            Platform.runLater(
                    scoreboard.getFXML()::stopLoop
            );
        }
        if (gamesList.getFXML().isDisplayed() && !displayedPage.equals("gamesList"))
        {
            Platform.runLater(
                    gamesList.getFXML()::stopLoop
            );
        }
        // TODO add more conditions
    }

    public void initialize()
    {
        Platform.runLater(
                () -> {
                    firstPage.getFXML().setListener(listener);
                    stage.setTitle("Battleship");
                    stage.setResizable(false);
                    stage.show();
                    stage.setScene(firstPage.getScene());
                }
        );
    }

    public void showFirstPage()
    {
        stopLoops("firstPage");
        Platform.runLater(
                () -> {
                    firstPage.getFXML().setListener(listener);
                    stage.setScene(firstPage.getScene());
                }
        );
    }

    public void showLoginPage()
    {
        stopLoops("login");
        Platform.runLater(
                () -> {
                    loginPage.getFXML().clear();
                    loginPage.getFXML().setListener(listener);
                    stage.setScene(loginPage.getScene());
                }
        );
    }

    public void setLoginPageMessage(String message)
    {
        stopLoops("login");
        Platform.runLater(
                () -> loginPage.getFXML().setMessage(message)
        );
    }

    public void showSignUpPage()
    {
        stopLoops("signUp");
        Platform.runLater(
                () -> {
                    signUpPage.getFXML().clear();
                    signUpPage.getFXML().setListener(listener);
                    stage.setScene(signUpPage.getScene());
                }
        );
    }

    public void setSignUpPageMessage(String message)
    {
        stopLoops("signUp");
        Platform.runLater(
                () -> signUpPage.getFXML().setMessage(message)
        );
    }

    public void showMainMenu()
    {
        stopLoops("mainMenu");
        Platform.runLater(
                () -> {
                    mainMenu.getFXML().clear();
                    mainMenu.getFXML().setListener(listener);
                    mainMenu.getFXML().setAuthToken(authToken);
                    stage.setScene(mainMenu.getScene());
                }
        );
    }

    public void setMainMenuMessage(String message)
    {
        stopLoops("mainMenu");
        Platform.runLater(
                () -> mainMenu.getFXML().setMessage(message)
        );

    }

    public void showScoreboard(ObservableList<ScoreboardResultFinalized> list)
    {
        stopLoops("scoreboard");
        if (!scoreboard.getFXML().isDisplayed())
        {
            Platform.runLater(
                    scoreboard.getFXML()::startLoop
            );
        }
        Platform.runLater(
                () -> {
                    scoreboard.getFXML().setScoreboard(list);
                    scoreboard.getFXML().updateScoreboard();
                    scoreboard.getFXML().setListener(listener);
                    stage.setScene(scoreboard.getScene());
                }
        );
    }

    public void showGamesList(ObservableList<GamesListResultFinalized> list)
    {
        stopLoops("gamesList");
        if (!gamesList.getFXML().isDisplayed())
        {
            Platform.runLater(
                    gamesList.getFXML()::startLoop
            );
        }
        Platform.runLater(
                () -> {
                    gamesList.getFXML().setGamesList(list);
                    gamesList.getFXML().updateGamesList();
                    gamesList.getFXML().setListener(listener);
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
