package controller;

import constants.Constants;
import event.EventSender;
import event.Event;
import graphics.GraphicalAgent;
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
    private final Loop loop;
    private String authToken;
    private User user;

    public MainController(EventSender eventSender, Stage primaryStage)
    {
        loop = new Loop(Constants.FPS, this::sendEvents);
        graphicalAgent = new GraphicalAgent(this::addEvent);
        graphicalAgent.setStage(primaryStage);
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
            // graphicalAgent.getLoginPage().getText().setText(response);
        }
        else
        {
            graphicalAgent.setAuthToken(authToken);
            this.authToken = authToken;
            this.user = user;
            // does graphicalAgent need user?
            // graphicalAgent.showMainMenu();
        }
    }

    @Override
    public void signupResponse(String response)
    {

    }

    @Override
    public void logoutResponse(String response)
    {
        if (response.equals("logout successful"))
        {
            graphicalAgent.setAuthToken("");
            user = null;
            // does graphicalAgent need user?
        }
        else
        {
            // graphicalAgent.getMainMenu().getText().setText(response);
        }
    }

    @Override
    public void gameplay(Game game)
    {

    }

    @Override
    public void gamesList(Game[] games)
    {

    }

    @Override
    public void scoreboard(User[] users)
    {

    }

    @Override
    public void viewGame(Game game)
    {

    }

    @Override
    public void pickBoard(Board[] boards)
    {

    }

    @Override
    public void startGame(Game game)
    {

    }
}
