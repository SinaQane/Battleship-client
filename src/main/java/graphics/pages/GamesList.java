package graphics.pages;

import config.Config;
import constants.Constants;
import controller.gameslist.GamesListResultFinalized;
import event.EventListener;
import event.events.menu.ChangeFrameEvent;
import event.events.menu.ViewGameEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GamesList implements Initializable
{
    private static final String GAMES_LIST = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"gamesList").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;
    private EventListener listener;

    public Button backButton;
    public Button viewGameButton;
    public TextField gameTextBox;

    private ObservableList<GamesListResultFinalized> gamesList;

    public VBox tableContainer;

    public GamesList()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(GAMES_LIST)));
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
    public void initialize(URL location, ResourceBundle resources) {}

    public void setGamesList(ObservableList<GamesListResultFinalized> gamesList)
    {
        this.gamesList = gamesList;
        updateGamesList();
    }

    public void updateGamesList()
    {
        TableView<GamesListResultFinalized> table = new TableView<>();
        TableColumn<GamesListResultFinalized, Integer> indexColumn = new TableColumn<>("Index");
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        indexColumn.setPrefWidth(200);
        TableColumn<GamesListResultFinalized, Integer> playerOneDroppedBombsColumn = new TableColumn<>("Dropped Bombs (1)");
        playerOneDroppedBombsColumn.setCellValueFactory(new PropertyValueFactory<>("playerOneDroppedBombs"));
        playerOneDroppedBombsColumn.setPrefWidth(100);
        TableColumn<GamesListResultFinalized, Integer> playerTwoDroppedBombsColumn = new TableColumn<>("Dropped Bombs (2)");
        playerTwoDroppedBombsColumn.setCellValueFactory(new PropertyValueFactory<>("playerTwoDroppedBombs"));
        playerTwoDroppedBombsColumn.setPrefWidth(100);
        TableColumn<GamesListResultFinalized, Integer> playerOneSuccessfulBombsColumn = new TableColumn<>("Successful Bombs (1)");
        playerOneSuccessfulBombsColumn.setCellValueFactory(new PropertyValueFactory<>("playerOneSuccessfulBombs"));
        playerOneSuccessfulBombsColumn.setPrefWidth(100);
        TableColumn<GamesListResultFinalized, Integer> playerTwoSuccessfulBombsColumn = new TableColumn<>("Successful Bombs (2)");
        playerTwoSuccessfulBombsColumn.setCellValueFactory(new PropertyValueFactory<>("playerTwoSuccessfulBombs"));
        playerTwoSuccessfulBombsColumn.setPrefWidth(100);
        TableColumn<GamesListResultFinalized, Integer> playerOneSunkenShips = new TableColumn<>("Sunken Ships (1)");
        playerOneSunkenShips.setCellValueFactory(new PropertyValueFactory<>("playerOneSunkenShips"));
        playerOneSunkenShips.setPrefWidth(100);
        TableColumn<GamesListResultFinalized, Integer> playerTwoSunkenShips = new TableColumn<>("Sunken Ships (2)");
        playerTwoSunkenShips.setCellValueFactory(new PropertyValueFactory<>("playerTwoSunkenShips"));
        playerTwoSunkenShips.setPrefWidth(100);
        table.setItems(gamesList);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getColumns().add(indexColumn);
        table.getColumns().add(playerOneDroppedBombsColumn);
        table.getColumns().add(playerTwoDroppedBombsColumn);
        table.getColumns().add(playerOneSuccessfulBombsColumn);
        table.getColumns().add(playerTwoSuccessfulBombsColumn);
        table.getColumns().add(playerOneSunkenShips);
        table.getColumns().add(playerTwoSunkenShips);
        table.setPrefSize(800, 600);
        tableContainer.getChildren().add(0, table);
    }

    public void viewGame()
    {
        String game = gameTextBox.getText();
        gameTextBox.clear();
        try
        {
            int index = Integer.parseInt(game);
            listener.listen(new ViewGameEvent(index));
        }
        catch (Exception ignored) {}
    }

    public void back()
    {
        gameTextBox.clear();
        listener.listen(new ChangeFrameEvent("mainMenu"));
    }
}
