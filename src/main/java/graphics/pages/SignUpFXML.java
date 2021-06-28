package graphics.pages;

import event.EventListener;
import event.events.authentication.SignupEvent;
import event.events.menu.ChangeFrameEvent;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpFXML implements Initializable
{
    private EventListener listener;

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Text messageText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void setMessage(String message)
    {
        Platform.runLater(
            () -> {
                messageText.setText(message);
                messageText.setVisible(true);
            }
        );
    }

    public void clear()
    {
        Platform.runLater(
            () -> {
                usernameTextField.clear();
                passwordTextField.clear();
                messageText.setVisible(false);
            }
        );
    }

    public void signup()
    {
        String username =  usernameTextField.getText();
        String password =passwordTextField.getText();
        clear();
        listener.listen(new SignupEvent(username, password));
    }

    public void back()
    {
        clear();
        listener.listen(new ChangeFrameEvent("firstPage"));
    }
}
