package graphics.pages;

import config.Config;
import constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

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

    // private StringListener listener;

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
        //listener.listen("Login");
    }

    public void signUp()
    {
        //listener.listen("SignUp");
    }

    /* Should I use listener?
    public void setListener(StringListener listener)
    {
        this.listener = listener;
    } */
}