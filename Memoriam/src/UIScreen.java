import javax.swing.JPanel;

public class UIScreen extends ShowablePanel {


    /// Showable Panel class
    /// Whenever we can switch to a new panel, please make it extend showable panel instead of JPanel
    /// This is so that we can Identify what are the names of the panels we create
    /// Use the super("panelName") format so that it is easier and more identifiable which panel we swtich to

    public UIScreen(String name)
    {
        super(name);
    }

    @Override
    public String getShowablePanelName()
    {
        return this.name;
    }

    @Override
    public void setShowablePanelName(String name)
    {
        this.name = name;
    }
    
    @Override
    public void onInitiate()
    {
        requestFocusInWindow();
    }
    
    @Override
    public void onExit()
    {    }
}
