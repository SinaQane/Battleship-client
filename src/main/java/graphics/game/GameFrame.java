package graphics.game;

import config.Config;
import constants.ClientConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class GameFrame
{
    private static final String GAME_FRAME = new Config(ClientConstants.CONFIG_ADDRESS)
            .getProperty(String.class,"gameFrame").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    public GameFrame()
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

    public GameFrameFXML getFXML()
    {
        return loader.getController();
    }

    public Scene getScene()
    {
        return scene;
    }
}
