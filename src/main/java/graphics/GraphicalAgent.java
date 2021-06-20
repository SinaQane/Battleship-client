package graphics;

import event.EventListener;
import event.events.gameplay.GetBoardEvent;
import model.game.Game;
import util.Loop;

public class GraphicalAgent
{
    // private final Map<PanelType, AbstractPanel> panels;
    // Main frame (mainFrame) for being displayed
    private final EventListener listener;
    private String authToken;
    private Game currentGame;
    private Loop loop;

    public GraphicalAgent(EventListener listener)
    {
        // Set main frame to a new frame (sth like mainFrame = new MainFrame())
        // this.panels = new EnumMap<>(PanelType.class);
        this.listener = listener;
    }

    public void initialize()
    {
        // set frame visible
    }

    public void showLoginPage()
    {

    }

    public void showSignUpPage()
    {

    }

    public void showMainPage()
    {

    }

    public void showScoreboard()
    {

    }

    public void showGamesList()
    {

    }

    public void visitGame()
    {

    }

    private void updateWatchingGameBoard()
    {
        // Same as updateCurrentGameBoard()
    }

    public void showBoardPickingPage()
    {

    }

    public void showGamePage()
    {

    }

    private void updateCurrentGameBoard()
    {
        listener.listen(new GetBoardEvent(authToken));
    }
}
