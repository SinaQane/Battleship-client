package graphics;

import controller.gameslist.GamesListResultFinalized;
import controller.scoreboard.ScoreboardResultFinalized;
import event.EventListener;
import event.events.authentication.LogoutEvent;
import graphics.game.GameFrame;
import graphics.game.PickBoard;
import graphics.pages.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Board;
import model.game.Game;

public class GraphicalAgent
{
    private String authToken;

    private final EventListener listener;
    private final Stage stage;

    private final FirstPage firstPage = new FirstPage();
    private final MainMenu mainMenu = new MainMenu();
    private final Login loginPage = new Login();
    private final SignUp signUpPage = new SignUp();
    private final Scoreboard scoreboard = new Scoreboard();
    private final GamesList gamesList = new GamesList();
    private GameFrame gameFrame;

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
    }

    public void initialize()
    {
        Platform.runLater(
            () -> {
                firstPage.getFXML().setListener(listener);
                stage.setTitle("Battleship");
                stage.setResizable(false);
                stage.setOnHidden(e -> {
                    listener.listen(new LogoutEvent(authToken));
                    Platform.exit();
                });
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

    public void showBoardPickingPage(Board[] boards)
    {
        Platform.runLater(
            () -> {
                PickBoard pickBoardPage = new PickBoard();
                pickBoardPage.getFXML().setListener(listener);
                pickBoardPage.getFXML().setToken(authToken);
                pickBoardPage.getFXML().setBoards(boards);
                pickBoardPage.getFXML().startLoop();
                stage.setScene(pickBoardPage.getScene());
            }
        );
    }

    public void watchGame(Game game)
    {
        Platform.runLater(
            () -> {
                gameFrame = new GameFrame();
                gameFrame.getFXML().setListener(listener);
                gameFrame.getFXML().setGame(game);
                gameFrame.getFXML().setMode(0);
                gameFrame.getFXML().setPlayerToken(authToken);
                gameFrame.getFXML().startLoop();
                stage.setScene(gameFrame.getScene());
            }
        );
    }

    public void playGame(Game game, int player)
    {
        Platform.runLater(
            () -> {
                gameFrame = new GameFrame();
                gameFrame.getFXML().setListener(listener);
                if (game != null)
                {
                    gameFrame.getFXML().setGame(game);
                }
                gameFrame.getFXML().setMode(player);
                gameFrame.getFXML().setPlayerToken(authToken);
                gameFrame.getFXML().startLoop();
                stage.setScene(gameFrame.getScene());
            }
        );
    }

    public void updateGameBoard(Game game)
    {
        Platform.runLater(
            () -> {
                if (game != null) gameFrame.getFXML().setGame(game);
            }
        );
    }
}
