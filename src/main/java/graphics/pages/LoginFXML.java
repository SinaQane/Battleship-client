package graphics.pages;

import event.EventListener;
import event.events.authentication.LoginEvent;
import event.events.menu.ChangeFrameEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFXML implements Initializable
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
