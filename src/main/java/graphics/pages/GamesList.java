package graphics.pages;

import config.Config;
import constants.Constants;
import controller.gameslist.GamesListResultFinalized;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import listener.ButtonListener;
import listener.ViewGameListener;

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

    private final ButtonListener buttonListener = new ButtonListener();
    private final ViewGameListener gameListener = new ViewGameListener();
    public Button backButton;
    public Button viewGameButton;
    public TextField gameTextBox;

    private ObservableList<GamesListResultFinalized> gamesList;

    public VBox tableContainer;

    public GamesList(ObservableList<GamesListResultFinalized> gamesList)
    {
        this.gamesList = gamesList;
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

    // FXML Controller

    @Override
    public void initialize(URL location, ResourceBundle resources)
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

    public void setGamesList(ObservableList<GamesListResultFinalized> gamesList)
    {
        this.gamesList = gamesList;
    }

    public void viewGame()
    {
        String game = gameTextBox.getText();
        gameTextBox.clear();
        try
        {
            int index = Integer.parseInt(game);
            gameListener.eventOccurred(index);
        }
        catch (Exception ignored) {}
    }

    public void back()
    {
        buttonListener.eventOccurred("mainmenu");
    }
}
