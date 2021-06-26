package listener;

public class ButtonListener
{
    public void eventOccurred(String string)
    {
        switch (string)
        {
            case "firstpage":
            case "mainmenu":
            case "login":
            case "signup":
            case "logout":
            case "scoreboard":
            case "gameslist":
                break;
        }
    }
}
