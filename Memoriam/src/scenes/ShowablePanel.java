package scenes;

import scenes.*;
import systems.*;
import collision.*;
import object.*;

import javax.swing.JPanel;




public abstract class ShowablePanel extends JPanel{
    protected  String name;

    /// Showable Panel class
    /// Whenever we can switch to a new panel, please make it extend showable panel instead of JPanel
    /// This is so that we can Identify what are the names of the panels we create
    /// Use the super("panelName") format so that it is easier and more identifiable which panel we swtich to
    
    public ShowablePanel(String name)
    {
        requestFocusInWindow();
        this.name = name;
    }

    abstract public String getShowablePanelName();

    abstract public void setShowablePanelName(String name);
    abstract public void onInitiate();
    abstract public void onExit();
}
