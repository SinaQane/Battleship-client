package graphics.pages;

import event.EventListener;
import event.events.menu.ChangeFrameEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FirstPageFXML implements Initializable
{
    private EventListener listener;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void setListener(EventListener listener)
    {
        this.listener = listener;
    }

    public void login()
    {
        listener.listen(new ChangeFrameEvent("login"));
    }

    public void signUp()
    {
        listener.listen(new ChangeFrameEvent("signUp"));
    }
}
