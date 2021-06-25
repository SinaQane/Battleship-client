package graphics.mainmenu;

import config.Config;
import constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class MainMenu
{
    private static final String MAIN_MENU = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"mainMenu").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    public MainMenu()
    {
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
}
