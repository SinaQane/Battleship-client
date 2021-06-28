package graphics.pages;

import config.Config;
import constants.ClientConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class SignUp
{
    private static final String SIGNUP_PAGE = new Config(ClientConstants.CONFIG_ADDRESS)
            .getProperty(String.class,"signUp").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    public SignUp()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SIGNUP_PAGE)));
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

    public SignUpFXML getFXML()
    {
        return loader.getController();
    }

    public Scene getScene()
    {
        return scene;
    }
}
