package graphics.pages;

import config.Config;
import constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import listener.ButtonListener;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FirstPage implements Initializable
{
    private static final String FIRST_PAGE = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"firstPage").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    private final ButtonListener buttonListener = new ButtonListener();

    public FirstPage()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(FIRST_PAGE)));
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

    // FXML Controller

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void login()
    {
        buttonListener.eventOccurred("login");
    }

    public void signUp()
    {
        buttonListener.eventOccurred("signup");
    }
}
