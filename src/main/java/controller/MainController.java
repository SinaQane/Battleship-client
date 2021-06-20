package controller;

import constants.Constants;
import event.Event;
import graphics.GraphicalAgent;
import event.EventSender;
import model.Board;
import model.User;
import model.game.Game;
import response.Response;
import response.ResponseVisitor;
import util.Loop;

import java.util.LinkedList;
import java.util.List;

public class MainController implements ResponseVisitor
{
    private final GraphicalAgent graphicalAgent; // TODO use this in the front-end
    private final EventSender eventSender;
    private final List<Event> events;
    private final Loop loop;
    private String authToken;
    private User user;

    public MainController(EventSender eventSender)
    {
        this.loop = new Loop(Constants.FPS, this::sendEvents);
        this.graphicalAgent = new GraphicalAgent(this::addEvent);
        this.events = new LinkedList<>();
        this.eventSender = eventSender;
    }

    public void start()
    {
        loop.start();
        /* TODO graphic stuff for the front-end
        graphicalAgent.initialize();
        graphicalAgent.gotoMainMenu(); */
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
    public void loginResponse(String response, String authToken)
    {
        // TODO initialize authToken and user
    }

    @Override
    public void signupResponse(String response)
    {

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
