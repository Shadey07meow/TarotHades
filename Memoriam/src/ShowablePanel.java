import javax.swing.JPanel;

public class ShowablePanel extends JPanel{
    private String name;

    /// Showable Panel class
    /// Whenever we can switch to a new panel, please make it extend showable panel instead of JPanel
    /// This is so that we can Identify what are the names of the panels we create
    /// Use the super("panelName") format so that it is easier and more identifiable which panel we swtich to

    public ShowablePanel(String name)
    {
        this.name = name;
    }

    public String getShowablePanelName()
    {
        return this.name;
    }

    public void setShowablePanelName(String name)
    {
        this.name = name;
    }

    protected void onInitiate(){}
    protected void onExit(){}
}
