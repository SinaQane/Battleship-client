package graphics.pages;

import config.Config;
import constants.Constants;
import event.EventListener;
import event.events.authentication.LoginEvent;
import event.events.menu.ChangeFrameEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable
{
    private static final String LOGIN_PAGE = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"login").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;
    private EventListener listener;

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Text messageText;

    public Login()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(LOGIN_PAGE)));
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

    public void setMessage(String message)
    {
        messageText.setText(message);
        messageText.setVisible(true);
    }

    public void clear()
    {
        usernameTextField.clear();
        passwordTextField.clear();
        messageText.setVisible(false);
    }

    public void login()
    {
        String username =  usernameTextField.getText();
        String password =passwordTextField.getText();
        clear();
        listener.listen(new LoginEvent(username, password));
    }

    public void back()
    {
        clear();
        listener.listen(new ChangeFrameEvent("firstPage"));
    }
}
