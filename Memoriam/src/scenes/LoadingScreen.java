package scenes;


import java.awt.Graphics;
import java.awt.Image;
import images.*;


public class LoadingScreen extends ShowablePanel {


    private Image loadingImage;


    public LoadingScreen(GameFrame gameFrame) {
        super("loading", gameFrame);
        loadingImage = ImageLibrary.get().loadingScreen;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (loadingImage != null) {
            g.drawImage(loadingImage, 0, 0, getWidth(), getHeight(), null);
        }
    }


    @Override
    public String getShowablePanelName() {
        return name;
    }


    @Override
    public void setShowablePanelName(String name) {
        this.name = name;
    }


    @Override
    public void onInitiate() {}


    @Override
    public void onExit() {}
}




