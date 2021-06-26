package graphics.pages;

import config.Config;
import constants.Constants;
import event.EventListener;
import event.events.authentication.LogoutEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenu implements Initializable
{
    private static final String MAIN_MENU = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"mainMenu").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;
    private EventListener listener;
    private String authToken;

    public Label username;
    public Label message;

    public MainMenu()
    {
        this.authToken = "";
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(MAIN_MENU)));
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

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }

    // FXML Controller

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void newGame(ActionEvent actionEvent)
    {

    }

    public void viewGames(ActionEvent actionEvent)
    {

    }

    public void scoreboard(ActionEvent actionEvent)
    {

    }

    public void logout()
    {
        listener.listen(new LogoutEvent(authToken));
    }
}
