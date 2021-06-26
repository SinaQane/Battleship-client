package graphics.pages;

import config.Config;
import constants.Constants;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import listener.AuthListener;
import listener.ButtonListener;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUp implements Initializable
{
    private static final String SIGNUP_PAGE = new Config(Constants.CONFIG_ADDRESS)
            .getProperty(String.class,"signUp").orElse("");

    private final Scene scene;
    private final FXMLLoader loader;

    private final ButtonListener buttonListener = new ButtonListener();
    private final AuthListener authListener = new AuthListener();
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Text messageText;

    public SignUp()
    {
        this.loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(SIGNUP_PAGE)));
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
    public void initialize(URL location, ResourceBundle resources) {}

    public void signup()
    {
        String username =  usernameTextField.getText();
        String password =passwordTextField.getText();
        usernameTextField.clear();
        passwordTextField.clear();
        authListener.eventOccurred("signup", username, password);
    }

    public void back()
    {
        buttonListener.eventOccurred("firstpage");
    }
}
