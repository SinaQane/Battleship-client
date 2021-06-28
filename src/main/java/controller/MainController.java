package controller;

import constants.ClientConstants;
import controller.gameslist.GamesListController;
import controller.gameslist.GamesListResultFinalized;
import controller.scoreboard.ScoreboardController;
import controller.scoreboard.ScoreboardResultFinalized;
import event.EventSender;
import event.Event;
import graphics.GraphicalAgent;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.game.Game;
import model.Board;
import model.User;
import response.ResponseVisitor;
import response.Response;
import util.Loop;

import java.util.LinkedList;
import java.util.List;

public class MainController implements ResponseVisitor
{
    private final GraphicalAgent graphicalAgent;
    private final EventSender eventSender;
    private final List<Event> events = new LinkedList<>();
    private String authToken = "";
    private final Loop loop;
    private User user;

    public MainController(EventSender eventSender, Stage stage)
    {
        loop = new Loop(ClientConstants.EVENT_FPS, this::sendEvents);
        graphicalAgent = new GraphicalAgent(this::addEvent, stage);
        this.eventSender = eventSender;
    }

    public void start()
    {
        loop.start();
        graphicalAgent.initialize();
    }

    private void addEvent(Event event)
    {
        synchronized (events)
        {
            events.add(event);
        }
    }

    private void sendEvents()
    {
        List<Event> temp;
        synchronized (events)
        {
            temp = new LinkedList<>(events);
            events.clear();
        }
        for (Event event : temp)
        {
            Response response = eventSender.sendEvent(event);
            response.visit(this);
        }
    }

    @Override
    public void loginResponse(User user, String response, String authToken)
    {
        if (authToken.equals(""))
        {
            graphicalAgent.setLoginPageMessage(response);
        }
        else
        {
            this.user = user;
            this.authToken = authToken;
            graphicalAgent.setAuthToken(authToken);
            graphicalAgent.showMainMenu();
            // does graphicalAgent need user?
        }
    }

    @Override
    public void signupResponse(String response)
    {
        if (!response.equals(""))
        {
            graphicalAgent.setSignUpPageMessage(response);
        }
        else
        {
            graphicalAgent.showFirstPage();
        }
    }

    @Override
    public void logoutResponse(String response)
    {
        if (response.equals(""))
        {
            user = null;
            authToken = "";
            graphicalAgent.setAuthToken("");
            graphicalAgent.showFirstPage();
            // does graphicalAgent need user?
        }
        else
        {
            graphicalAgent.setMainMenuMessage(response);
        }
    }

    @Override
    public void gamesList(Game[] games)
    {
        ObservableList<GamesListResultFinalized> gamesList = GamesListController.getGamesList(games);
        graphicalAgent.showGamesList(gamesList);
    }

    @Override
    public void scoreboard(User[] users)
    {
        ObservableList<ScoreboardResultFinalized> scoreboard = ScoreboardController.getScoreboard(users);
        graphicalAgent.showScoreboard(scoreboard);
    }

    @Override
    public void pickBoard(Board[] boards)
    {
        graphicalAgent.showBoardPickingPage(boards);
    }

    @Override
    public void changeFrame(String frame)
    {
        switch (frame)
        {
            case "firstPage":
                graphicalAgent.showFirstPage();
                break;
            case "login":
                graphicalAgent.showLoginPage();
                break;
            case "signUp":
                graphicalAgent.showSignUpPage();
                break;
            case "mainMenu":
                graphicalAgent.showMainMenu();
                break;
        }
    }

    @Override
    public void gameplay(Game game)
    {

    }

    @Override
    public void viewGame(Game game)
    {

    }

    @Override
    public void startGame(Game game)
    {

    }
}
