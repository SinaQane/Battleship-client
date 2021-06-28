package graphics.game;

import config.Config;
import constants.ClientConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class BoardPane
{
    private static final String BOARD = new Config(ClientConstants.CONFIG_ADDRESS)
            .getProperty(String.class,"board").orElse("");

    private Pane pane;
    private final FXMLLoader loader;

    public BoardPane()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(BOARD)));
        try
        {
            pane = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public BoardPaneFXML getFXML()
    {
        return loader.getController();
    }

    public Pane getPane()
    {
        return pane;
    }
}
