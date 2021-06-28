package graphics.pages;

import constants.ClientConstants;
import controller.gameslist.GamesListResultFinalized;
import event.EventListener;
import event.events.menu.ChangeFrameEvent;
import event.events.menu.GamesListEvent;
import event.events.menu.ViewGameEvent;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import util.Loop;

import java.net.URL;
import java.util.ResourceBundle;

public class GamesListFXML implements Initializable
{
    private EventListener listener;

    public Button backButton;
    public Button viewGameButton;
    public TextField gameTextBox;
    public VBox tableContainer;

    private ObservableList<GamesListResultFinalized> gamesList;

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
        listener.listen(new GamesListEvent());
    }

    public boolean isDisplayed()
    {
        return displayed;
    }

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
        Platform.runLater(
            () -> tableContainer.getChildren().add(0, table)
        );
    }

    public void viewGame()
    {
        String game = gameTextBox.getText();
        Platform.runLater(
            () -> gameTextBox.clear()
        );
        try
        {
            int index = Integer.parseInt(game);
            listener.listen(new ViewGameEvent(index));
        }
        catch (Exception ignored) {}
    }

    public void back()
    {
        Platform.runLater(
            () -> gameTextBox.clear()
        );
        listener.listen(new ChangeFrameEvent("mainMenu"));
    }
}
