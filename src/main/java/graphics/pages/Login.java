package graphics.pages;

import config.Config;
import constants.ClientConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class Login
{
    private static final String LOGIN_PAGE = new Config(ClientConstants.CONFIG_ADDRESS)
            .getProperty(String.class,"login").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    public Login()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(LOGIN_PAGE)));
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

    public LoginFXML getFXML()
    {
        return loader.getController();
    }

    public Scene getScene()
    {
        return scene;
    }
}
