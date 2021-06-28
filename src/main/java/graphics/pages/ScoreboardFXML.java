package graphics.pages;

import constants.ClientConstants;
import controller.scoreboard.ScoreboardResultFinalized;
import event.EventListener;
import event.events.menu.ChangeFrameEvent;
import event.events.menu.ScoreboardEvent;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import util.Loop;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreboardFXML implements Initializable
{
    private EventListener listener;

    public VBox tableContainer;

    private ObservableList<ScoreboardResultFinalized> scoreboard;

    private boolean displayed;
    private Loop loop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void startLoop()
    {
        displayed = true;
        loop = new Loop(ClientConstants.UPDATE_FPS, this::update);
        loop.start();
    }

    public void stopLoop()
    {
        displayed = false;
        loop.stop();
    }

    public void update()
    {
        listener.listen(new ScoreboardEvent());
    }

    public boolean isDisplayed()
    {
        return displayed;
    }

    public void setScoreboard(ObservableList<ScoreboardResultFinalized> scoreboard)
    {
        this.scoreboard = scoreboard;
        updateScoreboard();
    }

    public void updateScoreboard()
    {
        TableView<ScoreboardResultFinalized> table = new TableView<>();
        TableColumn<ScoreboardResultFinalized, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        rankColumn.setPrefWidth(150);
        TableColumn<ScoreboardResultFinalized, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setPrefWidth(300);
        TableColumn<ScoreboardResultFinalized, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setPrefWidth(200);
        TableColumn<ScoreboardResultFinalized, Boolean> isOnlineColumn = new TableColumn<>("Online");
        isOnlineColumn.setCellValueFactory(new PropertyValueFactory<>("online"));
        isOnlineColumn.setPrefWidth(150);
        table.setItems(scoreboard);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getColumns().add(rankColumn);
        table.getColumns().add(usernameColumn);
        table.getColumns().add(scoreColumn);
        table.getColumns().add(isOnlineColumn);
        table.setPrefSize(800, 550);
        Platform.runLater(
            () -> {
                tableContainer.getChildren().clear();
                tableContainer.getChildren().add(0, table);
            }
        );
    }

    public void back()
    {
        listener.listen(new ChangeFrameEvent("mainMenu"));
    }
}
