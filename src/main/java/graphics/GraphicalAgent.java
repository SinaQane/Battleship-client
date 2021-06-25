package graphics;

import event.EventListener;
import event.events.gameplay.GetBoardEvent;
import graphics.firstpage.FirstPage;
import javafx.stage.Stage;
import model.game.Game;
import util.Loop;

public class GraphicalAgent
{
    // private final Map<PanelType, AbstractPanel> panels;
    // private final MainFrame mainFrame;
    private Stage stage;
    private final EventListener listener;
    private String authToken;
    private Game currentGame;
    private Loop loop;

    public GraphicalAgent(EventListener listener)
    {
        // this.panels = new EnumMap<>(PanelType.class);
        // mainFrame = new MainFrame();
        this.listener = listener;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    public void initialize()
    {
        stage.setScene(new FirstPage().getScene());
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
