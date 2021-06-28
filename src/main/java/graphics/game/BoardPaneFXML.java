package graphics.game;

import constants.ClientConstants;
import event.EventListener;
import event.events.gameplay.GameMoveEvent;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import model.Board;
import model.cell.CellStatus;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardPaneFXML implements Initializable
{
    private EventListener listener;
    private String rivalToken;
    private boolean fullAccess;

    public Pane buttonsPane;
    private final Button[][] buttons = new Button[10][10];

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void setRivalToken(String rivalToken)
    {
        this.rivalToken = rivalToken;
    }

    public void giveFullAccess()
    {
        fullAccess = true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Platform.runLater(
            () -> {
                for (int i = 0; i < 10; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        int finalI = i;
                        int finalJ = j;
                        Button button = new Button();
                        button.setVisible(true);
                        button.setPrefWidth(40);
                        button.setPrefHeight(40);
                        button.setLayoutX(40 * i);
                        button.setLayoutY(40 * j);
                        button.setStyle(String.format("-fx-background-color: %s", ClientConstants.INTACT_SHIP));
                        button.setOnAction(event -> clickOnButton(finalI, finalJ));
                        buttons[i][j] = button;
                        buttonsPane.getChildren().add(button);
                    }
                }
                for (int i = 0; i < 9; i++)
                {
                    Line hLine = new Line(0, (i + 1) * 40 , 400, (i + 1) * 40);
                    Line vLine = new Line((i + 1) * 40, 0, (i + 1) * 40, 400);
                    hLine.strokeWidthProperty().setValue(2);
                    vLine.strokeWidthProperty().setValue(2);
                    hLine.fillProperty().setValue(Paint.valueOf(ClientConstants.BOARD_COLOR));
                    vLine.fillProperty().setValue(Paint.valueOf(ClientConstants.BOARD_COLOR));
                    buttonsPane.getChildren().add(hLine);
                    buttonsPane.getChildren().add(vLine);
                }
            }
        );
    }

    public void buttonsAvailable(boolean b)
    {
        Platform.runLater(
            () -> {
                for (int i = 0; i < 10; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        buttons[i][j].setDisable(!b);
                    }
                }
            }
        );
    }

    public void paintBoard(Board board)
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                CellStatus status = board.getBoard()[i][j].getStatus();
                if (status.equals(CellStatus.EMPTY))
                {
                    paintButton(i, j, ClientConstants.NORMAL_CELL);
                }
                else if (status.equals(CellStatus.SHIP))
                {
                    if (fullAccess)
                    {
                        paintButton(i, j, ClientConstants.INTACT_SHIP);
                    }
                    else
                    {
                        paintButton(i, j, ClientConstants.NORMAL_CELL);
                    }
                }
                else if (status.equals(CellStatus.BOMBED))
                {
                    paintButton(i, j, ClientConstants.MISSED_BOMB);
                }
                else if (status.equals(CellStatus.DESTROYED))
                {
                    paintButton(i, j, ClientConstants.BOMBED_SHIP);
                }
            }
        }
    }

    public void paintButton(int x, int y, String color)
    {
        Platform.runLater(
            () -> buttons[x][y].setStyle(String.format("-fx-background-color: %s", color))
        );
    }

    public void clickOnButton(int x, int y)
    {
        listener.listen(new GameMoveEvent(rivalToken, x, y));
    }
}
