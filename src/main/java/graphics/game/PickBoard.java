package graphics.game;

import config.Config;
import constants.ClientConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class PickBoard
{
    private static final String PICK_BOARD = new Config(ClientConstants.CONFIG_ADDRESS)
            .getProperty(String.class,"pickBoard").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    public PickBoard()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(PICK_BOARD)));
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

    public PickBoardFXML getFXML()
    {
        return loader.getController();
    }

    public Scene getScene()
    {
        return scene;
    }
}
