import config.Config;
import constants.Constants;
import controller.MainController;
import event.SocketEventSender;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application
{
    static Config config;
    static String host;
    static int port;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        config = new Config(Constants.CONFIG_ADDRESS);
        host = config.getProperty(String.class,"host").orElse(Constants.DEFAULT_HOST);
        port = config.getProperty(Integer.class,"port").orElse(Constants.DEFAULT_PORT);
        Socket socket = null;
        try
        {
            socket = new Socket(host, port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        MainController controller = null;
        try
        {
            assert socket != null;
            controller = new MainController(new SocketEventSender(socket), stage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        assert controller != null;
        controller.start();
    }
}
