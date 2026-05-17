package scenes.levels;

import images.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

import object.Entities.Player;
import object.statics.LobbyTreasureChest;
import object.statics.MapObj;
import object.statics.TreasureChest;
import scenes.templates.PlayableScreen;
import scenes.ui.GameFrame;
import systems.LevelManager;
import systems.Vector2;


public class TutorialScreen extends PlayableScreen {
    private JButton backBtn;

    public TutorialScreen(GameFrame gameFrame){
        super("tutorial", 67, gameFrame);
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());

    }

    @Override
    public void startGamePanel(){
        LevelManager.startNewRun();

        TreasureChest lobbyChest = new LobbyTreasureChest(
            Vector2.add(
                this.player.getPosition(),
                Vector2.multiply(Vector2.UP, 100)),
            player, 2, this);

            world.addObject(lobbyChest);

    }

    @Override
    public void stopGamePanel()
    {
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    @Override
    public MapObj setMap()
    {
        return new MapObj(ImageLibrary.get().tutorialMap, player.getPosition(), 1 , this);
    }

    @Override
    public Player setPlayer()
    {
        return new Player(new Vector2(getWidth() / 2, getHeight() /  2), 3, 10, 10, this, this.getGameFrame()); 
    }
}
