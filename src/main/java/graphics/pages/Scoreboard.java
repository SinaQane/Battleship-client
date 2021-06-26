package graphics.pages;

import config.Config;
import constants.Constants;
import controller.scoreboard.ScoreboardResultFinalized;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Scoreboard implements Initializable
{
    private static final String SCOREBOARD = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"scoreboard").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    private ObservableList<ScoreboardResultFinalized> scoreboard;

    public VBox tableContainer;

    public Scoreboard(ObservableList<ScoreboardResultFinalized> scoreboard)
    {
        this.scoreboard = scoreboard;
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

    public FXMLLoader getLoader()
    {
        return this.loader;
    }

    public Scene getScene()
    {
        return this.scene;
    }

    // FXML Controller

    @Override
    public void initialize(URL location, ResourceBundle resources)
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
        table.setPrefSize(800, 600);
        tableContainer.getChildren().add(0, table);
    }

    public void setScoreboard(ObservableList<ScoreboardResultFinalized> scoreboard)
    {
        this.scoreboard = scoreboard;
    }

    public void back(ActionEvent actionEvent)
    {

    }
}
