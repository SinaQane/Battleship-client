package graphics;

import event.EventListener;
import event.events.gameplay.GetBoardEvent;
import util.Loop;

public class GraphicalAgent
{
    // private final Map<PanelType, AbstractPanel> panels;
    private final EventListener listener;
    private Loop loop;
    // frame


    public GraphicalAgent(EventListener listener)
    {
        // this.panels = new EnumMap<>(PanelType.class);
        this.listener = listener;
        // frame
    }

    public void initialize()
    {
        // set frame visible
    }

    private void updateBoard()
    {
        // listener.listen(new GetBoardEvent());
        // TODO token
    }
}
