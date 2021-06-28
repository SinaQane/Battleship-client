package graphics.pages;

import config.Config;
import constants.ClientConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class Scoreboard
{
    private static final String SCOREBOARD = new Config(ClientConstants.CONFIG_ADDRESS)
            .getProperty(String.class,"scoreboard").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    public Scoreboard()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SCOREBOARD)));
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

    public ScoreboardFXML getFXML()
    {
        return loader.getController();
    }

    public Scene getScene()
    {
        return scene;
    }
}
