package graphics.game;

import config.Config;
import constants.Constants;
import event.EventListener;
import event.events.gameplay.GameMoveEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import model.Board;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BoardPane implements Initializable
{
    private static final String BOARD = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"board").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;
    private EventListener listener;
    private final String rivalToken;
    private final boolean viewHidden;

    private final Button[][] buttons = new Button[10][10];

    public BoardPane(String rivalToken, boolean viewHidden)
    {
        this.rivalToken = rivalToken;
        this.viewHidden = viewHidden;
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(BOARD)));
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

    // FXML Controller

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                int finalI = i;
                int finalJ = j;
                Button button = new Button();
                button.setPrefWidth(40);
                button.setPrefHeight(40);
                button.setLayoutX(40 * i);
                button.setLayoutY(40 * j);
                button.setStyle(String.format("-fx-background-color: %s", Constants.NORMAL_CELL));
                button.setOnAction(event -> clickOnButton(finalI, finalJ));
                buttons[i][j] = button;
            }
        }
        for (int i = 0; i < 9; i++)
        {
            Line hLine = new Line(0, (i + 1) * 40 , 400, (i + 1) * 40);
            Line vLine = new Line((i + 1) * 40, 0, (i + 1) * 40, 400);
            hLine.strokeWidthProperty().setValue(2);
            vLine.strokeWidthProperty().setValue(2);
            hLine.fillProperty().setValue(Paint.valueOf(Constants.BOARD_COLOR));
            vLine.fillProperty().setValue(Paint.valueOf(Constants.BOARD_COLOR));
        }
    }

    public void buttonsAvailable(boolean b)
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                buttons[i][j].setDisable(!b);
            }
        }
    }

    public void paintBoard(Board board)
    {
        if (viewHidden)
        {
            // TODO
        }
        else
        {
            // TODO
        }
    }

    public void paintButton(int x, int y, String color)
    {
        buttons[x][y].setStyle(String.format("-fx-background-color: %s", color));
    }

    public void clickOnButton(int x, int y)
    {
        listener.listen(new GameMoveEvent(rivalToken, x, y));
    }
}
