package graphics.game;

import config.Config;
import constants.Constants;
import event.EventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameFrame implements Initializable
{
    private static final String GAME_FRAME = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"gameFrame").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;
    private EventListener listener;

    public Label gameMessageText;
    public Label timerText;
    public Text firstPlayerText;
    public Text secondPlayerText;
    public Pane firstBoard;
    public Pane secondBoard;
    public Button backButton;

    public GameFrame(String mode)
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(GAME_FRAME)));
        Parent root = null;
        try
        {
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        assert root != null;
        this.scene = new Scene(root);
    }

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public Scene getScene()
    {
        return this.scene;
    }

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    // FXML Controller

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void back(ActionEvent actionEvent)
    {

    }
}
